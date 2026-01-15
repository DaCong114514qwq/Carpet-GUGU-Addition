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

package gugu.cong.carpet_gugu_addition.wheel;

import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Optional;


public class BlockHardnessModifiers {
// 抄org的（）
    private static final List<Block> ANVIL = List.of(
            Blocks.ANVIL,
            Blocks.CHIPPED_ANVIL,
            Blocks.DAMAGED_ANVIL
    );
    public static Optional<Float> getHardness(Block block, BlockGetter world, BlockPos pos) {
        if(GUGUSettings.softAnvil) {
            if(ANVIL.contains(block)) {
                return Optional.of(Blocks.DIRT.defaultDestroyTime());
            }
        }
        if(GUGUSettings.softTrialSpawner && (block == Blocks.TRIAL_SPAWNER)) {
            return  Optional.of(Blocks.DIRT.defaultDestroyTime());
        }
        if(GUGUSettings.softVault && (block == Blocks.VAULT)) {
            return  Optional.of(Blocks.STONE.defaultDestroyTime());
        }
        if(GUGUSettings.softReinforcedDeepslate && (block == Blocks.REINFORCED_DEEPSLATE)) {
            return  Optional.of(Blocks.DIRT.defaultDestroyTime());
        }
        return Optional.empty();
    }
}
