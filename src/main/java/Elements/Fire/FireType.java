package Elements.Fire;

import Elements.ElementType;
import Elements.Grass.GrassType;

/**
 * Represents the Fire elemental type.
 * <p>
 * Fire-type attacks deal bonus damage when used against
 * Grass-type Pokémon.
 * </p>
 */
public class FireType implements ElementType {

  /**
   * Calculates bonus attack damage when a Fire-type Pokémon
   * attacks an opponent Pokémon.
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return bonus damage against Grass types; {@code 0} otherwise
   */
  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    int bonusDamage;

    if (elementTypeOfOpponentPokemon instanceof GrassType) {
      bonusDamage = 20;
      return bonusDamage;
    } else {
      bonusDamage = 0;
      return bonusDamage;
    }
  }
}
