package com.example.melphrasuggui;

import java.util.ArrayList;

public class Phrase {
    // Instance variables
    private int numOfMeasures;
    private int unitsOfDuration;
    private int durationSoFar;
    private ArrayList<Note> notes;
    private boolean wasTriad;

    // Constructor
    public Phrase(int numOfMeasures) {
        this.numOfMeasures = numOfMeasures;
        unitsOfDuration = numOfMeasures * 16;
        durationSoFar = 0;
        notes = new ArrayList<Note>();
        wasTriad = false;
    }

    // Build the phrase from random durations and pitches of notes, within certain constraints (rules)
    public void buildPhrase() {
        // Keep adding notes until the measures are full
        while (durationSoFar < unitsOfDuration) {
            int scaleDegree = determineScaleDegree();
            int duration = determineDuration();
            Note newNote = new Note(scaleDegree, duration);
            notes.add(newNote);
            durationSoFar += duration;
        }
        // If the duration of the notes runs past the length of the
        // last measure, cut the last note shorter accordingly
        if (durationSoFar > unitsOfDuration) {
            int excess = durationSoFar - unitsOfDuration;
            int finalDuration = notes.get(notes.size()-1).getDuration();
            finalDuration -= excess;
            notes.get(notes.size()-1).setDuration(finalDuration);
        }
    }

    // Determine the pitch (in terms of scale degree) of the next note
    public int determineScaleDegree() {
        if (notes.size() == 0) {
            System.out.println("Setting first note of phrase");
            int randInteger = getRandomInteger(1, 3);
            switch(randInteger) {
                case 1:
                    return 8;
                case 2:
                    return 12;
                case 3:
                    return 15;
            }
        } else {
            System.out.println("Giving meaningful value to lastScaleDegree");
            int lastScaleDegree = notes.get(notes.size()-1).getScaleDegree();
            if (notes.size() > 1) {
                System.out.println("Size is greater than 1; setting secondLastScaleDegree");
                int secondLastScaleDegree = notes.get(notes.size()-2).getScaleDegree();
                if (lastScaleDegree - secondLastScaleDegree == 5) {
                    System.out.println("Last jump was +5");
                    return lastScaleDegree - 1;
                } else if (secondLastScaleDegree - lastScaleDegree == 5) {
                    System.out.println("Last jump was -5");
                    return lastScaleDegree + 1;
                } else if (lastScaleDegree - secondLastScaleDegree == 2 && !wasTriad) {
                    System.out.println("Last jump was +2 but did not complete triad");
                    wasTriad = true;
                    return lastScaleDegree + 2;
                } else if (secondLastScaleDegree - lastScaleDegree == 2 && !wasTriad) {
                    System.out.println("Last jump was -2 but did not complete triad");
                    wasTriad = true;
                    return lastScaleDegree - 2;
                }
            }
            // If scale degree is 2 or 4, then resolve probably downward, otherwise upward
            if (lastScaleDegree == 2 || lastScaleDegree == 16 || lastScaleDegree == 4 || lastScaleDegree == 18) {
                System.out.println("Last note was tendency tone that could go up or down");
                int randInteger = getRandomInteger(1, 3);
                if (randInteger == 1 || randInteger == 2) {
                    System.out.println("Should go down");
                    return lastScaleDegree - 1;
                } else {
                    System.out.println("Should go up");
                    return lastScaleDegree + 1;
                }
                // Resolve scale degree 6 downward and scale degree 7 upward
            } else if (lastScaleDegree == 6 || lastScaleDegree == 20) {
                System.out.println("Last note was 6 or 20");
                return lastScaleDegree - 1;
            } else if (lastScaleDegree == 7 || lastScaleDegree == 21) {
                System.out.println("Last note was 7 or 21");
                return lastScaleDegree + 1;
                // Catch-all for other cases:
            } else {
                System.out.println("Catch-all for cases not caught above");
                // Determine whether we will step/leap upward or downward
                System.out.println("Step factor defaults to +1");
                int stepFactor = 1;
                if (getRandomInteger(1, 2) == 2) {
                    System.out.println("Step factor randomly set to -1");
                    stepFactor = -1;
                }
                // The smaller the step/leap, the more likely it is to occur
                System.out.println("Randomly determining size of step");
                int randInteger = getRandomInteger(1, 31);
                switch(randInteger) {
                    case 1:
                        return lastScaleDegree + (stepFactor * 5);
                    case 2: case 3:
                        return lastScaleDegree + (stepFactor * 4);
                    case 4: case 5: case 6: case 7:
                        return lastScaleDegree + (stepFactor * 3);
                    case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15:
                        return lastScaleDegree + (stepFactor * 2);
                    default:
                        return lastScaleDegree + stepFactor;
                }
            }
        }
        System.out.println("Shouldn't happen?");
        return notes.get(notes.size()-1).getScaleDegree();
    }

