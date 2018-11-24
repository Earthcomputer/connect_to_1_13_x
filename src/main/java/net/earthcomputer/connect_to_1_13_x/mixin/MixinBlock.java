package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public class MixinBlock {

    static {
        Block.BLOCK_STATE_IDS = Protocols.createBlockStateIdMap();
    }

}
