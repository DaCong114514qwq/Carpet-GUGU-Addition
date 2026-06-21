package gugu.cong.carpet_gugu_addition.mixins.rule.accurateBlockPlacement;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import gugu.cong.carpet_gugu_addition.GUGUSettings;
import net.minecraft.world.phys.Vec3;

@Restriction(
        conflict = @Condition("carpet-extra")
)
@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin
{
    @Redirect(method = "handleUseItemOn",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/phys/Vec3;subtract(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/Vec3;"),
            require = 0)
    private Vec3 carpetextra_removeHitPosCheck(Vec3 hitVec, Vec3 blockCenter)
    {
        if (GUGUSettings.accurateBlockPlacement)
        {
            return Vec3.ZERO;
        }

        return hitVec.subtract(blockCenter);
    }
}
