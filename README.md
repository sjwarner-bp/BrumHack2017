# BrumHack2017 - LeapTheremin
BrumHack2017 Project

*by Sam Warner, Nikolas Mouzourides and Chloe Seivwright.*

## LeapTheremin
The LeapTheremin is a mock-theremin / whole host of different musical instruments all of which may be played in a theremin-like style.

It uses a [Leap Motion](www.leapmotion.com), their awesome Java API, and a Java-based [MIDI package](https://docs.oracle.com/javase/tutorial/sound/overview-MIDI.html).

These have all been tied together in an effort to make some music (or at least, loud noises).

### A crash course in playing the theremin
So, we aren't 100% sure this is correct, but the way OUR theremin (and theremin-flavoured instruments) work is:
* Left hand palm elevation (i.e. y-plane) controls volume. A higher palm is loud, and a lower palm is quiet).
* Right hand's middle fingertip depth (i.e. z-plane) controls pitch. Closer produces a higher note, farther produces a lower note.

### To compile and run:
Change directory to `/src`
```cd /src```

Code to compile:
```javac -cp "../LeapJava.jar" Theremin.java```

Code to run:
```LD_LIBRARY_PATH=. java -cp ../LeapJava.jar:. Theremin```

...or, just set it up in your favourite IDE!

## Rock, Paper, Scissors
Now this one is probably a bit more correct than the theremin...

In the advent of intelligent personal assistants, we delegate more and more chores to these 'AI'. These range from the useful (check the weather, or remind me it's mum's birthday next Tuesday) to the somewhat silly (roll a 20-sided dice for me).

However, some people use these functions to make decisions simpler. If I flip a coin and it comes up heads, I'll do the vacuuming today instead of tomorrow.

But these disembodied voices are somewhat lackluster. 'Heads' or 'Tails' doesn't feel as real without flipping a coin, and 'Rock, Paper, Scissors' isn't as fun without throwing some shapes out there.

**This ends now.**

But more seriously, this 'Rock, Paper, Scissors' game (again) uses the [Leap Motion](www.leapmotion.com), their API, and some pretty basic Java.

### A crash course in Rock, Paper, Scissors
Nope, you don't need one.

### To compile and run:
Change directory to `/src`
```cd /src```

Code to compile:
```javac -cp "../LeapJava.jar" RockPaperScissors.java```

Code to run:
```LD_LIBRARY_PATH=. java -cp ../LeapJava.jar:. RockPaperScissors```

...or, just set it up in your favourite IDE!

### Possible extension to Rock, Paper, Scissors...
Rock, Paper, Scissors, Lizard, Spock? Why are all the shapes so similar?!?!?! :scream:
