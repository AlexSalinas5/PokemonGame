package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import java.util.Random;
import Pokemon.PokemonAndHealth;
import Pokemon.AttackInfo;

/**
 * Represents the Pokémon Wartortle, a Water-type Pokémon.
 * <p>
 * Wartortle has two available attacks:
 * <ul>
 *   <li>{@code Withdraw}: May grant temporary invincibility based on a coin flip.</li>
 *   <li>{@code Bite}: Deals base damage plus any elemental bonus against the opponent.</li>
 * </ul>
 * This class defines Wartortle's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Wartortle extends Pokemon {

  /** Array of attacks available to Wartortle. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Wartortle Pokémon with default health, element type,
   * ASCII art, and its two attacks.
   */
  public Wartortle() {
    super(
        "Wartortle",
        PokemonAndHealth.WARTORTLE.getHealth(),
        new WaterType(),
        PokemonArt.wartortleArt,
        new AttackInfo[] {
          new AttackInfo(
              "Withdraw",
              "Flip a coin. If heads, prevents all damage done to Wartortle during opponent's next turn.",
              0),
          new AttackInfo(
              "Bite",
              "Wartortle sinks its sharp fangs into the foe, delivering a fierce and decisive bite.",
              40)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes the {@code Withdraw} attack.
   * <p>
   * Performs a coin flip to determine if Wartortle becomes invincible
   * for the opponent's next turn.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return 0 (Withdraw does not deal direct damage)
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int decidesIfYouBecomeInvincible = random.nextInt(2);

    if (decidesIfYouBecomeInvincible == 1) {
      super.setTellsIfPokemonIsInvincible(true);
      System.out.println("You rolled heads, so you become invincible!");
    } else {
      System.out.println("Unfortunately, you rolled tails, so you do not become invincible!");
    }
    return 0;
  }

  /**
   * Executes the {@code Bite} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return total damage dealt by the Bite attack
   */
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[1].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * If {@code attack} equals 1, executes {@code Withdraw}. Otherwise,
   * executes {@code Bite} and applies poison status to the opponent.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (1 for Withdraw, others for Bite)
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
      OpponentPokemon.setTellsIfPokemonIsPoisoned(true);
    }
  }
}
