package com.seweryn.schess;

import com.seweryn.schess.Logic.MovesStrategy.BishopMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.IMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.KingMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.KnightMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.PawnMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.QueenMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.RockMoveStrategy;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.SearchAlgoritm.ISearchTree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MoveStrategiesForPiecesTest {
    @Test
        public void testMoveStrategyForPawnPositionForBoard4By4() throws Exception {
        Integer[] result = new PawnMoveStrategy(4,4).getMoveForPiece(Vector.convertToVector(4, 4, 5));
        assertArrayEquals(new Integer[]{0,2},result);
    }
    @Test
    public void testMoveStrategyForKingPositionForBoard4By4() throws Exception {
        Integer[] result = new KingMoveStrategy(4,4).getMoveForPiece(Vector.convertToVector(4, 4, 5));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{0, 1, 2, 4, 6, 8, 9, 10}, result);
    }
    @Test
    public void testMoveStrategyForBishopPositionForBoard4By4() throws Exception {
        Integer[] result = new BishopMoveStrategy(4,4).getMoveForPiece(Vector.convertToVector(4, 4, 5));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{0,2,8,10,15},result);
    }

    @Test
    public void testMoveStrategyForKinightPositionForBoard4By4() throws Exception {
        Integer[] result = new KnightMoveStrategy(4,4).getMoveForPiece(Vector.convertToVector(4, 4, 5));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{3,11,12,14},result);
    }
    @Test
    public void testMoveStrategyForQueenPositionForBoard4By4() throws Exception {
        Integer[] result = new QueenMoveStrategy(4,4).getMoveForPiece(Vector.convertToVector(4, 4, 5));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{0,1,2,4,6,7,8,9,10,13,15},result);
    }
    @Test
    public void testMoveStrategyForRockPositionForBoard4By4() throws Exception {
        Integer[] result = new RockMoveStrategy(4,4).getMoveForPiece(Vector.convertToVector(4, 4, 5));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{1,4,6,7,9,13},result);
    }
    @Test
    public void testMoveStrategyForPawnPositionForBoard6By7() throws Exception {
        Integer[] result = new PawnMoveStrategy(6,7).getMoveForPiece(Vector.convertToVector(6, 7, 34));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{27,29},result);
    }
    @Test
    public void testMoveStrategyForKingPositionForBoard6By7() throws Exception {
        Integer[] result = new KingMoveStrategy(6,7).getMoveForPiece(Vector.convertToVector(6, 7, 34));
        QuickSort.quickSort(result,0,result.length-1);
        assertArrayEquals(new Integer[]{27,28,29,33,35,39,40,41},result);
    }
}