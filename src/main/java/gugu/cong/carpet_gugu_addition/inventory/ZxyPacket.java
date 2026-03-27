package gugu.cong.carpet_gugu_addition.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record ZxyPacket(BlockState blockState, boolean isOpen) {
    // 用于序列化数据以发送给客户端的方法
    public static void encode(ZxyPacket msg, FriendlyByteBuf buffer) {
        buffer.writeVarInt(Block.getId(msg.blockState));
        buffer.writeBoolean(msg.isOpen);
    }

    // 用于接收客户端数据的方法
    public static ZxyPacket decode(FriendlyByteBuf buffer) {
        return new ZxyPacket(Block.stateById(buffer.readVarInt()), buffer.readBoolean());
    }
}
