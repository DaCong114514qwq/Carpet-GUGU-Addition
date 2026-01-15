/*
 * This file is part of the Carpet GUGU Addition project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Fallen_Breath and contributors
 *
 * Carpet GUGU Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Carpet GUGU Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Carpet GUGU Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

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
