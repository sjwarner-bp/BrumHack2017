# BrumHack2017 - LeapTheremin
BrumHack2017 Project

by Sam Warner, Nikolas Mouzourides and Chloe Seivwright.

## LeapTheremin
The LeapTheremin is a mock-theremin / whole host of different musical instruments all of which may be played in a theremin-like style.

It uses a [Leap Motion](www.leapmotion.com), their awesome Java API, and a Java-based [MIDI package](https://docs.oracle.com/javase/tutorial/sound/overview-MIDI.html).

These have all been tied together in an effort to make some music (or at least, loud noises).

### A crash course in playing the theremin
So, we aren't 100% sure this is correct, but the way OUR theremin (and theremin-flavoured instruments) work is:
* Left hand palm elevation (i.e. y-plane) controls volume. A higher palm is loud, and a lower palm is quiet).
* Right hand's middle fingertip depth (i.e. z-plane) controls pitch. Closer produces a higher note, farther produces a lower note.

### Must be in the src directory to run these commands
Code to compile:
```javac -cp "../LeapJava.jar" Theremin.java```

Code to run:
```LD_LIBRARY_PATH=. java -cp ../LeapJava.jar:. Theremin```
