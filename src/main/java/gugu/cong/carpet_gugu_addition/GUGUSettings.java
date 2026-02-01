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

    @Rule(categories = {GUGU, SURVIVAL})
    public static boolean autoMending = false;
}
