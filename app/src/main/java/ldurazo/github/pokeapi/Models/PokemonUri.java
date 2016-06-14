package ldurazo.github.pokeapi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PokemonUri implements Comparable {
    //TODO El iván va a hacer override de hashcode e equals sin usar librerías

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("resource_uri")
    @Expose
    private String resourceUri;

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The resourceUri
     */
    public String getResourceUri() {
        return resourceUri;
    }

    /**
     * @param resourceUri The resource_uri
     */
    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    @Override
    public int compareTo(Object another) {
        PokemonUri anotherPokemon = (PokemonUri) another;
        Integer anotherPokemonNum = getPokemonNum(anotherPokemon);
        Integer pokemonNum = getPokemonNum(this);
        return pokemonNum.compareTo(anotherPokemonNum);
    }

    public int getPokemonNum(PokemonUri anotherPokemon) {
        String[] pokeUri = anotherPokemon.getResourceUri().split("\\/");
        String pokeNum = pokeUri[pokeUri.length-1];
        return Integer.parseInt(pokeNum);
    }
}