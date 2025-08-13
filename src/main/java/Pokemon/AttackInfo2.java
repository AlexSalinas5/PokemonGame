package Pokemon;

import Elements.ElementType;

public interface AttackInfo2 {

  String getAttackName2();

  String getAttackDescription2();

  int getAttackDamage2();

  int getAttackResult2(ElementType elementTypeOfOpponentPokemon);
}
