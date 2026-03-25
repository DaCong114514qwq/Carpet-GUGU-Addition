package gugu.cong.carpet_gugu_addition.mixins.rule.updateSuppressionCrashFix;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gugu.cong.carpet_gugu_addition.GUGUSettings;
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