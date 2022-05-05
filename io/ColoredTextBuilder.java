package io;

import java.util.ArrayList;

public class ColoredTextBuilder {
    ArrayList<ColoredText> parts;
    
    public ColoredTextBuilder() {
        parts = new ArrayList<ColoredText>();
    }

    public ColoredTextBuilder red(String part) {
        parts.add(new ColoredText(part, ColoredText.RED));
        return this;
    }

    public ColoredTextBuilder green(String part) {
        parts.add(new ColoredText(part, ColoredText.GREEN));
        return this;
    }

    public ColoredTextBuilder yellow(String part) {
        parts.add(new ColoredText(part, ColoredText.YELLOW));
        return this;
    }

    public ColoredTextBuilder purple(String part) {
        parts.add(new ColoredText(part, ColoredText.PURPLE));
        return this;
    }

    public ColoredTextBuilder blue(String part) {
        parts.add(new ColoredText(part, ColoredText.BLUE));
        return this;
    }

    public ColoredTextBuilder cyan(String part) {
        parts.add(new ColoredText(part, ColoredText.CYAN));
        return this;
    }

    public ColoredTextBuilder white(String part) {
        parts.add(new ColoredText(part, ColoredText.WHITE));
        return this;
    }

    public ColoredTextBuilder then(String part) {
        parts.add(new ColoredText(part, ColoredText.RESET));
        return this;
    }

    public ColoredTextBuilder then(String part, String color) {
        parts.add(new ColoredText(part, color));
        return this;
    }

    public ColoredTextBuilder then(ColoredText part) {
        parts.add(part);
        return this;
    }

    public ColoredTextBuilder then(ColoredText part, String color) {
        parts.add(new ColoredText(part.getUncoloredString(), color));
        return this;
    }

    public ColoredText build() {
        ColoredText out = new ColoredText("");
        for (int i = 0; i < parts.size(); i++) {
            out = ColoredText.concat(out, parts.get(i));
        }
        return out;
    }
}
