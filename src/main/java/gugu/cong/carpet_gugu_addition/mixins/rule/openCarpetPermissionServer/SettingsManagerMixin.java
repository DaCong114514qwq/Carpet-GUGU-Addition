package gugu.cong.carpet_gugu_addition.mixins.rule.openCarpetPermissionServer;

import carpet.api.settings.SettingsManager;
import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin {
    //#if MC <= 12111
    //$$ @Inject(method = "lambda$registerCommand$11", at = @At("HEAD"), cancellable = true)
    //#else
    @Inject(method = "lambda$registerCommand$1", at = @At("HEAD"), cancellable = true)
    //#endif
    private void carpet(CommandSourceStack player, CallbackInfoReturnable<Boolean> cir) {
        if(GUGUSettings.openCarpetPermissionServer) {
            cir.setReturnValue(true);
        }
    }
}
