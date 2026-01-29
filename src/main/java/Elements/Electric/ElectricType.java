package Elements.Electric;

import Elements.ElementType;
import Elements.Water.WaterType;

/**
 * Represents the Electric elemental type.
 *
 * <p>Electric-type attacks are effective against Water-type Pokémon and deal additional bonus
 * damage when attacking them.
 */
public class ElectricType implements ElementType {

  /**
   * Determines the bonus damage applied when an Electric-type Pokémon attacks an opponent Pokémon
   * of a specific elemental type.
   *
   * <p>If the opponent is of type {@link WaterType}, a fixed bonus damage value is applied.
   * Otherwise, no bonus damage is granted.
   *
   * @param elementTypeOfOpponentPokemon the elemental type of the opponent Pokémon
   * @return the bonus attack damage applied based on elemental effectiveness
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
