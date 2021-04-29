package net.titanrealms.lang.formatter.strings.segment.segments;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.titanrealms.lang.formatter.strings.segment.ModifiableLangSegment;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

public class NumericConditionalSegment extends ModifiableLangSegment {
    private final String variable;
    private final Operator operator;
    private final int conditionalValue;
    private final String trueExtension;
    private final String falseExtension;

    public NumericConditionalSegment(@NotNull String condition, @NotNull Collection<TextModifier> modifiers) {
        super(modifiers);
        String[] args = condition.split("\"");
        this.variable = args[1];
        this.operator = Operator.parse(args[2].substring(0, this.isNumeric(String.valueOf(args[2].charAt(1))) ? 1 : 2));
        String[] extensions = args[2].split("\\(");
        this.conditionalValue = Integer.parseInt(extensions[0].replace(this.operator.symbol, ""));
        this.trueExtension = extensions[1].substring(0, extensions[1].length() - 1);
        this.falseExtension = extensions[2].substring(0, extensions[2].length() - 1);
    }

    @Override
    public void apply(TextComponent.@NotNull Builder builder, @Nullable Map<String, Object> placeholders) {
        if (placeholders == null) {
            return;
        }
        String stringValue = placeholders.getOrDefault(this.variable, "?").toString();
        if (!this.isNumeric(stringValue)) {
            return;
        }
        int value = Integer.parseInt(stringValue);
        TextComponent text = Component.text(this.operator.test(value, this.conditionalValue) ? this.trueExtension : this.falseExtension);
        builder.append(this.applyModifiers(text));
    }

    private enum Operator {
        EQUAL("=="),
        BIGGER(">"),
        SMALLER("<"),
        BIGGER_EQUAL(">="),
        SMALLER_EQUAL("<=");

        private final String symbol;

        Operator(String symbol) {
            this.symbol = symbol;
        }

        public static Operator parse(String string) {
            for (Operator operator : values()) {
                if (operator.symbol.equals(string)) {
                    return operator;
                }
            }
            return EQUAL;
        }

        public boolean test(int value, int conditionalValue) {
            switch (this) {
                case EQUAL:
                    return value == conditionalValue;
                case BIGGER:
                    return value > conditionalValue;
                case SMALLER:
                    return value < conditionalValue;
                case BIGGER_EQUAL:
                    return value >= conditionalValue;
                case SMALLER_EQUAL:
                    return value <= conditionalValue;
                default:
                    return false;
            }
        }
    }

    private boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
