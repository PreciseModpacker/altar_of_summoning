
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.altarofsummoning.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

import net.mcreator.altarofsummoning.item.HauntedbellItem;
import net.mcreator.altarofsummoning.item.FossilbaitItem;
import net.mcreator.altarofsummoning.item.DuskkeyItem;
import net.mcreator.altarofsummoning.item.DevilsanteItem;
import net.mcreator.altarofsummoning.block.display.AltarofsummoningDisplayItem;
import net.mcreator.altarofsummoning.AltarOfSummoningMod;

public class AltarOfSummoningModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, AltarOfSummoningMod.MODID);
	public static final RegistryObject<Item> HAUNTEDBELL = REGISTRY.register("hauntedbell", () -> new HauntedbellItem());
	public static final RegistryObject<Item> ALTAROFSUMMONING = REGISTRY.register(AltarOfSummoningModBlocks.ALTAROFSUMMONING.getId().getPath(),
			() -> new AltarofsummoningDisplayItem(AltarOfSummoningModBlocks.ALTAROFSUMMONING.get(), new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
	public static final RegistryObject<Item> DEVILSANTE = REGISTRY.register("devilsante", () -> new DevilsanteItem());
	public static final RegistryObject<Item> FOSSILBAIT = REGISTRY.register("fossilbait", () -> new FossilbaitItem());
	public static final RegistryObject<Item> DUSKKEY = REGISTRY.register("duskkey", () -> new DuskkeyItem());
}
