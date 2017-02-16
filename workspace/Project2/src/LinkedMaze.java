/*
NAME:		Daniel DelyMcShane
PROJECT:	Project 2
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 8, 2016

DESCRIPTION:
LinkedMaze represents a Maze that is made up of Cell objects. 
It reads input from a txt file and represents it as a maze. It then finds
a path to solve the maze.
	*/

import java.util.Scanner;

public class LinkedMaze {

	int length, width, maxDistance;	//dimensions of maze
	CellQueue pathfinder; //Queue for traversal
	Cell entry, exit, maxCell; 
	Cell pathCell; //This is a special Cell used hold the location of the cell before exit
	Cell[][] cells; //2D array to represent maze

	/*
	 * Constructor:
	 * Initializes size of maze and the Queue for traversal
	 */
	public LinkedMaze(int rows, int columns, CellQueue pathfinder) {
		
		length = columns;
		width = rows;
		cells = new Cell[rows][columns];
		this.pathfinder = pathfinder;
		
		//Initialize maze and assign cell positions
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}//end for
		
		entry = cells[0][0]; //sets entry cell
		exit = cells[rows-1][columns-1]; //sets exit cell
	}

	/*ConnectMaze:
	 *Connects the directional link of each cell in the maze
	 *takes two scanners as an input that read integers from a txt file representing the maze
	 *Scanner ns reperesents the north and south links, Scanner ew represents the east and west links
	*/
	public void connectMaze(Scanner ns, Scanner ew) {

		//Uses a nested for to loop to each cell and set links
		for (int i = 0; i < width; i++) { 
			for (int j = 0; j < length; j++) {
				if (ew.nextInt() == 0) {
					cells[i][j].seteLink(cells[i][j + 1]); // sets the east link of the current cell to the cell over
					cells[i][j + 1].setwLink(cells[i][j]); // sets the west link of the cell to the west to the current cell 
				}
			}// end inner for
		}// end outer for

		// set north and south links
		//uses same method as east/west
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if (ns.nextInt() == 0) {
					cells[i][j].setsLink(cells[i + 1][j]);
					cells[i + 1][j].setnLink(cells[i][j]);
				}
			}//end inner for
		}// end outer for
	}// end connectMaze
	
	/*
	 * FindPath:
	 * Uses a breadth-first search algorithm to solve the maze
	 * Returns a boolean value depending on result of search
	 */
	public boolean findPath() {
		
		int maxDistance = 0;
		
		Cell current = entry; //set start cell to entry to mark as visited
		current.setVisited(true);
		pathfinder.enqueue(current); //add entry to queue

		//loops while items remain in queue. If the queue becomes empty the maze was not solved
		while (!pathfinder.isEmpty()) {
		
			//removes the next item from queue and assign it to current
			current = pathfinder.dequeue();
			pathCell = current; //Pathcell is used to store the cell before the exit, so if the maze is solved we can use it to reverse and display the path
			
			//if any of the links of the current cell are the exit then method returns true
			if (   current.getnLink() == exit || current.getsLink() == exit
				|| current.geteLink() == exit || current.getwLink() == exit) {
				return true;
			}

			//checks the north link of the current cell. 
			//If the cell isn't null and not visited then mark it visited and add it to the queue. Also sets the backlink of current to this
			if ( current.getnLink() != null && !current.getnLink().isVisited() ) {
				Cell temp = current.getnLink();
				temp.setVisited(true);
				pathfinder.enqueue(temp);
				temp.setBackLink(current);
			}

			//checks the east link of the current cell. 
			//If the cell isn't null and not visited then mark it visited and add it to the queue. Also sets the backlink of current to this
			if ( current.geteLink() != null && !current.geteLink().isVisited())
			{
				Cell temp = current.geteLink();
				temp.setVisited(true);
				pathfinder.enqueue(temp);
				temp.setBackLink(current);
				if (temp.sum() > maxDistance) {
					maxDistance = temp.sum();
				}
			}
			
			//checks the south link of the current cell. 
			//If the cell isn't null and not visited then mark it visited and add it to the queue. Also sets the backlink of current to this
			if (current.getsLink() != null && !current.getsLink().isVisited()) {
				Cell temp = current.getsLink();
				temp.setVisited(true);
				pathfinder.enqueue(temp);
				temp.setBackLink(current);

				if (temp.sum() > maxDistance) {
					maxDistance = temp.sum();
				}
			}

			//checks the west link of the current cell. 
			//If the cell isn't null and not visited then mark it visited and add it to the queue. Also sets the backlink of current to this
			if (current.getwLink() != null &&!current.getwLink().isVisited() ) {
				Cell temp = current.getwLink();
				temp.setVisited(true);
				pathfinder.enqueue(temp);
				temp.setBackLink(current);
			}

		}

		//if this point is reached then the queue is empty and no solution was found
		return false;
	}// end findPath

	/*
	 * ReversePath:
	 * Takes a Cell as the parameter that represents the head of the list
	 * uses the Cell's backlink to recursively reverse the list where start is the head\
	 * used to print maze solution math in conjunction with displayPath()
	 */
	public Cell reversePath(Cell start) {
		if (start == null || start.getBackLink() == null) {
			return start;
		}

		Cell tail = start.getTail(start); // set to tail
		Cell second = start; // to get to second to last cell

		while (second.getBackLink() != tail) // loop to second to last cell
		{
			second = second.getBackLink();
		}

		second.setBackLink(null);
		tail.setBackLink(reversePath(start));

		return tail;

	}//end ReversePath

	/*
	 * DisplayPath:
	 * Takes Cell path as a paramter and traverses the list created by its backlink
	 * prints the info of each Cell
	 */
	public void displayPath(Cell path) {
		int counter = 2;
		System.out.print("Cell 1: [" + path.getRow() + "]" + "["
				+ path.getColumn() + "]\n");
		while (path.getBackLink() != null) {
			path = path.getBackLink();
			System.out.print("Cell " + counter + ": [" + path.getRow() + "]"
					+ "[" + path.getColumn() + "]\n");
			counter++;
		}

	}
	
	//returns the pathCell which is used to display the solution path
	public Cell pathCell()
	{
		return pathCell;
	}

}
