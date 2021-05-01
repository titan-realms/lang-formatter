package net.titanrealms.lang.formatter.strings;

import net.kyori.adventure.text.Component;
import net.titanrealms.lang.formatter.parser.LangParser;
import net.titanrealms.lang.formatter.strings.segment.LangSegment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MultiLineLangString extends LangString {
    @NotNull
    private final List<List<LangSegment>> segments = new ArrayList<>();

    public MultiLineLangString(@NotNull String identifier, @NotNull List<String> lines) {
        super(identifier);
        for (String line : lines) {
            this.segments.add(LangParser.createSegments(line));
        }
    }

    @Override
    public Component toComponent() {
        return null;
    }
}
