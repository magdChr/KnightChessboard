package com.magdaproject.knightchessboardapp.Utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.magdaproject.knightchessboardapp.ui.ChessFragment;

import androidx.core.content.ContextCompat;

public class GlobalUtils {

    public static void setstatusBarColor(Activity activity, int color){
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }

    public static int[][] createColorList(int dim){
        int[][] colorList = new int[dim][dim];
        for(int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i % 2 == 0)) {
                    colorList[i][j] = ChessFragment.DarkChessColor;
                } else {
                    colorList[i][j] = ChessFragment.LightChessColor;
                }
            }
        }
            return colorList;
    }

}
