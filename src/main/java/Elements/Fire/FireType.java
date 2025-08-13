package Elements.Fire;

import Elements.ElementType;
import Elements.Grass.GrassType;

public class FireType extends ElementType {

  @Override
  public int tellerOfBonusAttackDamage(ElementType elementTypeOfOpponentPokemon) {
    if (elementTypeOfOpponentPokemon instanceof GrassType) {
      return 20;
    } else {
      return 0;
    }
  }
}
