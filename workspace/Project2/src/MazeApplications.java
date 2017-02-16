/*
NAME:		Daniel DelyMcShane
PROJECT:	Project 2
COURSE:	CS 260
INSTRUCTOR:	Professor Petruska

DUE DATE:	November 8, 2016

DESCRIPTION:
MazeApplications demonstrates the LinkedMaze class by solving a maze. Both a solvable and unsolvable maze are
used to test both cases
	*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MazeApplications {

	public static void main(String[] args) throws FileNotFoundException {

//********Switch files below to test for true/false cases*************
		//File fns = new File("TrueNS.txt");
		//File few = new File("TrueEW.txt");
		File fns = new File("FalseNS.txt");
		File few = new File("FalseEW.txt");

		
		
		Scanner ns = new Scanner(fns);
		Scanner ew = new Scanner(few);
		
		CellQueue pathFinder = new CellQueue(100);
		int rows =  ew.nextInt() ;
		int columns = ew.nextInt();
		LinkedMaze maze = new LinkedMaze(rows, columns, pathFinder);
		maze.connectMaze(ns, ew);
		boolean flag = maze.findPath();
		//if a path was found print result
		if(flag){
			Cell p = maze.pathCell();
			Cell reversed = maze.reversePath(p);
			maze.displayPath(reversed);
		}
		else
		{
			System.out.println("A solution was not found");
		}
		
		
	}
	

}