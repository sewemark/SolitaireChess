package com.seweryn.schess;

/**
 * Created by sew on 2016-01-03.
 */
public class SolutionFinder {
    public static LinkedListHandler findSolution(int[][] board){
       try {
           LinkedListHandler handler = new LinkedListHandler(board);
           //int solutionNumber = handler.DFSSearch();
           ThreadGroup group = new ThreadGroup("threadGroup");
           Thread t = new Thread(group, handler, "YourThreadName", 2000000);
           t.start();
           t.join();
           return  handler;
       }
        catch (Exception ex){}
        return null;

    }
}
