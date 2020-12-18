package ru.job4j.tracker;

public class StartUI {
    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        String name = input.askString("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAllItems(Input input, Tracker tracker) {
        System.out.println("=== All Items ===");
        Item[] items = tracker.findAll();
        if (items.length == 0) {
            System.out.println("No Items");
            return;
        }
        for (Item item : items) {
            System.out.println(item.getId() + " " + item.getName());
        }
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("=== Edit Item ===");
        int id = input.askInt("Enter item's id: ");
        String name = input.askString("Enter name of new Item: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            System.out.println("Successfully");
        } else {
            System.out.println("Error! Program Can't Edit Item");
        }
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("=== Delete item ===");
        int id = input.askInt("Enter item's id: ");
        if (tracker.delete(id)) {
            System.out.println("Successfully");
        } else {
            System.out.println("Error. Incorrect ID");
        }
    }

    public static void findItemById(Input input, Tracker tracker) {
        System.out.println("=== Find Item By Id ===");
        int id = input.askInt("Enter item's id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("Id not found");
            return;
        }
        System.out.println(item);
    }

    public static void findItemsByName(Input input, Tracker tracker) {
        System.out.println("=== Find Items By Name");
        String name = input.askString("Enter item's name: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            for (Item item : items) {
                System.out.println(item);
            }
        } else {
            System.out.println("Items not found");
        }
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = input.askInt("Select: ");
            if (select == 0) {
                StartUI.createItem(input, tracker);
            } else if (select == 1) {
                StartUI.showAllItems(input, tracker);
            } else if (select == 2) {
                StartUI.replaceItem(input, tracker);
            } else if (select == 3) {
                StartUI.deleteItem(input, tracker);
            } else if (select == 4) {
                StartUI.findItemById(input, tracker);
            } else if (select == 5) {
                StartUI.findItemsByName(input, tracker);
            } else if (select == 6) {
                run = false;
            } else {
                System.out.println("Incorrect Select");
            }
            System.out.println(System.lineSeparator());
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
