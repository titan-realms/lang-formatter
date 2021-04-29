package net.titanrealms.lang.formatter.strings.segment;

import net.kyori.adventure.text.TextComponent;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;

import java.util.Collection;

public abstract class ModifiableLangSegment implements LangSegment {
    private final Collection<TextModifier> modifiers;

    public ModifiableLangSegment(Collection<TextModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public TextComponent applyModifiers(TextComponent text) {
        for (TextModifier modifier : this.modifiers) {
            text = modifier.modify(text);
        }
        return text;
    }
}
