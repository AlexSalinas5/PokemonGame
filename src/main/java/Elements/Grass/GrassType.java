package Elements.Grass;

import Elements.ElementType;

/**
 * Represents the Grass elemental type.
 * <p>
 * Grass-type attacks currently do not deal bonus damage against
 * any opposing elemental types.
 * </p>
 */
public class GrassType implements ElementType {

  /**
   * Calculates bonus attack damage when a Grass-type Pokémon
   * attacks an opponent Pokémon.
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return {@code 0}, as Grass types do not receive bonus damage
   */
  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    return 0;
  }
}
