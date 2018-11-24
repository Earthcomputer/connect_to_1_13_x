package net.earthcomputer.connect_to_1_13_x;

import net.minecraft.util.registry.RegistryNamespaced;

import java.util.TreeSet;

public class ShiftedRegistry extends RegistryNamespaced {

    private TreeSet<IDShift> shifts = new TreeSet<>();

    public void addShift(int protocol, int affectedId, int shiftAmount) {
        shifts.add(new IDShift(protocol, affectedId, shiftAmount));
    }

    @SuppressWarnings("unchecked")
    public int getOriginalId(Object obj) {
        return super.getIDForObject(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getIDForObject(Object obj) {
        int id = super.getIDForObject(obj);
        return IDShift.forwardShift(id, shifts);
    }

    @Override
    public Object getObjectById(int id) {
        id = IDShift.backwardsShift(id, shifts);
        return super.getObjectById(id);
    }
}
