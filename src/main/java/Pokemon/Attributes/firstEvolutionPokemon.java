package Pokemon.Attributes;

/**
 * Enum representing Pokémon that are in their first evolution stage.
 *
 * <p>Each constant in this enum corresponds to a Pokémon that is the base form in its evolutionary
 * line. These Pokémon can evolve into a second-stage Pokémon and then a third-stage Pokémon as
 * defined in their respective evolution lines.
 *
 * <p>Examples of first-stage Pokémon and their evolutions:
 *
 * <ul>
 *   <li>{@link #CHARMANDER} – evolves into Charmeleon → Charizard
 *   <li>{@link #BULBASAUR} – evolves into Ivysaur → Venusaur
 *   <li>{@link #SQUIRTLE} – evolves into Wartortle → Blastoise
 * </ul>
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * firstEvolutionPokemon basePokemon = firstEvolutionPokemon.CHARMANDER;
 * }</pre>
 */
public enum firstEvolutionPokemon {

  /** Charmander, first evolution. */
  CHARMANDER,

  /** Bulbasaur, first evolution. */
  BULBASAUR,

  /** Squirtle, first evolution. */
  SQUIRTLE
}
