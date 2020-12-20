package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    private final Output out;

    public DeleteAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Delete item ===");
        int id = input.askInt("Enter item's id: ");
        if (tracker.delete(id)) {
            System.out.println("Successfully");
        } else {
            System.out.println("Error. Incorrect ID");
        }
        return true;
    }
}
