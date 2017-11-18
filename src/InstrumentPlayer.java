import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

class InstrumentPlayer{
    private static Instrument[] instruments;
    private static Integer pitch = 48;
    private static Integer volume = 80;
    private static boolean isSound = false;

    public static void increasePitch(){
        pitch++;
        if (pitch > 83) pitch = 48;
    }

    public static void decreasePitch(){
        pitch -=1;
        if (pitch < 48) pitch = 83;
    }


    public static Integer getPitch(){
        return pitch;
    }

    public static void increaseVolume(){
        volume++;
        if (volume > 100) volume = 100;
    }

    public static void decreaseVolume(){
        volume -=1;
        if (volume < 0) volume = 0;
    }

    public static Integer getVolume(){
        return volume;
    }

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
            Instrument[] orchestra = synthesizer.getAvailableInstruments();
            MidiChannel[] channels = synthesizer.getChannels();
            instruments = synthesizer.getDefaultSoundbank().getInstruments();

            channels[0].programChange(instruments[50].getPatch().getProgram());

            while(isSound){
                //Audio out test Parameters
                int NoteOut = pitch;
                if (NoteOut > 11) NoteOut = 0;

                channels[0].noteOn((pitch), (volume));
                System.out.println("Volume: " + volume);
                System.out.println("Pitch: " + pitch);
                System.out.println(NoteBank[NoteOut]);
                Thread.sleep(1000);

                channels[0].allNotesOff();
                increasePitch();

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