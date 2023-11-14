
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.altarofsummoning.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.altarofsummoning.block.AltarofsummoningBlock;
import net.mcreator.altarofsummoning.AltarOfSummoningMod;

public class AltarOfSummoningModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, AltarOfSummoningMod.MODID);
	public static final RegistryObject<Block> ALTAROFSUMMONING = REGISTRY.register("altarofsummoning", () -> new AltarofsummoningBlock());
}
