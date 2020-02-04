package com.magdaproject.knightchessboardapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.magdaproject.knightchessboardapp.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ShowPathDialog extends DialogFragment {



    public ShowPathDialog() {}

    public static ShowPathDialog newInstance(String title) {
        ShowPathDialog frag = new ShowPathDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.totla_path_list, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
