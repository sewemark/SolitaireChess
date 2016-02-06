package com.seweryn.schess;

import com.seweryn.schess.Controllers.BoardLogicController;

import org.junit.Test;

import static org.junit.Assert.*;
public class BoardLogicTest {
    @Test
    public void testForBoardSetting(){
        BoardLogicController controller = new BoardLogicController();
        int width=5;
        int height=5;
        controller.setBoard(new int[height][width]);
        assertEquals(controller.getBoard().length, height);
        assertEquals(controller.getBoard()[0].length, width);

    }
    @Test
    public void testForBoardInitialization(){
        BoardLogicController controller = new BoardLogicController();
        int board[][] = new int[5][5];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] =0;
            }
        }
        controller.setBoard(new int[5][5]);
        controller.initializeBoard();
        assertArrayEquals(controller.getBoard(), board);
    }
    @Test
    public void testForSettingPieceAtPositionInitialization(){
        BoardLogicController controller = new BoardLogicController();
        controller.setBoard(new int[5][5]);
        controller.initializeBoard();
        controller.setPieceAtPosition(9, 4);
        assertEquals(controller.getPieceAtPosition(9), 4);
    }
    @Test
    public void testForNotSettingPieceAtPositionInitialization(){
        BoardLogicController controller = new BoardLogicController();
        controller.setBoard(new int[5][5]);
        controller.initializeBoard();
        controller.setPieceAtPosition(0, 3);
        assertNotEquals(controller.getPieceAtPosition(9), 4);
    }
    @Test
    public void testRemovingPieceAtPositionInitialization(){
        BoardLogicController controller = new BoardLogicController();
        controller.setBoard(new int[5][5]);
        controller.initializeBoard();
        controller.setPieceAtPosition(9, 4);
        controller.removePiece(9, 2, 2);
        assertEquals(controller.getPieceAtPosition(9), 0);
        assertEquals(controller.getPieceAtPosition(2), 2);
    }
    @Test
    public void testNotRemovingPieceAtPositionInitialization(){
        BoardLogicController controller = new BoardLogicController();
        controller.setBoard(new int[5][5]);
        controller.initializeBoard();
        controller.setPieceAtPosition(9, 4);
        controller.removePiece(8, 2, 2);
        assertEquals(controller.getPieceAtPosition(9), 4);
        assertEquals(controller.getPieceAtPosition(2), 2);
    }
}
