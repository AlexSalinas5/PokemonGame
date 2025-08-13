package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.PokemonAndHealth;

public class Blastoise extends Pokemon implements AttackInfo1 {
  int attackDamage = 40;

  public Blastoise() {
    super(
        "Blastoise",
        PokemonAndHealth.BLASTOISE.getHealth(),
        new WaterType(),
        PokemonArt.blastoiseArt);
  }

  @Override
  public String getAttackName1() {
    return "Hydro Pump";
  }

  @Override
  public String getAttackDescription1() {
    return "After each attack use, attack damage increases by 10.";
  }

  @Override
  public int getAttackDamage1() {
    return this.attackDamage;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    int firstTimeAttacking = 0;

    if (this.getCounterToIncreaseAttackDamage() == firstTimeAttacking) {
      IncreaseCounterToIncreaseAttackDamage();
      return getAttackDamage1()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    } else {
      int bonusDamage = 10;
      int calibratesCounterToIncreaseAttackDamage = 1;

      IncreaseCounterToIncreaseAttackDamage();
      return this.attackDamage
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon)
          + ((this.getCounterToIncreaseAttackDamage() - calibratesCounterToIncreaseAttackDamage)
              * bonusDamage);
    }
  }

  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }

  @Override
  public void getAttackAndDamageInfo() {
    System.out.println("Your Pokémon: " + getName() + "\nYour health: " + getHealth());

    printBuffsAndDebuffs();

    System.out.println(
        "\nAttack: "
            + getAttackName1()
            + "\nDamage: "
            + getAttackDamage1()
            + "\nAttack Description: "
            + getAttackDescription1());
  }
}
