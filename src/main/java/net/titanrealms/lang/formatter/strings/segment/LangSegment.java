package net.titanrealms.lang.formatter.strings.segment;

import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public interface LangSegment {

    void apply(@NotNull TextComponent.Builder builder, @Nullable Map<String, Object> placeholders);
}
