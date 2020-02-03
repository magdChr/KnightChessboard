package com.magdaproject.knightchessboardapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Point implements Parcelable {

    private int Xdim;
    private int Ydim;

    public Point(int x, int y){
        this.Xdim = x;
        this.Ydim = y;
    }

    protected Point(Parcel in) {
        Xdim = in.readInt();
        Ydim = in.readInt();
    }

    public static final Creator<Point> CREATOR = new Creator<Point>() {
        @Override
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        @Override
        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

    public int getXdim() {
        return Xdim;
    }

    public int getYdim() {
        return Ydim;
    }

    public void setXdim(int xdim) {
        Xdim = xdim;
    }

    public void setYdim(int ydim) {
        Ydim = ydim;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Xdim);
        dest.writeInt(Ydim);
    }
}

