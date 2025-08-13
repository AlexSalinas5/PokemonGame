package Pokemon.Fire;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Fire.FireType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.PokemonAndHealth;

public class Charmeleon extends Pokemon implements AttackInfo1 {

  public Charmeleon() {
    super(
        "Charmeleon",
        PokemonAndHealth.CHARMELEON.getHealth(),
        new FireType(),
        PokemonArt.charmeleonArt);
  }

  @Override
  public String getAttackName1() {
    return "Flamethrower";
  }

  @Override
  public String getAttackDescription1() {
    return "Charmeleon unleashes a stream of scorching flames, burning its target with intense heat.";
  }

  @Override
  public int getAttackDamage1() {
    return 50;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return getAttackDamage1()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
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
