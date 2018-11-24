package net.earthcomputer.connect_to_1_13_x.mixin;

import net.earthcomputer.connect_to_1_13_x.BookFix;
import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketEditBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CPacketEditBook.class)
public class MixinCPacketEditBook {

    @Inject(method = "writePacketData", at = @At("TAIL"))
    public void maybeSendHand(PacketBuffer buf, CallbackInfo ci) {
        if (Protocols.activeProtocol >= Protocols.PROTOCOL_18w30a)
            buf.writeEnumValue(BookFix.bookHand);
    }

}
