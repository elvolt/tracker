package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    @Override
    public String name() {
        return "=== Edit Item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter item's id: ");
        String name = input.askString("Enter name of new Item: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Successfully");
        } else {
            System.out.println("Error! Program Can't Edit Item");
        }
        return true;
    }
}
