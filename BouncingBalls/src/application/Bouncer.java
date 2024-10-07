package application;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Simple bouncer based on an image that moves and bounces.
 *
 * @author Robert C. Duvall and Shannon Duvall
 */
public class Bouncer {
    public static final int BOUNCER_MIN_SPEED = -60;
    public static final int BOUNCER_MAX_SPEED = 60;
    public static final int BOUNCER_MIN_SIZE = 20;
    public static final int BOUNCER_MAX_SIZE = 40;

    private Random dice = new Random();
    private ImageView myView;
    private Point2D myVelocity;


    /**
     * Create a bouncer from a given image with random attributes.
     */
    public Bouncer (Image image, int screenWidth, int screenHeight) {
        myView = new ImageView(image);
        // make sure it stays a circle
        int size = getRandomInRange(BOUNCER_MIN_SIZE, BOUNCER_MAX_SIZE);
        myView.setFitWidth(size);
        myView.setFitHeight(size);
        // make sure it stays within the bounds
        myView.setX(getRandomInRange(size,  screenWidth - size));
        myView.setY(getRandomInRange(size, screenHeight - size));
        // turn speed into velocity that can be updated on bounces
        myVelocity = new Point2D(getRandomInRange(BOUNCER_MIN_SPEED, BOUNCER_MAX_SPEED),
                                 getRandomInRange(BOUNCER_MIN_SPEED, BOUNCER_MAX_SPEED));
    }

    /**
     * Move by taking one step based on its velocity.
     *
     * Note, elapsedTime is used to ensure consistent speed across different machines.
     */
    public void move (double elapsedTime) {
        myView.setX(myView.getX() + myVelocity.getX() * elapsedTime);
        myView.setY(myView.getY() + myVelocity.getY() * elapsedTime);
    }

    /**
     * Bounce off the walls represented by the edges of the screen.
     */
    public void bounce (double screenWidth, double screenHeight) {
        // collide all bouncers against the walls
        if (myView.getX() < 0 || myView.getX() > screenWidth - myView.getBoundsInLocal().getWidth()) {
            myVelocity = new Point2D(-myVelocity.getX(), myVelocity.getY());
        }
        if (myView.getY() < 0 || myView.getY() > screenHeight - myView.getBoundsInLocal().getHeight()) {
            myVelocity = new Point2D(myVelocity.getX(), -myVelocity.getY());
        }
    }

    /**
     * Returns internal view of bouncer to interact with other JavaFX methods.
     */
    public Node getView () {
        return myView;
    }

    // Returns an "interesting", non-zero random value in the range (min, max)
    private int getRandomInRange (int min, int max) {
        return min + dice.nextInt(max - min) + 1;
    }
}
