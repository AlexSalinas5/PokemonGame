package Elements.Fire;

import Elements.ElementType;
import Elements.Grass.GrassType;

/**
 * Represents the Fire elemental type.
 *
 * <p>Fire-type attacks are effective against Grass-type Pokémon and deal additional bonus damage
 * when attacking them.
 */
public class FireType implements ElementType {

  /**
   * Determines the bonus damage applied when a Fire-type Pokémon attacks an opponent Pokémon of a
   * specific elemental type.
   *
   * <p>If the opponent is of type {@link GrassType}, a fixed bonus damage value is applied.
   * Otherwise, no bonus damage is granted.
   *
   * @param elementTypeOfOpponentPokemon the elemental type of the opponent Pokémon
   * @return the bonus attack damage applied based on elemental effectiveness
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