    // Determine the duration of the next note to be added to the
    // phrase
    public int determineDuration() {
        int randInteger = getRandomInteger(1, 20);
        switch(randInteger) {
            case 1:
                return 1;
            case 2: case 3: case 4: case 5:
                return 2;
            case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13:
                return 4;
            case 14:
                return 6;
            case 15: case 16: case 17: case 18:
                return 8;
            case 19:
                return 12;
            case 20:
                return 16;
        }
        return -1;
    }

    // Get a random integer between low and high (inclusive)
    public int getRandomInteger(int low, int high) {
        return (int)(Math.random() * (high - low + 1) + low);
    }

    // Create a string representation of the phrase for printing
    public String toString() {
        String returnStr = "";
        for (Note note : notes) {
            returnStr += "\nScale degree: " + translateDegree(note.getScaleDegree()) + "; duration: " + translateDuration(note.getDuration());
        }
        return returnStr;
    }

    // Translate scale degrees into letter names for notes
    // NOTE: Assumes key of C major; prefix indicates octave
    public String translateDegree(int scaleDegree) {
        String returnString = "";
        if (scaleDegree < 8) {
            returnString = "\\/";
        } else if (scaleDegree > 14) {
            returnString = "/\\";
        } else {
            returnString = "--";
        }
        switch(scaleDegree) {
            case 1: case 8: case 15: case 22:
                returnString += "C";
                break;
            case 2: case 9: case 16:
                returnString += "D";
                break;
            case 3: case 10: case 17:
                returnString += "E";
                break;
            case 4: case 11: case 18:
                returnString += "F";
                break;
            case 5: case 12: case 19:
                returnString += "G";
                break;
            case 6: case 13: case 20:
                returnString += "A";
                break;
            case 7: case 14: case 21:
                returnString += "B";
                break;
            default:
                returnString += "(" + scaleDegree + ")";
        }
        return returnString;
    }

    // Translate duration units into more common names for durations
    // of notes
    public String translateDuration(int duration) {
        switch(duration) {
            case 1:
                return "sixteenth note";
            case 2:
                return "eighth note";
            case 3:
                return "dotted eighth note";
            case 4:
                return "quarter note";
            case 5:
                return "quarter note + sixteenth note (tie)";
            case 6:
                return "dotted quarter note";
            case 7:
                return "dotted quarter note + sixteenth note (tie)";
            case 8:
                return "half note";
            case 9:
                return "half note + sixteenth note (tie)";
            case 10:
                return "half note + eighth note (tie)";
            case 11:
                return "half note + dotted eighth note (tie)";
            case 12:
                return "dotted half note";
            case 13:
                return "dotted half note + sixteenth note (tie)";
            case 14:
                return "dotted half note + eighth note (tie)";
            case 15:
                return "dotted half note + dotted eighth note (tie)";
            case 16:
                return "whole note";
        }
        return "unknown duration";
    }

    // Translate pitch and duration to symbols that can be read by jFugue's Player.play()
    public String toJFugueString() {
        String jFugueString = "";
        for (Note note : notes) {
            int scaleDegree = note.getScaleDegree();
            // Correct scale degrees lying out-of-bounds by raising or lowering them by octaves
            while (scaleDegree < 1) {
                scaleDegree += 7;
            }
            while (scaleDegree > 22) {
                scaleDegree -= 7;
            }
            int duration = note.getDuration();
            switch(scaleDegree) {
                case 1: case 8: case 15: case 22:
                    jFugueString += "C";
                    break;
                case 2: case 9: case 16:
                    jFugueString += "D";
                    break;
                case 3: case 10: case 17:
                    jFugueString += "E";
                    break;
                case 4: case 11: case 18:
                    jFugueString += "F";
                    break;
                case 5: case 12: case 19:
                    jFugueString += "G";
                    break;
                case 6: case 13: case 20:
                    jFugueString += "A";
                    break;
                case 7: case 14: case 21:
                    jFugueString += "B";
                    break;
            }
            if (scaleDegree < 8) {
                jFugueString += "4";
            } else if (scaleDegree > 14) {
                jFugueString += "6";
            } else {
                jFugueString += "5";
            }
            switch(duration) {
                case 1:
                    jFugueString += "s ";
                    break;
                case 2:
                    jFugueString += "i ";
                    break;
                case 3:
                    jFugueString += "is ";
                    break;
                case 4:
                    jFugueString += "q ";
                    break;
                case 5:
                    jFugueString += "qs ";
                    break;
                case 6:
                    jFugueString += "q. ";
                    break;
                case 7:
                    jFugueString += "q.s ";
                    break;
                case 8:
                    jFugueString += "h ";
                    break;
                case 9:
                    jFugueString += "hs ";
                    break;
                case 10:
                    jFugueString += "hi ";
                    break;
                case 11:
                    jFugueString += "his ";
                    break;
                case 12:
                    jFugueString += "h. ";
                    break;
                case 13:
                    jFugueString += "h.s ";
                    break;
                case 14:
                    jFugueString += "h.i ";
                    break;
                case 15:
                    jFugueString += "h.is ";
                    break;
                case 16:
                    jFugueString += "w ";
            }
        }
        System.out.println("\n\nPlaying: " + jFugueString);
        return jFugueString;
    }
}
