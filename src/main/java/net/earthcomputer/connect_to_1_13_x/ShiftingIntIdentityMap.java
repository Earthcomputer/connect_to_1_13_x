package net.earthcomputer.connect_to_1_13_x;

import net.minecraft.util.ObjectIntIdentityMap;

import java.util.TreeSet;

public class ShiftingIntIdentityMap<T> extends ObjectIntIdentityMap<T> {

    private TreeSet<IDShift> shifts = new TreeSet<>();

    public void addShift(int protocol, int affectedId, int shiftAmount) {
        shifts.add(new IDShift(protocol, affectedId, shiftAmount));
    }

    @Override
    public int get(T value) {
        int id = super.get(value);
        return IDShift.forwardShift(id, shifts);
    }

    @Override
    public T getByValue(int id) {
        id = IDShift.backwardsShift(id, shifts);
        return super.getByValue(id);
    }

}
