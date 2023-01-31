package UI;

import Configs.Config;
import Core.MVP.Presenter;
import Core.Models.Toy;

import java.util.Scanner;

public class App {
    public static void buttonClick() {
        Presenter presenter = new Presenter(new ConsoleView(), Config.pathDb);
        presenter.loadFromFile();

        String id;
        String command;

        while (true) {

            command = prompt("""

                    1 - Add toy to drawing
                    2 - Delete toy from drawing
                    3 - Make drawing (show results)
                    4 - Show toys for drawing
                    5 - Clear all entries
                    6 - Save all entries to file
                    7 - Load all entries from file
                    8 - Exit
                    Make your choice:\s""");
            if (command.equals("8")) {
                return;
            }
            try {
                switch (command) {
                    case "1" -> presenter.putForDrawing();
                    case "2" -> presenter.deleteFromDrawing();
                    case "3" ->
                            presenter.getDrawing();
                    case "4" -> presenter.showAll();
                    case "5" -> presenter.clearAll();
                    case "6" -> presenter.saveToFile();
                    case "7" -> presenter.loadFromFile();
                    default -> System.out.println("\n Command not found!");
                }
            } catch (Exception e) {
                System.out.println("Error. " + e.getMessage());
            }
        }
    }

    private static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private static Toy toyCreate() {
        int id = Integer.parseInt(prompt("Toy id: "));
        String name = prompt("Toy name: ");
        String weight = prompt("Toy weight: ");
        return (new Toy(id, name, Integer.parseInt(weight)));
    }
}

