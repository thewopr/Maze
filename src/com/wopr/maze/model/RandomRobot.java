package com.wopr.maze.model;

import java.util.Random;

public class RandomRobot extends RobotModel {
	
	private Random r;
	
	public RandomRobot(RobotModel r) {
		super(r);
		this.r = new Random();
	}

	public void move(MazeModel m) {

		m.setXY(getX(), getY(), " ");

		do {
			setDirection(Direction.values()[r.nextInt(4)]);
		} while (!m.isValid(getX() + dx, getY() + dy));

		System.out.println(nextMove());

		moveForward();

		if (m.isExitLocation(getX(), getY()))
			isFinished = true;

		m.setXY(getX(), getY(), toString());
	}

}