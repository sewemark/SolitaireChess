package com.seweryn.schess;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import com.seweryn.schess.DAL.DatabaseContext;
import com.seweryn.schess.DAL.DatabaseInfo;
import com.seweryn.schess.DAL.DatabaseInitializer;
import com.seweryn.schess.DAL.DatabaseManager;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.verify;

@LargeTest
    public class DatabaseContextTest extends AndroidTestCase {
        public DatabaseContextTest(){
        }
   @Before
   public void setUp(){
       DatabaseContext context = new DatabaseContext(getContext(),new DatabaseInfo(getContext()));
       DatabaseManager manger = new DatabaseManager(new DatabaseInfo(getContext()));
       manger.createDatabaseIfNotExists();
      if(manger.wasCreated()) {
          DatabaseInitializer initializer = new DatabaseInitializer(context);
          initializer.initializaDataBase();
      }
   }
    @Test
    public void testGettingListOfEasyPuzzleShouldReturnListOfEasyPuzzles() {
       try {

           DatabaseContext dbHandler= new DatabaseContext(getContext(), new DatabaseInfo(getContext()));
           String []lista =dbHandler.getPuzzleListByType(PuzzleType.EASY);
           assertTrue(Arrays.equals(lista, new String[]{"1E", "2E", "3E", "4E", "5E"}));
        } catch (Exception e) {
           System.out.println(e.toString());
            e.printStackTrace();

        }
    }
    @Test
    public void testGettingListOfMediumPuzzleShouldReturnListOfEasyPuzzles() {
        try {

            DatabaseContext dbHandler= new DatabaseContext(getContext(),new DatabaseInfo(getContext()));
            String []lista =dbHandler.getPuzzleListByType(PuzzleType.MEDIUM);
            assertTrue(Arrays.equals(lista, new String[]{"1M", "2M", "3M", "4M", "5M"}));
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }
    }
    @Test
    public void testGettingListOfHardPuzzleShouldReturnListOfEasyPuzzles() {
        try {

            DatabaseContext dbHandler= new DatabaseContext(getContext(),new DatabaseInfo(getContext()));
            String []lista =dbHandler.getPuzzleListByType(PuzzleType.HARD);
            assertTrue(Arrays.equals(lista, new String[]{"1H", "2H", "3H", "4H", "5H"}));
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }
    }
    @Test
    public void testGettingListOfVeryHardPuzzleShouldReturnListOfEasyPuzzles() {
        try {

            DatabaseContext dbHandler= new DatabaseContext(getContext(),new DatabaseInfo(getContext()));
            String []lista =dbHandler.getPuzzleListByType(PuzzleType.VERYHARD);
            assertTrue(Arrays.equals(lista, new String[]{"1V", "2V", "3V", "4V", "5V"}));
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }
    }
    @Test
    public void testReadingFirstEazyPuzzleShouldReturnEasyPuzzle() {
        try {

            DatabaseContext dbHandler= new DatabaseContext(getContext(),new DatabaseInfo(getContext()));
            DatabaseObject easyPuzzle = dbHandler.readPuzzle(PuzzleType.EASY,"1E");
            assertEquals(PuzzleType.EASY, easyPuzzle.getPuzzleType());
            assertEquals("1E",easyPuzzle.getFileName());
            assertArrayEquals(new int[][]{{0, 3, 0, 0},
                    {0, 6, 3, 5},
                    {0, 0, 0, 0},
                    {5, 0 , 0, 0}}, easyPuzzle.getBoard());
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }
    }


}
