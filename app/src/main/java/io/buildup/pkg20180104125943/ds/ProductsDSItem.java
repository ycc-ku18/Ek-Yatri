
package io.buildup.pkg20180104125943.ds;
import android.graphics.Bitmap;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.Uri;

import buildup.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ProductsDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("name") public String name;
    @SerializedName("dataField0") public String dataField0;
    @SerializedName("price") public String price;
    @SerializedName("rating") public String rating;
    @SerializedName("picture") public String picture;
    @SerializedName("thumbnail") public String thumbnail;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;
    @SerializedName("thumbnailUri") public transient Uri thumbnailUri;

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
        dest.writeString(name);
        dest.writeString(dataField0);
        dest.writeString(price);
        dest.writeString(rating);
        dest.writeString(picture);
        dest.writeString(thumbnail);
        dest.writeString(id);
    }

    public static final Creator<ProductsDSItem> CREATOR = new Creator<ProductsDSItem>() {
        @Override
        public ProductsDSItem createFromParcel(Parcel in) {
            ProductsDSItem item = new ProductsDSItem();

            item.name = in.readString();
            item.dataField0 = in.readString();
            item.price = in.readString();
            item.rating = in.readString();
            item.picture = in.readString();
            item.thumbnail = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public ProductsDSItem[] newArray(int size) {
            return new ProductsDSItem[size];
        }
    };

}

