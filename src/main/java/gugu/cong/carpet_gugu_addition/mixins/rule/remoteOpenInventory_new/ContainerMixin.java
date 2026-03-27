package gugu.cong.carpet_gugu_addition.mixins.rule.remoteOpenInventory_new;


import gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static gugu.cong.carpet_gugu_addition.GUGUSettings.remoteOpenInventory_new;

@Mixin(Container.class)
public interface ContainerMixin {
    @Inject(
            at = @At("HEAD"),
            method = "stillValidBlockEntity(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/player/Player;)Z",
            cancellable = true
    )
    private static void canPlayeruse(BlockEntity blockEntity,
                                     Player player,
                                     CallbackInfoReturnable<Boolean> cir) {
        if(remoteOpenInventory_new) {
            if (player instanceof ServerPlayer) {
                for (ServerPlayer serverPlayer : OpenInventoryPacket.playerlist) {
                    if (serverPlayer.equals(player)) {
                        cir.setReturnValue(true);
                    }
                }
            }
        }
    }
}
