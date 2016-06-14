package ldurazo.github.pokeapi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Evolution {

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

}