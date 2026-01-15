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

package gugu.cong.carpet_gugu_addition;

import carpet.api.settings.Rule;

import static carpet.api.settings.RuleCategory.SURVIVAL;

public class GUGUSettings {
    public static final String GUGU = "GUGU";

    //#if MC >= 12104
    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean reIntroduceSkeletonLooting = false;
    //#endif

    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean softAnvil = false;

    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean softTrialSpawner = false;

    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean softVault = false;

    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean softReinforcedDeepslate = false;

    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean updateSuppressionCrashFix = false;
}
