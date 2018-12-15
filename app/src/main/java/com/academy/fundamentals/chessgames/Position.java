package com.academy.fundamentals.chessgames;

public class Position {
    private int row, column;

    public Position() {}

    public Position(int column, int row) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     *
     * @param pos integer between 0-63 representing a position
     * @return the Position of the item
     */
    public Position convertIntToPos(int pos){
        int row = pos / 8;
        int column = pos % 8;
        return new Position(column, row);
    }

    public int convertPosToInt() {
        return row * 8 + column;
    }
}
