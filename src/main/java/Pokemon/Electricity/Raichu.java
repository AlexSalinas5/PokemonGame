package Pokemon.Electricity;

import ASCIIArt.PokemonArt;
import Elements.Electric.ElectricType;
import Elements.ElementType;
import Pokemon.Pokemon;
import java.util.Random;
import Pokemon.Attributes.PokemonAndHealth;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Raichu, an Electric-type Pokémon.
 *
 * <p>Raichu has two available attacks:
 *
 * <ul>
 *   <li>{@code Agility}: Deals base damage plus any elemental bonus. Performs a coin flip; if
 *       heads, Raichu becomes invincible for the opponent's next turn.
 *   <li>{@code Thunder}: Deals base damage plus any elemental bonus. Performs a coin flip; if
 *       tails, Raichu damages itself for 30 HP.
 * </ul>
 *
 * This class defines Raichu's specific attack logic and overrides the abstract {@link
 * Pokemon#hitOpponent(Pokemon, int)} method.
 */
public class Raichu extends Pokemon {

  /** Array of attacks available to Raichu. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Raichu Pokémon with default health, element type, ASCII art, and its two
   * attacks.
   */
  public Raichu() {
    super(
        "Raichu",
        PokemonAndHealth.RAICHU.getHealth(),
        new ElectricType(),
        PokemonArt.raichuArt,
        new AttackInfo[] {
          new AttackInfo(
              "Agility",
              "Flip a coin. If heads, prevents all damage done to Raichu during opponent's next turn.",
              20),
          new AttackInfo("Thunder", "Flip a coin. If tails, Raichu does 30 damage to itself.", 60)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Raichu's {@code Agility} attack.
   *
   * <p>Deals damage equal to the attack's base damage plus any elemental bonus against the
   * opponent's type. A coin flip determines whether Raichu becomes invincible for the opponent's
   * next turn.
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Agility}
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int coinFlipAnswer = random.nextInt(2);

    if (coinFlipAnswer == 1) {
      super.setTellsIfPokemonIsInvincible(true);
      System.out.println("You rolled heads, so you become invincible!");
    } else {
      System.out.println("Unfortunately, you rolled tails, so you do not become invincible!");
    }

    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Executes Raichu's {@code Thunder} attack.
   *
   * <p>Deals damage equal to the attack's base damage plus any elemental bonus against the
   * opponent's type. A coin flip determines whether Raichu damages itself for 30 HP.
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Thunder}
   */
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int decidesIfDamageYourself = random.nextInt(2);

    if (decidesIfDamageYourself == 0) {
      int damageAmount = 30;
      System.out.println("Unfortunately, you rolled tails, so you damage yourself!");
      super.setHealthIfPokemonDamagesItsSelf(damageAmount);
      return attackInfo[1].getAttackDamage()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    } else {
      System.out.println("You rolled heads, so you do not damage yourself!");
      return attackInfo[1].getAttackDamage()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    }
  }

  /**
   * Performs an attack on an opponent Pokémon.
   *
   * <p>Executes {@code Agility} if {@code attack} equals 1; otherwise, executes {@code Thunder} and
   * may damage Raichu itself based on a coin flip.
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (1 for {@code Agility}, others for {@code
   *     Thunder})
   */
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
}
