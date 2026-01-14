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
 * Factory class responsible for creating {@link Pokemon} instances.
 * <p>
 * The {@code PokemonFactory} centralizes Pokémon object creation based on
 * a provided Pokémon name. This follows the Factory design pattern and
 * allows Pokémon instantiation logic to be maintained in a single location.
 * </p>
 */
public class PokemonFactory {

  /**
   * Creates and returns a {@link Pokemon} instance corresponding to the
   * given Pokémon name.
   * <p>
   * The provided name is case-insensitive. If the Pokémon name is not
   * recognized, an {@link IllegalArgumentException} is thrown, indicating
   * that the factory must be updated to support the requested Pokémon.
   * </p>
   *
   * @param pokemonToBeCreated the name of the Pokémon to create
   * @return a new instance of the specified {@link Pokemon}
   * @throws IllegalArgumentException if the Pokémon name is not supported
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
