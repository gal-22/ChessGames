package com.academy.fundamentals.chessgames;

import java.util.ArrayList;

public abstract class Piece {

    protected int color;
    private int column;
    private int row;

    public Piece(int color, int column, int row)
    {
        this.color = color;
        this.row = row;
        this.column = column;
    }

    /**
     * @return returns the color code of the piece
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @return returns a drawable icon of the piece.
     */
    public abstract int getPieceIcon();

    public abstract ArrayList<Position> getPotentialMoves(Piece[][] pieces);

    public void move(Position position) {
        this.column = position.getColumn();
        this.row = position.getRow();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     *
     * @return returns an int value of the piece position
     * Instead of the column, row format.
     */
    public int getPosition() {
        return (8 * row + column);
    }
}
