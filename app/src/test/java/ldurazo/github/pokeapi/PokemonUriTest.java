package ldurazo.github.pokeapi;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ldurazo.github.pokeapi.Models.PokemonUri;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Iván on 12/06/2016.
 */
public class PokemonUriTest {
    @Test
    public void testSort(){
        PokemonUri pokemon1 = new PokemonUri();
        PokemonUri pokemon2 = new PokemonUri();
        PokemonUri pokemon3 = new PokemonUri();
        pokemon1.setName("Bulbasaur");
        pokemon2.setName("Charmander");
        pokemon3.setName("Squirtle");
        pokemon1.setResourceUri("api/v1/pokemon/1/");
        pokemon2.setResourceUri("api/v1/pokemon/2/");
        pokemon3.setResourceUri("api/v1/pokemon/3/");
        List<PokemonUri> pokemonList = new ArrayList<>();
        pokemonList.add(pokemon2);
        pokemonList.add(pokemon3);
        pokemonList.add(pokemon1);
        assertEquals("Charmander", pokemonList.get(0).getName());
        assertEquals("Squirtle", pokemonList.get(1).getName());
        assertEquals("Bulbasaur", pokemonList.get(2).getName());
        Collections.sort(pokemonList);
        assertEquals("Bulbasaur", pokemonList.get(0).getName());
        assertEquals("Charmander", pokemonList.get(1).getName());
        assertEquals("Squirtle", pokemonList.get(2).getName());
    }
}
