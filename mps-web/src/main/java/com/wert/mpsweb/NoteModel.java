package com.wert.mpsweb;

import java.util.regex.Pattern;

// Data model for webapp phrase format

// Record types are automatically implemented read-only classes, auto-generating
// getters, equality methods, etc. for the specified parameters while still allowing
// for customization

// Params: Note in form of NOTE OCTAVE (e.g. "C4"), duration in number of sixteenth notes
public record NoteModel(String note, int duration) {
    // Regular expression for validating note string
    private static final Pattern notePattern = Pattern.compile("[A-G][0-9]+");

    // Record types automatically implement the constructor, but with this syntax,
    // we can provide more validation that will be executed with that constructor
    // (Note that no parameter are specified, unlike the Note class)
    public NoteModel {
        var matcher = notePattern.matcher(note);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid note " + note);
        }
        if (duration < 1) {
            throw new IllegalArgumentException(String.format(("Invalid duration %d"), duration));
        }
    }
}
