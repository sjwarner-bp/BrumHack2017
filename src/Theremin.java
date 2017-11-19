import java.io.IOException;
import com.leapmotion.leap.*;

class ThereminListener extends Listener {

    InstrumentPlayer instrumentPlayer = new InstrumentPlayer();

    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();

        //Get hands
        for(Hand hand : frame.hands()) {

            if (frame.hands().count() != 2) {
                instrumentPlayer.setVolume(0);
                System.out.println("You're out of range!");
                continue;
            }

            for(Finger finger : hand.fingers()) {
                if (finger.type() == Finger.Type.TYPE_MIDDLE) {
                    if (hand.isLeft()) {
                        // do volume stuff
                        float yPos = hand.palmPosition().getY();
                        instrumentPlayer.setVolume((int) yPos / 3);
                        System.out.println("Left hand is at: " + yPos);
                    } else {
                        // do some pitch stuff
                        float zPos = finger.tipPosition().getZ();
                        instrumentPlayer.setPitch((int) ((zPos / 12) + 60));
                        System.out.println("Right hand is at: " + zPos);
                    }
                }
            }


        }
        instrumentPlayer.playNote();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Theremin {
    public static void main(String[] args) {
        // Create a sample listener and controller
        ThereminListener thereminListener = new ThereminListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(thereminListener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");

        // Remove this code later when done diagnostics
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(thereminListener);
    }
}
