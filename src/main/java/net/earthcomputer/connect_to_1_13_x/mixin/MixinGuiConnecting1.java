package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.ICPacketHandshake;
import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(targets = "net.minecraft.client.gui.GuiConnecting$1")
public class MixinGuiConnecting1 {

    @ModifyArg(method = "run", remap = false,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal = 0))
    public Packet<?> modifyHandshakePacket(Packet<?> packet) {
        ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();

        if (serverData != null && Protocols.isProtocolSupported(serverData.version)) {
            ((ICPacketHandshake) packet).setProtocolVersion(serverData.version);
            Protocols.switchProtocol(serverData.version);
        }

        return packet;
    }

}
