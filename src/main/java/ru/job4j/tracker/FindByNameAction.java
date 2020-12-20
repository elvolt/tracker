package ru.job4j.tracker;

public class FindByNameAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Items By Name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askString("Enter item's name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Items not found");
        }
        return true;
    }
}
