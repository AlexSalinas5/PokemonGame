package Elements;

/**
 * Represents an elemental type in the game (e.g., Fire, Water, Grass).
 * <p>
 * Each {@code ElementType} defines how much bonus attack damage is applied
 * when attacking a Pokémon of another element type. Concrete implementations
 * must implement the damage calculation logic based on type matchups.
 * </p>
 */
public interface ElementType {

  /**
   * Determines the bonus attack damage dealt to an opponent Pokémon
   * based on the interaction between this element type and the
   * opponent's element type.
   *
   * @param elementTypeOfOpponentPokemon the element type of the opposing Pokémon
   * @return the bonus damage value (may be positive or zero)
   */
  int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon);
}
