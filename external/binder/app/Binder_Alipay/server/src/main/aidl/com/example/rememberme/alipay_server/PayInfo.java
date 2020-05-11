package com.example.rememberme.alipay_server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rememberme on 2018/7/12.
 * <p>
 * Email：891904833@qq.com
 * <p>
 * Description ：
 */
public class PayInfo implements Parcelable {

    private String data;
    private String local;
    private String desc;
    private float money;
    private boolean ok;
    private String errorInfo;

    public PayInfo() {
    }

    protected PayInfo(Parcel in) {
        data = in.readString();
        local = in.readString();
        desc = in.readString();
        money = in.readFloat();
        ok = in.readByte() != 0;
        errorInfo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeString(local);
        dest.writeString(desc);
        dest.writeFloat(money);
        dest.writeByte((byte) (ok ? 1 : 0));
        dest.writeString(errorInfo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PayInfo> CREATOR = new Creator<PayInfo>() {
        @Override
        public PayInfo createFromParcel(Parcel in) {
            return new PayInfo(in);
        }

        @Override
        public PayInfo[] newArray(int size) {
            return new PayInfo[size];
        }
    };

    public String getData() {
        return data;
    }

    public PayInfo setData(String data) {
        this.data = data;
        return this;
    }

    public String getLocal() {
        return local;
    }

    public PayInfo setLocal(String local) {
        this.local = local;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public PayInfo setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public float getMoney() {
        return money;
    }

    public PayInfo setMoney(float money) {
        this.money = money;
        return this;
    }

    public boolean isOk() {
        return ok;
    }

    public PayInfo setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public PayInfo setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
        return this;
    }

    @Override
    public String toString() {
        return "PayInfo{" +
                "data='" + data + '\'' +
                ", local='" + local + '\'' +
                ", desc='" + desc + '\'' +
                ", money=" + money +
                ", ok=" + ok +
                ", errorInfo='" + errorInfo + '\'' +
                '}';
    }
}
