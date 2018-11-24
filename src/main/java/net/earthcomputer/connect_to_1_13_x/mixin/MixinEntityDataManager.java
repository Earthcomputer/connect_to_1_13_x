package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.datasync.EntityDataManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(EntityDataManager.class)
public class MixinEntityDataManager {

    @Shadow @Final private Entity entity;

    @Inject(method = "setEntryValues", at = @At("HEAD"))
    public void removeArrowUUID(List<EntityDataManager.DataEntry<?>> entries, CallbackInfo ci) {
        if (Protocols.activeProtocol >= Protocols.PROTOCOL_18w30a) {
            if (entity instanceof EntityArrow) {
                Iterator<EntityDataManager.DataEntry<?>> itr = entries.iterator();
                while (itr.hasNext()) {
                    EntityDataManager.DataEntry<?> entry = itr.next();
                    int id = entry.getKey().getId();
                    if (id == 7)
                        itr.remove();
                    else if (id == 8)
                        entry.getKey().id = 7;
                }
            }
        }
    }

}
