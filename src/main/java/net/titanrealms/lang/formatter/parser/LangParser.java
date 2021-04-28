package net.titanrealms.lang.formatter.parser;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.titanrealms.lang.formatter.strings.segment.LangSegment;
import net.titanrealms.lang.formatter.strings.segment.TextSegment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LangParser {

    public static void main(String[] args) {
        List<LangSegment> segments = LangParser.createSegments("{#FFFFFF}f");
        TextComponent.Builder componentBuilder = Component.text();
        segments.forEach(segment -> segment.apply(componentBuilder, null));

        System.out.println("Children: " + componentBuilder.build().children());
        System.out.println("Content: " + PlainComponentSerializer.plain().serialize(componentBuilder.build()));
    }

    /**
     * Parses a message from "sdgsdgsdg {xxxx}"
     */
    @NotNull
    public static List<LangSegment> createSegments(@NotNull String line) {
        List<LangSegment> segments = new ArrayList<>();
        int length = line.length();
        int lastClosingIndex = 0;
        for (int i = 0; i < length; i++) {
            char openCharacter = line.charAt(i);
            if (openCharacter == '{') {
                if (i > lastClosingIndex) {
                    segments.add(new TextSegment(line.substring(lastClosingIndex, i)));
                }
                for (int j = i + 1; j < length; j++) {
                    char closingCharacter = line.charAt(j);
                    if (closingCharacter == '}') {
                        segments.add(parseSegment(line.substring(i + 1, j))); // Only include what's contained within the { }
                        lastClosingIndex = j;
                        i = j; // No need to check if anything in between is {
                    }
                }
            }
        }
        System.out.println(lastClosingIndex);
        System.out.println(length);
        if (lastClosingIndex < length) {
            segments.add(new TextSegment(line.substring(lastClosingIndex)));
        }
        return segments;
    }

    /**
     * Parses a segment from {xxxx}
     */
    @NotNull
    private static LangSegment parseSegment(@NotNull String segment) {
        System.out.println("SEGMENT START " + segment);
        char indicator = segment.charAt(0);
        switch (indicator) {
            case '#':
                return
        }
        return null;
    }
}
