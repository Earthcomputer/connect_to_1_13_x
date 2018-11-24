package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.ICPacketHandshake;
import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.CPacketHandshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CPacketHandshake.class)
public class MixinCPacketHandshake implements ICPacketHandshake {

    @Shadow private int protocolVersion;

    @Inject(method = "<init>(Ljava/lang/String;ILnet/minecraft/network/EnumConnectionState;)V", at = @At("TAIL"), remap = false)
    public void onConstruct(String ip, int port, EnumConnectionState requestedState, CallbackInfo ci) {
        Protocols.switchProtocol(Protocols.PROTOCOL_1_13);
    }

    @Override
    public void setProtocolVersion(int version) {
        this.protocolVersion = version;
    }
}
