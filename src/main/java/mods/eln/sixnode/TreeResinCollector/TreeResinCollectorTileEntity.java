package mods.eln.sixnode.TreeResinCollector;

import mods.eln.Eln;
import mods.eln.misc.Direction;
import mods.eln.misc.Utils;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TreeResinCollectorTileEntity extends TileEntity{

	float occupancy = 0f;
	final float occupancyMax = 2f;
	final float occupancyProductPerSecondPerTreeBlock = 1f / 5f / 5f;
	final float timeRandom = 0.2f;

    float timeTarget = (float) (Math.random() * timeRandom);
    float timeCounter = 0;

	boolean onBlockActivated() {
		if (worldObj.isRemote) return true;
		while(occupancy >= 1f) {
			Utils.dropItem(Eln.treeResin.newItemStack(1), getPos(), worldObj);
			occupancy -= 1f;
		}
		return true;
	}
	
	@Override
	public boolean canUpdate() {
		return true;
	}
    
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) return;
		timeCounter += 1f / 20f;
		if (timeCounter > timeTarget) {
			int[] posWood = new int[3];
			int[] posCollector = new int[3];
			Direction woodDirection = Direction.fromIntMinecraftSide(getBlockMetadata()).getInverse();
			posWood[0] = getPos().getX(); 
            posWood[1] = getPos().getY(); 
            posWood[2] = getPos().getZ(); 
			posCollector[0] = getPos().getX();
            posCollector[1] = getPos().getY();
            posCollector[2] = getPos().getZ(); 
			woodDirection.applyTo(posWood, 1);
			
			int yStart, yEnd;
			
			while (worldObj.getBlockState(new BlockPos(posWood[0], posWood[1] - 1, posWood[2])).getBlock() == Blocks.log) {
				posWood[1]--;
			}
			yStart = posWood[1];
			
			posWood[1] = getPos().getY();
			timeCounter-= timeTarget;
			while(worldObj.getBlockState(new BlockPos(posWood[0],posWood[1]+1,posWood[2])).getBlock() == Blocks.log)
			{
				posWood[1]++;
			}
			yEnd = posWood[1];
			
			int collectiorCount = 0;
			posCollector[1] = yStart;
			for (posCollector[1] = yStart; posCollector[1] <= yEnd; posCollector[1]++) {
			//////	if (worldObj.getBlockId(posCollector[0], posCollector[1] + 1, posCollector[2]) == Eln.treeResinCollectorBlock.blockID)
				{
			//////		collectiorCount++;
				}
			}
			
			occupancy += occupancyProductPerSecondPerTreeBlock * (yEnd - yStart + 1) * timeTarget / collectiorCount;
				
			if (occupancy > occupancyMax) occupancy = occupancyMax;
			
			Utils.println("Occupancy : " + occupancy);
			timeTarget = (float) (Math.random() * timeRandom);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setFloat("occupancy", occupancy);
	//	woodDirection.writeToNBT(nbt, "woodDirection");
	}
    
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		occupancy = nbt.getFloat("occupancy");
	//	woodDirection = Direction.readFromNBT(nbt, "woodDirection");
	}
}
