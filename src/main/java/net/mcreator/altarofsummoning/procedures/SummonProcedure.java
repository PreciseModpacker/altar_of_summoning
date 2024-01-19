package net.mcreator.altarofsummoning.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.altarofsummoning.init.AltarOfSummoningModItems;
import net.mcreator.altarofsummoning.AltarOfSummoningMod;

import java.util.Comparator;

public class SummonProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double y_block = 0;
		double z_block = 0;
		double profondita = 0;
		double lato_quadrato = 0;
		double start_x = 0;
		double lato_x = 0;
		double lato_y = 0;
		double lato_z = 0;
		double start_z = 0;
		double start_y = 0;
		double larghezza = 0;
		double x_block = 0;
		double altezza = 0;
		boolean found = false;
		boolean spawning = false;
		if (true) {
			found = false;
			lato_quadrato = 20;
			larghezza = 0;
			profondita = 0;
			altezza = 0;
			if (lato_quadrato > 0) {
				start_x = Math.floor(lato_quadrato / 2) * (-1);
				start_z = Math.floor(lato_quadrato / 2) * (-1);
				start_y = Math.floor(lato_quadrato / 2) * (-1);
				lato_x = lato_quadrato;
				lato_z = lato_quadrato;
				lato_y = lato_quadrato;
			} else {
				start_x = Math.floor(larghezza / 2) * (-1);
				start_z = Math.floor(profondita / 2) * (-1);
				start_y = Math.floor(larghezza / 2) * (-1);
				lato_x = larghezza;
				lato_z = profondita;
				lato_y = altezza;
			}
			x_block = start_x;
			z_block = start_z;
			y_block = start_y;
			for (int index0 = 0; index0 < (int) lato_y; index0++) {
				for (int index1 = 0; index1 < (int) lato_x; index1++) {
					for (int index2 = 0; index2 < (int) lato_z; index2++) {
						if (((Entity) world.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(new Vec3((x + x_block), (y + y_block), (z + z_block)), 1, 1, 1), e -> true).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf((x + x_block), (y + y_block), (z + z_block))).findFirst().orElse(null)) instanceof LivingEntity _livEnt ? _livEnt.hasEffect(MobEffects.UNLUCK) : false) {
							found = true;
						}
						z_block = z_block + 1;
					}
					x_block = x_block + 1;
					z_block = start_z;
				}
				y_block = y_block + 1;
				x_block = start_x;
			}
		}
		if (!found) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.HAUNTEDBELL.get() && !(new Object() {
				public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos(x, y, z), "spawning"))) {
				{
					int _value = 1;
					BlockPos _pos = new BlockPos(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				AltarOfSummoningMod.queueServerWork(60, () -> {
					{
						int _value = 5;
						BlockPos _pos = new BlockPos(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
					AltarOfSummoningMod.queueServerWork(20, () -> {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					});
					AltarOfSummoningMod.queueServerWork(60, () -> {
						if (!world.isClientSide()) {
							BlockPos _bp = new BlockPos(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putBoolean("spawning", false);
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:bellringer ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						{
							int _value = 0;
							BlockPos _pos = new BlockPos(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					});
				});
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(AltarOfSummoningModItems.HAUNTEDBELL.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				if (!world.isClientSide()) {
					BlockPos _bp = new BlockPos(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("spawning", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.DEVILSANTE.get() && !(new Object() {
				public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos(x, y, z), "spawning"))) {
				{
					int _value = 3;
					BlockPos _pos = new BlockPos(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				AltarOfSummoningMod.queueServerWork(60, () -> {
					{
						int _value = 5;
						BlockPos _pos = new BlockPos(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
					AltarOfSummoningMod.queueServerWork(20, () -> {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					});
					AltarOfSummoningMod.queueServerWork(60, () -> {
						if (!world.isClientSide()) {
							BlockPos _bp = new BlockPos(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putBoolean("spawning", false);
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:dame_fortuna ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						{
							int _value = 0;
							BlockPos _pos = new BlockPos(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					});
				});
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(AltarOfSummoningModItems.DEVILSANTE.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				if (!world.isClientSide()) {
					BlockPos _bp = new BlockPos(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("spawning", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.DUSKKEY.get() && !(new Object() {
				public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos(x, y, z), "spawning"))) {
				{
					int _value = 4;
					BlockPos _pos = new BlockPos(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				AltarOfSummoningMod.queueServerWork(60, () -> {
					{
						int _value = 5;
						BlockPos _pos = new BlockPos(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
					AltarOfSummoningMod.queueServerWork(20, () -> {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					});
					AltarOfSummoningMod.queueServerWork(60, () -> {
						if (!world.isClientSide()) {
							BlockPos _bp = new BlockPos(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putBoolean("spawning", false);
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:rosalyne ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), (z + 1)), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:rose_spirit ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), (z - 1)), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:rose_spirit ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((x - 1), (y + 1), z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:rose_spirit ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(
									new CommandSourceStack(CommandSource.NULL, new Vec3((x + 1), (y + 1), z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:rose_spirit ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						{
							int _value = 0;
							BlockPos _pos = new BlockPos(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					});
				});
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(AltarOfSummoningModItems.DUSKKEY.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				if (!world.isClientSide()) {
					BlockPos _bp = new BlockPos(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("spawning", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.FOSSILBAIT.get() && !(new Object() {
				public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getPersistentData().getBoolean(tag);
					return false;
				}
			}.getValue(world, new BlockPos(x, y, z), "spawning"))) {
				{
					int _value = 2;
					BlockPos _pos = new BlockPos(x, y, z);
					BlockState _bs = world.getBlockState(_pos);
					if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
						world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambient.underwater.exit")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
				AltarOfSummoningMod.queueServerWork(60, () -> {
					{
						int _value = 5;
						BlockPos _pos = new BlockPos(x, y, z);
						BlockState _bs = world.getBlockState(_pos);
						if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
							world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
					}
					AltarOfSummoningMod.queueServerWork(20, () -> {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.end_portal.spawn")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					});
					AltarOfSummoningMod.queueServerWork(60, () -> {
						if (!world.isClientSide()) {
							BlockPos _bp = new BlockPos(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getPersistentData().putBoolean("spawning", false);
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, (y + 1), z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"summon meetyourfight:swampjaw ~ ~ ~ {PersistenceRequired:1,ActiveEffects:[{Id:27,Amplifier:0,Duration:999999,ShowParticles:0b}]}");
						{
							int _value = 0;
							BlockPos _pos = new BlockPos(x, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("animation") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
								world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
						}
					});
				});
				if (entity instanceof Player _player) {
					ItemStack _stktoremove = new ItemStack(AltarOfSummoningModItems.FOSSILBAIT.get());
					_player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
				}
				if (!world.isClientSide()) {
					BlockPos _bp = new BlockPos(x, y, z);
					BlockEntity _blockEntity = world.getBlockEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_blockEntity != null)
						_blockEntity.getPersistentData().putBoolean("spawning", true);
					if (world instanceof Level _level)
						_level.sendBlockUpdated(_bp, _bs, _bs, 3);
				}
			} else if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.FOSSILBAIT.get())
					|| !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.DUSKKEY.get())
					|| !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.DEVILSANTE.get())
					|| !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AltarOfSummoningModItems.HAUNTEDBELL.get())) {
				if (entity instanceof Player _player_) {
					if (!_player_.getMainHandItem().isEmpty() && _player_.getMainHandItem().getCount() > 0) {
						_player_.drop(new ItemStack(_player_.getMainHandItem().getItem(), 1), true);
						_player_.getMainHandItem().shrink(1);
						_player_.getInventory().setChanged();
					}
				}
			}
		}
	}
}
