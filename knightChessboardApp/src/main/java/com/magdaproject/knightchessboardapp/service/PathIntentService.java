package com.magdaproject.knightchessboardapp.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.media.Session2Command;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.magdaproject.knightchessboardapp.model.Point;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.annotation.Nullable;

public class PathIntentService extends IntentService {

    private static final String STARTING_POINT = "starting_point";

    private static final String ENDING_POINT = "ending_point";

    private static final String RESULT_RECEIVER = "result_receiver";

    private static final String TOTAL_PATHS = "total_paths";

    private static final String MAX_MOVES = "max_moves";

    private static final String BOARD_DIMENSION = "board_dimension";
    private int boardDimension;
    private int maxMoves;
    private HashSet<ArrayList<Point>> total_paths = new HashSet<>();
    Point target;
    //all possible moves a knight can do
    final int[] possibleXmove = {-2, 1, -1, 2, -2, -1, 1, 2};
    final int[] possibleYmove = {1, 2, 2, 1, -1, -2, -2, -1};

    private static final String TAG = "PATH_SERVICE";

    public PathIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Point starting_point = intent.getParcelableExtra(STARTING_POINT);
        target = intent.getParcelableExtra(ENDING_POINT);
        maxMoves = intent.getIntExtra(MAX_MOVES,3);
        boardDimension = intent.getIntExtra(BOARD_DIMENSION,6);
        ResultReceiver resultReceiver = intent.getParcelableExtra(RESULT_RECEIVER);
        calculatePossiblePaths(starting_point.getXdim(), starting_point.getYdim(),new ArrayList<Point>());
        resultReceiver.send(Activity.RESULT_OK, bundleTotalPaths());
    }

    private void calculatePossiblePaths(int x, int y, ArrayList<Point> pathList) {


        Point current = new Point(x, y);

        if ((pathList.size() == maxMoves) && (((current.getXdim() != target.getXdim()) || (current.getYdim() != target.getYdim())))) {

            pathList.add(current);
            return;
        }

        //check to see if we have reached the target point
        if (((current.getXdim() == target.getXdim()) && (current.getYdim() == target.getYdim()))) {

            pathList.add(current);
            ArrayList<Point> temp = new ArrayList<>();
            temp.addAll(pathList);
            for(int i = 0; i<pathList.size();i++)
//            Log.e("point: ",pathList.get(i).getXdim()+","+pathList.get(i).getYdim()+" ");
//            Log.e("sieze",pathList.size()+" ");
//            Log.e("and","-------------------");

            //if(!(total_paths.contains(pathList)))//return;
            total_paths.add(temp);

            return;
        } else {
            pathList.add(current);//N++ TODO:Add here!!!
        }
        /*find all possible moves from point x to y, taking into account that there are always 8 possible moves from each place,
        either though some of them may be out of the board*/
        for (int i = 0; i < 8; i++) {
            //make a move
            int Xnext = x + possibleXmove[i];
            int Ynext = y + possibleYmove[i];
            if (checkPointValidity(Xnext, Ynext)) {

                calculatePossiblePaths(Xnext, Ynext, pathList);
                //When the calculation is over for on iteration Point, remove point and move on to the next one
                if (!pathList.isEmpty())
                    pathList.remove(pathList.size() - 1);
            }

        }


    }

    private boolean checkPointValidity(int x, int y) {
        if (x >= 0 && y >= 0 && x < boardDimension && y < boardDimension)
            return true;
        return false;
    }

    private Bundle bundleTotalPaths(){
        Bundle totalPaths = new Bundle();
        totalPaths.putSerializable(TOTAL_PATHS, total_paths);
        return totalPaths;
    }
}
