package Pokemon.Electricity;

import ASCIIArt.PokemonArt;
import Elements.Electric.ElectricType;
import Elements.ElementType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.AttackInfo2;
import java.util.Random;
import Pokemon.PokemonAndHealth;

public class Raichu extends Pokemon implements AttackInfo1, AttackInfo2 {

  public Raichu() {
    super("Raichu", PokemonAndHealth.RAICHU.getHealth(), new ElectricType(), PokemonArt.raichuArt);
  }

  @Override
  public String getAttackName1() {
    return "Agility";
  }

  @Override
  public String getAttackDescription1() {
    return "Flip a coin. If heads, prevents all damage done to Raichu during opponent's next turn.";
  }

  @Override
  public int getAttackDamage1() {
    return 20;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int coinFlipAnswer = random.nextInt(2);

    if (coinFlipAnswer == 1) {
      setTellsIfPokemonIsInvincible(true);
      System.out.println("You rolled heads, so you become invincible!");
    } else {
      System.out.println("Unfortunately, you rolled tails, so you do not become invincible!");
    }

    return getAttackDamage1()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  @Override
  public String getAttackName2() {
    return "Thunder";
  }

  @Override
  public String getAttackDescription2() {
    return "Flip a coin. If tails, Raichu does 30 damage to itself.";
  }

  @Override
  public int getAttackDamage2() {
    return 60;
  }

  @Override
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int a = random.nextInt(2);

    if (a == 0) {
      System.out.println("Unfortunately, you rolled tails, so you damage yourself!");
      this.setHealthIfPokemonDamagesItsSelf(30);
      return getAttackDamage2()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    } else {
      System.out.println("You rolled heads, so you do not damage yourself!");
      return getAttackDamage2()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    }
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
