package com.seweryn.schess.Controllers;

import android.content.Context;

import com.seweryn.schess.DAL.DatabaseContext;
import com.seweryn.schess.DAL.DatabaseInfo;
import com.seweryn.schess.DAL.DatabaseInitializer;
import com.seweryn.schess.DAL.DatabaseManager;
import com.seweryn.schess.DAL.IDatabaseContext;
import com.seweryn.schess.DAL.IDatabaseInfo;
import com.seweryn.schess.DAL.IDatabaseInitializer;
import com.seweryn.schess.DAL.IDatabaseManager;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 2016-01-10.
 */
public class DatabaseContextController implements IDatabaseContextController {

    private IDatabaseInfo databaeInfo;
    private IDatabaseContext dbContext;
    private IDatabaseInitializer databaseInitializer;
    private IDatabaseManager databaseManager;
    /**
     * updates database
     * @param  databaseObject source position a piece
     * */
    @Override
    public void update(DatabaseObject databaseObject) {
        dbContext.updatePuzzle(databaseObject);
    }

    /**
     * resets database and initializes newly crated database
     * */
    @Override
    public void resetDatabase() {
        databaseManager.resetDatabase();
        databaseInitializer.initializaDataBase();
    }
    public DatabaseContextController(Context context){
        InjectDALComponents(context);
        DatabaseSetUp();
    }
    /**
     * creates database if it dose not exist and initlilizes it with custom puzzles
     * */
    private void DatabaseSetUp() {
        databaseManager.createDatabaseIfNotExists();
        if(databaseManager.wasCreated()){
                databaseInitializer.initializaDataBase();
        }
    }
    /**
     * injects proper instance of DAL components classes
     * @param  context application context
     * */
    private void InjectDALComponents(Context context) {
        databaeInfo= new DatabaseInfo(context);
        dbContext = new DatabaseContext(context,databaeInfo);
        databaseInitializer = new DatabaseInitializer(dbContext);
        databaseManager  =  new DatabaseManager(databaeInfo);
    }

    @Override
    /**
     * reads dabase object rom database
     * @param  puzzleType puzzle type to be read
     * @param fileName to be read
     * */
    public DatabaseObject read(PuzzleType puzzleType, String fileName) {

       return dbContext.readPuzzle(puzzleType,fileName);
    }

    /**
     * save puzzle to dabase
     * @param  puzzleType puzzle type to be read
     * @param board puzzle board to be saved
     * */
    @Override
    public void save(PuzzleType puzzleType, int[][] board) {
            dbContext.savePuzzle(puzzleType,board);
    }

    /**
     * returns list of puzzle by puzzle type
     * @param  puzzleType puzzle type to be read
     * @return string array with puzzle' name
     * */
    @Override
    public String[] getPuzzleListByType(PuzzleType puzzleType) {
        return dbContext.getPuzzleListByType(puzzleType);
    }
    /**
     * returns list of database objects
     * @param  puzzleType puzzle type to be read
     * @return database object array
     * */
    @Override
    public DatabaseObject[] getPuzzleObjectByType(PuzzleType puzzleType) {
        return dbContext.getPuzzleObjectByType(puzzleType);
    }
    /**
     * returns database object that is previous puzzle to particular puzzle
     * @param  puzzleType puzzle type
     * @param  boardName name of the board
     * @return string array with puzzle' name
     * */
    @Override
    public DatabaseObject readNextPuzzle(PuzzleType puzzleType, String boardName) {
        return dbContext.readNextOrPreviousPuzzle(puzzleType, boardName, 1);
    }
    /**
     * returns database object that is next puzzle to particular puzzle
     * @param  puzzleType puzzle type
     * @param  boardName name of the board
     * @return string array with puzzle' name
     * */
    @Override
    public DatabaseObject readPreviousPuzzle(PuzzleType puzzleType, String boardName) {
        return dbContext.readNextOrPreviousPuzzle(puzzleType, boardName, -1);
    }
}
