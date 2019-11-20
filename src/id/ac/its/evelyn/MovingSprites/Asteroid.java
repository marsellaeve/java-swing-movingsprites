package id.ac.its.evelyn.MovingSprites;
import java.util.Random;
public class Asteroid extends Sprite {
	private final int BOARD_LENGTH = 300;
    private final int ASTEROID_SPEED_X;
    private final int ASTEROID_SPEED_Y;

    public Asteroid(int x, int y) {
        super(x, y);
        ASTEROID_SPEED_X = new Random().nextInt(3)+1;
        ASTEROID_SPEED_Y = new Random().nextInt(2)==0?-1:1;
        initAsteroid();
    }
    
    private void initAsteroid() {
        
        loadImage("image/meteor.png");  
        getImageDimensions();
    }

    public void move() {
        
        x -= ASTEROID_SPEED_X;
        y += ASTEROID_SPEED_Y;
        if (x < 0) {
            visible = false;
        }
        if (y > BOARD_LENGTH || y < 0) {
            visible = false;
        }
    }
}