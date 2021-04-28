package net.titanrealms.lang.formatter.parser;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.titanrealms.lang.formatter.strings.segment.ColoredSegment;
import net.titanrealms.lang.formatter.strings.segment.LangSegment;
import net.titanrealms.lang.formatter.strings.segment.LangSegmentTextModifier;
import net.titanrealms.lang.formatter.strings.segment.TextSegment;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LangParser {

    public static void main(String[] args) {
        List<LangSegment> segments = LangParser.createSegments("{#0000}{!bold}get your free ice cream{\"ice-cream-limit\">1(s)()} today");
        TextComponent.Builder componentBuilder = Component.text();
        for (int i = 0; i < segments.size(); i++) {
            LangSegment segment = segments.get(i);
            if (segment instanceof TextSegment) {
                for (int j = i - 1; j >= 0; j--) {
                    LangSegment previousSegment = segments.get(j);
                    if (previousSegment instanceof LangSegmentTextModifier) {
                        LangSegmentTextModifier textModifier = (LangSegmentTextModifier) previousSegment;
                        textModifier.modify(((TextSegment) segment));
                    }
                }
                segment.apply(componentBuilder, null);
            }
        }
        System.out.println("Children: " + componentBuilder.build().children());
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
                    segments.add(new TextSegment(line.substring(lastClosingIndex + 1, i)));
                }
                for (int j = i + 1; j < length; j++) {
                    char closingCharacter = line.charAt(j);
                    if (closingCharacter == '}') {
                        segments.add(parseSegment(line.substring(i + 1, j))); // Only include what's contained within the { }
                        lastClosingIndex = j;
                        i = j; // No need to check if anything in between is {
                        break;
                    }
                }
            }
        }
        if (lastClosingIndex < length) {
            segments.add(new TextSegment(line.substring(lastClosingIndex + 1)));
        }
        return segments;
    }

    /**
     * Parses a segment from {xxxx}
     */
    @NotNull
    private static LangSegment parseSegment(@NotNull String segment) {
        char indicator = segment.charAt(0);
        switch (indicator) {
            case '#':
                return new ColoredSegment(segment);
            default:
                return null;
        }
    }
}
