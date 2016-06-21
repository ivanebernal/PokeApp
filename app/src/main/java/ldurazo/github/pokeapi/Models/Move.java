package ldurazo.github.pokeapi.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Move implements Parcelable {

    @SerializedName("learn_type")
    @Expose
    private String learnType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;
    @SerializedName("level")
    @Expose
    private Integer level;

    protected Move(Parcel in) {
        learnType = in.readString();
        name = in.readString();
        resourceUri = in.readString();
    }

    public static final Creator<Move> CREATOR = new Creator<Move>() {
        @Override
        public Move createFromParcel(Parcel in) {
            return new Move(in);
        }

        @Override
        public Move[] newArray(int size) {
            return new Move[size];
        }
    };

    /**
     *
     * @return
     * The learnType
     */
    public String getLearnType() {
        return learnType;
    }

    /**
     *
     * @param learnType
     * The learn_type
     */
    public void setLearnType(String learnType) {
        this.learnType = learnType;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(learnType);
        dest.writeString(name);
        dest.writeString(resourceUri);
        dest.writeInt(level);
    }
}