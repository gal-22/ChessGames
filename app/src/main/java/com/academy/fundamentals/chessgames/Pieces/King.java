package com.academy.fundamentals.chessgames.Pieces;

import com.academy.fundamentals.chessgames.Constants;
import com.academy.fundamentals.chessgames.Piece;
import com.academy.fundamentals.chessgames.Position;
import com.academy.fundamentals.chessgames.R;

import java.util.ArrayList;

public class King extends Piece {

    private ArrayList<Position> potentialMoves = new ArrayList<>();

    public King(int color, int column, int row) {
        super(color, column, row);
    }

    /**
     * checks for a code (100 for white 200 black)
     *
     * @return returns the drawable of a king piece
     */
    @Override
    public int getPieceIcon() {
        if (color == Constants.blackCode) {
            return R.drawable.ic_king_black;
        } else return R.drawable.ic_king_white;
    }

    @Override
    public ArrayList<Position> getPotentialMoves(Piece[][] pieces) {
        // front pos is taken and isn't out of bounds
        if (getRow() < 7 && isPositionTaken(new Position(getColumn(), getRow() + 1), pieces)) {
            // front is occupied by enemy piece
            if (pieces[getColumn()][getRow() + 1].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn(), getRow() + 1));
            }
        }
        // front row is clear and in bounds
        else if (getRow() < 7) {
            potentialMoves.add(new Position(getColumn(), getRow() + 1));
        }
        // back pos is taken and isn't out of bounds
        if (getRow() > 0 && isPositionTaken(new Position(getColumn(), getRow() - 1), pieces)) {
            // back is occupied by enemy piece
            if (pieces[getColumn()][getRow() - 1].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn(), getRow() - 1));
            }
        }
        // back row is clear and in bounds
        else if (getRow() > 0) {
            potentialMoves.add(new Position(getColumn(), getRow() - 1));
        }
        // right pos is taken and isn't out of bounds
        if (getColumn() < 7 && isPositionTaken(new Position(getColumn() + 1, getRow()), pieces)) {
            // right is occupied by enemy piece
            if (pieces[getColumn() + 1][getRow()].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn() + 1, getRow()));
            }
        }
        // right row is clear and in bounds
        else if (getColumn() < 7) {
            potentialMoves.add(new Position(getColumn() + 1, getRow()));
        }

        // left pos is taken and isn't out of bounds
        if (getColumn() > 0 && isPositionTaken(new Position(getColumn() - 1, getRow()), pieces)) {
            // left is occupied by enemy piece
            if (pieces[getColumn() - 1][getRow()].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn() - 1, getRow()));
            }
        }
        // left row is clear and in bounds
        else if (getColumn() > 0) {
            potentialMoves.add(new Position(getColumn() - 1, getRow()));
        }
        // front-left pos is taken and isn't out of bounds
        if (getColumn() > 0 && getRow() < 7 && isPositionTaken(new Position(getColumn() - 1, getRow() + 1), pieces)) {
            // front-left is occupied by enemy piece
            if (pieces[getColumn() - 1][getRow() + 1].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn() - 1, getRow() + 1));
            }
        }
        // front-left row is clear and in bounds
        else if (getColumn() < 7 && getRow() < 7) {
            potentialMoves.add(new Position(getColumn() - 1, getRow() + 1));
        }
        // front-right pos is taken and isn't out of bounds
        if (getColumn() < 7 && getRow() < 7 && isPositionTaken(new Position(getColumn() + 1, getRow() + 1), pieces)) {
            // front-right is occupied by enemy piece
            if (pieces[getColumn() - 1][getRow() + 1].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn() + 1, getRow() + 1));
            }
        }
        // front-right row is clear and in bounds
        else if (getColumn() < 7 && getRow() < 7) {
            potentialMoves.add(new Position(getColumn() + 1, getRow() + 1));
        }
        // back-right pos is taken and isn't out of bounds
        if (getColumn() < 7 && getRow() > 0 && isPositionTaken(new Position(getColumn() + 1, getRow() - 1), pieces)) {
            // back-right is occupied by enemy piece
            if (pieces[getColumn() + 1][getRow() - 1].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn() + 1, getRow() - 1));
            }
        }
        // back-right row is clear and in bounds
        else if (getColumn() < 7 && getRow() > 0) {
            potentialMoves.add(new Position(getColumn() + 1, getRow() - 1));
        }
        // back-left pos is taken and isn't out of bounds
        if (getColumn() > 0 && getRow() > 0 && isPositionTaken(new Position(getColumn() - 1, getRow() - 1), pieces)) {
            // back-right is occupied by enemy piece
            if (pieces[getColumn() - 1][getRow() - 1].getColor() != getColor()) {
                potentialMoves.add(new Position(getColumn() - 1, getRow() - 1));
            }
        }
        // back-left row is clear and in bounds
        else if (getColumn() > 0 && getRow() > 0) {
            potentialMoves.add(new Position(getColumn() - 1, getRow() - 1));
        }
        return potentialMoves;
    }

    /**
     * @param position receives a position
     * @param pieces   a board representation as a piece arr.
     * @return returns true if the position is occupied else returns false
     * if the position is invalid will return false
     */
    private boolean isPositionTaken(Position position, Piece[][] pieces) {
        if (position.getColumn() <= 7 && position.getColumn() >= 0 && position.getRow() >= 0 && position.getRow() <= 7 &&
                pieces[position.getColumn()][position.getRow()] != null) {
            return true;
        } else return false;
    }

}
