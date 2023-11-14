package net.mcreator.altarofsummoning.block.renderer;

import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.altarofsummoning.block.model.AltarofsummoningBlockModel;
import net.mcreator.altarofsummoning.block.entity.AltarofsummoningTileEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class AltarofsummoningTileRenderer extends GeoBlockRenderer<AltarofsummoningTileEntity> {
	public AltarofsummoningTileRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
		super(rendererDispatcherIn, new AltarofsummoningBlockModel());
	}

	@Override
	public RenderType getRenderType(AltarofsummoningTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, ResourceLocation texture) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
