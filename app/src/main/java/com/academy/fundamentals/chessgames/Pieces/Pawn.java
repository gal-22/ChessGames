package com.academy.fundamentals.chessgames.Pieces;


import com.academy.fundamentals.chessgames.Constants;
import com.academy.fundamentals.chessgames.Piece;
import com.academy.fundamentals.chessgames.Position;
import com.academy.fundamentals.chessgames.R;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean hasMoved;
    private ArrayList<Position> potentialMoves = new ArrayList<>();

    public Pawn(int color, int column, int row) {
        super(color, column, row); // replaces the this.color = color because it has been done in Piece
    }

    /**
     * checks for a code (100 for white 200 black)
     *
     * @return returns the drawable of a pawn piece
     */
    public int getPieceIcon() {
        if (color == Constants.blackCode) {
            return R.drawable.ic_pawn_black;
        } else return R.drawable.ic_pawn_white;
    }

    public void setPotentialMoves(ArrayList<Position> potentialMoves) {
        this.potentialMoves = potentialMoves;
    }

    /**
     *
     * @param pieces receives the board as a piece array
     * @return returns the potential positions the piece can move to
     */
    @Override
    public ArrayList<Position> getPotentialMoves(Piece[][] pieces) {
        // TODO Add a check for out of bounds
        if(!hasMoved) {
            // hasn't moved, can move 2 rows at first.

            // for white
            if (color == Constants.blackCode) {
                if(getRow() < 6 && pieces[getColumn()][getRow() + 1]
                        == null && pieces[getColumn()][getRow() + 2] == null) {
                    potentialMoves.add(new Position(getColumn(), getRow() + 2));
                }
            }
            // for black
            else { if(getRow() > 1 && pieces[getColumn()][getRow() - 1]
                    == null && pieces[getColumn()][getRow() - 2] == null) {
                potentialMoves.add(new Position(getColumn(), getRow() - 2));
                }
            }
        }

        // for black
            if (color == Constants.blackCode) {
                // can move 1 row front if it is empty and not the end
                if(getRow() < 7 && pieces[getColumn()][getRow() + 1] == null) {
                    potentialMoves.add(new Position(getColumn(), getRow() + 1));
                }
                // can eat diagonally to the front-left if there is a piece there
                if(getRow() < 7 && getColumn() > 0 && pieces[getColumn() - 1][getRow() + 1] != null
                        && pieces[getColumn() - 1][getRow() + 1].getColor() == Constants.whiteCode) {
                    potentialMoves.add(new Position(getColumn() - 1, getRow() + 1));
                }
                // can eat diagonally to the front-right if there is a piece there
                if(getRow() < 7 && getColumn() < 7 && pieces[getColumn() + 1][getRow() + 1] != null
                        && pieces[getColumn() + 1][getRow() + 1].getColor() == Constants.whiteCode) {
                    potentialMoves.add(new Position(getColumn() + 1, getRow() + 1));
                }
            }

            // for white
            else {
                // can go front if it is empty and not the end
                if(getRow() > 0 && pieces[getColumn()][getRow() - 1] == null) {
                    potentialMoves.add(new Position(getColumn(), getRow() - 1 ));
                }
                // can eat diagonally to the behind-left if there is a piece there
                if(getColumn() > 0 && getRow() > 0 && pieces[getColumn() - 1][getRow() - 1] != null
                        && pieces[getColumn() - 1][getRow() - 1].getColor() == Constants.blackCode) {
                    potentialMoves.add(new Position(getColumn() - 1, getRow() - 1));
                }
                // can eat diagonally to the behind-right if there is a piece there
                if(getRow() > 0 && getColumn() < 7 && pieces[getColumn() + 1][getRow() - 1] != null
                        && pieces[getColumn() + 1][getRow() - 1].getColor() == Constants.blackCode) {
                    potentialMoves.add(new Position(getColumn() + 1, getRow() - 1));
                }
            }
        return potentialMoves;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

}
