package fr.ostix.nickelm.proxy;

import fr.ostix.nickelm.init.ModEntity;

public class ClientProxy extends CommonProxy
{

    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void postInit() {
        super.postInit();

        ModEntity.registerEntityReners();
    }
}
