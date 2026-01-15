/*
 * This file is part of the Carpet GUGU Addition project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
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

package gugu.cong.carpet_gugu_addition.mixins.rule.reIntroduceSkeletonLooting;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.tags.TagKey;
//#if MC <= 12110
//$$ import net.minecraft.world.entity.monster.AbstractSkeleton;
//#endif
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSkeleton.class)
public abstract class AbstractSkeletonMixin {
    //#if MC >= 12104
    @Inject(method = "getPreferredWeaponType", at = @At("HEAD"), cancellable = true)
    private void injectGetPreferredWeaponType(CallbackInfoReturnable<TagKey<Item>> cir) {
        if (GUGUSettings.reIntroduceSkeletonLooting) {
            cir.setReturnValue(null);
        }
    }
    //#endif
}
