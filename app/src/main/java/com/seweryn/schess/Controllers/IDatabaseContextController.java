package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.PuzzleType;

/**
 * Created by sew on 2016-01-10.
 */
public interface IDatabaseContextController {
     int[][] read();
     void save(int[][] t1);
     String[] getPuzzleList();
     String[] getPuzzleListByType(PuzzleType t1);
}
