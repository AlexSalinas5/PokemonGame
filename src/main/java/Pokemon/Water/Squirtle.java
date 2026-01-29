package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import Pokemon.Attributes.PokemonAndHealth;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Squirtle, a Water-type Pokémon.
 * <p>
 * Squirtle has a single attack:
 * <ul>
 *   <li>{@code Shell Attack}: Deals base damage plus any elemental bonus
 *       against the opponent.</li>
 * </ul>
 * This class defines Squirtle's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Squirtle extends Pokemon {

  /** Array of attacks available to Squirtle. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Squirtle Pokémon with default health, element type,
   * ASCII art, and its single attack.
   */
  public Squirtle() {
    super(
        "Squirtle",
        PokemonAndHealth.SQUIRTLE.getHealth(),
        new WaterType(),
        PokemonArt.squirtleArt,
        new AttackInfo[] {
          new AttackInfo(
              "Shell Attack",
              "Squirtle strikes with its hard shell, delivering a strong and steady hit.",
              20)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Squirtle's {@code Shell Attack}.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return total damage dealt by {@code Shell Attack}
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Since Squirtle has only one attack, this method always executes
   * {@code Shell Attack}.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Squirtle has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
