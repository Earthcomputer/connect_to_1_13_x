package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
@SuppressWarnings("unchecked")
public class MixinItem {

    static {
        Item.REGISTRY = Protocols.createItemRegistry();
    }

}
