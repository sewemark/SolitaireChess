package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public abstract class MoveStrategy  {
    protected  int width;
    protected  int height;
    public MoveStrategy(int _width, int _height){
        this.width= _width;
        this.height =_height;
    }
    public Vector getLastCoordinate(Vector position, Vector vector){
        Vector temp=position;
        while(checkRange(position.plus(vector))){
            position= position.plus(vector);
        }
        return position;
    }
    public Integer[] toIntArray(List<Integer> list){
        Integer[] ret = new Integer[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }

    public boolean checkRange(Vector position){
        if(position.getX()>= 0 && position.getX() < this.width){
            if(position.getY() >= 0&& position.getY() < this.height)
                return  true;
        }
        return false;

    }
}
