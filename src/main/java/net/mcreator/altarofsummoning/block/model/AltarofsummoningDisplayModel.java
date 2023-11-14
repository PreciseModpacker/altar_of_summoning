package net.mcreator.altarofsummoning.block.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.altarofsummoning.block.display.AltarofsummoningDisplayItem;

public class AltarofsummoningDisplayModel extends AnimatedGeoModel<AltarofsummoningDisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(AltarofsummoningDisplayItem animatable) {
		return new ResourceLocation("altar_of_summoning", "animations/cauldron_mob.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AltarofsummoningDisplayItem animatable) {
		return new ResourceLocation("altar_of_summoning", "geo/cauldron_mob.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AltarofsummoningDisplayItem entity) {
		return new ResourceLocation("altar_of_summoning", "textures/blocks/altarofsummoning.png");
	}
}
