package com.seweryn.schess.DAL;

import android.content.Context;
import android.support.annotation.NonNull;

import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.SearchAlgoritm.DFSTree;
import com.seweryn.schess.SearchAlgoritm.ISearchTree;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.SearchAlgoritm.SolutionFinder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by sew on 2015-11-23.
 */
public class DatabaseContext implements IDatabaseContext {

    private Context context;
    private IDatabaseInfo dbInfo;
    public DatabaseContext(Context _context,IDatabaseInfo _dbInfo) {
         this.context = _context;
        this.dbInfo =_dbInfo;
    }
    /**
     * updates databaseObject to database
     * @param  objectToUpdate database object
     * */
    public void updatePuzzle(DatabaseObject objectToUpdate){
        try{
            String fileName =getFilePath(objectToUpdate.getPuzzleType(),objectToUpdate.getFileName());
            File fileToUpdate = new File(fileName);

            FileOutputStream fos = new FileOutputStream(fileToUpdate);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectToUpdate);
            oos.close();
            fos.close();
        }
        catch(Exception ex){

        }
    }
    /**
     * save puzzle to database, creates DatabaseObject based on the puzzle type and board array
     * @param  puzleType puzzle type
     * @param  puzzleToSave int array that holds board
     * */
     public  void savePuzzle(PuzzleType puzleType, int[][] puzzleToSave ) {
         try{
             String fileName = genarateNextFileName(puzleType);
             File fileToCreate = new File(getFilePath(puzleType, fileName));
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

         }
         catch (Exception ex){

         }
     }
    /**
     * generates a file name for next puzzle that is being saved
     * @param  puzleType puzzle type of the next puzzle to be saved
     * */
    @NonNull
    private String genarateNextFileName(PuzzleType puzleType) {
        File parrentDir = new File(getFolderPath(puzleType));
        return String.valueOf(parrentDir.list().length +1) + puzleType.toString().charAt(0);
    }
    /**
     * read next or previos puzzle to actual puzzle
     * @param  puzzleType actual puzzle type
     * @param  fileName actual file name
     * @param prevOrNext indicates whether get next or previous to the  actual  puzzle
     * */
    public DatabaseObject readNextOrPreviousPuzzle(PuzzleType puzzleType, String fileName,int prevOrNext){
        char puzzleTypeName = fileName.charAt(fileName.length()-1);
        String nextfileName = String.valueOf(Integer.valueOf(fileName.substring(0, fileName.length() - 1)) +prevOrNext) + puzzleTypeName;
        File fileToRead = new File(getFilePath(puzzleType,nextfileName));
        if(fileToRead.exists()){
            return readPuzzle(puzzleType, nextfileName);
        }
        else
        {
            String[] puzzleNames =  getPuzzleListByType(getPuzzleType(puzzleType, prevOrNext));
            if(prevOrNext==1) {
                return readPuzzle(getPuzzleType(puzzleType, prevOrNext),puzzleNames[0]);
            }
                else
                return  readPuzzle(getPuzzleType(puzzleType,prevOrNext),puzzleNames[puzzleNames.length-1]);

        }

    }
    /**
     * method return puzzle type next to actual puzzle type
     * @param  puzzleType actual puzzle type
     * @param prevOrNext indicates whether get next or previous to the  actual  puzzle
     * @return PuzzleType puzzle type of the next to actua puzzle type
     * */
    public  PuzzleType getPuzzleType(PuzzleType puzzleType, int prevOrNext){
        PuzzleType type=  null;
        for(int i=0;i<PuzzleType.values().length;i++){
            if(PuzzleType.values()[i]==puzzleType){
                if(( (i+prevOrNext) <puzzleType.values().length) && ((i+prevOrNext) >=0)){
                     type =  PuzzleType.values()[i+prevOrNext];
                }
                else if(i+prevOrNext<0){
                    type = puzzleType.values()[puzzleType.values().length-1];
                }
            }
        }
        if(type==null)
            return  PuzzleType.values()[0];
        return  type;
    }
    /**
     * method read puzzle of particular type and name from database
     * @param  puzzleType actual puzzle type
     * @param fileName file name of the puzzle
     * @return DatabaseObject that contain requestes puzzle
     * */
    public  DatabaseObject readPuzzle(PuzzleType puzzleType, String fileName ) {
        try{
            File fileToRead = new File(getFilePath(puzzleType,fileName));
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
    /**
     * method read all puzzle of particular type from database
     * @param  puzzleType actual puzzle type
     * @return array of DatabaseObject that contain  puzzles of requested type
     * */
    public DatabaseObject[] getPuzzleObjectByType(PuzzleType puzzleType){
        String[] puzzleByTypeNames = this.getPuzzleListByType(puzzleType);
        DatabaseObject[] databaseObjects= new DatabaseObject[puzzleByTypeNames.length];
        for(int i=0; i < puzzleByTypeNames.length; i++){
            databaseObjects[i] = this.readPuzzle(puzzleType,puzzleByTypeNames[i]);
        }
        return  databaseObjects;
    }
    /**
     * method read all puzzle names of particular type from database
     * @param  puzzleType actual puzzle type
     * @return array of puzzle names that contain  puzzles of requested type
     * */
    public  String[] getPuzzleListByType(PuzzleType puzzleType){
        File parrentDir = new File(getFolderPath(puzzleType));
        if(parrentDir.exists())
            return  parrentDir.list();
        else
            return  new String[]{};
    }
    /**
     * method read path to the puzzle of given name and type
     * @param  puzzleType actual puzzle type
     * @param  fileName file name of requested puzzle
     * @return path to requested puzzle
     * */
    private String getFilePath(PuzzleType puzzleType,String fileName){
      return  context.getCacheDir().toString() + '/' + dbInfo.getDatabaseName() + '/' + puzzleType.toString() + "/"+ fileName;
    }
    /**
     * method read path to the given puzzle type folder
     * @param  puzzleType actual puzzle type
     * @return path to requested puzzle type
     * */
    private  String getFolderPath(PuzzleType puzzleType){
        return  context.getCacheDir().toString() + '/' + dbInfo.getDatabaseName() + '/' + puzzleType.toString();
    }

}

