package com.academy.fundamentals.chessgames;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ChessAdapter extends RecyclerView.Adapter<ChessAdapter.ViewHolder> {

    private Piece[][] pieces;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private PieceClickListener pieceClickListener;

    public ChessAdapter(Piece[][] pieces, Context context, PieceClickListener pieceClickListener) {
        this.pieces = pieces;
        this.mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.pieceClickListener = pieceClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = mLayoutInflater.inflate(R.layout.square, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 64;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout movieSquare;
        ImageView pieceIv;

        // ViewHolder Constructor
        private ViewHolder(View view) {
            super(view);
            movieSquare = view.findViewById(R.id.square_constraints);
            pieceIv = view.findViewById(R.id.piece_iv);
            view.setOnClickListener(this);
        }

        private int getSquareColor(int pos) {
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

        private void bind() {
            // Setting the color of the squares
            int row = getAdapterPosition() / 8;
            int column = getAdapterPosition() % 8;
            if (row % 2 == 0) {
                if (getAdapterPosition() % 2 != 0) {
                    movieSquare.setBackgroundColor(ContextCompat.getColor(context, getSquareColor(getAdapterPosition())));
                }
            } else {
                if (getAdapterPosition() % 2 == 0) {
                    movieSquare.setBackgroundColor(ContextCompat.getColor(context, getSquareColor(getAdapterPosition())));
                }
            }
            // Setting the pieces
            Piece piece = pieces[column][row];
            if (piece != null) {
                pieceIv.setImageResource(piece.getPieceIcon());
                pieceIv.setVisibility(View.VISIBLE);
            }
            else {
                pieceIv.setVisibility(View.GONE);
                pieceIv.setBackgroundColor(getSquareColor(getAdapterPosition()));

                // TODO Explain why he gets null here! -> it was the cause for the spawned figures of white pieces behind the black
                // TODO It fixes the image which is drawn but not the red background color added to it
            }
        }

        @Override
        public void onClick(View view) {
            pieceClickListener.onPieceClickListener(getAdapterPosition(), view);
        }

    }
}
