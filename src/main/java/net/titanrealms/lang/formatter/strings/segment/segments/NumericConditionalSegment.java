package net.titanrealms.lang.formatter.strings.segment.segments;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.titanrealms.lang.formatter.strings.segment.ModifiableLangSegment;
import net.titanrealms.lang.formatter.strings.segment.modifiers.TextModifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class NumericConditionalSegment extends ModifiableLangSegment {
    private final String variable;
    private final Operator operator;
    private final int conditionalValue;
    private final String trueExtension;
    private final String falseExtension;

    // gonna keep here for a while for testing
    public static void main(String... initArgs) {
        String input = "\"placeholder\" > 1 ? \"true text\" : \"false text\"";

        Operator operator;
        int conditionalValue;

        String[] args = input.split("\"");
        String variable = args[1];
        String middleSection = args[2].replace(" ", "");
        String beforeTernary = middleSection.split("\\?")[0];
        if (Character.isDigit(middleSection.charAt(1))) {
            operator = Operator.parse(String.valueOf(middleSection.charAt(0)));
            conditionalValue = Integer.parseInt(beforeTernary.substring(1));
        } else {
            operator = Operator.parse(middleSection.substring(0, 1));
            conditionalValue = Integer.parseInt(beforeTernary.substring(2));
        }
        String trueExtension = args[3];
        String falseExtension = args[5];

        System.out.println("Input: " + input);
        System.out.println("Args: " + Arrays.toString(args));
        System.out.println("Variable: " + variable);
        System.out.println("Middle Section: " + middleSection);
        System.out.println("Before Ternary: " + beforeTernary);
        System.out.println("Operator: " + operator);
        System.out.println("Conditional Value: " + conditionalValue);
        System.out.println(" ");
        System.out.println("True Extension: " + trueExtension);
        System.out.println("False Extension: " + falseExtension);

        System.out.println("Reconstructed: \"" + variable + "\" " + operator.symbol + " " + conditionalValue + " ? \"" + trueExtension + "\" : \"" + falseExtension + "\"");
    }

    public NumericConditionalSegment(@NotNull String condition, @NotNull Collection<TextModifier> modifiers) {
        super(modifiers);
        String[] args = condition.split("\"");
        this.variable = args[1];
        String middleSection = args[2].replace(" ", "");
        String beforeTernary = middleSection.split("\\?")[0];
        if (Character.isDigit(middleSection.charAt(1))) {
            this.operator = Operator.parse(String.valueOf(middleSection.charAt(0)));
            this.conditionalValue = Integer.parseInt(beforeTernary.substring(1));
        } else {
            this.operator = Operator.parse(middleSection.substring(0, 1));
            this.conditionalValue = Integer.parseInt(beforeTernary.substring(2));
        }
        this.trueExtension = args[3];
        this.falseExtension = args.length <= 5 ? "" : args[5];
    }

    @Override
    public void apply(@NotNull TextComponent.Builder builder, @Nullable Map<String, Object> placeholders) {
        if (placeholders == null) {
            return;
        }
        String stringValue = placeholders.getOrDefault(this.variable, "?").toString();
        Integer value = this.isNumeric(stringValue);
        if (value == null) {
            throw new IllegalArgumentException("Non numeric value passed in as integer for numeric conditional statement: " + stringValue);
        }
        TextComponent text = Component.text(this.operator.test(value, this.conditionalValue) ? this.trueExtension : this.falseExtension);
        builder.append(this.applyModifiers(text));
    }

    private enum Operator {
        EQUAL_TO("=="),
        GREATER_THAN(">"),
        SMALLER_THAN("<"),
        GREATER_THAN_OR_EQUAL_TO(">="),
        SMALLER_THAN_OR_EQUAL_TO("<=");

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
            return EQUAL_TO;
        }

        public boolean test(int value, int conditionalValue) {
            switch (this) {
                case EQUAL_TO:
                    return value == conditionalValue;
                case GREATER_THAN:
                    return value > conditionalValue;
                case SMALLER_THAN:
                    return value < conditionalValue;
                case GREATER_THAN_OR_EQUAL_TO:
                    return value >= conditionalValue;
                case SMALLER_THAN_OR_EQUAL_TO:
                    return value <= conditionalValue;
                default:
                    return false;
            }
        }
    }

    @Nullable
    private Integer isNumeric(@NotNull String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "NumericConditionalSegment{" +
                "variable='" + variable + '\'' +
                ", operator=" + operator +
                ", conditionalValue=" + conditionalValue +
                ", trueExtension='" + trueExtension + '\'' +
                ", falseExtension='" + falseExtension + '\'' +
                '}';
    }
}
