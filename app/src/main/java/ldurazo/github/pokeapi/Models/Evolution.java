package ldurazo.github.pokeapi.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evolution implements Parcelable {

    @SerializedName("level")
    @Expose
    private Integer level;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("to")
    @Expose
    private String to;

    protected Evolution(Parcel in) {
        method = in.readString();
        resourceUri = in.readString();
        to = in.readString();
    }

    public static final Creator<Evolution> CREATOR = new Creator<Evolution>() {
        @Override
        public Evolution createFromParcel(Parcel in) {
            return new Evolution(in);
        }

        @Override
        public Evolution[] newArray(int size) {
            return new Evolution[size];
        }
    };

    /**
     *
     * @return
     * The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *
     * @param level
     * The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     *
     * @return
     * The method
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method
     * The method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     *
     * @return
     * The resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     *
     * @param resourceUri
     * The resource_uri
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    /**
     *
     * @return
     * The to
     */
    public String getTo() {
        return to;
    }

    /**
     *
     * @param to
     * The to
     */
    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(method);
        dest.writeString(resourceUri);
        dest.writeString(to);
        dest.writeInt(level);
    }
}