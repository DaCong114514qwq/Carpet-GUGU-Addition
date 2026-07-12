package gugu.cong.carpet_gugu_addition.mixins.rule.nbUndeadMob;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public class MobMixin {
    @Inject(method = "isSunBurnTick", at = @At("HEAD"), cancellable = true)
    private void onSunBurn(CallbackInfoReturnable<Boolean> cir) {
        if (GUGUSettings.nbUndeadMob) {
            cir.setReturnValue(false);
        }
    }
}