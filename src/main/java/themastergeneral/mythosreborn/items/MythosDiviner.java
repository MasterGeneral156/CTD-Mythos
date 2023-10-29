package themastergeneral.mythosreborn.items;

import com.themastergeneral.ctdcore.item.CTDDurabilityItem;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import themastergeneral.mythosreborn.MythosReborn;
import themastergeneral.mythosreborn.blocks.BlockConstants;

public class MythosDiviner extends CTDDurabilityItem
{

	public MythosDiviner() 
	{
		super(new Properties().stacksTo(1).setNoRepair(), 128);
	}

	@Override
	public InteractionResult useOn(UseOnContext use) {
		Level level = use.getLevel();
		BlockPos pos = use.getClickedPos();
		BlockState state = level.getBlockState(pos);
		BlockState newstate = null;
		//@todo change to tag system please
		if (state.getBlock() == Blocks.REDSTONE_ORE)		//red
			newstate = BlockConstants.ore_crystal_fire.defaultBlockState();
		else if (state.getBlock() == Blocks.EMERALD_ORE)	//green
			newstate = BlockConstants.ore_crystal_memory.defaultBlockState();
		else if (state.getBlock() == Blocks.LAPIS_ORE)		//blue
			newstate = BlockConstants.ore_crystal_woe.defaultBlockState();
		else if (state.getBlock() == Blocks.COAL_ORE)		//gray
			newstate = BlockConstants.ore_crystal_grief.defaultBlockState();
		else if (state.getBlock() == Blocks.GOLD_ORE)		//yellow
			newstate = BlockConstants.ore_crystal_oath.defaultBlockState();
		
		//change to ores
		if (newstate != null)
		{
			level.setBlock(pos, newstate, 0);
			use.getItemInHand().hurt(1, RandomSource.createNewThreadLocalInstance(), null);
			MythosReborn.LOGGER.info(use.getPlayer().getStringUUID());
		}
		return InteractionResult.PASS;
	}
}
