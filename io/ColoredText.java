package io;

import java.util.HashMap;
import java.util.Set;

public class ColoredText {
    // Strings that represent different colors as put forward by ANSI CSI SGR parameters
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    private HashMap<Integer, String> colors;
    private String str;

    // equivalent to doing a + b if the two were Strings
    public static ColoredText concat(ColoredText a, ColoredText b) {
        ColoredText out = new ColoredText("", a.colors.get(0));
        // Add a to the output
        for (int i = 0; i < a.str.length(); i++) {
            if (a.colors.keySet().contains(i)) {
                out.colors.put(i, a.colors.get(i));
            }
            out.str += a.str.charAt(i);
        }

        // Add b to the output
        for (int i = 0; i < b.str.length(); i++) {
            if (b.colors.keySet().contains(i)) {
                out.colors.put(i + a.str.length(), b.colors.get(i));
            }
            out.str += b.str.charAt(i);
        }

        return out;
    }

    // Useful because ColoredText is being accessed anyway
    public static ColoredTextBuilder builder() {
        return new ColoredTextBuilder();
    }
    
    // Initialize default text
    public ColoredText(String str) {
        this.str = str;
        colors = new HashMap<Integer, String>();
        colors.put(0, RESET);
    }

    // Initialize single-color text
    public ColoredText(String str, String color) {
        this.str = str;
        colors = new HashMap<Integer, String>();
        colors.put(0, color);
    }

    // Return the text without the color
    public String getUncoloredString() {
        return str;
    }

    // Return the text including color symbols
    public String getRawString() {
        Set<Integer> st = colors.keySet();
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            // Add appropriate color character when there is one available
            if (st.contains(i))
                output += colors.get(i);
            output += str.charAt(i);
        }
        return output;
    }

    // toString function that works for printing
    public String toString() {
        return getRawString();
    }

    // Get the length of the string without color symbols
    public int length() {
        return str.length();
    }
}
