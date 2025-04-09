package mastergeneral156.ctdmythos.blocks;

import com.themastergeneral.ctdcore.block.CTDBlock;

public class MythosBlockBase extends CTDBlock {

	public MythosBlockBase(Properties materialIn) {
		super(materialIn);
	}

	public MythosBlockBase(Properties materialIn, float destroyTime) {
		super(materialIn.destroyTime(destroyTime));
	}
	
	public MythosBlockBase(Properties materialIn, float destroyTime, float strength) {
		super(materialIn.strength(strength).destroyTime(destroyTime));
	}
}
