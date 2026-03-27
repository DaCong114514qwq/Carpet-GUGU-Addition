package gugu.cong.carpet_gugu_addition.mixins.rule.remoteOpenInventory_new;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket.playerlist;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {

    @Inject(at = @At("HEAD"), method = "disconnect")
    public void onDisconnect(CallbackInfo ci) {
        deletePlayerList();
    }

    @Inject(at = @At("HEAD"), method = "doCloseContainer")
    public void onHandledScreenClosed(CallbackInfo ci) {
        deletePlayerList();
    }

    @Unique
    private void deletePlayerList() {
        ServerPlayer self = (ServerPlayer) (Object) this;
        playerlist.remove(self);
        OpenInventoryPacket.tickMap.remove(self);
    }

    @WrapOperation(
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;stillValid(Lnet/minecraft/world/entity/player/Player;)Z"),
            method = "tick"
    )
    public boolean tick$stillValid(AbstractContainerMenu instance, Player player, Operation<Boolean> original) {
        if (player instanceof ServerPlayer serverPlayer) {
            if (playerlist.contains(serverPlayer)) {
                return true;
            }
        }
        return original.call(instance, player);
    }
}