package net.titanrealms.lang.formatter.strings.segment.modifiers.text;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ColorModifier implements TextModifier {
    private final TextColor color;

    public ColorModifier(@NotNull String hexCode) {
        Color color = Color.decode(hexCode);
        this.color = TextColor.color(color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public TextComponent modify(@NotNull TextComponent text) {
        return text.color(this.color);
    }

    @Override
    public String toString() {
        return "ColorModifier{" +
                "color=" + color +
                '}';
    }
}
