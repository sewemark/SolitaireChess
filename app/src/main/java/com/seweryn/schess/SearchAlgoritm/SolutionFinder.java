package com.seweryn.schess.SearchAlgoritm;

/**
 * Created by sew on 2016-01-03.
 */
public class SolutionFinder {
    public static ISearchTree findSolution(ISearchTree _tree){
       try {
           ISearchTree tree = _tree;
           //int solutionNumber = handler.DFSSearch();
           ThreadGroup group = new ThreadGroup("threadGroup");
           Thread t = new Thread(group, tree, "YourThreadName", 100000000000L);

           t.start();
           t.join();
           return  tree;
       }
        catch (Exception ex){
            System.out.println(ex);
        }
        return null;

    }
}
