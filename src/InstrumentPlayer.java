import javax.sound.midi.*;
import java.util.Arrays;

class InstrumentPlayer{
    private static Instrument[] instruments;
    private static MidiChannel[] channels;
    private static Integer pitch;
    private static Integer volume;
    private static String[] NoteBank;
    private static int[] sharpNotes = {1, 3, 6, 8, 10};
    private static boolean isSound = false;

    InstrumentPlayer(){
        isSound = true;
        NoteBank = new String[12];
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

        Synthesizer synthesizer = null;
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        channels = synthesizer.getChannels();
        instruments = synthesizer.getDefaultSoundbank().getInstruments();
        channels[0].programChange(instruments[50].getPatch().getProgram());
    }

    public static void setPitch(int pitchIn){
        final int finalPitchIn = pitchIn;
        if (Arrays.stream(sharpNotes).anyMatch(i -> i == finalPitchIn % 12)) ++pitchIn;
        if (pitchIn > 72) pitchIn = 72;
        if (pitchIn < 48) pitchIn = 48;
        pitch = pitchIn;
    }


    public static void setVolume(int volumeIn){
        if(volumeIn > 127) volumeIn = 127;
        if(volumeIn < 0) volumeIn = 0;
        volume = volumeIn;
    }

    public static Integer getPitch(){
        return pitch;
    }

    public static Integer getVolume(){
        return volume;
    }

    public static void toggleSound(){
        isSound = !isSound;
    }


    public void run(){
        try{
            while(isSound){
                //Audio out test Parameters
                playNote();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void playNote() {
        int NoteOut = pitch;
        while (NoteOut > 11) NoteOut = NoteOut - 12;

        channels[0].noteOn((pitch), (volume));
        System.out.println("Volume: " + volume);
        System.out.println("Pitch: " + pitch);
        System.out.println(NoteBank[NoteOut]);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channels[0].allNotesOff();
    }
}