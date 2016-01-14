package com.seweryn.schess;

import java.util.List;

/**
 * Created by sew on 2016-01-11.
 */
public interface ISearchTree<T>  extends Runnable {
    int Search();
    int getNumberOfResults();
    int getTreeWidth();
    int getTreeHeight();
    int getTreeLeaves();
    List<T> getSolutoins();

}
