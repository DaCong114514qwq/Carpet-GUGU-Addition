package gugu.cong.carpet_gugu_addition.wheel;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Optional;

import static gugu.cong.carpet_gugu_addition.GUGUSettings.autoMending;

public class AutoMending {
    public static void mending(ServerPlayer player){
        if (!autoMending) return;
        int exp = (int) Math.floor(player.experienceProgress * (float)player.getXpNeededForNextLevel());
        int amount = exp == 0 ? player.experienceLevel > 0 ? 1 : 0 : exp;
        if(amount == 0) return;
        Optional<EnchantedItemInUse> optional = EnchantmentHelper.getRandomItemWith(EnchantmentEffectComponents.REPAIR_WITH_XP, player, ItemStack::isDamaged);
        if (optional.isPresent()) {
            ItemStack itemStack = optional.get().itemStack();
            int i = EnchantmentHelper.modifyDurabilityToRepairFromXp(player.level(), itemStack, amount);
            int j = Math.min(i, itemStack.getDamageValue());
            itemStack.setDamageValue(itemStack.getDamageValue() - j);
            if (j > 0) {
                player.giveExperiencePoints(-j);
            }
        }
    }
}
