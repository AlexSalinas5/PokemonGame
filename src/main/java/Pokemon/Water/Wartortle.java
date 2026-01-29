package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import java.util.Random;
import Pokemon.Attributes.PokemonAndHealth;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Wartortle, a Water-type Pokémon.
 *
 * <p>Wartortle has two available attacks:
 *
 * <ul>
 *   <li>{@code Withdraw}: May grant temporary invincibility based on a coin flip.
 *   <li>{@code Bite}: Deals base damage plus any elemental bonus against the opponent.
 * </ul>
 *
 * This class defines Wartortle's specific attack logic and overrides the abstract {@link
 * Pokemon#hitOpponent(Pokemon, int)} method.
 */
public class Wartortle extends Pokemon {

  /** Array of attacks available to Wartortle. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Wartortle Pokémon with default health, element type, ASCII art, and its two
   * attacks.
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
   * Executes Wartortle's {@code Withdraw} attack.
   *
   * <p>Performs a coin flip to determine if Wartortle becomes invincible for the opponent's next
   * turn. This attack does not deal direct damage.
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type (ignored, Withdraw does no
   *     damage)
   * @return 0 as {@code Withdraw} does not deal damage
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
   * Executes Wartortle's {@code Bite} attack.
   *
   * <p>Deals damage equal to the attack's base damage plus any elemental bonus against the
   * opponent's type.
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return total damage dealt by {@code Bite}
   */
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[1].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   *
   * <p>Executes {@code Withdraw} if {@code attack} equals 1; otherwise, executes {@code Bite}.
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
    }
  }
}
