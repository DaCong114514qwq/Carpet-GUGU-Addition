package gugu.cong.carpet_gugu_addition.mixins.rule.remoteOpenInventory_new;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = {AbstractFurnaceBlockEntity.class,
        RandomizableContainerBlockEntity.class,
        BrewingStandBlockEntity.class
})
public class AbstractFurnaceBlockEntityMixin extends BlockEntity {
    public AbstractFurnaceBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}