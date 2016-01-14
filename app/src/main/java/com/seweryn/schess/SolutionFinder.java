package com.seweryn.schess;

/**
 * Created by sew on 2016-01-03.
 */
public class SolutionFinder {
    public static ISearchTree findSolution(ISearchTree _tree){
       try {
           ISearchTree tree = _tree;
           //int solutionNumber = handler.DFSSearch();
           ThreadGroup group = new ThreadGroup("threadGroup");
           Thread t = new Thread(group, tree, "YourThreadName", 2000000);
           t.start();
           t.join();
           return  tree;
       }
        catch (Exception ex){}
        return null;

    }
}
