package mastergeneral156.ctdmythos.blocks.blockentity;

import mastergeneral156.ctdmythos.items.mythos.MythosItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class MythosWeatherPylonBlockEntity extends BlockEntity {

    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public MythosWeatherPylonBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.mythos_weather_pylon.get(), pos, state);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
