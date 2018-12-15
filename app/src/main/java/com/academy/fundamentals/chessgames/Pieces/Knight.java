package com.academy.fundamentals.chessgames.Pieces;

import com.academy.fundamentals.chessgames.Constants;
import com.academy.fundamentals.chessgames.Piece;
import com.academy.fundamentals.chessgames.Position;
import com.academy.fundamentals.chessgames.R;

import java.util.ArrayList;

public class Knight extends Piece {

    private ArrayList<Position> potentialMoves = new ArrayList<>();
    public Knight(int color, int column, int row) {
        super(color, column, row);
    }

    /**
     * checks for a code (100 for white 200 black)
     *
     * @return returns the drawable of a knight piece
     */
    @Override
    public int getPieceIcon() {
        if (color == Constants.blackCode) {
            return R.drawable.ic_knight_black;
        } else return R.drawable.ic_knight_white;
    }

    @Override
    public ArrayList<Position> getPotentialMoves(Piece[][] pieces) {
        // Handling long front-right movement
        // Should make the check only if he is in a valid position
        if (getRow() < 6 && getColumn() < 7) {
            if (isLongFrontRightClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() + 1, getRow() + 2));
            }
            // The long front right pos contains a piece
            else {
                if (getRow() < 6 && getColumn() < 7 && pieces[getColumn() + 1][getRow() + 2].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() + 1, getRow() + 2));
                }
            }
        }
        // Handling long front-left movement
        // Should make the check only if he is in a valid position
        if (getRow() < 6 && getColumn() > 0) {
            if (isLongFrontLeftClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() - 1, getRow() + 2));
            }
            // The long front left pos contains a piece
            else {
                if (getRow() < 6 && getColumn() > 0 && pieces[getColumn() - 1][getRow() + 2].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() - 1, getRow() + 2));
                }
            }
        }

        // Handling long back-left movement
        // Should make the check only if he is in a valid position
        if (getRow() > 1 && getColumn() > 0) {
            if (isLongBackLeftClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() - 1, getRow() - 2));
            }
            // The long back-left pos contains a piece
            else {
                if (getRow() > 1 && getColumn() > 0 && pieces[getColumn() - 1][getRow() - 2].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() - 1, getRow() - 2));
                }
            }
        }

        // Handling long back-right movement
        // Should make the check only if he is in a valid position
        if (getRow() > 1  && getColumn() < 7) {
            if (isLongBackRightClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() + 1, getRow() - 2));
            }
            // The long back right pos contains a piece
            else {
                if (getRow() > 1 && getColumn() < 7 && pieces[getColumn() + 1][getRow() - 2].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() + 1, getRow() - 2));
                }
            }
        }
        // Handling short back-left movement
        // Should make the check only if he is in a valid position
        if (getRow() > 0 && getColumn() > 1) {
            if (isShortBackLeftClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() - 2, getRow() - 1));
            }
            // The short back left pos contains a piece
            else {
                if (getRow() > 0 && getColumn() > 1 && pieces[getColumn() - 2][getRow() - 1].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() - 2, getRow() - 1));
                }
            }
        }
        // Handling short front-left movement
        // Should make the check only if he is in a valid position
        if (getRow() < 7 && getColumn() > 1) {
            if (isShortFrontLeftClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() - 2, getRow() + 1));
            }
            // The short front left pos contains a piece
            else {
                if (getRow() < 7 && getColumn() > 1 && pieces[getColumn() - 2][getRow() + 1].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() - 2, getRow() + 1));
                }
            }
        }
        // Handling short front-right movement
        // Should make the check only if he is in a valid position
        if (getRow() < 7 && getColumn() < 6) {
            if (isShortFrontRightClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() + 2, getRow() + 1));
            }
            // The short front-right pos contains a piece
            else {
                if (getRow() < 7 && getColumn() < 6 && pieces[getColumn() + 2][getRow() + 1].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() + 2, getRow() + 1));
                }
            }
        }
        // Handling short back-right movement
        // Should make the check only if he is in a valid position
        if (getRow() > 0 && getColumn() > 1) {
            if (isShortBackRightClear(pieces, new Position(getColumn(), getRow()))) {
                potentialMoves.add(new Position(getColumn() + 2, getRow() - 1));
            }
            // The short back-right pos contains a piece
            else {
                if (getRow() > 0 && getColumn() < 6 && pieces[getColumn() + 2][getRow() - 1].getColor() != getColor()) {
                    potentialMoves.add(new Position(getColumn() + 2, getRow() - 1));
                }
            }
        }
        return potentialMoves;
    }


    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the long front-right of it is clear
     * @return returns true if the long front-right of the position is clear, long front-right is looked as a plus one column
     * and plus two rows relative to the board
     */
    private boolean isLongFrontRightClear(Piece[][] pieces, Position position) {
        return position.getColumn() < 7 && position.getRow() < 6 && pieces[position.getColumn() + 1][position.getRow() + 2] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the long front-left of it is clear
     * @return returns true if the long front-left of the position is clear, long front-left is looked as a minus one column
     * and plus two rows relative to the board
     */
    private boolean isLongFrontLeftClear(Piece[][] pieces, Position position) {
        return position.getColumn() > 0 && position.getRow() < 6 && pieces[position.getColumn() - 1][position.getRow() + 2] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the long back-left of it is clear
     * @return returns true if the long back-left of the position is clear, long back-left is looked as a minus one column
     * and minus two rows relative to the board
     */
    private boolean isLongBackLeftClear(Piece[][] pieces, Position position) {
        return position.getColumn() > 0 && position.getRow() > 1 && pieces[position.getColumn() - 1][position.getRow() - 2] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the long back-right of it is clear
     * @return returns true if the long back-right of the position is clear, long back-right is looked as a plus one column
     * and minus two rows relative to the board
     */
    private boolean isLongBackRightClear(Piece[][] pieces, Position position) {
        return position.getColumn() < 7 && position.getRow() > 1 && pieces[position.getColumn() + 1][position.getRow() - 2] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the short back-left of it is clear
     * @return returns true if the short back-left of the position is clear, short back-left is looked as a minus two column
     * and minus one row relative to the board
     */
    private boolean isShortBackLeftClear(Piece[][] pieces, Position position) {
        return position.getColumn() > 1 && position.getRow() > 0 && pieces[position.getColumn() - 2][position.getRow() - 1] == null;
    } // WORKS

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the short front-left of it is clear
     * @return returns true if the short front-left of the position is clear, short front-left is looked as a minus two column
     * and plus one row relative to the board
     */
    private boolean isShortFrontLeftClear(Piece[][] pieces, Position position) {
        return position.getColumn() > 1 && position.getRow() < 7 && pieces[position.getColumn() - 2][position.getRow() + 1] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the short front-right of it is clear
     * @return returns true if the short front-right of the position is clear, short front-right is looked as a plus two column
     * and plus one row relative to the board
     */
    private boolean isShortFrontRightClear(Piece[][] pieces, Position position) {
        return position.getColumn() < 6 && position.getRow() < 7 && pieces[position.getColumn() + 2][position.getRow() + 1] == null;
    }

    /**
     * @param pieces   an array representing the board
     * @param position the position we check that the short back-right of it is clear
     * @return returns true if the short back-right of the position is clear, short back-right is looked as a plus two column
     * and minus one row relative to the board
     */
    private boolean isShortBackRightClear(Piece[][] pieces, Position position) {
        return position.getColumn() < 6 && position.getRow() > 0 && pieces[position.getColumn() + 2][position.getRow() - 1] == null;
    }
}
