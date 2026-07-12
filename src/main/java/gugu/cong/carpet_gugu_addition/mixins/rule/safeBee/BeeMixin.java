package gugu.cong.carpet_gugu_addition.mixins.rule.safeBee;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.world.entity.animal.bee.Bee;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public abstract class BeeMixin {
    //#if MC >= 12111
    @Inject(method = "getPersistentAngerEndTime", at = @At("HEAD"), cancellable = true)
    //#else
    //$$ @Inject(method = "getRemainingPersistentAngerTime", at = @At("HEAD"), cancellable = true)
    //#endif
    private void onGetPersistentAngerEndTime(CallbackInfoReturnable<Long> cir) {
        if (GUGUSettings.safeBee) {
            cir.setReturnValue(0L);
        }
    }
}