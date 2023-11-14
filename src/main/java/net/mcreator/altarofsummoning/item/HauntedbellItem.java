
package net.mcreator.altarofsummoning.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class HauntedbellItem extends Item {
	public HauntedbellItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).stacksTo(1).rarity(Rarity.COMMON));
	}
}
