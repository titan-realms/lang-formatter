package net.titanrealms.lang.formatter.strings.segment.segments;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.titanrealms.lang.formatter.strings.segment.ModifiableLangSegment;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public class VariableSegment extends ModifiableLangSegment {
    private final String variable;

    public VariableSegment(@NotNull String variable, @NotNull Collection<TextModifier> modifiers) {
        super(modifiers);
        this.variable = variable.substring(1);
    }

    @Override
    public void apply(TextComponent.@NotNull Builder builder, @Nullable Map<String, Object> placeholders) {
        if (placeholders == null) {
            return;
        }
        TextComponent text = Component.text(placeholders.getOrDefault(this.variable, "?").toString());
        builder.append(this.applyModifiers(text));
    }
}
