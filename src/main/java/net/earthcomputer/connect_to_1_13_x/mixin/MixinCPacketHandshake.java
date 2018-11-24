package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.CPacketHandshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CPacketHandshake.class)
public class MixinCPacketHandshake {

    @Shadow private int protocolVersion;

    @Inject(method = "<init>(Ljava/lang/String;ILnet/minecraft/network/EnumConnectionState;)V", at = @At("TAIL"))
    public void onConstruct(String ip, int port, EnumConnectionState requestedState, CallbackInfo ci) {
        ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
        int newProtocol = Protocols.PROTOCOL_1_13;
        if (serverData != null && Protocols.isProtocolSupported(serverData.version))
            newProtocol = serverData.version;

        protocolVersion = newProtocol;
        Protocols.switchProtocol(newProtocol);
    }
}
