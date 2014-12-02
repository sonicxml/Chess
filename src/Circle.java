/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.util.Random;

/**
 * A basic game object displayed as a yellow circle, starting in the upper left
 * corner of the game court.
 * 
 */
public class Circle extends GameObj {

    static Random rand = new Random();
	public static final int SIZE = 20;
	public static final int INIT_POS_X = 170;
	public static final int INIT_POS_Y = 170;
	public static final int INIT_VEL_X = rand.nextInt((15 - 0) + 1) + 0;;
	public static final int INIT_VEL_Y = rand.nextInt((15 - 0) + 1) + 15;;

	public Circle(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE,
				courtWidth, courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(pos_x, pos_y, width, height);
	}

}