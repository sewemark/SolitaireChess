package com.seweryn.schess;

/**
 * Created by sew on 2015-11-15.
 */
public class Vector {

    private int x;
    private int y;
    public Vector(int x,  int y) {
        this.x = x;
        this.y = y;
    }
    public Vector plus (Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    };
    public Vector minus (Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    };
    public  boolean equals (Vector other){
        return (this.x ==other.x) &&(this.y ==other.y);
    };
    public static Vector convertToVecotr(int width, int height, int position){
         int row = (int)Math.floor (position/(double)height);
         int column  = position%width;
        return new Vector(row, column);
    }
    public static int convertToScalar(int width, int height, Vector vector){
        return Math.abs(vector.getX())*width + Math.abs(vector.getY());
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int setX(int val){
        return this.x =val;
    }
    public int sety(int val){
        return this.y =val;
    }
}
