package Pokemon;

import Pokemon.Electricity.Pikachu;
import Pokemon.Electricity.Raichu;
import Pokemon.Fire.Charizard;
import Pokemon.Fire.Charmander;
import Pokemon.Fire.Charmeleon;
import Pokemon.Grass.Bulbasaur;
import Pokemon.Grass.Ivysaur;
import Pokemon.Grass.Venusaur;
import Pokemon.Water.Blastoise;
import Pokemon.Water.Squirtle;
import Pokemon.Water.Wartortle;

/**
 * Factory class for creating {@link Pokemon} instances.
 *
 * <p>This class implements the Factory design pattern, centralizing the creation of Pokémon objects
 * based on their names. Pokémon names are treated case-insensitively. Adding new Pokémon requires
 * updating this factory.
 */
public class PokemonFactory {

  /**
   * Creates a new {@link Pokemon} instance based on the provided Pokémon name.
   *
   * <p>The method is case-insensitive. If the name does not match any known Pokémon, an {@link
   * IllegalArgumentException} is thrown.
   *
   * @param pokemonToBeCreated the name of the Pokémon to create
   * @return a new instance of the specified {@link Pokemon}
   * @throws IllegalArgumentException if the Pokémon name is not recognized
   */
  public Pokemon pokemonFactory(String pokemonToBeCreated) {
    return switch (pokemonToBeCreated.toUpperCase()) {
      case "PIKACHU" -> new Pikachu();
      case "RAICHU" -> new Raichu();
      case "CHARMANDER" -> new Charmander();
      case "CHARMELEON" -> new Charmeleon();
      case "CHARIZARD" -> new Charizard();
      case "BULBASAUR" -> new Bulbasaur();
      case "IVYSAUR" -> new Ivysaur();
      case "VENUSAUR" -> new Venusaur();
      case "SQUIRTLE" -> new Squirtle();
      case "WARTORTLE" -> new Wartortle();
      case "BLASTOISE" -> new Blastoise();
      default ->
          throw new IllegalArgumentException(
              "The Pokémon factory must be updated to return the desired Pokémon.");
    };
  }
}
