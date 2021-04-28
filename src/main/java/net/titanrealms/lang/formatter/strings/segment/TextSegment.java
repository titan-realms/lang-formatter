package net.titanrealms.lang.formatter.strings.segment;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.UnaryOperator;

public class TextSegment implements LangSegment {
    private TextComponent text;

    public TextSegment(@NotNull String text) {
        this.text = Component.text(text);
    }

    public void updateText(UnaryOperator<TextComponent> text) {
        this.text = text.apply(this.text);
    }

    @Override
    public void apply(@NotNull TextComponent.Builder builder, @Nullable Map<String, Object> placeholders) {
        builder.append(this.text);
    }
}
