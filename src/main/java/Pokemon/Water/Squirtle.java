package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.PokemonAndHealth;

public class Squirtle extends Pokemon implements AttackInfo1 {

  public Squirtle() {
    super(
        "Squirtle", PokemonAndHealth.SQUIRTLE.getHealth(), new WaterType(), PokemonArt.squirtleArt);
  }

  @Override
  public String getAttackName1() {
    return "Shell Attack";
  }

  @Override
  public String getAttackDescription1() {
    return "Squirtle strikes with its hard shell, delivering a strong and steady hit.";
  }

  @Override
  public int getAttackDamage1() {
    return 20;
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
