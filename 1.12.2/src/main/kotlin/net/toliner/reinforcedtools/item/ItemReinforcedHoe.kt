package net.toliner.reinforcedtools.item

import net.minecraft.init.Enchantments
import net.minecraft.item.Item
import net.minecraft.item.ItemHoe
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.toliner.reinforcedtools.ReinforcedTools

/**
 * @author kojin15.
 */
class ItemReinforcedHoe(material: Item.ToolMaterial) : ItemHoe(material), IReinforcedTool {
    init {
        unlocalizedName = "reinforced_${material.name.toLowerCase()}_hoe"
        registryName = ResourceLocation(ReinforcedTools.MOD_ID, unlocalizedName)
        creativeTab = ReinforcedTools.tabReinforcedTools
    }

    override fun getEnchantedItem(level: Int): ItemStack {
        return ItemStack(this).also {
            it.addEnchantment(Enchantments.EFFICIENCY, level)
            it.addEnchantment(Enchantments.FORTUNE, level)
            it.addEnchantment(Enchantments.UNBREAKING, level)
        }
    }
}