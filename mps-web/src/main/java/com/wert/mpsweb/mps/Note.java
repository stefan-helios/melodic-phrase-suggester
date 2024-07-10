package main.java.com.wert.mpsweb.mps;

public record Note(int scaleDegree, int duration) {
    public Note(int scaleDegree, int duration) {
         // Normalize degree
        while (scaleDegree < 1) {
            scaleDegree += 7;
        }
        while (scaleDegree > 22) {
            scaleDegree -= 7;
        }

        this.scaleDegree = scaleDegree;

        // Validate duration
        if (duration < 1) throw new IllegalArgumentException("Duration must be greater than zero");
        this.duration = duration;
    }

    public String toNoteOctave() {
        var note = switch(scaleDegree % 7) {
            case 0 -> "B";
            case 1 -> "C";
            case 2 -> "D";
            case 3 -> "E";
            case 4 -> "F";
            case 5 -> "G";
            case 6 -> "A";
            default -> throw new IllegalArgumentException("Invalid scale degree: " + scaleDegree);
        };
        if (scaleDegree < 8) {
            note += "4";
        } else if (scaleDegree <= 14) {
            note += "5";
        } else {
            note += "6";
        }
    
        return note;
    }

    public String toJFugue() {
        // Duration codes indexed by number of sixteenth notes, skipping 0
        final String durations[] = {
            "s",
            "i",
            "is",
            "q",
            "qs",
            "q.",
            "q.s",
            "h",
            "hs",
            "hi",
            "his",
            "h.",
            "h.s",
            "h.i",
            "h.is",
            "w",
        };
        return toNoteOctave() + durations[duration-1];
    }
}