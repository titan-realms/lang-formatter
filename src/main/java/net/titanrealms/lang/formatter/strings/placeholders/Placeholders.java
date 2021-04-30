package net.titanrealms.lang.formatter.strings.placeholders;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Placeholders {
    private final Map<String, Object> placeholders = new HashMap<>();

    public <T> void set(@NotNull String placeholder, @NotNull T value) {
        this.placeholders.put(placeholder, value);
    }

    @NotNull
    public Map<String, Object> toMap() {
        return this.placeholders;
    }
}
