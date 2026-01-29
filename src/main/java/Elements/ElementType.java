package Elements;

/**
 * Represents an elemental type in the game (e.g., Fire, Water, Grass).
 *
 * <p>Each {@code ElementType} defines how much bonus attack damage is applied when attacking a
 * Pokémon of another elemental type. Concrete implementations must implement the bonus damage
 * calculation based on type effectiveness.
 */
public interface ElementType {

  /**
   * Determines the bonus attack damage applied when a Pokémon of this elemental type attacks an
   * opponent Pokémon of a specific elemental type.
   *
   * <p>Implementations should define the bonus based on type matchups. For example, Fire-type
   * attacks may deal bonus damage against Grass-type Pokémon, while Grass-type attacks may not have
   * any bonus.
   *
   * @param elementTypeOfOpponentPokemon the elemental type of the opponent Pokémon
   * @return the bonus attack damage applied, which may be {@code 0} if there is no type advantage
   */
  int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon);
}
