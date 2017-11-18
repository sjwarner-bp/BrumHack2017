import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

class InstrumentPlayer{
    private static Instrument[] instruments;
    private static Integer pitch = 48;
    private static Integer volume = 100;
    private static boolean isSound = false;

//    public static void increasePitch(){
//        pitch++;
//        if (pitch > 83) pitch = 48;
//    }
//
//    public static void decreasePitch(){
//        pitch -=1;
//        if (pitch < 48) pitch = 83;
//    }

    public static void setPitch(int pitchIn){
        if (pitchIn > 83) pitchIn = 48;
        if (pitchIn < 48) pitchIn = 83;
        pitch = pitchIn;
    }


    public static void setVolume(int volumeIn){
        if(volumeIn > 100) volumeIn = 100;
        if(volumeIn < 0) volumeIn = 0;
        volume = volumeIn;
    }

    public static Integer getPitch(){
        return pitch;
    }

    public static Integer getVolume(){
        return volume;
    }


//    public static void increaseVolume(){
//        volume++;
//        if (volume > 100) volume = 100;
//    }
//
//    public static void decreaseVolume(){
//        volume -=1;
//        if (volume < 0) volume = 0;
//    }

    public static void toggleSound(){
        isSound = !isSound;
    }


    public void run(){
        isSound = true;

        String[] NoteBank = new String[12];
        NoteBank[0] = "C";
        NoteBank[1] = "C#";
        NoteBank[2] = "D";
        NoteBank[3] = "D#";
        NoteBank[4] = "E";
        NoteBank[5] = "F";
        NoteBank[6] = "F#";
        NoteBank[7] = "G";
        NoteBank[8] = "G#";
        NoteBank[9] = "A";
        NoteBank[10] = "A#";
        NoteBank[11] = "B";


        try{
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            MidiChannel[] channels = synthesizer.getChannels();
            instruments = synthesizer.getDefaultSoundbank().getInstruments();
            channels[0].programChange(instruments[50].getPatch().getProgram());

            while(isSound){
                //Audio out test Parameters
                int NoteOut = pitch;
                while (NoteOut > 11) NoteOut = NoteOut - 12;

                channels[0].noteOn((pitch), (volume));
                System.out.println("Volume: " + volume);
                System.out.println("Pitch: " + pitch);
                System.out.println(NoteBank[NoteOut]);
                Thread.sleep(1000);
                channels[0].allNotesOff();

                if (pitch == 0 || volume == 0)
                {
                    channels[0].allNotesOff();
                    toggleSound();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}