package gugu.cong.carpet_gugu_addition.wheel;

import java.util.ArrayList;
import java.util.HashMap;

import gugu.cong.carpet_gugu_addition.inventory.MyPacket;
import gugu.cong.carpet_gugu_addition.utils.remoteOpenInventoryUtils;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
//? if <=1.21.5 {
import java.util.Comparator;
//?}

import static gugu.cong.carpet_gugu_addition.GUGUSettings.remoteOpenInventory_new;
import static gugu.cong.carpet_gugu_addition.utils.remoteOpenInventoryUtils.loadPrinter;
import static gugu.cong.carpet_gugu_addition.utils.remoteOpenInventoryUtils.loadCarpetWuhu;

public class OpenInventoryPacket {
    //? if >1.21.4 {
    private static final TicketType OPEN_TICKET = TicketType.UNKNOWN;
    //?} else {
    /*private static final TicketType<ChunkPos> OPEN_TICKET = TicketType.create("openInv", Comparator.comparingLong(ChunkPos::toLong), 2);*/
    //?}

    public static HashMap<ServerPlayer, TickList> tickMap = new HashMap<>();

    private static final Identifier OPEN_INVENTORY = remoteOpenInventoryUtils.of("remoteinventory", "open_inventory");
    private static final Identifier OPEN_RETURN = remoteOpenInventoryUtils.of("openreturn", "open_return");
    private static final Identifier HELLO_REMOTE_INTERACTIONS = remoteOpenInventoryUtils.of("hello", "hello_remote_interactions");
    public static ArrayList<ServerPlayer> playerlist = new ArrayList<>();

    public static class OpenPackage implements CustomPacketPayload {
        public static final Type<OpenPackage> OPEN_INVENTORY_ID = new Type<>(OPEN_INVENTORY);
        public static final StreamCodec<RegistryFriendlyByteBuf,OpenPackage> CODEC = new StreamCodec<>() {

            @Override
            public void encode(RegistryFriendlyByteBuf buf, OpenPackage value) {
                buf.writeResourceKey(value.world);
                buf.writeBlockPos(value.pos);
            }
            @Override
            public OpenPackage decode(RegistryFriendlyByteBuf buf) {
                OpenPackage openPackage = new OpenPackage();
                openPackage.world = buf.readResourceKey(Registries.DIMENSION);
                openPackage.pos = buf.readBlockPos();
                return openPackage;
            }
        };
        ResourceKey<Level> world = null;
        BlockPos pos = null;
        public OpenPackage() {
        }
        @Override
        public Type<? extends CustomPacketPayload> type() {
            return OPEN_INVENTORY_ID;
        }
    }

    public static class HelloPackage implements CustomPacketPayload{
        public static final Type<HelloPackage> HELLO_REMOTE_INTERACTIONS_ID = new Type<>(HELLO_REMOTE_INTERACTIONS);
        public static final StreamCodec<RegistryFriendlyByteBuf,HelloPackage> CODEC = new StreamCodec<>() {
            @Override
            public void encode(RegistryFriendlyByteBuf buf, HelloPackage value) {
            }
            @Override
            public HelloPackage decode(RegistryFriendlyByteBuf buf) {
                return new HelloPackage();
            }
        };
        @Override
        public Type<? extends CustomPacketPayload> type() {
            return HELLO_REMOTE_INTERACTIONS_ID;
        }
    }

    public static class ReturnPackage implements CustomPacketPayload{
        BlockState state = null;
        boolean isOpen = false;
        public static final Type<ReturnPackage> OPEN_RETURN_ID = new Type<>(OPEN_RETURN);
        public static final StreamCodec<RegistryFriendlyByteBuf,ReturnPackage> CODEC = new StreamCodec<>() {
            @Override
            public void encode(RegistryFriendlyByteBuf buf, ReturnPackage value) {
                buf.writeInt(Block.getId(value.state));
                buf.writeBoolean(value.isOpen);
            }
            @Override
            public ReturnPackage decode(RegistryFriendlyByteBuf buf) {
                ReturnPackage returnPackage = new ReturnPackage();
                returnPackage.state = Block.stateById(buf.readInt());
                returnPackage.isOpen = buf.readBoolean();
                return returnPackage;
            }
        };

        @Override
        public Type<? extends CustomPacketPayload> type() {
            return OPEN_RETURN_ID;
        }
    }

