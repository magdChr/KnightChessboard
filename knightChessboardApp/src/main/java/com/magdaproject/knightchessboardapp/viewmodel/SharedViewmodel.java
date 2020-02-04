package com.magdaproject.knightchessboardapp.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;

import com.magdaproject.knightchessboardapp.model.Point;
import com.magdaproject.knightchessboardapp.service.PathIntentService;

import java.util.ArrayList;
import java.util.HashSet;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SharedViewmodel extends AndroidViewModel {

    private MutableLiveData<Boolean> globalToolbarVisibility = new MutableLiveData<>();

    private MutableLiveData<Integer> boardDimension = new MutableLiveData<>();

    private MutableLiveData<Integer> maxMoves = new MutableLiveData<>();

    private MutableLiveData<Point> startingPoint = new MutableLiveData<>();

    private MutableLiveData<Point> endingPoint = new MutableLiveData<>();

    private MutableLiveData<Boolean> squareClicked = new MutableLiveData<>();

    private MutableLiveData<DialogFragment> showPathFragment = new MutableLiveData<>();

    public void setTotalPaths(MutableLiveData<HashSet<ArrayList<Point>>> totalPaths) {
        this.totalPaths = totalPaths;
    }

    MutableLiveData<HashSet<ArrayList<Point>>> totalPaths = new MutableLiveData<>();

    MutableLiveData<Fragment> fragmentToAdd = new MutableLiveData<>();

    MutableLiveData<Fragment> fragmentToremove = new MutableLiveData<>();

    Context context;

    private static final String STARTING_POINT = "starting_point";

    private static final String ENDING_POINT = "ending_point";

    private static final String RESULT_RECEIVER = "result_receiver";

    private static final String TOTAL_PATHS = "total_paths";

    private static final String MAX_MOVES = "max_moves";

    private static final String BOARD_DIMENSION = "board_dimension";

    public SharedViewmodel(@NonNull Application application) {

        super(application);
        this.context = application;
    }

    public MutableLiveData<Boolean> getGlobalToolbarVisibility() {
        return globalToolbarVisibility;
    }

    public void setGlobalToolbarVisibility(boolean action) {
        this.globalToolbarVisibility.setValue(action);
    }

    public void resetTotalPaths() {
        this.totalPaths.getValue().clear();
    }

    public MutableLiveData<Integer> getBoardDimension() {
        return boardDimension;
    }

    public void setBoardDimension(int boardDimension) {
        this.boardDimension.setValue(boardDimension);
    }

    public MutableLiveData<Integer> getMaxMoves() {
        return maxMoves;
    }

    public void setMaxMoves(int maxMoves) {
        this.maxMoves.setValue(maxMoves);
    }

    public MutableLiveData<Point> getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint.setValue(startingPoint);
    }

    public MutableLiveData<Point> getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Point endingPoint) {
        this.endingPoint.setValue(endingPoint);
    }

    public MutableLiveData<Fragment> getFragmentToAdd() {
        return fragmentToAdd;
    }

    public void setFragmentToAdd(Fragment fragmentToAdd) {
        this.fragmentToAdd.setValue(fragmentToAdd);
    }

    public MutableLiveData<Fragment> getFragmentToremove() {
        return fragmentToremove;
    }

    public void setFragmentToremove(Fragment fragmentToremove) {
        this.fragmentToremove.setValue(fragmentToremove);
    }

    public MutableLiveData<Boolean> getSquareClicked() {
        return squareClicked;
    }

    public void setSquareClicked(boolean squareClicked) {
        this.squareClicked.setValue(squareClicked);
    }

    public MutableLiveData<DialogFragment> getShowPathFragment() {
        return showPathFragment;
    }

    public void setShowPathFragment(DialogFragment showPathFragment) {
        this.showPathFragment.setValue(showPathFragment);
    }

    public class PathResultReceiver extends ResultReceiver {

        public PathResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultCode == Activity.RESULT_OK) {
                HashSet<ArrayList<Point>> total_paths = (HashSet<ArrayList<Point>>) resultData.getSerializable(TOTAL_PATHS);
                totalPaths.setValue(total_paths);
            } else {

            }
            super.onReceiveResult(resultCode, resultData);
        }
    }


    public LiveData<HashSet<ArrayList<Point>>> getTotalPaths() {
        Intent intentPathService = new Intent(context, PathIntentService.class);
        intentPathService.putExtra(STARTING_POINT, getStartingPoint().getValue());
        //intentPathService.putExtra(STARTING_POINT,getStartingPoint().getValue().getYdim());
        intentPathService.putExtra(ENDING_POINT, getEndingPoint().getValue());
        //intentPathService.putExtra(ENDING_POINT,getEndingPoint().getValue().getYdim());
        intentPathService.putExtra(MAX_MOVES, getMaxMoves().getValue());
        intentPathService.putExtra(BOARD_DIMENSION,getBoardDimension().getValue());
        //intentPathService.putExtra(STARTING_POINT, new Point(2, 3));
        //intentPathService.putExtra(ENDING_POINT, new Point(0, 4));
        intentPathService.putExtra(RESULT_RECEIVER, new PathResultReceiver(new Handler(Looper.getMainLooper())));
        context.startService(intentPathService);
        return totalPaths;
    }


}
