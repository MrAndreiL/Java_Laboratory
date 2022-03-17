package com.company;

public interface Storage {
    int getStorageCapacity();

    default int toMB(int storageCapacity) {
        return 1024 * storageCapacity;
    }

    default int toKB(int storageCapacity) {
        return 1024 * toMB(storageCapacity);
    }

    default int toB(int storageCapacity) {
        return 1024 * toKB(storageCapacity);
    }
}
