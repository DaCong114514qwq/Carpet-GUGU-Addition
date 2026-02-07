package gugu.cong.carpet_gugu_addition.mixins.rule.autoMending;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static gugu.cong.carpet_gugu_addition.wheel.AutoMending.mending;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
    @Inject(at = @At("TAIL"),method = "tick")
    public void tick(CallbackInfo ci){
        if (GUGUSettings.autoMending) {
            if (((ServerPlayer) (Object) this).level().getGameTime() % 20 == 0) {
                mending((ServerPlayer) (Object) this);
            }
        }
    }
}
