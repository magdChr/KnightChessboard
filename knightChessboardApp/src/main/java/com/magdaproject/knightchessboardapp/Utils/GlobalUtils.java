package com.magdaproject.knightchessboardapp.Utils;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.model.Point;
import com.magdaproject.knightchessboardapp.ui.ChessFragment;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

public class GlobalUtils {

    public static void setstatusBarColor(Activity activity, int color) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));
    }

    public static Integer[] createColorList(int dim) {
        ArrayList<Integer> helperBoard = createHelperBoard(dim);
        int chessDim = dim * dim;
        Integer[] colorList = new Integer[chessDim];
               return helperBoard.toArray(colorList);
        }



    private static ArrayList<Integer> createHelperBoard(int dim) {
        int[][] board = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (((i % 2 == 0) && (j % 2 == 0))||((i % 2 != 0) && (j % 2 != 0)))
                    board[i][j] = ChessFragment.DarkChessColor;
                else board[i][j] = ChessFragment.LightChessColor;
            }
        }
        ArrayList<Integer> arrayBoard = new ArrayList<>();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                arrayBoard.add(board[i][j]);
            }
        }
        return arrayBoard;
    }

    public static Point convertToPoint(int position, int dim){
        int Xdim = position % dim;
        int Ydim = position / dim;
        return new Point(Xdim, Ydim);
    }
}