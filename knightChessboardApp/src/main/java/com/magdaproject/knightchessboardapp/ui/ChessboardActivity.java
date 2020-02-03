package com.magdaproject.knightchessboardapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.Utils.GlobalUtils;
import com.magdaproject.knightchessboardapp.databinding.ActivityMainBinding;
import com.magdaproject.knightchessboardapp.viewmodel.SharedViewmodel;

public class ChessboardActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;

    private SharedViewmodel mSharedViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android.os.Debug.waitForDebugger();
        setTheme(R.style.Theme_MaterialComponents_Light_NoActionBar);
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        GlobalUtils.setstatusBarColor(this,R.color.colorBlack);
        setSupportActionBar(((Toolbar) mActivityMainBinding.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSharedViewmodel = ViewModelProviders.of(this).get(SharedViewmodel.class);
        mSharedViewmodel.getGlobalToolbarVisibility().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!aBoolean)
                    getSupportActionBar().hide();
                getSupportActionBar().show();
            }
        });

        mSharedViewmodel.getFragmentToAdd().observe(this, new Observer<Fragment>() {
            @Override
            public void onChanged(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                //ChessFragment chessFragment = new ChessFragment();
                fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName()).addToBackStack(null).commit();
            }
        });

        //load DetailsFragment
        if ((savedInstanceState == null) && (mActivityMainBinding.fragmentContainer != null)) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            DetailsFragment detailsFragment = new DetailsFragment();
            fragmentTransaction.add(R.id.fragment_container, detailsFragment, detailsFragment.getClass().getName()).addToBackStack(null).commit();

        }
    }
}
