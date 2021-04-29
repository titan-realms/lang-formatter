package net.titanrealms.lang.formatter.strings.segment.segments;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.titanrealms.lang.formatter.strings.segment.ModifiableLangSegment;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public class TextSegment extends ModifiableLangSegment {
    private final TextComponent text;

    public TextSegment(@NotNull String text, @NotNull Collection<TextModifier> modifiers) {
        super(modifiers);
        this.text = this.applyModifiers(Component.text(text));
    }

    @Override
    public void apply(@NotNull TextComponent.Builder builder, @Nullable Map<String, Object> placeholders) {
        builder.append(this.text);
    }
}
