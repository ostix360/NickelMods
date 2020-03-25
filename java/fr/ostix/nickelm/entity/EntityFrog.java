package fr.ostix.nickelm.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityFrog extends EntityAnimal {


    public EntityFrog(World worldIn) {
        super(worldIn);

        this.setSize(0.9f,0.9f);
        this.tasks.addTask(0,new EntityAILookIdle(this));
        this.tasks.addTask(1,new EntityAITempt(this,0.7D, Items.WATER_BUCKET,false));
    }

    @Override
    protected void applyEntityAttributes() {
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3524D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(3);

        super.applyEntityAttributes();
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return this;
    }
}
