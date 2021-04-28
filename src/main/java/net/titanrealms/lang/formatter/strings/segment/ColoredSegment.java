package net.titanrealms.lang.formatter.strings.segment;

import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ColoredSegment implements LangSegmentTextModifier {
    private final TextColor color;

    public ColoredSegment(@NotNull String hexCode) {
        Color color = Color.decode(hexCode);
        this.color = TextColor.color(color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public void modify(@NotNull TextSegment child) {
        child.updateText((text) -> text.color(this.color));
    }
}
