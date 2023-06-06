/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Lisa
 */
public class LayoutPosition {
    private Position gamePos;
    private int x;
    private int y;

    public LayoutPosition(Position gamePos, int x, int y) {
        this.gamePos = gamePos;
        gamePos.setLayout(this);
        this.x = x;
        this.y = y;
    }
    
    public Position getGamePos() {
        return gamePos;
    }

    public void setGamePos(Position gamePos) {
        this.gamePos = gamePos;
        gamePos.setLayout(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
