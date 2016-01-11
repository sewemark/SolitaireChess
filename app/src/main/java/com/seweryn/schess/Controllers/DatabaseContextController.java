package com.seweryn.schess.Controllers;

import android.content.Context;

import com.seweryn.schess.DAL.DatabaseHandler;
import com.seweryn.schess.Enums.PuzzleType;

/**
 * Created by sew on 2016-01-10.
 */
public class DatabaseContextController implements IDatabaseContextController {

    private DatabaseHandler handler;
    public DatabaseContextController(Context context){
        handler = new DatabaseHandler(context);
    }
    @Override
    public int[][] read() {
        return new int[0][];
    }

    @Override
    public void save(int[][] t1) {

    }

    @Override
    public String[] getPuzzleList() {
        return new String[0];
    }

    @Override
    public String[] getPuzzleListByType(PuzzleType puzzleType) {
        return handler.getPuzzleListByType(puzzleType);
    }
}
