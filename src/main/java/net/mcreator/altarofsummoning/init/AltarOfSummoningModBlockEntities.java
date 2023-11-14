
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.altarofsummoning.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.altarofsummoning.block.entity.AltarofsummoningTileEntity;
import net.mcreator.altarofsummoning.AltarOfSummoningMod;

public class AltarOfSummoningModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, AltarOfSummoningMod.MODID);
	public static final RegistryObject<BlockEntityType<AltarofsummoningTileEntity>> ALTAROFSUMMONING = REGISTRY.register("altarofsummoning",
			() -> BlockEntityType.Builder.of(AltarofsummoningTileEntity::new, AltarOfSummoningModBlocks.ALTAROFSUMMONING.get()).build(null));

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
