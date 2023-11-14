package net.mcreator.altarofsummoning.block.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.altarofsummoning.block.entity.AltarofsummoningTileEntity;

public class AltarofsummoningBlockModel extends AnimatedGeoModel<AltarofsummoningTileEntity> {
	@Override
	public ResourceLocation getAnimationResource(AltarofsummoningTileEntity animatable) {
		return new ResourceLocation("altar_of_summoning", "animations/cauldron_mob.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AltarofsummoningTileEntity animatable) {
		return new ResourceLocation("altar_of_summoning", "geo/cauldron_mob.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AltarofsummoningTileEntity entity) {
		return new ResourceLocation("altar_of_summoning", "textures/blocks/altarofsummoning.png");
	}
}
