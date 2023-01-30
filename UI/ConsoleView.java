package UI;

import java.util.List;
import java.util.Scanner;

import Configs.Config;
import Core.MVP.View;
import Core.Models.Toy;

public class ConsoleView implements View {
    Scanner in;

    public ConsoleView() {
        in = new Scanner(System.in);
    }

    @Override
    public int getToyId() {
        System.out.print("Toy id: ");
        return Integer.parseInt(in.nextLine());
    }

    @Override
    public void setToyId(int value) {
        System.out.printf("Toy id: %s\n", value);
    }

    @Override
    public String getToyNaming() {
        System.out.print("Toy name: ");
        return in.nextLine();
    }

    @Override
    public void setToyNaming(String value) {
        System.out.printf("Toy name: %s\n", value);
    }

    @Override
    public int getToyWeight() {
        System.out.print("Toy weight: ");
        return Integer.parseInt(in.nextLine());
    }

    @Override
    public void setToyWeight(int value) {
        System.out.printf("Toy weight: %s\n", value);
    }

    @Override
    public void showAll(List<Toy> toys) {
        System.out.println("\nAll toys for drawing:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }

    }

    @Override
    public boolean clearAllDecision() {
        boolean f = false;
        System.out.print("\nAre you sure to clean all entries (y/n): ");
        if (in.nextLine().equalsIgnoreCase("Y")){
            f = true;
        }
        return f;
    }

    @Override
    public void savedAll() {
        System.out.println("\nAll entries are saved to file: " + Config.pathDb);
    }

    @Override
    public void savedItem() {
        System.out.println("\nSuccessfully added in current service");
    }

    @Override
    public void saveError() {
        System.out.println("\nAdding in current service failed");
    }

    @Override
    public void emptyListMessage() {
        System.out.println("Toys list is empty!");
    }

}
