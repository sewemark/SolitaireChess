package com.seweryn.schess.Controllers;

/**
 * Created by sew on 2016-01-24.
 */
public class SCBoardLogicController implements  IBoardLogicInjector {
    @Override
    public IBoardLogicController getBoardLogicController() {
      return  new BoardLogicController();
    }
}
