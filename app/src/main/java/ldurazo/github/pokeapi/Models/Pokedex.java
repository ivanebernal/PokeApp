package ldurazo.github.pokeapi.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokedex {
    //TODO El iván va a hacer override de hashcode e equals sin usar librerías

    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("pokemon")
    @Expose
    private List<PokemonUri> pokemonUri = new ArrayList<>();
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;

    /**
     *
     * @return
     * The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(String created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
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
     * The pokemon
     */
    public List<PokemonUri> getPokemonUri() {
        return pokemonUri;
    }

    /**
     *
     * @param pokemonUri
     * The pokemon
     */
    public void setPokemonUri(List<PokemonUri> pokemonUri) {
        this.pokemonUri = pokemonUri;
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

}
