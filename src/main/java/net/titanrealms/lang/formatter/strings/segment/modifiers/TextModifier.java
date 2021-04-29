package net.titanrealms.lang.formatter.strings.segment.modifiers;

import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

public interface TextModifier {

    TextComponent modify(@NotNull TextComponent text);
}
