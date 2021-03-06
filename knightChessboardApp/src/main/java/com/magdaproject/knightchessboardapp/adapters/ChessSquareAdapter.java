package com.magdaproject.knightchessboardapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.Utils.GlobalUtils;
import com.magdaproject.knightchessboardapp.databinding.ChessSquareBinding;
import com.magdaproject.knightchessboardapp.listeners.SelectListener;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ChessSquareAdapter extends RecyclerView.Adapter<ChessSquareAdapter.SquareViewHolder> {

    private SelectListener mSelectListener;

    private Integer[] mColorList;

    private Context mContext;

    private int mBoardDimension;

    private  boolean isStaringSquare = false;

    public ChessSquareAdapter(SelectListener selectListener, Context context) {
        this.mSelectListener = selectListener;
        this.mContext = context;
    }

    public void setAdapterList(Integer[] colorList, int dim) {
        this.mBoardDimension = dim;
        if (mColorList == null) {
            mColorList = colorList;
            notifyItemRangeInserted(0, mColorList.length);
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ChessSquareAdapter.this.mColorList.length;
                }

                @Override
                public int getNewListSize() {
                    return mColorList.length;
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ChessSquareAdapter.this.mColorList[oldItemPosition] ==
                            mColorList[newItemPosition];
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return ChessSquareAdapter.this.mColorList[oldItemPosition].equals(
                            mColorList[newItemPosition]);
                }
            });
            this.mColorList = colorList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public SquareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChessSquareBinding mChessSquareBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.chess_square, parent, false);
        //mChessSquareBinding.setSelectListener(mSelectListener);
        return new SquareViewHolder(mChessSquareBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SquareViewHolder squareViewHolder, int position) {
        final int mPosition = position;
        final SquareViewHolder mSquareViewHolder = squareViewHolder;
        squareViewHolder.mChessSquareBinding.square.setBackgroundColor(mContext.getResources().getColor(mColorList[position]));
        //squareViewHolder.mChessSquareBinding.knightPiece.setVisibility(View.GONE);
        //squareViewHolder.mChessSquareBinding.setPoint(GlobalUtils.convertToPoint(position, mBoardDimension));
        squareViewHolder.mChessSquareBinding.square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStaringSquare) {
                    mSquareViewHolder.mChessSquareBinding.knightPiece.setImageResource(R.drawable.icon_knight_svgrepo_com);
                    isStaringSquare = true;
                }else {
                    mSquareViewHolder.mChessSquareBinding.knightPiece.setImageResource(R.drawable.icon_target_svgrepo_com);
                }
                mSelectListener.onSquareSelect(GlobalUtils.convertToPoint(mPosition, mBoardDimension));
            }
        });
        squareViewHolder.mChessSquareBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mColorList != null ? mColorList.length : 0;
    }

    public class SquareViewHolder extends RecyclerView.ViewHolder {
        ChessSquareBinding mChessSquareBinding;


        public SquareViewHolder(@NonNull ChessSquareBinding chessSquareBinding) {
            super(chessSquareBinding.getRoot());
            this.mChessSquareBinding = chessSquareBinding;
        }
    }
}
