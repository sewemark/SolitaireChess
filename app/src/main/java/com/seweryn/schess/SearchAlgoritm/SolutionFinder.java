package com.seweryn.schess.SearchAlgoritm;

/**
 * Created by sew on 2016-01-03.
 */
public class SolutionFinder {
    /**
     * method that creates a separate Thread where a tree search take place
     * @param  ISearchTree  interface that implements runnable
     * @return   ISearchTree returns search Tree
     * */
    public static ISearchTree findSolution(ISearchTree _tree){
       try {
           ISearchTree tree = _tree;
           ThreadGroup group = new ThreadGroup("ThreadGroup");
           Thread t = new Thread(group, tree, "SolutionFinderThread", 100000000000L);

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
