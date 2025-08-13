package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.AttackInfo2;
import java.util.Random;
import Pokemon.PokemonAndHealth;

public class Wartortle extends Pokemon implements AttackInfo1, AttackInfo2 {

  public Wartortle() {
    super(
        "Wartortle",
        PokemonAndHealth.WARTORTLE.getHealth(),
        new WaterType(),
        PokemonArt.wartortleArt);
  }

  @Override
  public String getAttackName1() {
    return "Withdraw";
  }

  @Override
  public String getAttackDescription1() {
    return "Flip a coin. If heads, prevents all damage done to Wartortle during opponent's next turn.";
  }

  @Override
  public int getAttackDamage1() {
    return 0;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int a = random.nextInt(2);

    if (a == 1) {
      setTellsIfPokemonIsInvincible(true);
      System.out.println("You rolled heads, so you become invincible!");
    } else {
      System.out.println("Unfortunately, you rolled tails, so you do not become invincible!");
    }
    return 0;
  }

  @Override
  public String getAttackName2() {
    return "Bite";
  }

  @Override
  public String getAttackDescription2() {
    return "Wartortle sinks its sharp fangs into the foe, delivering a fierce and decisive bite.";
  }

  @Override
  public int getAttackDamage2() {
    return 40;
  }

  @Override
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    return getAttackDamage2()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    int AttackInfo2 = 2;
    if (attack == AttackInfo2) {
      OpponentPokemon.setHealth(
          OpponentPokemon.getHealth() - getAttackResult2(OpponentPokemon.getElementType()));
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
