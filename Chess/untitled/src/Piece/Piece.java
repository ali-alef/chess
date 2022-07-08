package Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece extends JLabel {
    private final int width = 100;
    private final int height = 100;

    public boolean isDead = false;

    public int x;
    public int y;

    public int id;

    protected BufferedImage image;


    public Piece(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics graphics){
        graphics.drawImage(image, x * 100, y * 100 + 30, width, height, this);
    }
    public abstract ArrayList<int[]> possibleMoves(int[][] board);
}