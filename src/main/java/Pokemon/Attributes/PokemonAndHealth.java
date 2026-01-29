package Pokemon.Attributes;

/**
 * Enumeration defining Pokémon species along with their default health values.
 *
 * <p>{@code PokemonAndHealth} provides a centralized mapping between a Pokémon's species and its
 * starting health. This enum is typically used during Pokémon creation to ensure consistent
 * initialization of health values across the game.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * int pikachuHealth = PokemonAndHealth.PIKACHU.getHealth();
 * }</pre>
 */
public enum PokemonAndHealth {

  /** Pikachu's default health value. */
  PIKACHU(40),

  /** Raichu's default health value. */
  RAICHU(80),

  /** Charizard's default health value. */
  CHARIZARD(120),

  /** Charmander's default health value. */
  CHARMANDER(50),

  /** Charmeleon's default health value. */
  CHARMELEON(80),

  /** Bulbasaur's default health value. */
  BULBASAUR(40),

  /** Ivysaur's default health value. */
  IVYSAUR(60),

  /** Venusaur's default health value. */
  VENUSAUR(100),

  /** Blastoise's default health value. */
  BLASTOISE(100),

  /** Squirtle's default health value. */
  SQUIRTLE(50),

  /** Wartortle's default health value. */
  WARTORTLE(70);

  /** The default health associated with the Pokémon. */
  private final int health;

  /**
   * Constructs a {@code PokemonAndHealth} enum constant with the specified default health value.
   *
   * @param health the starting health of the Pokémon
   */
  PokemonAndHealth(int health) {
    this.health = health;
  }

  /**
   * Returns the default health value for this Pokémon.
   *
   * @return the Pokémon's starting health
   */
  public int getHealth() {
    return health;
  }
}
