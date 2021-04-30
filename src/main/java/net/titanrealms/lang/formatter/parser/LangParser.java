package net.titanrealms.lang.formatter.parser;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import net.titanrealms.lang.formatter.strings.segment.LangSegment;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import net.titanrealms.lang.formatter.strings.segment.modifiers.text.ColorModifier;
import net.titanrealms.lang.formatter.strings.segment.modifiers.text.DecorationModifier;
import net.titanrealms.lang.formatter.strings.segment.segments.NumericConditionalSegment;
import net.titanrealms.lang.formatter.strings.segment.segments.TextSegment;
import net.titanrealms.lang.formatter.strings.segment.segments.VariableSegment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class LangParser {

    public static void main(String[] args) {
        List<LangSegment> segments = LangParser.createSegments("{#00000}{!bold}get your {#ffffff}{@ice-cream-limit} {#00000}free ice cream{\"ice-cream-limit\">1(s)()} today");
        TextComponent text = segmentsToText(segments, new HashMap<>() {{
            this.put("ice-cream-limit", 5);
        }});
        System.out.println("Children: " + text.children());
        System.out.println("Plain: " + PlainComponentSerializer.plain().serialize(text));
    }

    public static TextComponent segmentsToText(@NotNull Collection<LangSegment> segments) {
        return segmentsToText(segments);
    }

    public static TextComponent segmentsToText(@NotNull Collection<LangSegment> segments, @Nullable Map<String, Object> placeholders) {
        TextComponent.Builder textBuilder = Component.text();
        for (LangSegment segment : segments) {
            segment.apply(textBuilder, placeholders);
        }
        return textBuilder.build();
    }

    @NotNull
    public static List<LangSegment> createSegments(@NotNull String line) {
        List<LangSegment> segments = new ArrayList<>();
        List<TextModifier> currentModifiers = new ArrayList<>();
        Consumer<TextModifier> newModifier = (modifier) -> {
            boolean replaced = false;
            for (int i = 0; i < currentModifiers.size(); i++) {
                Class<? extends TextModifier> modifierType = currentModifiers.get(i).getClass();
                if (modifierType.equals(modifier.getClass())) {
                    currentModifiers.set(i, modifier);
                    replaced = true;
                    break;
                }
            }
            if (!replaced) {
                currentModifiers.add(modifier);
            }
        };

        int length = line.length();
        int lastClosingIndex = 0;
        for (int i = 0; i < length; i++) {
            char openCharacter = line.charAt(i);
            if (openCharacter != '{') {
                continue;
            }
            if (i - 1 > lastClosingIndex) {
                segments.add(new TextSegment(line.substring(lastClosingIndex + 1, i), currentModifiers));
            }
            for (int j = i + 1; j < length; j++) {
                char closingCharacter = line.charAt(j);
                if (closingCharacter != '}') {
                    continue;
                }
                String value = line.substring(i + 1, j);
                TextModifier modifier = parseModifier(value);
                if (modifier != null) {
                    newModifier.accept(modifier);
                } else {
                    segments.add(parseSegment(value, currentModifiers));
                }
                lastClosingIndex = j;
                i = j;
                break;
            }
        }
        if (lastClosingIndex < length) {
            segments.add(new TextSegment(line.substring(lastClosingIndex + 1), currentModifiers));
        }
        return segments;
    }

    @NotNull
    private static LangSegment parseSegment(@NotNull String value, Collection<TextModifier> modifiers) {
        char indicator = value.charAt(0);
        switch (indicator) {
            case '@':
                return new VariableSegment(value, modifiers);
            case '\"':
                return new NumericConditionalSegment(value, modifiers);
            default:
                return new TextSegment(value, modifiers);
        }
    }

    @Nullable
    private static TextModifier parseModifier(@NotNull String value) {
        char indicator = value.charAt(0);
        switch (indicator) {
            case '#':
                return new ColorModifier(value);
            case '!':
                return new DecorationModifier(value);
            default:
                return null;
        }
    }
}
