package ru.job4j.tracker;

public class FindByIdAction implements UserAction {
    @Override
    public String name() {
        return "=== Find Item By Id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Enter item's id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("Id not found");
            return false;
        }
        System.out.println(item);
        return true;
    }
}
