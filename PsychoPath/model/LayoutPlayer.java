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
public class LayoutPlayer {
    private Player gamePlayer;
    private LayoutPosition layout;
    private int x;
    private int y;

    public LayoutPlayer(Player gamePlayer, LayoutPosition layout, int x, int y) {
        this.gamePlayer = gamePlayer;
        this.layout = layout;
        this.x = x;
        this.y = y;
    }

    public Player getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(Player gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public LayoutPosition getLayout() {
        return layout;
    }

    public void setLayout(LayoutPosition layout) {
        this.layout = layout;
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
