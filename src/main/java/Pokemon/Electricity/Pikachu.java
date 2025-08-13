package Pokemon.Electricity;

import ASCIIArt.PokemonArt;
import Elements.Electric.ElectricType;
import Elements.ElementType;
import Pokemon.Pokemon;
import Pokemon.AttackInfo1;
import Pokemon.AttackInfo2;
import Pokemon.PokemonAndHealth;
import java.util.Random;

public class Pikachu extends Pokemon implements AttackInfo1, AttackInfo2 {

  public Pikachu() {
    super(
        "Pikachu", PokemonAndHealth.PIKACHU.getHealth(), new ElectricType(), PokemonArt.pikachuArt);
  }

  @Override
  public String getAttackName1() {
    return "Gnaw";
  }

  @Override
  public String getAttackDescription1() {
    return "Pikachu uses its sharp teeth to bite down on its target, delivering a quick and painful strike.";
  }

  @Override
  public int getAttackDamage1() {
    return 10;
  }

  @Override
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return getAttackDamage1()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  @Override
  public String getAttackName2() {
    return "Thunder Jolt";
  }

  @Override
  public String getAttackDescription2() {
    return "Flip a coin. If tails, Pikachu does 10 damage to itself.";
  }

  @Override
  public int getAttackDamage2() {
    return 30;
  }

  @Override
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int coinFlipAnswer = random.nextInt(2);

    if (coinFlipAnswer == 0) {
      System.out.println("Unfortunately, you rolled tails, so you damage yourself!");
      this.setHealthIfPokemonDamagesItsSelf(10);
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
