package fr.ostix.nickelm.gen;

import fr.ostix.nickelm.init.ModBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGeneration implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimensionType())
        {
            case NETHER:
                generatorNether(world, random, chunkX * 16, chunkZ * 16);
            case OVERWORLD:
                generatorOveworld(world, random, chunkX * 16, chunkZ * 16);
            case THE_END:
                generatorEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generatorEnd(World world, Random random, int x, int z)
    {
    }

    private void generatorOveworld(World world, Random random, int x, int z)
    {
        generateOre(ModBlock.nickel_ore.getDefaultState(), world, random, x, z, 5, 14, 3, 6, BlockMatcher.forBlock(Blocks.STONE));
        generateOre(ModBlock.plati_ore.getDefaultState(), world, random, x, z, 1, 19, 6, 15, BlockMatcher.forBlock(Blocks.STONE));
        generateOre(ModBlock.ruby_ore.getDefaultState(), world, random, x, z, 1, 45, 8, 35, BlockMatcher.forBlock(Blocks.STONE));

    }

    private void generatorNether(World world, Random random, int x, int z)
    {
        generateOre(ModBlock.malachite_ore.getDefaultState(),world,random,x,z,1,19,6,85,BlockMatcher.forBlock(Blocks.NETHERRACK));

    }

    private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int maxVeinSize, int chance, BlockMatcher blockchanging)
    {
        int deltaY = maxY - minY;
        int VeinSize = random.nextInt(maxVeinSize + 1);

        for(int i = 0; i < chance; i++)
        {
            BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));

            WorldGenMinable generator = new WorldGenMinable(ore, VeinSize, blockchanging);

            generator.generate(world, random, pos);
        }
    }
}
























