package Pokemon.Grass;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Grass.GrassType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.PokemonAndHealth;

public class Venusaur extends Pokemon implements AttackInfo1 {

  public Venusaur() {
    super(
        "Venusaur", PokemonAndHealth.VENUSAUR.getHealth(), new GrassType(), PokemonArt.venusaurArt);
  }

  @Override
  public String getAttackName1() {
    return "Solarbeam";
  }

  @Override
  public String getAttackDescription1() {
    return "Venusaur absorbs sunlight and unleashes a powerful beam of solar energy, scorching its target with intense force.";
  }

  @Override
  public int getAttackDamage1() {
    return 60;
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
