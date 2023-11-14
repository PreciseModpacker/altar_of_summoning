package net.mcreator.altarofsummoning.block.listener;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.altarofsummoning.init.AltarOfSummoningModBlockEntities;
import net.mcreator.altarofsummoning.block.renderer.AltarofsummoningTileRenderer;
import net.mcreator.altarofsummoning.AltarOfSummoningMod;

@Mod.EventBusSubscriber(modid = AltarOfSummoningMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(AltarOfSummoningModBlockEntities.ALTAROFSUMMONING.get(), AltarofsummoningTileRenderer::new);
	}
}
