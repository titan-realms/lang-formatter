package net.titanrealms.lang.formatter.strings.segment;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ColouredSegment implements LangSegment {
    @NotNull
    private final Component component;

    public ColouredSegment(@NotNull String hexCode) {
        this.component = Component.text()
    }

    @Override
    public void apply(TextComponent.@NotNull Builder builder, @Nullable Map<String, Object> placeholders) {

    }
}
