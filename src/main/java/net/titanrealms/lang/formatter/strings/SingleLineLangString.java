package net.titanrealms.lang.formatter.strings;

import net.kyori.adventure.text.Component;
import net.titanrealms.lang.formatter.parser.LangParser;
import net.titanrealms.lang.formatter.strings.segment.LangSegment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SingleLineLangString extends LangString {
    @NotNull
    private final List<LangSegment> segments;

    public SingleLineLangString(@NotNull String identifier, @NotNull String value) {
        super(identifier);
        this.segments = LangParser.createSegments(value);
    }

    @Override
    public Component toComponent() {
        return null;
    }

    @Override
    public String toString() {
        return "SingleLineLangString{" +
                "segments=" + segments +
                '}';
    }
}
