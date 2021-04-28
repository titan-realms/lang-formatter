package net.titanrealms.lang.formatter.strings.segment;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class TextSegment implements LangSegment {
    @NotNull
    private final Component content;

    public TextSegment(@NotNull String content) {
        this.content = Component.text(content);
    }

    @Override
    public void apply(@NotNull TextComponent.Builder builder, @Nullable Map<String, Object> placeholders) {
        builder.append(this.content);
    }
}
