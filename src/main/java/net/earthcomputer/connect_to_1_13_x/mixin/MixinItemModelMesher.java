package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.ShiftedRegistry;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemModelMesher.class)
public class MixinItemModelMesher {

    @Redirect(method = "func_199310_c", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;getIdFromItem(Lnet/minecraft/item/Item;)I"))
    private static int getOriginalItemId(Item item) {
        return ((ShiftedRegistry) Item.REGISTRY).getOriginalId(item);
    }

}
