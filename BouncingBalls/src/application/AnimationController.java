//Anthony Mozloom

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * 
 * @author Shannon Duvall
 * 
 * This program animates two squares.  The top is the "mover" and 
 * the bottom is the "grower".
 * 
 * Changes for you to make in the code:
 * 
 * 1. Currently the mover is rotating counter-clockwise.  Change the code so the grower
 * 	  rotates in the opposite direction (clockwise).
 * 2. If the mover and grower ever intersect, the mover should change color to be HIGHLIGHT,
 *    and change back to the MOVER_COLOR if not intersecting.
 * 3. If any of the bouncing blue balls intersect the grower, the grower should change color
 *    to be HIGHLIGHT, and change back when not intersecting.
 * 4. The mover can currently only move up and down.  Enable left and right movement with arrow keys.
 * 	  This will require changing Main as well as this class.
 * 5. The grower currently only grows horizontally, changing the square to a rectangle.  Change
 *    this to grow in both directions equally so the square shape is preserved.
 */


public class AnimationController {

	public static final String BOUNCER_IMAGE = "resources/ball.gif";
	public static final Paint MOVER_COLOR = Color.PLUM;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
	public static final int MOVER_SIZE = 50;
	public static final int MOVER_SPEED = 5;
	public static final Paint GROWER_COLOR = Color.BISQUE;
	public static final double GROWER_RATE = 1.1;
	public static final int GROWER_SIZE = 50;
	public static final int NUM_BOUNCERS = 5;

	
	private List<Bouncer> myBouncers;
	private Rectangle myMover;
	private Rectangle myGrower;
	private int width;
	private int height;

	public Group createRootForAnimation(int windowWidth, int windowHeight) {
		width = windowWidth;
		height = windowHeight;

		// create one top level collection to organize the things in the scene
		Group root = new Group();
		// make some shapes and set their properties
		try {
			Image image = new Image(new FileInputStream(BOUNCER_IMAGE));
			myBouncers = new ArrayList<>();
			for (int k = 0; k < NUM_BOUNCERS; k++) {
				Bouncer b = new Bouncer(image, width, height);
				myBouncers.add(b);
				root.getChildren().add(b.getView());
			}
		}
		catch (FileNotFoundException e) {}
		myMover = new Rectangle(width / 2 - MOVER_SIZE / 2, height / 2 - 100, MOVER_SIZE, MOVER_SIZE);
		myMover.setFill(MOVER_COLOR);
		myGrower = new Rectangle(width / 2 - GROWER_SIZE / 2, height / 2 + 50, GROWER_SIZE, GROWER_SIZE);
		myGrower.setFill(GROWER_COLOR);
		// order added to the group is the order in which they are drawn
		root.getChildren().add(myMover);
		root.getChildren().add(myGrower);
		return root;
	}

	public void step (double elapsedTime) {
		// update "actors" attributes
		for (Bouncer b : myBouncers) {
			b.move(elapsedTime);
		}
		myMover.setRotate(myMover.getRotate() - 1);
		myGrower.setRotate(myGrower.getRotate() + 1);


		// check for collisions
		// with shapes, can check precisely
		Shape intersection = Shape.intersect(myMover, myGrower);
		if (intersection.getBoundsInLocal().getWidth() != -1) {
		    myMover.setFill(HIGHLIGHT);  
		} else {
		    myMover.setFill(MOVER_COLOR);  
		}

		// with images can only check bounding box
		boolean hit = false;
		for (Bouncer b : myBouncers) {
		    if (myGrower.getBoundsInParent().intersects(b.getView().getBoundsInParent())) {
		        hit = true;
		        myGrower.setFill(HIGHLIGHT);  
		    }
		}
		if (!hit) {
		    myGrower.setFill(GROWER_COLOR);  
		}

		// bounce off all the walls
		for (Bouncer b : myBouncers) {
			b.bounce(width, height);
		}
	}

	// What to do each time a key is pressed
	public void moverMovesVertically (boolean goUp) {
		if(goUp)
			myMover.setY(myMover.getY() - MOVER_SPEED);

		else {
			myMover.setY(myMover.getY() + MOVER_SPEED);
		}
	}
	
	public void moverMovesHorizontally (boolean goLeft) {
	    if (goLeft)
	        myMover.setX(myMover.getX() - MOVER_SPEED);
	    else
	        myMover.setX(myMover.getX() + MOVER_SPEED);
	}


	public void handleMouseInput (double x, double y) {
		if (myGrower.contains(x, y)) {
			myGrower.setScaleX(myGrower.getScaleX() * GROWER_RATE);
			myGrower.setScaleY(myGrower.getScaleY() * GROWER_RATE);

		}
	}
}
