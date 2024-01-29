# melodic-phrase-suggester
Java project: App generates melodic phrases to spark ideas for composing songs

Melodic Phrase Suggester for Mac

Copyright 2022 by Randall Wert

How to get started:

1. Copy the JAR file (MelPhraSug.jar) to a convenient location on your computer.

2. Download and install the Java Development Kit (JDK) from https://www.oracle.com/java/technologies/downloads  If the newest version of the JDK does not work with your version of the Mac OS, click the “Java archive” link to reach a page where you can download older versions of the JDK. The MelPhraSug application requires JDK version 11 or newer.

3. Double-click the JAR file (MelPhraSug.jar) to launch the application.

Alternative: If you’re comfortable running programs from the Mac Terminal, you can open a Terminal window, then cd to the folder where you’ve installed the JAR, then run: java -jar MelPhraSug.jar  As the program generates new melodic phrases, it will print messages to the Terminal window (behind the GUI) that you might find interesting. These messages pertain to the logic that is used to generate the phrases.

4. If you would like to edit the current melodic phrase in the text box provided, you will need to know a few basics about the Staccato syntax (formerly known as MusicString syntax). As used in the application, each part of the string contains: (a) the letter name of the note, (b) a number representing the octave in which the note occurs, and (c) letters (and sometimes a dot) indicating the duration of the note. As a default, the application will generate strings that are two measures long in common (4/4) time.

The more you learn about the Staccato syntax, the more easily you can edit the generated strings to create much more complexity and diversity than the application itself generates!

Here are some resources to help you learn the MusicString/Staccato syntax:

https://usermanual.wiki/Document/The20Complete20Guide20to20JFugue2C20Second20Edition2C20v200.723471053.pdf (See page 33.)

https://www.cs.utexas.edu/users/novak/cs314/jfugue-chapter2.pdf

https://www.cs.allegheny.edu/sites/jjumadinova/teaching/111/labs/JFugueHandout.pdf
