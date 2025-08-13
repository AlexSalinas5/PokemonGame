package Elements.Water;

import Elements.ElementType;
import Elements.Fire.FireType;

public class WaterType extends ElementType {

  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    if (elementTypeOfOpponentPokemon instanceof FireType) {
      return 20;
    } else {
      return 0;
    }
  }
}
