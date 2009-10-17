package com.wopr.maze.model;

public class MazeModel extends AbstractModel {
	public String textMaze;

	public int height, width;

	public double zoom = 1.5;

	public boolean DEBUG = false;

	public RobotModel rob;

	private final String VALID_ROBOT_CHARS = "> V v < ^";

	private int[][] m;
	
	public static final int WALL = -1;
	public static final int BLANK = 0;
	
	/**
	 * @param file
	 *            that you want the maze to be initialized with eg. "maze2.txt"
	 */
	public MazeModel(String file) {

		textMaze = "";
		String maze = "";

		parseMaze(maze);
	}

	/**
	 * A method for checking whether or not a char is a robot or not
	 * 
	 * @param c
	 *            is the char that you are checking
	 * @return returns true if it is a possible robot, accounts for all
	 *         directions
	 */
	private boolean isARobotChar(String s) {
		return VALID_ROBOT_CHARS.contains(s);
	}

	public boolean isExitLocation(int x, int y) {
		boolean flag = false;

		if ((x == 0 || x == width - 1 || y == 0 || y == height - 1))
			flag = (getXY(x, y) == ' ');
		return flag;
	}

	/**
	 * Given unformatted text of a maze. parseMaze will take the necessary
	 * meausurements, width and height and also initialize the robot that will
	 * be currently running the maze
	 * 
	 * @param s
	 *            the unformatted text maze
	 */
	private void parseMaze(String s) {

		StringTokenizer st = new StringTokenizer(s, "\n");

		height = 0;

		while (st.hasMoreTokens()) {
			textMaze += st.nextToken() + "\n";
			height++;
		}
		width = textMaze.indexOf("\n") + 1;
		rob = new RandomRobot(getStartingRobotInfo());
	}

	/**
	 * Extracts the starting info of the current robot in the maze.
	 * 
	 * @return returns a robot that represent the location and direction of the
	 *         starting robot in the maze
	 */
	private RobotModel getStartingRobotInfo() {
		RobotModel r = new RobotModel();

		for (int i = 0; i < textMaze.length(); i++)
			if (isARobotChar(String.valueOf(textMaze.charAt(i)))) {
				r.setDirection(String.valueOf(textMaze.charAt(i) + ""));
				r.setX(i % width);
				r.setY((i - r.getX()) / width);
			}
		return r;
	}

	/**
	 * Method for debugging that prints the current neighbors of the robot
	 * 
	 * @return a string representation of the current neighbors of the robot
	 */

	public String printNeighbors() {

		String s = rob.nextMove() + "\n";

		s += "To the forward is " + getXY(rob.forwardLocation()) + "\n";
		s += "To the right is " + getXY(rob.rightLocation()) + "\n";
		s += "To the left is " + getXY(rob.leftLocation()) + "\n";

		return s;
	}

	/**
	 * A method to set an (x,y) location in the maze to a given String
	 * 
	 * @param x
	 *            the x value location 0 counted
	 * @param y
	 *            the y value location 0 counted
	 * @param s
	 *            the String that is going to be inserted into the maze
	 */
	private void setXY(int x, int y, int change) {
		if (x < 0 || x > width || y < 0 || y > height)
			System.err.println("setXY is being used incorrectly");
		
		m[x][y] = change;
	}

	/**
	 * A method that returns the char at a given location (x,y)
	 * 
	 * @param x
	 *            the x value location 0 counted
	 * @param y
	 *            the y value location 0 counted
	 * @return the char at location (x,y)
	 */
	public int getXY(int x, int y) {
		return m[x][y];
	}

	/**
	 * A method that returns the char at a given location (Point)
	 * 
	 * @param p
	 *            the location that you wish to retrieve the char from
	 * @return the char at the location (Point)
	 */
	public int getXY(Point p) {
		return m[p.x][p.y];
	}

	/**
	 * A method for debugging that prints out info about the maze and the
	 * contained robot
	 * 
	 * @return a String of info about the maze and the containing robot
	 */
	public String printVerbose() {
		String s = "";
		s = "<Maze>\n" + width + "x" + height + "\n" + "Rob is at ("
				+ rob.getX() + "," + rob.getY() + ")";
		return s;
	}

	/**
	 * A method to determine whether a given location (x,y) is valid to move to
	 * or not
	 * 
	 * @param x
	 *            the x location 0 counted
	 * @param y
	 *            the y location 0 counted
	 * @return true if space is not a wall
	 */
	public boolean isValid(int x, int y) {
		return getXY(x,y) == MazeModel.BLANK;
	}

	/**
	 * A method to determine whether a given location (Point) is valid to move
	 * to or not
	 * 
	 * @param p
	 * @return true if given location is not a wall
	 */
	public boolean isValid(Point p) {
		return getXY(p) == MazeModel.BLANK;
	}

}
