package com.seweryn.schess;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Test;
import java.util.Arrays;
import com.seweryn.schess.DAL.DatabaseHandler;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@LargeTest
    public class DALTest extends AndroidTestCase {
        public DALTest(){
        }

    @Test
    public void testGettingListOfEasyPuzzleShouldReturnListOfEasyPuzzles() {
       try {

           DatabaseHandler dbHandler= new DatabaseHandler(getContext());
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

            DatabaseHandler dbHandler= new DatabaseHandler(getContext());
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

            DatabaseHandler dbHandler= new DatabaseHandler(getContext());
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

            DatabaseHandler dbHandler= new DatabaseHandler(getContext());
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

            DatabaseHandler dbHandler= new DatabaseHandler(getContext());
            DatabaseObject easyPuzzle = dbHandler.readPuzzle(PuzzleType.EASY,"1E");
            assertEquals(PuzzleType.EASY, easyPuzzle.getPuzzleType());
            assertEquals("1E",easyPuzzle.getFileName());
            assertArrayEquals(new int[][]{{0, 0, 0, 2},
                    {4, 0, 0, 0},
                    {0, 6, 0, 0},
                    {0, 0, 4, 0}}, easyPuzzle.getBoard());
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();

        }
    }


}
