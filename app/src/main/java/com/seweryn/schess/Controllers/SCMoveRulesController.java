package com.seweryn.schess.Controllers;

import com.seweryn.schess.Logic.MoveRulesLogic;

/**
 * Created by sew on 2016-01-24.
 */
public class SCMoveRulesController implements IMoveRulesControllerInjector {
    /**
     * method that returns instance of new move rules controller
     * @return class that implements IMoveRulesController
     * */
    @Override
    public IMoveRulesController getMoveRulesController() {
        return  new MoveRulesController(new MoveRulesLogic());
    }
}
