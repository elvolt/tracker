package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    private final Output out;

    public FindByIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Find Item By ID ===");
        int id = input.askInt("Enter item's id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            out.println("Id not found");
            return true;
        }
        out.println(item);
        return true;
    }
}
