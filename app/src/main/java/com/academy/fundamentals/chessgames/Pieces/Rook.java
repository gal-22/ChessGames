package com.academy.fundamentals.chessgames.Pieces;

import com.academy.fundamentals.chessgames.Constants;
import com.academy.fundamentals.chessgames.Piece;
import com.academy.fundamentals.chessgames.Position;
import com.academy.fundamentals.chessgames.R;

import java.util.ArrayList;


public class Rook extends Piece {

    private ArrayList<Position> potentialMoves = new ArrayList<>();

    public Rook(int color, int column, int row) {
        super(color, column, row);
    }

    /**
     * checks for a code (100 for white 200 black)
     *
     * @return returns the drawable of a rook piece
     */
    @Override
    public int getPieceIcon() {
        if (color == Constants.blackCode) {
            return R.drawable.ic_rook_black;
        } else return R.drawable.ic_rook_white;
    }

    /**
     *
     * @param pieces pieces arr representing the board
     * @return returns potential moves for the rook piece selected
     */
    @Override
    public ArrayList<Position> getPotentialMoves(Piece[][] pieces) {
        // Handling front movement
        for (int i = 0; i < 8; i++) {
            if (getRow() == 7) break;
            if (isFrontClear(pieces, new Position(getColumn(), getRow() + i))) {
                potentialMoves.add(new Position(getColumn(), getRow() + i + 1));
            } else {
                // front is not clear, checking for friendly or enemy pieces to decide if add or not
                if (getRow() + i < 7 && pieces[getColumn()][getRow() + i + 1] != null && pieces[getColumn()][getRow() + i + 1].getColor() != color) {
                    potentialMoves.add(new Position(getColumn(), getRow() + i + 1));
                }
                break;
            }
        }
        // Handling back movement
        for (int i = 0; i < 8; i++) {
            if (getRow() == 0) break;
            if (isBackClear(pieces, new Position(getColumn(), getRow() - i))) {
                potentialMoves.add(new Position(getColumn(), getRow() - i - 1));
            } else {
                // back is not clear, checking for friendly or enemy pieces to decide if add or not
                if (getRow() - i > 0 && pieces[getColumn()][getRow() - i - 1] != null && pieces[getColumn()][getRow() - i - 1].getColor() != color) {
                    potentialMoves.add(new Position(getColumn(), getRow() - i - 1));
                }
                break;
            }
        }
        // Handling right movement
        for (int i = 0; i < 8; i++) {
            if (getColumn() == 7) break;
            if (isRightClear(pieces, new Position(getColumn() + i, getRow()))) {
                potentialMoves.add(new Position(getColumn() + i + 1, getRow()));
            } else {
                // right is not clear, checking for friendly or enemy pieces to decide if add or not
                if (getColumn() + i < 7 && pieces[getColumn() + i + 1][getRow()] != null && pieces[getColumn() + i + 1][getRow()].getColor() != color) {
                    potentialMoves.add(new Position(getColumn() + i + 1, getRow()));
                }
                break;
            }
        }
        // Handling left movement
        for (int i = 0; i < 8; i++) {
            if (getColumn() == 0) break;
            if (isLeftClear(pieces, new Position(getColumn() - i, getRow()))) {
                potentialMoves.add(new Position(getColumn() - i - 1, getRow()));
            } else {
                // left is not clear, checking for friendly or enemy pieces to decide if add or not
                if (getColumn() - i > 0 && pieces[getColumn() - i - 1][getRow()] != null && pieces[getColumn() - i - 1][getRow()].getColor() != color) {
                    potentialMoves.add(new Position(getColumn() - i - 1, getRow()));
                }
                break;
            }
        }
        return potentialMoves;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the front of it is clear
     * @return returns true if the front of the position is clear, front is looked as a plus one row
     * relative to the board
     */
    private boolean isFrontClear(Piece[][] pieces, Position position) {
        return position.getRow() < 7 && pieces[position.getColumn()][position.getRow() + 1] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the back of it is clear
     * @return returns true if the back of the position is clear, back is looked as a minus one row
     * relative to the board
     */
    private boolean isBackClear(Piece[][] pieces, Position position) {
        return position.getRow() > 0 && pieces[position.getColumn()][position.getRow() - 1] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the right of it is clear
     * @return returns true if the right of the position is clear, right is looked as a plus one column
     * relative to the board
     */
    private boolean isRightClear(Piece[][] pieces, Position position) {
        return position.getColumn() < 7 && pieces[position.getColumn() + 1][position.getRow()] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the left of it is clear
     * @return returns true if the left of the position is clear, left is looked as a minus one column
     * relative to the board
     */
    private boolean isLeftClear(Piece[][] pieces, Position position) {
        return position.getColumn() > 0 && pieces[position.getColumn() - 1][position.getRow()] == null;
    }

}
