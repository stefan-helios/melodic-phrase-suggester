package com.wert.mpsweb;

import java.util.regex.Pattern;

// Note in form of NOTE OCTAVE (e.g. "C4"), duration in number of sixteenth notes
public record NoteModel(String note, int duration) {
    private static final Pattern notePattern = Pattern.compile("[A-G][0-9]+");

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
