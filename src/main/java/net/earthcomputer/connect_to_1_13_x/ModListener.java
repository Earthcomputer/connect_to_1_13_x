package net.earthcomputer.connect_to_1_13_x;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class ModListener implements InitializationListener {
    private static final Logger LOGGER = LogManager.getLogger("Connect to 1.13.x");

    @Override
    public void onInitialization() {
        LOGGER.info("Loading 1.13.x connection mod");

        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.connect_to_1_13_x.json");
    }
}
