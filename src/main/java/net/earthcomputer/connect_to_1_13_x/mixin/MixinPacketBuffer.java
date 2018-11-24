package net.earthcomputer.connect_to_1_13_x.mixin;

import io.netty.buffer.ByteBuf;
import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PacketBuffer.class)
public class MixinPacketBuffer {

    @Redirect(method = "writeItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;writeShort(I)Lio/netty/buffer/ByteBuf;", ordinal = 0))
    public ByteBuf writeItemStack_empty(PacketBuffer _this, int itemId) {
        if (Protocols.activeProtocol >= Protocols.PROTOCOL_1_13_2_pre1)
            _this.writeBoolean(false);
        else
            _this.writeShort(-1);
        return _this;
    }

    @Redirect(method = "writeItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;writeShort(I)Lio/netty/buffer/ByteBuf;", ordinal = 1))
    public ByteBuf writeItemStack_id(PacketBuffer _this, int itemId) {
        if (Protocols.activeProtocol >= Protocols.PROTOCOL_1_13_2_pre1) {
            _this.writeBoolean(true);
            _this.writeVarInt(itemId);
        } else {
            _this.writeShort(itemId);
        }
        return _this;
    }

    @Inject(method = "readItemStack", cancellable = true, at = @At("HEAD"))
    public void readItemStack_checkEmpty(CallbackInfoReturnable<ItemStack> ci) {
        if (Protocols.activeProtocol >= Protocols.PROTOCOL_1_13_2_pre1) {
            if (!((PacketBuffer) (Object) this).readBoolean()) {
                ci.setReturnValue(ItemStack.EMPTY);
            }
        }
    }

    @Redirect(method = "readItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketBuffer;readShort()S"))
    public short readItemStack_short2varInt(PacketBuffer _this) {
        if (Protocols.activeProtocol >= Protocols.PROTOCOL_1_13_2_pre1)
            return (short) _this.readVarInt(); // TODO: find a better way which doesn't cast to short?
        else
            return _this.readShort();
    }

}
