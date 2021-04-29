package net.titanrealms.lang.formatter.strings.segment.modifiers.text;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import org.jetbrains.annotations.NotNull;

public class DecorationModifier implements TextModifier {
    private final TextDecoration decoration;

    public DecorationModifier(@NotNull String decoration) {
        this.decoration = TextDecoration.valueOf(decoration.substring(1).toUpperCase());
    }

    @Override
    public TextComponent modify(@NotNull TextComponent text) {
        return text.decoration(this.decoration, true);
    }
}
