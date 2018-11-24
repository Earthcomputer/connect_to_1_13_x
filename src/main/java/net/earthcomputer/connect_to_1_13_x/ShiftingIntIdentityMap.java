package net.earthcomputer.connect_to_1_13_x;

import net.earthcomputer.connect_to_1_13_x.Protocols;
import net.minecraft.util.ObjectIntIdentityMap;

import javax.annotation.Nonnull;
import java.util.TreeSet;

public class ShiftingIntIdentityMap<T> extends ObjectIntIdentityMap<T> {

    private TreeSet<Shift> shifts = new TreeSet<>();

    public void addShift(int protocol, int affectedId, int shiftAmount) {
        shifts.add(new Shift(protocol, affectedId, shiftAmount));
    }

    @Override
    public int get(T value) {
        int id = super.get(value);
        for (Shift shift : shifts) {
            if (shift.protocol > Protocols.activeProtocol)
                break;
            if (id >= shift.affectedId)
                id += shift.shiftAmount;
        }
        return id;
    }

    @Override
    public T getByValue(int id) {
        for (Shift shift : shifts) {
            if (shift.protocol > Protocols.activeProtocol)
                break;
            if (id >= shift.affectedId)
                id -= shift.shiftAmount;
        }
        return super.getByValue(id);
    }

    private static final class Shift implements Comparable<Shift> {
        private int protocol;
        private int affectedId;
        private int shiftAmount;

        public Shift(int protocol, int affectedId, int shiftAmount) {
            this.protocol = protocol;
            this.affectedId = affectedId;
            this.shiftAmount = shiftAmount;
        }

        @Override
        public int compareTo(@Nonnull Shift other) {
            if (protocol != other.protocol) {
                return Integer.compare(protocol, other.protocol);
            }
            if (affectedId != other.affectedId) {
                return Integer.compare(other.affectedId, affectedId); // reversed
            }
            return Integer.compare(shiftAmount, other.shiftAmount);
        }

        @Override
        public int hashCode() {
            int h = protocol;
            h = 31 * h + affectedId;
            h = 31 * h + shiftAmount;
            return h;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) return true;
            if (!(other instanceof Shift)) return false;
            Shift that = (Shift) other;
            return protocol == that.protocol && affectedId == that.affectedId && shiftAmount == that.shiftAmount;
        }
    }

}
