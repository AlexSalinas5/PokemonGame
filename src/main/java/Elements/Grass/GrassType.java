package Elements.Grass;

import Elements.ElementType;

/**
 * Represents the Grass elemental type.
 *
 * <p>Grass-type attacks currently do not receive any bonus damage when used against opposing
 * elemental types.
 */
public class GrassType implements ElementType {

  /**
   * Determines the bonus damage applied when a Grass-type Pokémon attacks an opponent Pokémon of a
   * specific elemental type.
   *
   * <p>No bonus damage is applied regardless of the opponent’s elemental type.
   *
   * @param elementTypeOfOpponentPokemon the elemental type of the opponent Pokémon
   * @return the bonus attack damage applied, which is always {@code 0}
   */
  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    return 0;
  }
}
