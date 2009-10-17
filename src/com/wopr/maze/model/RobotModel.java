package com.wopr.maze.model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class RobotModel extends AbstractModel {

	int x, y;
	Direction dir;
	int dx, dy;
	boolean isFinished = false;

	String textRobot;
	Image robotImages[];

	public RobotModel(int x, int y) {
		this();		
		this.x = x;
		this.y = y;
	}

	public RobotModel() {
		this.x = 0;
		this.y = 0;
		dir = Direction.RIGHT;
		textRobot = "^";
		robotImages = new Image[4];
		loadImages();
	}

	private void loadImages() {
		for (int i = 0; i < 4; i++)
			robotImages[i] = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(
					"img/robot" + i + ".gif"));
	}

	public RobotModel(RobotModel r) {
		this.x = r.getX();
		this.y = r.getY();
		this.dx = r.dx;
		this.dy = r.dy;
		this.dir = r.dir;
		this.textRobot = r.textRobot;
		robotImages = new Image[4];
		loadImages();
	}

	public Point forwardLocation() {
		return new Point(getX() + dx, getY() + dy);
	}

	public Point rightLocation() {
		if (dx == 0)
			return new Point(getX() - dy, getY());
		else
			return new Point(getX(), getY() + dx);
	}

	public Point leftLocation() {
		if (dx == 0)
			return new Point(getX() + dy, getY());
		else
			return new Point(getX(), getY() - dx);
	}

	public String toString() {
		return textRobot;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	public void setDirection(String s) {
		
		if (s.equals(">"))
			setRight();
		else if (s.equals("v"))
			setDown();
		else if (s.equals("<")) 
			setLeft();
		else if (s.equals("^"))
			setUp();
		
		System.err.println("Bad input!");
	}

	private void setRight() {
		dir = Direction.RIGHT;
		dx = 1;
		dy = 0;
		textRobot = ">";
	}
	
	private void setUp() {
		dir = Direction.UP;
		dx = 0;
		dy = -1;
		textRobot = "^";
	}

	private void setLeft() {
		dir = Direction.LEFT;
		dx = -1;
		dy = 0;
		textRobot = "<";
	}

	private void setDown() {
		dir = Direction.DOWN;
		dx = 0;
		dy = 1;
		textRobot = "v";
	}

	public void setDirection(Direction d) {
		
		if (d == Direction.RIGHT)
			setRight();
		
		if (d == Direction.DOWN) 
			setDown();
		
		if (d == Direction.LEFT) 
			setLeft();
		
		if (d == Direction.UP) 
			setUp();

		System.err.println("Bad input!");
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void spinClockwise() {
		setDirection(Direction.values()[(dir.ordinal() + 1) % 4]);
	}

	public void spinCounterClockwise() {
		setDirection(Direction.values()[(dir.ordinal() + 3) % 4]);
	}

	public void spinAround() {
		spinClockwise();
		spinClockwise();
	}

	public String nextMove() {
		String s = "";
		s = "Rob at (" + getX() + "," + getY() + ") moves " + toString()
				+ " to ";
		s += "(" + (getX() + dx) + "," + (getY() + dy) + ")";
		return s;
	}

	public void moveForward() {
		setX(getX() + dx);
		setY(getY() + dy);
	}

	public Image getRealImage() {
		return robotImages[dir.ordinal()];
	}

}
