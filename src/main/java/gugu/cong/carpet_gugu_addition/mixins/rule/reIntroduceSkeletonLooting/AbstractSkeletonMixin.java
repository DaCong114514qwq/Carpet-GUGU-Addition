package gugu.cong.carpet_gugu_addition.mixins.rule.reIntroduceSkeletonLooting;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.tags.TagKey;
//? if <=1.21.10 {
/*import net.minecraft.world.entity.monster.AbstractSkeleton;*/
//?} else {
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
//?}
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin {
    //? if >=1.21.4 {
    @Inject(method = "getPreferredWeaponType", at = @At("HEAD"), cancellable = true)
    private void injectGetPreferredWeaponType(CallbackInfoReturnable<TagKey<Item>> cir) {
        if (GUGUSettings.reIntroduceSkeletonLooting) {
            cir.setReturnValue(null);
        }
    }
    //?}
}