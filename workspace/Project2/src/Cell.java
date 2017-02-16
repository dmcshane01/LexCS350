/*
NAME:		Daniel DelyMcShane
PROJECT:	Project 2
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 8, 2016

DESCRIPTION:
Cell is a super node that contains 5 links to other cells. It has directional links as
well as a link that points to the cell that proceeds it. The cells are used to make up a maze
	*/

public class Cell {

	//row and column contain the position of the cell in the maze
	private int row;
	private int column;
	//directional links of the cell
	private Cell nLink, eLink, sLink, wLink, backLink; // null if not passable
	private boolean visited; //flag to determine if cell has been visited in maze path finder algorithm

	//Constructor intializes the row and column of the cell
	public Cell(int row, int column) // coordinates for cell in array
	{
		this.visited = false;
		this.row = row;
		this.column = column;
	}

	//determines if cell a and b are linked by checking all the links of both cells
	//static method
	public static boolean linked(Cell a, Cell b) {
		return a.nLink == b || a.sLink == b || a.eLink == b || a.wLink == b
				|| b.nLink == a || b.sLink == a || b.eLink == a || b.wLink == a;
	}

	//return string representation of position in maze
	public String toString() {
		return "Row: " + row + " Column: " + column;
	}

	//sum is a helper method that returns the sum of the row and column of the calling cell
	public int sum() {
		return row + column;
	}

	/*
	 * This is a helper method not required in the project specification
	 * getTail is used only by the reversePath method of LinkedMaze
	 * This is used to get the last cell in a linked list of cell
	 * Used to recursively reverse a linked list of cells
	 */
	public Cell getTail(Cell head) {
		while (head.getBackLink() != null) {
			head = head.getBackLink();
		}

		return head;
	}

	/* ==========Getters and Setters below============ */
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setnLink(Cell nLink) {
		this.nLink = nLink;
	}

	public void seteLink(Cell eLink) {
		this.eLink = eLink;
	}

	public void setsLink(Cell sLink) {
		this.sLink = sLink;
	}

	public void setwLink(Cell wLink) {
		this.wLink = wLink;
	}

	public void setBackLink(Cell backLink) {
		this.backLink = backLink;
	}

	public boolean isVisited() {
		return visited;
	}

	public Cell getnLink() {
		return nLink;
	}

	public Cell geteLink() {
		return eLink;
	}

	public Cell getsLink() {
		return sLink;
	}

	public Cell getwLink() {
		return wLink;
	}

	public Cell getBackLink() {
		return backLink;
	}

}
