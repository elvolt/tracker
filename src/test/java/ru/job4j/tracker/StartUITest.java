package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {
    @Test
    public void whenAddItem() {
        Output output = new StubOutput();
        String[] answers = {"0", "Item name", "1"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output),
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(output),
                new ExitAction()
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        String[] answers = {"0", String.valueOf(item.getId()), "1"};
        Input input = new StubInput(answers);
        UserAction[] actions = {
                new DeleteAction(output),
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output output = new StubOutput();
        String[] answers = {"0"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllItems() {
        Output output = new StubOutput();
        String[] answers = {"0", "1"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        UserAction[] actions = {
                new ShowAllAction(output),
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Show all items" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== All Items ===" + System.lineSeparator()
                        + item1.getId() + " " + item1.getName() + System.lineSeparator()
                        + item2.getId() + " " + item2.getName() + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Show all items" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindItemById() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        tracker.add(item1);
        tracker.add(item2);
        String[] answers = {"0", String.valueOf(item2.getId()), "1"};
        Input input = new StubInput(answers);
        UserAction[] actions = {
                new FindByIdAction(output),
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find item by id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find Item By ID ===" + System.lineSeparator()
                        + item2.toString() + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Find item by id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindItemsByName() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        Item item3 = new Item("Item 1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        String[] answers = {"0", "Item 1", "1"};
        Input input = new StubInput(answers);
        UserAction[] actions = {
                new FindByNameAction(output),
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find items by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find Items By Name ===" + System.lineSeparator()
                        + item1.toString() + System.lineSeparator()
                        + item3.toString() + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Find items by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new StubInput(
                new String[] {"1", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new ExitAction()
        };
        new StartUI(output).init(input, tracker, actions);
        assertThat(output.toString(), is(
                String.format(
                        "Menu.%n"
                                + "0. Exit%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n"
                                + "0. Exit%n"
                )
        ));
    }
}