package ldurazo.github.pokeapi.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpriteUri implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;

    protected SpriteUri(Parcel in) {
        name = in.readString();
        resourceUri = in.readString();
    }

    public static final Creator<SpriteUri> CREATOR = new Creator<SpriteUri>() {
        @Override
        public SpriteUri createFromParcel(Parcel in) {
            return new SpriteUri(in);
        }

        @Override
        public SpriteUri[] newArray(int size) {
            return new SpriteUri[size];
        }
    };

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(resourceUri);
    }
}