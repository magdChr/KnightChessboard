package com.magdaproject.knightchessboardapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.databinding.TotlaPathListBinding;
import com.magdaproject.knightchessboardapp.model.Point;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class ShowPathDialog extends DialogFragment {

    private TotlaPathListBinding mTotlaPathListBinding;

    public ShowPathDialog() {
    }

    public static ShowPathDialog newInstance(HashSet<ArrayList<Point>> pathList) {
        ShowPathDialog frag = new ShowPathDialog();
        Bundle args = new Bundle();
        //args.putParcelable("path_data", pathList);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTotlaPathListBinding = DataBindingUtil.inflate(inflater, R.layout.totla_path_list,container,false);
        return mTotlaPathListBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

       // mTotlaPathListBinding.reposList.setAdapter();
    }
}
