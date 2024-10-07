//Anthony Mozloom

package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A basic example JavaFX program for the first lab.
 * 
 * @author Shannon Duvall
 * 
 * This class contains the code that runs the basic window.
 * 
 */
public class Main extends Application {

	public static final int SIZE = 400;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String TITLE = "Example JavaFX";
    public static final Paint BACKGROUND = Color.AZURE;


	private Scene myScene;
	
	// This class will handle the logic that is specific to this application.
	private AnimationController myAnimation;

	
	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {
		myAnimation = new AnimationController();
		// attach scene to the stage and display it
		myScene = setupScene(SIZE, SIZE, BACKGROUND);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
		// attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	// Create the "scene": what shapes will be drawn and their starting properties
	private Scene setupScene (int width, int height, Paint background) {
		Group root = myAnimation.createRootForAnimation(width, height);
		// create a place to see the shapes
		Scene scene = new Scene(root, width, height, background);
		// respond to input
		scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
		return scene;
	}

	// This is the method that gets called over and over and over...
	// every 15 milliseconds or so!
	private void step (double elapsedTime) {
		myAnimation.step(elapsedTime);
	}

	// What to do each time a key is pressed
	private void handleKeyInput (KeyCode code) {
		if (code == KeyCode.UP) {
			myAnimation.moverMovesVertically(true);
		}
		else if (code == KeyCode.DOWN) {
			myAnimation.moverMovesVertically(false);
		}
		else if (code == KeyCode.LEFT) {
	        myAnimation.moverMovesHorizontally(true);  
	    }
	    else if (code == KeyCode.RIGHT) {
	        myAnimation.moverMovesHorizontally(false); 
	    }
	}

	// What to do each time a key is pressed
	private void handleMouseInput (double x, double y) {
		myAnimation.handleMouseInput(x, y);
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
}
