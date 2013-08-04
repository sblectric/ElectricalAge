package mods.eln.ore;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import mods.eln.CommonProxy;
import mods.eln.Eln;
import mods.eln.generic.GenericItemBlockUsingDamageDescriptor;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class OreDescriptor extends GenericItemBlockUsingDamageDescriptor implements IWorldGenerator {

	int metadata;
	
	int spawnRate,spawnSizeMax,spawnSizeMin,spawnHeightMin,spawnHeightMax;
	public OreDescriptor(
			String name,int metadata,
			int spawnRate,int spawnSizeMin,int spawnSizeMax,int spawnHeightMin,int spawnHeightMax
			) {
		super(name);
		this.metadata = metadata;
		this.spawnHeightMax = spawnHeightMax;
		this.spawnHeightMin = spawnHeightMin;
		this.spawnRate = spawnRate;
		this.spawnSizeMin = spawnSizeMin;
		this.spawnSizeMax = spawnSizeMax;
	}
	public Icon getBlockIconId(int side,int damage)
	{
		return getIcon();
	}
	

	public ArrayList<ItemStack> getBlockDropped(int fortune)
	{
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(Eln.oreItem,1,metadata));
		return list;

	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.isSurfaceWorld()){
			generateSurface(random,chunkX*16,chunkZ*16,world); //This makes it gen overworld (the *16 is important)
		}

	}

	public void generateSurface(Random random, int x, int z, World w)
	{
		
		//for(int i = 0;i<4;i++){ //This goes through the ore metadata
			for(int ii=0;ii<spawnRate;ii++){ //This makes it gen multiple times in each chunk
				int posX = x + random.nextInt(16); //X coordinate to gen at
				int posY = spawnHeightMin + random.nextInt(spawnHeightMax-spawnHeightMin); //Y coordinate less than 40 to gen at
				int posZ = z + random.nextInt(16); //Z coordinate to gen at
				int size = spawnSizeMin + random.nextInt(spawnSizeMax - spawnSizeMin);
				new WorldGenMinable(Eln.oreBlock.blockID,metadata,size,Block.stone.blockID).generate(w, random, posX, posY, posZ); //The gen call
			}
		//}
		//new WorldGenTrees(par1, par2, par3, par4, par5)
	}
}