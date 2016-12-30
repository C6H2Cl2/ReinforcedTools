package c6h2cl2.ReinforcedTools.Item

import c6h2cl2.ReinforcedTools.EnumToolType
import c6h2cl2.ReinforcedTools.IReinforcedTools
import c6h2cl2.ReinforcedTools.ReinforcedToolsCore
import c6h2cl2.ReinforcedTools.ReinforcedToolsRegistry
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemTool
import net.minecraftforge.common.ForgeHooks

/**
 * @author C6H2Cl2
 */
class BattleAxe(material: ToolMaterial, name: String) : ItemTool(8.0f, material, null), IReinforcedTools {
    init {
        unlocalizedName = "${name.toLowerCase()}BattleAxe"
        setTextureName("${ReinforcedToolsCore.Domain}:${name.toLowerCase()}BattleAxe")
        creativeTab = ReinforcedToolsRegistry.tabReinforcedTools
        efficiencyOnProperMaterial *= 0.7f
        maxDamage = material.maxUses * 3
        for (toolClass in getToolClasses(ItemStack(this))) {
            setHarvestLevel(toolClass, material.harvestLevel)
        }
    }

    override fun getEnchanted(itemStack: ItemStack, enchantLevel: Int): ItemStack {
        val level1 = (enchantLevel.toFloat() / 5f * 1f).toInt()
        val level3 = (enchantLevel.toFloat() / 5f * 3f).toInt()
        val level7 = (enchantLevel.toFloat() / 5f * 7f).toInt()
        itemStack.addEnchantment(Enchantment.sharpness, level7)
        itemStack.addEnchantment(Enchantment.efficiency, level1)
        itemStack.addEnchantment(Enchantment.looting, level3)
        itemStack.addEnchantment(Enchantment.unbreaking, enchantLevel)
        return itemStack
    }

    override fun getToolType(): EnumToolType = EnumToolType.BATTLEAXE

    override fun getToolClasses(stack: ItemStack?): MutableSet<String> {
        return mutableSetOf("axe", "sword", "battleaxe", "Axe", "Sword", "BattleAxe", "battleAxe")
    }

    override fun hitEntity(itemStack: ItemStack, targetEntity: EntityLivingBase?, player: EntityLivingBase?): Boolean {
        itemStack.damageItem(1, player)
        return true
    }

    override fun func_150893_a(itemStack: ItemStack?, block: Block): Float {
        return if (itemStack != null && ForgeHooks.isToolEffective(itemStack, block, 0)) {
            efficiencyOnProperMaterial
        } else {
            0f
        }
    }

    override fun canHarvestBlock(block: Block, itemStack: ItemStack?): Boolean {
        return block.getHarvestLevel(0) <= toolMaterial.harvestLevel && getToolClasses(itemStack).contains(block.getHarvestTool(0))
    }
}