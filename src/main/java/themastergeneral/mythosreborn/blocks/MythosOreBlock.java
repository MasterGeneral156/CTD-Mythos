package themastergeneral.mythosreborn.blocks;

import net.minecraft.world.level.block.Block;

public class MythosOreBlock extends MythosBlockBase 
{

	public MythosOreBlock() 
	{
		super(Block.Properties.of().requiresCorrectToolForDrops().strength(1.25F).destroyTime(20F));
	}
}
