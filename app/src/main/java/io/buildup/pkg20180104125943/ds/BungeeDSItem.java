
package io.buildup.pkg20180104125943.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import buildup.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class BungeeDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("text1") public String text1;
    @SerializedName("text2") public String text2;
    @SerializedName("picture") public String picture;
    @SerializedName("text3") public String text3;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text1);
        dest.writeString(text2);
        dest.writeString(picture);
        dest.writeString(text3);
        dest.writeString(id);
    }

    public static final Creator<BungeeDSItem> CREATOR = new Creator<BungeeDSItem>() {
        @Override
        public BungeeDSItem createFromParcel(Parcel in) {
            BungeeDSItem item = new BungeeDSItem();

            item.text1 = in.readString();
            item.text2 = in.readString();
            item.picture = in.readString();
            item.text3 = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public BungeeDSItem[] newArray(int size) {
            return new BungeeDSItem[size];
        }
    };

}

