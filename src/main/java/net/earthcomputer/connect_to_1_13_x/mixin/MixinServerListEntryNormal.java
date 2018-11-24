package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.multiplayer.ServerData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerListEntryNormal.class)
public class MixinServerListEntryNormal {

    @Shadow @Final private ServerData server;

    @ModifyConstant(method = "func_194999_a", constant = @Constant(intValue = Protocols.PROTOCOL_1_13), require = 2)
    public int onDraw_modifyProtocolVersion(int oldProtocol) {
        return Protocols.isProtocolSupported(server.version) ? server.version : oldProtocol;
    }

}
