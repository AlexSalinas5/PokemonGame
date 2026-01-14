package Elements.Electric;

import Elements.ElementType;
import Elements.Water.WaterType;

/**
 * Represents the Electric elemental type.
 * <p>
 * Electric-type attacks deal bonus damage when used against
 * Water-type Pokémon.
 * </p>
 */
public class ElectricType implements ElementType {

  /**
   * Calculates bonus attack damage when an Electric-type Pokémon
   * attacks an opponent Pokémon.
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return bonus damage against Water types; {@code 0} otherwise
   */
  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    int bonusDamage;

    if (elementTypeOfOpponentPokemon instanceof WaterType) {
      bonusDamage = 20;
      return bonusDamage;
    } else {
      bonusDamage = 0;
      return bonusDamage;
    }
  }
}
