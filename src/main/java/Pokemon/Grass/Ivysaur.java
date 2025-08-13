package Pokemon.Grass;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Grass.GrassType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.AttackInfo2;
import Pokemon.PokemonAndHealth;

public class Ivysaur extends Pokemon implements AttackInfo1, AttackInfo2 {

  public Ivysaur() {
    super("Ivysaur", PokemonAndHealth.IVYSAUR.getHealth(), new GrassType(), PokemonArt.ivysaurArt);
  }

  @Override
  public String getAttackName1() {
    return "Vine Whip";
  }

  @Override
  public String getAttackDescription1() {
    return "Ivysaur lashes out with its sturdy vines, striking the opponent with swift and precise attacks.";
  }

  @Override
  public int getAttackDamage1() {
    return 30;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return getAttackDamage1()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  @Override
  public String getAttackName2() {
    return "Poisonpowder";
  }

  @Override
  public String getAttackDescription2() {
    return "The defending player's Pokémon is now poisoned.";
  }

  @Override
  public int getAttackDamage2() {
    return 20;
  }

  @Override
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    return getAttackDamage1()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    int AttackInfo1 = 1;
    if (attack == AttackInfo1) {
      OpponentPokemon.setHealth(
          OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
    } else {
      OpponentPokemon.setHealth(
          OpponentPokemon.getHealth() - getAttackResult2(OpponentPokemon.getElementType()));
      OpponentPokemon.setTellsIfPokemonIsPoisoned(true);
    }
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
            + getAttackDescription1()
            + "\nAttack: "
            + getAttackName2()
            + "\nDamage: "
            + getAttackDamage2()
            + "\nAttack Description: "
            + getAttackDescription2());
  }
}
