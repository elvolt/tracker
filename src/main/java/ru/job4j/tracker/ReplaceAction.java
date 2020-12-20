package ru.job4j.tracker;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Replace item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Edit Item ===");
        int id = input.askInt("Enter item's id: ");
        String name = input.askString("Enter name of new Item: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            out.println("Successfully");
        } else {
            out.println("Error! Program Can't Edit Item");
        }
        return true;
    }
}
