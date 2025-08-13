package Elements.Electric;

import Elements.ElementType;
import Elements.Water.WaterType;

public class ElectricType extends ElementType {

  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    if (elementTypeOfOpponentPokemon instanceof WaterType) {
      return 20;
    } else {
      return 0;
    }
  }
}
