package com.seweryn.schess.Controllers;

/**
 * Created by sew on 2016-01-24.
 */
public class SCBoardLogicController implements  IBoardLogicInjector {
    /**
     * method that returns instance of new board logic controller
     * @return class that implements IBoardLogicController
     * */
    @Override
    public IBoardLogicController getBoardLogicController() {
      return  new BoardLogicController();
    }
}
