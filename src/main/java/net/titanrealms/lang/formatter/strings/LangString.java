package net.titanrealms.lang.formatter.strings;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public abstract class LangString {
    @NotNull
    private final String identifier;

    protected LangString(@NotNull String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public abstract Component toComponent();
}
