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
