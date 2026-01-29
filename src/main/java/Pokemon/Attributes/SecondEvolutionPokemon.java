package Pokemon.Attributes;

/**
 * Enumeration of Pokémon species that represent the second stage in their evolutionary line.
 *
 * <p>Each constant corresponds to a Pokémon that evolves from its first stage counterpart and can
 * further evolve into a third-stage Pokémon. This enum is typically used for tracking evolution
 * progress or determining available moves and stats for mid-evolution Pokémon.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * SecondEvolutionPokemon evo = SecondEvolutionPokemon.PIKACHU;
 * }</pre>
 */
public enum SecondEvolutionPokemon {

  /** Pikachu, no previous evolution (evolves directly into third evolution: Raichu). */
  PIKACHU,

  /** Charmeleon, second evolution of Charmander. */
  CHARMELEON,

  /** Ivysaur, second evolution of Bulbasaur. */
  IVYSAUR,

  /** Wartortle, second evolution of Squirtle. */
  WORTORTLE
}
