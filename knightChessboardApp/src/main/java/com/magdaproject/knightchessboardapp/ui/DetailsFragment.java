package com.magdaproject.knightchessboardapp.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.databinding.FragmentDetailBinding;
import com.magdaproject.knightchessboardapp.viewmodel.SharedViewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

public class DetailsFragment extends Fragment {

    public static final String BOARD_DIMENSION = "board_dimension";

    public static final String MAX_MOVES = "max_moves";

    private FragmentDetailBinding mFragmentDetailBinding;

    private SharedViewmodel mSharedViewmodel;

    private EditText boardDimension;

    private EditText maxMoves;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        View detailsRootView = mFragmentDetailBinding.getRoot();
        return detailsRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSharedViewmodel = ViewModelProviders.of(getActivity()).get(SharedViewmodel.class);
        mSharedViewmodel.setGlobalToolbarVisibility(true);
        mFragmentDetailBinding.setLifecycleOwner(this);
        mFragmentDetailBinding.dimenPicker.setMinValue(6);
        mFragmentDetailBinding.dimenPicker.setMaxValue(16);
        mSharedViewmodel.setBoardDimension(6);
        mFragmentDetailBinding.dimenPicker.setOnValueChangedListener(DimensionValueListener);
        mFragmentDetailBinding.maxMovesPicker.addTextChangedListener(MaxMoveTextWatcher);
    }

    public NumberPicker.OnValueChangeListener DimensionValueListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mSharedViewmodel.setBoardDimension(newVal);
        }
    };

    public TextWatcher MaxMoveTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mSharedViewmodel.setMaxMoves(Integer.valueOf(mFragmentDetailBinding.maxMovesPicker.getText().toString()));
            mSharedViewmodel.setFragmentToAdd(new ChessFragment());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
