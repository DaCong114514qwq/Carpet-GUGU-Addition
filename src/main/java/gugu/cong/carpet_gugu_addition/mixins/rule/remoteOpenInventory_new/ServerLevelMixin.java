package gugu.cong.carpet_gugu_addition.mixins.rule.remoteOpenInventory_new;

import gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket;
import gugu.cong.carpet_gugu_addition.wheel.TickList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket.playerlist;
import static gugu.cong.carpet_gugu_addition.wheel.OpenInventoryPacket.tickMap;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(at = @At("HEAD"),method = "tick")
    public void tick(CallbackInfo ci){
        for (ServerPlayer s : playerlist) {
            TickList list = tickMap.get(s);
            if (!list.world.areEntitiesLoaded(ChunkPos.asLong(list.pos))) {
                list.world.shouldTickBlocksAt(list.pos);
            }
            BlockState state2 = list.world.getBlockState(list.pos);
            if(state2.isAir()){
                OpenInventoryPacket.openReturn(s,state2,false);
            }
        }
    }
}
