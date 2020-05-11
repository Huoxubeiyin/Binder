package com.allies.frameworkBinder.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by allies on 17-10-28.
 */

public class MyData implements Parcelable {

    private int id;
    private String command;
    private String retV;

    protected MyData(Parcel in) {
        id = in.readInt();
        command = in.readString();
        retV = in.readString();
    }

    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(command);
        dest.writeString(retV);
    }

}
