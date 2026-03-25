package gugu.cong.carpet_gugu_addition.mixins.rule;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import gugu.cong.carpet_gugu_addition.wheel.BlockHardnessModifiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(value = BlockBehaviour.BlockStateBase.class, priority = 999)
public abstract class BlockStateBaseMixin {
    // 抄Org的（）
    @Shadow
    public abstract Block getBlock();

    @ModifyReturnValue(method = "getDestroySpeed", at = @At("RETURN"))
    public float getBlockHardness(float hardness, @Local(argsOnly = true) BlockGetter world, @Local(argsOnly = true) BlockPos pos) {
        Optional<Float> optional = BlockHardnessModifiers.getHardness(this.getBlock(), world, pos);
        return optional.orElse(hardness);
    }
}
