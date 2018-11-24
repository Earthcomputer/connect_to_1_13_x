package net.earthcomputer.connect_to_1_13_x;

import javax.annotation.Nonnull;
import java.util.TreeSet;

public class IDShift implements Comparable<IDShift> {
    private int protocol;
    private int affectedId;
    private int shiftAmount;

    public IDShift(int protocol, int affectedId, int shiftAmount) {
        this.protocol = protocol;
        this.affectedId = affectedId;
        this.shiftAmount = shiftAmount;
    }

    @Override
    public int compareTo(@Nonnull IDShift other) {
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
        if (!(other instanceof IDShift)) return false;
        IDShift that = (IDShift) other;
        return protocol == that.protocol && affectedId == that.affectedId && shiftAmount == that.shiftAmount;
    }

    public static int forwardShift(int id, TreeSet<IDShift> shifts) {
        for (IDShift shift : shifts) {
            if (shift.protocol > Protocols.activeProtocol)
                break;
            if (id >= shift.affectedId)
                id += shift.shiftAmount;
        }
        return id;
    }

    public static int backwardsShift(int id, TreeSet<IDShift> shifts) {
        for (IDShift shift : shifts) {
            if (shift.protocol > Protocols.activeProtocol)
                break;
            if (id >= shift.affectedId)
                id -= shift.shiftAmount;
        }
        return id;
    }
}
