package com.seweryn.schess.DAL;

import android.content.Context;

import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.DFSTree;
import com.seweryn.schess.ISearchTree;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.SolutionFinder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by sew on 2015-11-23.
 */
public class DatabaseHandler {
    public DatabaseHandler(Context _context) {
        this.context = _context;
        rootPath = this.context.getCacheDir().toString();
        this.CreateDatabaseIfNotExists();
    }
    private Context context;
    private String rootPath;
    private final String[] puzzleDirectories ={"EASY","MEDIUM","HARD","VERYHARD"};
    private final String rootDirectory ="puzzles68";

    public  void CreateDatabaseIfNotExists() {

        File parrentDir = new File(context.getCacheDir() + "/"  + rootDirectory);

        if (!parrentDir.exists()) {
            parrentDir.mkdir();

            for (int i = 0; i < puzzleDirectories.length; i++) {
                File childDir = new File(context.getCacheDir() + "/" + rootDirectory + "/" + puzzleDirectories[i]);
                if (!childDir.exists()) {
                    childDir.mkdir();
                }

            }
            InitializaDataBase();
        }

      }
    public void updatePuzzle(DatabaseObject objectToUpdate){
        try{
            File fileToUpdate = new File(context.getCacheDir() + "/" + rootDirectory + "/" + objectToUpdate.getPuzzleType().toString() + "/"  + objectToUpdate.getFileName());

            FileOutputStream fos = new FileOutputStream(fileToUpdate);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectToUpdate);
            oos.close();
            fos.close();
        }
        catch(Exception ex){

        }
    }
     public  void savePuzzle(PuzzleType puzleType, int[][] puzzleToSave ) {
         try{
             File parrentDir = new File(context.getCacheDir() +  "/" +  rootDirectory + "/" + puzleType.toString());
             String fileName = String.valueOf(parrentDir.list().length +1) + puzleType.toString().charAt(0);
             File fileToCreate = new File(context.getCacheDir() + "/" + rootDirectory + "/" + puzleType.toString() + "/"  + fileName);
             if(!fileToCreate.exists()){
                 fileToCreate.createNewFile();
             }
             ISearchTree handler= SolutionFinder.findSolution(new DFSTree(puzzleToSave));
             DatabaseObject objectToSave = new DatabaseObject(puzzleToSave,handler.getSolutoins(),puzleType,fileName);

             FileOutputStream fos = new FileOutputStream(fileToCreate);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(objectToSave);
             oos.close();
             fos.close();
             String [] pliki = parrentDir.list();
         }
         catch (Exception ex){

         }
     }
    public DatabaseObject readNextOrPreviousPuzzle(PuzzleType puzzleType, String fileName,int prevOrNext){
        char puzzleTypeName = fileName.charAt(fileName.length()-1);
        String nextfileName = String.valueOf(Integer.valueOf(fileName.substring(0, fileName.length() - 1)) +prevOrNext) + puzzleTypeName;
        File fileToRead = new File(context.getCacheDir() + "/" + rootDirectory + "/" + puzzleType.toString() + "/"  + nextfileName);
        if(fileToRead.exists()){
            return readPuzzle(puzzleType, nextfileName);
        }
        else
        {
            if(prevOrNext==1) {
             PuzzleType type = getPuzzleType(puzzleType, prevOrNext);
                int ia=0;
                return readPuzzle(getPuzzleType(puzzleType, prevOrNext), getPuzzleListByType(getPuzzleType(puzzleType, prevOrNext))[0]);
            }
                else
                return  readPuzzle(getPuzzleType(puzzleType,prevOrNext),getPuzzleListByType(getPuzzleType(puzzleType,prevOrNext))[0]);

        }

    }
    private PuzzleType getPuzzleType(PuzzleType puzzleType, int prevOrNext){
        for(int i=0;i<PuzzleType.values().length;i++){
            if(PuzzleType.values()[i]==puzzleType){
                if(( (i+prevOrNext) <puzzleType.values().length) && ((i+prevOrNext) >=0)){
                    PuzzleType type =  PuzzleType.values()[i+prevOrNext];
                    return type;
                }
            }
        }
        return PuzzleType.values()[0];

    }
    public  DatabaseObject readPuzzle(PuzzleType puzzleType, String fileName ) {
        try{
            File fileToRead = new File(context.getCacheDir() + "/" + rootDirectory + "/" + puzzleType.toString() + "/"  + fileName);
            FileInputStream fis = new FileInputStream(fileToRead);
            ObjectInputStream iis = new ObjectInputStream(fis);
            DatabaseObject databaseObject= (DatabaseObject)iis.readObject();
            return  databaseObject;
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return  null;
    }
    public DatabaseObject[] getPuzzleObjecyByType(PuzzleType puzzleType){


        String[] puzzleByTypeNames = this.getPuzzleListByType(puzzleType);
        DatabaseObject[] databaseObjects= new DatabaseObject[puzzleByTypeNames.length];
        for(int i=0;i<puzzleByTypeNames.length;i++){
            databaseObjects[i] = this.readPuzzle(puzzleType,puzzleByTypeNames[i]);
            //if(databaseObjects[i]!=null)
              //  databaseObjects[i].resetBoard();
        }
        return  databaseObjects;


    }
    public  String[] getPuzzleListByType(PuzzleType puzzleType){
        File parrentDir = new File(context.getCacheDir().toString() + '/' + rootDirectory + '/' + puzzleType.toString());
        if(parrentDir.exists())
            return  parrentDir.list();
        else
            return  new String[]{};
    }
    public void InitializaDataBase(){
        InitializeEasyPuzzleDataBase();
        InitializeMediumPuzzleDataBase();
    }
    public void InitializeEasyPuzzleDataBase(){
        int[][] board01 = {{0, 0, 0, 2},
                {4, 0, 0, 0},
                {0, 6, 0, 0},
                {0, 0 , 4, 0}};
         int[][] board02 = {{0, 0, 5, 0},
                {4, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0 , 2, 0}};
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
        savePuzzle(PuzzleType.EASY,board01);
        savePuzzle(PuzzleType.EASY,board02);
        savePuzzle(PuzzleType.EASY,board03);
        savePuzzle(PuzzleType.EASY,board04);
        savePuzzle(PuzzleType.EASY, board05);
    }
    public void InitializeMediumPuzzleDataBase(){
        int[][] board01 = {{0, 4, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 1, 4},
                {1, 0 , 0, 0}};
        int[][] board02 = {{0, 0, 5, 0},
                {4, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0 , 2, 0}};
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
        savePuzzle(PuzzleType.MEDIUM,board01);
        savePuzzle(PuzzleType.MEDIUM,board02);
        savePuzzle(PuzzleType.MEDIUM,board03);
        savePuzzle(PuzzleType.MEDIUM,board04);
        savePuzzle(PuzzleType.MEDIUM,board05);
    }
}

