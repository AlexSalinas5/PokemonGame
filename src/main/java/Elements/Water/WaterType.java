package Elements.Water;

import Elements.ElementType;
import Elements.Fire.FireType;

/**
 * Represents the Water elemental type.
 * <p>
 * Water-type attacks deal bonus damage when used against
 * Fire-type Pokémon.
 * </p>
 */
public class WaterType implements ElementType {

  /**
   * Calculates bonus attack damage when a Water-type Pokémon
   * attacks an opponent Pokémon.
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return bonus damage against Fire types; {@code 0} otherwise
   */
  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    int bonusDamage;

    if (elementTypeOfOpponentPokemon instanceof FireType) {
      bonusDamage = 20;
      return bonusDamage;
    } else {
      bonusDamage = 0;
      return bonusDamage;
    }
  }
}
