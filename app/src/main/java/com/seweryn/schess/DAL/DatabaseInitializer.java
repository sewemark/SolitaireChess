package com.seweryn.schess.DAL;

import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 23/02/2016.
 */
public class DatabaseInitializer implements IDatabaseInitializer {
    public IDatabaseContext dbContext;
    public DatabaseInitializer(IDatabaseContext _dbContext){
      this.dbContext =_dbContext;
    }
    /**
     * method that creates and saves custom puzzles
     * */
    public void initializaDataBase(){
        InitializeEasyPuzzleDataBase();
        InitializeMediumPuzzleDataBase();
        InitializeHardPuzzleDataBase();
        InitializeVeryHardPuzzleDataBase();
    }
    /**
     * method that creates and saves custom easy puzzles
     * */
    private void InitializeEasyPuzzleDataBase(){
        int[][] board01 = {{0, 3, 0, 0},
                {0, 6, 3, 5},
                {0, 0, 0, 0},
                {5, 0 , 0, 0}};
        int[][] board02 = {{0, 2, 0, 5},
                {0, 5, 0, 0},
                {3, 0, 2, 0},
                {0, 0 , 0, 0}};
        int[][] board03 = {{1, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 5, 0, 0},
                {3, 0 , 0, 0}};
        int[][] board04 = {{0, 5, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 5, 0},
                {3, 0 , 0, 0}};
        int[][] board05 = {{0, 4, 0, 0},
                {0, 1, 0, 0},
                {3, 0, 1, 0},
                {0, 0 , 0, 0}};
        dbContext.savePuzzle(PuzzleType.EASY, board01);
        dbContext.savePuzzle(PuzzleType.EASY, board02);
        dbContext.savePuzzle(PuzzleType.EASY, board03);
        dbContext.savePuzzle(PuzzleType.EASY, board04);
        dbContext. savePuzzle(PuzzleType.EASY, board05);
    }
    /**
     * method that creates and saves custom medium puzzles
     * */
    private void InitializeMediumPuzzleDataBase(){
        int[][] board01 = {{0, 0, 2, 0},
                {5, 0, 0, 3},
                {0, 0, 3, 0},
                {5, 2 , 0, 0}};
        int[][] board02 = {{0, 5, 0, 0},
                {0, 4, 3, 4},
                {3, 0, 5, 0},
                {0, 0 , 0, 0}};
        int[][] board03 = {{0, 0, 5, 0},
                {0, 4, 0, 0},
                {3, 4, 5, 0},
                {3, 0 , 0, 0}};
        int[][] board04 = {{0, 5, 0, 0},
                {0, 0, 3, 0},
                {0, 0, 4, 0},
                {5, 2 , 0, 5}};
        int[][] board05 = {{2, 0, 0, 0},
                {6, 0, 0, 4},
                {0, 3, 0, 0},
                {0, 5, 3, 0}};
        dbContext.savePuzzle(PuzzleType.MEDIUM, board01);
        dbContext.savePuzzle(PuzzleType.MEDIUM, board02);
        dbContext.savePuzzle(PuzzleType.MEDIUM, board03);
        dbContext.savePuzzle(PuzzleType.MEDIUM, board04);
        dbContext.savePuzzle(PuzzleType.MEDIUM,board05);
    }
    /**
     * method that creates and saves custom hard puzzles
     * */
    private void InitializeHardPuzzleDataBase(){
        int[][] board01 = {{2, 0, 0, 0},
                {0, 3, 5, 4},
                {0, 0, 3, 2},
                {4, 0 , 0, 0}};
        int[][] board02 = {{0, 0, 4, 0},
                {0, 4, 1, 0},
                {5, 5, 0, 3},
                {3, 0 , 0, 0}};
        int[][] board03 = {{0, 0, 0, 2},
                {0, 2, 3, 0},
                {4, 0, 0, 5},
                {0, 3 , 4, 0}};
        int[][] board04 = {{0, 0, 2, 4},
                {0, 0, 0, 3},
                {4, 5, 0, 0},
                {3, 0 , 2, 0}};
        int[][] board05 = {{0, 0, 0, 4},
                {0, 0, 0, 2},
                {2, 5, 5, 0},
                {3, 3 , 0, 0}};
        dbContext.savePuzzle(PuzzleType.HARD, board01);
        dbContext.savePuzzle(PuzzleType.HARD, board02);
        dbContext.savePuzzle(PuzzleType.HARD, board03);
        dbContext.savePuzzle(PuzzleType.HARD, board04);
        dbContext. savePuzzle(PuzzleType.HARD, board05);
    }
    /**
     * method that creates and saves custom vary hard puzzles
     * */
    private void InitializeVeryHardPuzzleDataBase(){
        int[][] board01 = {{0, 0, 0, 2},
                {2, 4, 3, 0},
                {1, 0, 0, 0},
                {5, 3 , 3, 0}};
        int[][] board02 = {{0, 4, 5, 5},
                {0, 4, 3, 3},
                {0, 2, 0, 0},
                {2, 0 , 0, 0}};
        int[][] board03 = {{0, 3,5, 0},
                {0, 3, 5, 0},
                {4, 0, 0, 4},
                {2, 0 , 0, 2}};
        int[][] board04 = {{0, 5, 4, 5},
                {0,4, 3, 2},
                {0, 0, 0, 3},
                {2, 0 , 0, 0}};
        int[][] board05 = {{0, 5, 4, 5},
                {0,4, 3, 2},
                {0, 0, 0, 3},
                {2, 0 , 0, 0}};
        dbContext.savePuzzle(PuzzleType.VERYHARD, board01);
        dbContext.savePuzzle(PuzzleType.VERYHARD, board02);
        dbContext.savePuzzle(PuzzleType.VERYHARD, board03);
        dbContext.savePuzzle(PuzzleType.VERYHARD, board04);
        dbContext.savePuzzle(PuzzleType.VERYHARD, board05);
    }
}
