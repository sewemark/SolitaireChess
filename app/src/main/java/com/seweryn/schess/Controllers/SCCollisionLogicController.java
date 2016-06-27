package com.seweryn.schess.Controllers;

import com.seweryn.schess.Logic.CollisionLogic;

/**
 * Created by sew on 2016-01-24.
 */
public class SCCollisionLogicController  implements ICollisionLogicInjector{
    /**
     * method that returns instance of new collision logic controller
     * @return class that implements ICollisionLogicController
     * */
    @Override
    public ICollisionLogicController getCollisionLogicController() {
        return new CollisionLogicController(new CollisionLogic());
    }
}
