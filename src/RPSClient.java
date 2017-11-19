import com.leapmotion.leap.*;

import java.io.*;
import java.net.*;

class RPSClientListener extends Listener {

	GestureType userGestureType = null;

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

		if (frame.hands().count() != 1) {
			System.out.println("Get your hand ready!");
			return;
		}

		for (int i = 3; i > 0; i--) {
			try {
				System.out.println(i);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// There will only ever be one hand, so rightmost will work...
		Hand hand = frame.hands().rightmost();
		int extendedFingerCount = 0;

		for (Finger finger : hand.fingers()) {
			if (finger.isExtended()) {
				extendedFingerCount++;
			}
		}

		if (extendedFingerCount == 0) {
			userGestureType = GestureType.ROCK;
		} else if (extendedFingerCount == 5) {
			userGestureType = GestureType.PAPER;
		} else {
			userGestureType = GestureType.SCISSORS;
		}

		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public GestureType getUserGestureType() {
		return userGestureType;
	}

}

class RPSClient {

    private static String host = "localhost";
	private static Integer port = 1337; // CHANGE THIS FOR MULTI MACHINE SUPPORT

    public static void main(String args[]) throws Exception {

		RPSClientListener rpsClientListener = new RPSClientListener();
		Controller controller = new Controller();

		// Have the sample listener receive events from the controller
		controller.addListener(rpsClientListener);

		while(rpsClientListener.getUserGestureType() == null) {
			Thread.sleep(10);
		}

		transmit(rpsClientListener.getUserGestureType());

    }

	static void transmit(GestureType userGestureType) throws IOException {
		Socket clientSocket = new Socket(RPSClient.host, RPSClient.port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    	String response;// Transmit input to the server and provide some feedback for the user
		outToServer.writeBytes(userGestureType + "\n");
		System.out.println("\nYour " + userGestureType.toString() + " was has been submitted to the server. No going back now, it's a waiting game...");

		response = inFromServer.readLine();
		System.out.println("Server says: " + response);

		clientSocket.close();
	}
}
