package gugu.cong.carpet_gugu_addition.mixins.rule.remoteOpenInventory_new;

import gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ChestMenu.class)
public class ChestMenuMixin {
    @Inject(at = @At("HEAD"), method = "removed",cancellable = true,locals = LocalCapture.CAPTURE_FAILHARD)
    public void onClosed(Player player, CallbackInfo ci) {
        if(!(player instanceof ServerPlayer)) return;
        for (ServerPlayer player1 : OpenInventoryPacket.playerlist) {
            if (player.equals(player1)) {
                ci.cancel();
            }
        }
    }
}
