package io;

import java.util.ArrayList;
import java.util.Scanner;

public class IOUtils {
    private static Scanner inp = new Scanner(System.in);

    public static void gradualPrintln(int timeMillis, String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(timeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("\n");
    }
    // Create a box using ASCII art and puts text inside
    public static void printInBox(int x, int y, String... lines) {
        // Limit line length to y
        ArrayList<String> actualLines = new ArrayList<String>();
        for (String line : lines) {
            while (line.length() > x) {
                actualLines.add(line.substring(0, x));
                line = line.substring(x);
            }
            actualLines.add(line);
        }

        // Top Row
        System.out.print("+");
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        // Content
        for (int i = 0; i < y; i++) {
            System.out.print("|");
            if (actualLines.size() > i)
            {
                System.out.print(actualLines.get(i));
                for (int j = 0; j < x - actualLines.get(i).length(); j++) {
                    System.out.print(" ");
                }
            } else {
                for (int j = 0; j < x; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
        
        // Bottom Row
        System.out.print("+");
        for (int i = 0; i < x; i++) {
            System.out.print("-");
        }
        System.out.println("+");

        if (actualLines.size() > y) {
            System.out.println(new ColoredText("ERROR: TOO MUCH TEXT IN BOX", ColoredText.RED));
        }
    }

    // Prompt the user for input and return what the user typed
    public static String getInput() {
        System.out.print("> ");
        String out = inp.nextLine();
        return out;
    }
}
