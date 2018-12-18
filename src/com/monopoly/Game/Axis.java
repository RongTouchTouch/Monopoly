package com.monopoly.Game;

public class Axis {
    private int step;
    private int x;
    private int y;
    public void cal(int x, int y, int step){
        x = x / 85;
        y = y / 90;
        while (step > 0)
        {
            if (y ==9 && x > 0) //bottom
                x = x-1;
            else if ( x == 0 && y > 0)
                y = y-1;
            else if(y == 0 && x < 9)
                x= x+1;
            else
                y = y - 1;
            step--;
        }
        this.x = x * 85+30;
        this.y = y * 90+30;
    }
    public int calX(){
        return this.x;
    }

    public int calY(){
        return this.y;
    }
}