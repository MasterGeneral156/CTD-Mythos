package mastergeneral156.ctdmythos.blocks.blockentity;

import mastergeneral156.ctdmythos.blocks.BlockConstants;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "ctdmythos");

    public static final RegistryObject<BlockEntityType<MythosPylonBlockEntity>> mythos_pylon = TILES.register("mythos_pylon", () -> BlockEntityType.Builder.of(MythosPylonBlockEntity::new, BlockConstants.mythos_pylon).build(null));
    public static final RegistryObject<BlockEntityType<MythosWeatherPylonBlockEntity>> mythos_weather_pylon = TILES.register("mythos_weather_pylon", () -> BlockEntityType.Builder.of(MythosWeatherPylonBlockEntity::new, BlockConstants.mythos_weather_pylon).build(null));

    public static final RegistryObject<BlockEntityType<MythosAltarBlockEntity>> mythos_altar = TILES.register("mythos_altar", () -> BlockEntityType.Builder.of(MythosAltarBlockEntity::new, BlockConstants.mythos_altar).build(null));
    public static final RegistryObject<BlockEntityType<MythosPedestalBlockEntity>> mythos_pedestal = TILES.register("mythos_pedestal", () -> BlockEntityType.Builder.of(MythosPedestalBlockEntity::new, BlockConstants.mythos_pedestal).build(null));
}
