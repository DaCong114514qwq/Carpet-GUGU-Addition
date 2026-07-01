package gugu.cong.carpet_gugu_addition.mixins.rule.softBee;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.world.entity.animal.bee.Bee;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public abstract class BeeMixin {
    @Inject(method = "getPersistentAngerEndTime", at = @At("HEAD"), cancellable = true)
    private void onGetPersistentAngerEndTime(CallbackInfoReturnable<Long> cir) {
        if (GUGUSettings.softBee) {
            cir.setReturnValue(0L);
        }
    }
}