package Maze;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	
    	//check if its a legal cell
    	if(x > maze.getNCols()-1 || y > maze.getNRows()-1 || x < 0 || y < 0) {
			return false;
		}
		
    	//check if the cell can be used for navigation
    	if(maze.getColor(x,y) == NON_BACKGROUND) {
    		maze.recolor(x, y, TEMPORARY);//mark the cell for no reuse
    		
    		if(x == maze.getNCols()-1 &&  y == maze.getNRows()-1) {//hit the exit
    			maze.recolor(x, y, PATH);
    			return true;
    		}
    		
    		if(findMazePath(x+1,y)) {//explore right
    			maze.recolor(x, y, PATH);
    			return true;
    		}
    		
    		if(findMazePath(x,y+1)) {//explore down
    			maze.recolor(x, y, PATH);
    			return true;
    		}
    		
    		if(findMazePath(x-1,y)) {//explore left
    			maze.recolor(x, y, PATH);
    			return true;
    		}
    		
    		if(findMazePath(x,y-1)) {//explore up
    			maze.recolor(x, y, PATH);
    			return true;
    		}

    	}
    	return false;
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    //frik stacks
    public void findMazePathArrayBased(int x,int y, ArrayList<ArrayList<PairInt>> result,ArrayList<PairInt> trace) {

    	if(x > maze.getNCols()-1 || y > maze.getNRows()-1 || 
    	   x < 0 || y < 0 ||
    	   maze.getColor(x,y) != NON_BACKGROUND) {//can probably be short circuited

    	}else if(x == maze.getNCols()-1 && y == maze.getNRows()-1) {
    		  	
    			trace.add(0, new PairInt(x,y));
    			System.out.println("found end");
    			
    			ArrayList<PairInt> path = new ArrayList<>();//this variable has to be added, i have no idea why but otherwise the scope doesn't want to work
    			path.addAll(trace);
    			Collections.reverse(path);//reverse the list because a stack gets reversed automatically
    			
    			result.add(path);
    			System.out.println("Result: " + result);
    			trace.remove(0);
		
		} else {
			
	    	maze.recolor(x, y, TEMPORARY);
	    	trace.add(0, new PairInt(x,y));
			//Explore the paths
	    		findMazePathArrayBased(x+1,y, result,trace);    	
	    		findMazePathArrayBased(x,y+1, result,trace);
	    		findMazePathArrayBased(x-1,y, result,trace);  
	    		findMazePathArrayBased(x,y-1, result,trace);   	
	    	//Remove the markets and cell that has been visited (otherwise the paths start to mix)
	    	trace.remove(0);
			maze.recolor(x, y, NON_BACKGROUND);
    	}
    	
    }
 
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        ArrayList<PairInt> trace = new ArrayList<>();
        findMazePathArrayBased(0, 0, result, trace);
        //System.out.println(result);
        return result;
    }

//relic if a non functional idea, at least this part of it worked
    
//    //path combiner?
//    
//    public ArrayList<ArrayList<PairInt>> listCombine(ArrayList<ArrayList<ArrayList<PairInt>>> arrays){ //ArrayList<ArrayList<PairInt>> y, ArrayList<ArrayList<PairInt>> z, ArrayList<ArrayList<PairInt>> g){
//    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
//    	
//    	System.out.println("0:" + arrays.get(0));
//    	System.out.println("1:" + arrays.get(1));
//    	System.out.println("2:" + arrays.get(2));
//    	System.out.println("3:" + arrays.get(3));
//    	
//    	for(int i = 0; i<4; i++) {//accessing right,left,up,down
//    		if(arrays.get(i) != null) {//if the paths found the end
//    			for(int j = 0; j<arrays.get(i).size() ;j++) {
//    				
//    				result.add(arrays.get(i).get(j));
//    			}
//    		}
//    	}
//    	
//    	return result;
//
//    }
//    

    
    // ADD METHOD FOR PROBLEM 3 HERE
    	//heres the thing, problem 2 was solved "by adapting the solution of Problem 1". 
    	//Thus, using problem 2's solution will satisfy "Adapt boolean.findMazePath()"
    	// I just adapted it twice and used it to solve another problem in the process
    
    public ArrayList<PairInt> findMazePathMin(int x,int y){
    	
    	 ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	 ArrayList<PairInt> shortest = new ArrayList<>();
    	 
    	 result = findAllMazePaths(x, y);
    	 shortest = result.get(0);
    	 
    	 for(int i = 0; i < result.size(); i++) {
    		 if(shortest.size() > result.get(i).size()) {
    			 shortest = result.get(i);
    		 }
    	 }
    	 System.out.println("ShortestPath: "+ shortest);
    	 return shortest;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
