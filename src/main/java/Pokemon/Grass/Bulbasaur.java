package Pokemon.Grass;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Grass.GrassType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.PokemonAndHealth;

public class Bulbasaur extends Pokemon implements AttackInfo1 {

  public Bulbasaur() {
    super(
        "Bulbasaur",
        PokemonAndHealth.BULBASAUR.getHealth(),
        new GrassType(),
        PokemonArt.bulbasaurArt);
  }

  @Override
  public String getAttackName1() {
    return "Leech Seed";
  }

  @Override
  public String getAttackDescription1() {
    return "This attack does 20 damage. Heal 10 HP from this Pokémon.";
  }

  @Override
  public int getAttackDamage1() {
    return 20;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    this.setHealthIfPokemonHealsItsSelf(getHealth(), 10);
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
