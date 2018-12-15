package com.academy.fundamentals.chessgames;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.academy.fundamentals.chessgames.Pieces.Bishop;
import com.academy.fundamentals.chessgames.Pieces.King;
import com.academy.fundamentals.chessgames.Pieces.Knight;
import com.academy.fundamentals.chessgames.Pieces.Pawn;
import com.academy.fundamentals.chessgames.Pieces.Queen;
import com.academy.fundamentals.chessgames.Pieces.Rook;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PieceClickListener {
    // Useful shortcuts
    // ctrl + P inside brackets for input.

    // Variables
    private int playerTurnColor = Constants.whiteCode;

    // UI Views
    private RecyclerView chessRv;
    private TextView playerTurnColorTv;

    // Objects
    private ChessAdapter chessAdapter;
    private Piece[][] pieces = new Piece[8][8];
    private Piece selectedPiece = null;

    // TODO 30.11.18 while selecting a piece and then pressing and immovable slot the piece will lost it's focous and will have to be double clicked again to move
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createBoard();
        initRecycler();
        initViews();
    }

    public void initViews() {
        playerTurnColorTv = findViewById(R.id.player_turn_color_tv);
        if (playerTurnColor == Constants.whiteCode) {
            playerTurnColorTv.setText(R.string.ma_playerturn_white);
        } else playerTurnColorTv.setText(R.string.ma_playerturn_black);
    }

    public void createBoard() {
        Pawn pawnBlack = new Pawn(Constants.blackCode, 0, 1);
        Pawn pawnBlack1 = new Pawn(Constants.blackCode, 1, 1);
        Pawn pawnBlack2 = new Pawn(Constants.blackCode, 2, 1);
        Pawn pawnBlack3 = new Pawn(Constants.blackCode, 3, 1);
        Pawn pawnBlack4 = new Pawn(Constants.blackCode, 4, 1);
        Pawn pawnBlack5 = new Pawn(Constants.blackCode, 5, 1);
        Pawn pawnBlack6 = new Pawn(Constants.blackCode, 6, 1);
        Pawn pawnBlack7 = new Pawn(Constants.blackCode, 7, 1);
        Pawn pawnWhite = new Pawn(Constants.whiteCode, 0, 6);
        Pawn pawnWhite1 = new Pawn(Constants.whiteCode, 1, 6);
        Pawn pawnWhite2 = new Pawn(Constants.whiteCode, 2, 6);
        Pawn pawnWhite3 = new Pawn(Constants.whiteCode, 3, 6);
        Pawn pawnWhite4 = new Pawn(Constants.whiteCode, 4, 6);
        Pawn pawnWhite5 = new Pawn(Constants.whiteCode, 5, 6);
        Pawn pawnWhite6 = new Pawn(Constants.whiteCode, 6, 6);
        Pawn pawnWhite7 = new Pawn(Constants.whiteCode, 7, 6);
        Rook blackRook = new Rook(Constants.blackCode, 0, 0);
        Rook blackRook1 = new Rook(Constants.blackCode, 7, 0);
        Rook whiteRook = new Rook(Constants.whiteCode, 0, 7);
        Rook whiteRook1 = new Rook(Constants.whiteCode, 7, 7);
        Knight blackKnight = new Knight(Constants.blackCode, 1, 0);
        Knight blackKnight1 = new Knight(Constants.blackCode, 6, 0);
        Knight whiteKnight = new Knight(Constants.whiteCode, 1, 7);
        Knight whiteKnight1 = new Knight(Constants.whiteCode, 6, 7);
        Bishop blackBishop = new Bishop(Constants.blackCode, 2, 0);
        Bishop blackBishop1 = new Bishop(Constants.blackCode, 5, 0);
        Bishop whiteBishop = new Bishop(Constants.whiteCode, 2, 7);
        Bishop whiteBishop1 = new Bishop(Constants.whiteCode, 5, 7);
        Queen blackQueen = new Queen(Constants.blackCode, 3, 0);
        Queen whiteQueen = new Queen(Constants.whiteCode, 3, 7);
        King blackKing = new King(Constants.blackCode, 4, 0);
        King whiteKing = new King(Constants.whiteCode, 4, 7);

        pieces[0][1] = pawnBlack;
        pieces[1][1] = pawnBlack1;
        pieces[2][1] = pawnBlack2;
        pieces[3][1] = pawnBlack3;
        pieces[4][1] = pawnBlack4;
        pieces[5][1] = pawnBlack5;
        pieces[6][1] = pawnBlack6;
        pieces[7][1] = pawnBlack7;
        pieces[0][6] = pawnWhite;
        pieces[1][6] = pawnWhite1;
        pieces[2][6] = pawnWhite2;
        pieces[3][6] = pawnWhite3;
        pieces[4][6] = pawnWhite4;
        pieces[5][6] = pawnWhite5;
        pieces[6][6] = pawnWhite6;
        pieces[7][6] = pawnWhite7;
        pieces[0][0] = blackRook;
        pieces[7][0] = blackRook1;
        pieces[0][7] = whiteRook;
        pieces[7][7] = whiteRook1;
        pieces[1][0] = blackKnight;
        pieces[6][0] = blackKnight1;
        pieces[1][7] = whiteKnight;
        pieces[6][7] = whiteKnight1;
        pieces[2][0] = blackBishop;
        pieces[5][0] = blackBishop1;
        pieces[2][7] = whiteBishop;
        pieces[5][7] = whiteBishop1;
        pieces[3][0] = blackQueen;
        pieces[3][7] = whiteQueen;
        pieces[4][0] = blackKing;
        pieces[4][7] = whiteKing;
    }

    public void initRecycler() {
        chessRv = findViewById(R.id.chess_rv);
        chessRv.setLayoutManager(new GridLayoutManager(this, 8));
        chessAdapter = new ChessAdapter(pieces, this, this);
        chessRv.setAdapter(chessAdapter);
        chessAdapter.notifyDataSetChanged();
    }

    /**
     * @param column a column int
     * @param row    a row int
     * @return returns the position of the item as an integer
     * instead of the format of column and row
     */
    public int getIntPos(int column, int row) {
        return (8 * row + column);
    }

    /**
     * @param column receives a column to check
     * @param row    receives a row to check
     * @return returns true if the position gives has a
     * piece else it returns false.
     */
    public boolean posHasPiece(int column, int row) {
        return pieces[column][row] != null;
    }

    /**
     * @param column receives a column to check
     * @param row    receives a row to check
     * @return returns true if it has a piece the same color as the player who's turn is.
     */
    public boolean hasFriendlyPiece(int column, int row) {
        return posHasPiece(column, row) && pieces[column][row].color == playerTurnColor;
    }

    /**
     * @param column receives a column to check
     * @param row    receives a row to check
     * @return returns true if it has a piece the same color as the player who's turn isn't.
     */
    public boolean hasEnemyPiece(int column, int row) {
        return posHasPiece(column, row) && pieces[column][row].getColor() != playerTurnColor;
    }

    /**
     * @param position the position of the selected item clicked
     *                 from the RecyclerView getAdapterPosition.
     */
    @Override
    public void onPieceClickListener(int position, View view) {
        int column = position % 8;
        int row = position / 8;
        // Pressed on a slot that contains a piece and it is it's turn
        if (selectedPiece == null && pieces[column][row]
                != null && pieces[column][row].getColor() == playerTurnColor) {
            selectedPiece = pieces[column][row];
            view.setBackgroundColor(getResources().getColor(R.color.pieceSelectColor));
            // Pressed the same piece twice to cancel the click
        } else if (selectedPiece != null && selectedPiece.getRow() == row && selectedPiece.getColumn() == column) {
            //    selectedPiece.getPotentialMoves(pieces).clear(); TODO remove
            view.setBackgroundColor(getResources().getColor(getSquareColor(position)));
            selectedPiece = null;
            // Pressed Piece then another position
        } else {
            if (selectedPiece != null) {
//                selectedPiece = null;
                Position pos = new Position();
                pos = pos.convertIntToPos(position);
                ArrayList<Position> possiblePositions;
                possiblePositions = selectedPiece.getPotentialMoves(pieces);
                view.setBackgroundColor(getResources().getColor(getSquareColor(position)));
                if (isPotentialMove(pos, possiblePositions)) {
                    makeMove(pos);
                    possiblePositions.clear();
                    selectedPiece = null;
                }
            //    view.setBackgroundColor(getResources().getColor(getSquareColor(position)));
            }
        }
    }

    public int getSquareColor(int pos) {
        int row = pos / 8;
        int color = R.color.boardWhite;
        if (row % 2 == 0) {
            if (pos % 2 != 0) {
                color = R.color.boardBlack;
            }
        } else {
            if (pos % 2 == 0) {
                color = R.color.boardBlack;
            }
        }
        return color;
    }

    public void makeMove(Position position) {

        Position currentPosition = new Position(selectedPiece.getColumn(), selectedPiece.getRow());
        pieces[selectedPiece.getColumn()][selectedPiece.getRow()] = null;
        selectedPiece.move(position);
        Piece movedPiece = selectedPiece;
        if (selectedPiece instanceof Pawn) {
            ((Pawn) selectedPiece).setHasMoved(true);
        }

        selectedPiece = null;
        pieces[position.getColumn()][position.getRow()] = movedPiece;
      //  chessAdapter.notifyItemChanged(currentPosition.convertPosToInt());
      //  chessAdapter.notifyItemChanged(position.convertPosToInt()); // TODO It was recommanded to use this , check if notify data set changed wont work
        chessAdapter.notifyDataSetChanged();
        if (playerTurnColor == Constants.whiteCode) {
            playerTurnColor = Constants.blackCode;
        } else playerTurnColor = Constants.whiteCode;
    }

    /**
     * @param position receives a position and the available positions.
     * @return returns true is the move is available.
     */
    public boolean isPotentialMove(Position position, ArrayList<Position> availablePositions) {
        for (int i = 0; i < availablePositions.size(); i++) {
            if (availablePositions.get(i).getRow() == position.getRow() &&
                    availablePositions.get(i).getColumn() == position.getColumn()) {
                return true;
            }
        }
        return false;
    }


}
