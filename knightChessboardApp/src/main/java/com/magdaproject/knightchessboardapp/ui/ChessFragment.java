package com.magdaproject.knightchessboardapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.Utils.GlobalUtils;
import com.magdaproject.knightchessboardapp.adapters.ChessSquareAdapter;
import com.magdaproject.knightchessboardapp.databinding.FragmentChessBinding;
import com.magdaproject.knightchessboardapp.listeners.ClickListener;
import com.magdaproject.knightchessboardapp.listeners.PathsClickListener;
import com.magdaproject.knightchessboardapp.listeners.SelectListener;
import com.magdaproject.knightchessboardapp.model.Point;
import com.magdaproject.knightchessboardapp.viewmodel.SharedViewmodel;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

public class ChessFragment extends Fragment {

    public  static final String TAG = ChessFragment.class.getName();

    private FragmentChessBinding mFragmentChessBinding;

    private SharedViewmodel mSharedViewmodel;

    private ChessSquareAdapter mChessSquareAdapter;

    public static final int DarkChessColor = R.color.colorBrown;

    public static final int LightChessColor = R.color.colorGold;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentChessBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chess,container,false);
        View chessRootView = mFragmentChessBinding.getRoot();
        mChessSquareAdapter = new ChessSquareAdapter(mSelectListener, getActivity());
        mFragmentChessBinding.chessBoard.setAdapter(mChessSquareAdapter);
        return  chessRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedViewmodel = ViewModelProviders.of(getActivity()).get(SharedViewmodel.class);
        mSharedViewmodel.setGlobalToolbarVisibility(true);
        mSharedViewmodel.setSquareClicked(false);
        mFragmentChessBinding.setClickListener(SetBtn_Click);
        mFragmentChessBinding.setPathsClickListener(PathBtn_Click);
       // mFragmentChessBinding.setResetClickListener(ResetBtn_Click);
        mSharedViewmodel.getBoardDimension().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer dim) {
                mFragmentChessBinding.chessBoard.setLayoutManager(new GridLayoutManager(getActivity(),dim));
                mChessSquareAdapter.setAdapterList(GlobalUtils.createColorList(dim),dim);
            }
        });

        mSharedViewmodel.getSquareClicked().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });
    }

    private SelectListener mSelectListener = new SelectListener() {
        @Override
        public void onSquareSelect(Point point) {
            if(!mSharedViewmodel.getSquareClicked().getValue()) {
                mSharedViewmodel.setStartingPoint(point);
                mSharedViewmodel.setSquareClicked(true);
            }
            else {
                mSharedViewmodel.setEndingPoint(point);
                mFragmentChessBinding.setBtn.setVisibility(View.VISIBLE);
            }

        }

    };

    public ClickListener SetBtn_Click = new ClickListener() {
        @Override
        public void onItemClick() {
            mSharedViewmodel.getTotalPaths().observe(ChessFragment.this, new Observer<HashSet<ArrayList<com.magdaproject.knightchessboardapp.model.Point>>>() {
                @Override
                public void onChanged(HashSet<ArrayList<com.magdaproject.knightchessboardapp.model.Point>> arrayLists) {
                    mFragmentChessBinding.totalPathsTxt.setText("There were found "+String.valueOf(arrayLists.size())+ " paths. SHOW");

                }
            });
        }
    };

    public PathsClickListener PathBtn_Click = new PathsClickListener() {
        @Override
        public void onPathsClick() {
         mSharedViewmodel.setShowPathFragment(ShowPathDialog.newInstance("KNIGHT PATHS"));
        }
    };





}
