package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        Item[] rsl = new Item[size];
        int rslSize = 0;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getName().equals(key)) {
               rsl[rslSize++] = item;
            }
        }
        return Arrays.copyOf(rsl, rslSize);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        item.setId(items[index].getId());
        items[index] = item;
        return true;
    }

    public boolean delete(int id) {
        int index = indexOf(id);
        if (index == -1) {
            return false;
        }
        items[index] = null;
        System.arraycopy(items, index + 1, items, index, size - index);
        items[size - 1] = null;
        size--;
        return true;
    }
}