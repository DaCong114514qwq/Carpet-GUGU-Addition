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

package gugu.cong.carpet_gugu_addition.mixins.rule.updateSuppressionCrashFix;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.ChatFormatting;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Shadow @Final private static Logger LOGGER;
    @Shadow private PlayerList playerList;


    @WrapOperation(method = "tickChildren", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;tick(Ljava/util/function/BooleanSupplier;)V"))
    private void addTryCatchLevel(ServerLevel level, BooleanSupplier shouldKeepTicking, Operation<Void> original) {
        if (GUGUSettings.updateSuppressionCrashFix) {
            try {
                original.call(level, shouldKeepTicking);
            } catch (Throwable e) {
                LOGGER.error("Caused a crash in level tick", e);
            }
        } else {
            original.call(level, shouldKeepTicking);
        }
    }

    @WrapMethod(method = "tickChildren")
    private void addTryCatchAll(BooleanSupplier booleanSupplier, Operation<Void> original) {
        if (GUGUSettings.updateSuppressionCrashFix) {
            try {
                original.call(booleanSupplier);
            } catch (Throwable e) {
                LOGGER.error("Caused a crash in server tick", e);
            }
        } else {
            original.call(booleanSupplier);
        }
    }
}