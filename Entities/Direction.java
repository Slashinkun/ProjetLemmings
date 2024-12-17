package Entities;

public enum Direction {

    
    DROITE(1,0), GAUCHE(-1,0), DESCEND(0,1), MONTE(0,-1);

    private int x;
    private int y;

    Direction(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