    public static void init(){
        if (loadPrinter || loadCarpetWuhu) return;
        PayloadTypeRegistry.playC2S().register(OpenPackage.OPEN_INVENTORY_ID, OpenPackage.CODEC);
        PayloadTypeRegistry.playC2S().register(ReturnPackage.OPEN_RETURN_ID, ReturnPackage.CODEC);
        PayloadTypeRegistry.playC2S().register(HelloPackage.HELLO_REMOTE_INTERACTIONS_ID, HelloPackage.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenPackage.OPEN_INVENTORY_ID, OpenPackage.CODEC);
        PayloadTypeRegistry.playS2C().register(ReturnPackage.OPEN_RETURN_ID, ReturnPackage.CODEC);
        PayloadTypeRegistry.playS2C().register(HelloPackage.HELLO_REMOTE_INTERACTIONS_ID, HelloPackage.CODEC);
    }

    public static void registerReceivePacket() {
        if (loadPrinter || loadCarpetWuhu) return;
        ServerPlayNetworking.registerGlobalReceiver(OpenPackage.OPEN_INVENTORY_ID, (payload, context) -> {
            if (payload instanceof OpenPackage packetByteBuf) {
                MinecraftServer server =  context.player().level().getServer();
                server.execute(() -> openInv(server, context.player(), packetByteBuf.pos, packetByteBuf.world));
            }
        });
    }

    public static void helloRemote(ServerPlayer player) {
        if (!remoteOpenInventory_new || loadPrinter || loadCarpetWuhu) return;
        ServerPlayNetworking.send(player,new HelloPackage());
    }

    public static void openInv(MinecraftServer server, ServerPlayer player, BlockPos pos, ResourceKey<Level> key) {
        if(!remoteOpenInventory_new || loadPrinter || loadCarpetWuhu) return;
        ServerLevel world = server.getLevel(key);
        if (world == null) return;
        BlockState blockState = world.getBlockState(pos);
        if (blockState == null) {
            //? if >1.21.4 {
            world.getChunkSource().addTicketWithRadius(OPEN_TICKET, new ChunkPos(pos), 2);
            //?} else {
            /*world.getChunkSource().addRegionTicket(OPEN_TICKET, new ChunkPos(pos), 2, new ChunkPos(pos));*/
            //?}
        }
        playerlist.add(player);
        if (blockState == null) return;
        tickMap.put(player, new TickList(blockState.getBlock(), world, pos, blockState));
        if (!canOpenInv(world,pos)) {
            System.out.println("openFail  " + pos);
            openReturn(player, blockState, false);
            return;
        }

        InteractionResult r = blockState.useWithoutItem(world, player, new BlockHitResult(Vec3.atCenterOf(pos), Direction.UP, pos, false));

        if (r != null && (!r.equals(InteractionResult.CONSUME)
                //? if >1.21.1 {
                && !r.equals(InteractionResult.SUCCESS)
                //?}
        )) {
            System.out.println("openFail  " + pos);
            openReturn(player, blockState, false);
            return;
        }
        openReturn(player, blockState, true);
    }

    public static void openReturn(ServerPlayer player, BlockState state, boolean open) {
        if (loadPrinter || loadCarpetWuhu) return;
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        MyPacket.encode(new MyPacket(state, open), buf);
        ReturnPackage returnPackage = new ReturnPackage();
        returnPackage.state = state;
        returnPackage.isOpen = open;
        ServerPlayNetworking.send(player,returnPackage);
    }

    public static boolean isContainer(BlockEntity blockEntity){
        return blockEntity instanceof Container;
    }

    public static boolean canOpenInv(Level world, BlockPos pos){
        if (world != null) {
            BlockState blockState = world.getBlockState(pos);
            BlockEntity blockEntity = world.getBlockEntity(pos);
            boolean isInventory = isContainer(blockEntity);
            try {
                if ((isInventory && blockState.getMenuProvider(world,pos) == null) ||
                        (blockEntity instanceof ShulkerBoxBlockEntity entity &&
                                //? if >1.21.3 {
                                !world.noCollision(Shulker.getProgressDeltaAabb(1.0F, blockState.getValue(BlockStateProperties.FACING), 0.0F, 0.5F, pos.getBottomCenter()).move(pos).deflate(1.0E-6)) &&
                                //?} else {
                                /*!world.noCollision(Shulker.getProgressDeltaAabb(1.0F, blockState.getValue(BlockStateProperties.FACING), 0.0F, 0.5F).move(pos).deflate(1.0E-6)) &&*/
                                //?}
                                entity.getAnimationStatus() == ShulkerBoxBlockEntity.AnimationStatus.CLOSED)) {
                    return false;
                }else if(!isInventory){
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
}