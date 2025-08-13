package Pokemon;

import Elements.ElementType;

public interface AttackInfo1 {

  String getAttackName1();

  String getAttackDescription1();

  int getAttackDamage1();

  int getAttackResult1(ElementType elementTypeOfOpponentPokemon);
}
