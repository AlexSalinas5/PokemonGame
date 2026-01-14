package Pokemon.Grass;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Grass.GrassType;
import Pokemon.Pokemon;
import Pokemon.PokemonAndHealth;
import Pokemon.AttackInfo;

/**
 * Represents the Pokémon Venusaur, a Grass-type Pokémon.
 * <p>
 * Venusaur has a single attack:
 * <ul>
 *   <li>{@code Solarbeam}: Deals base damage plus any elemental bonus
 *       against the opponent.</li>
 * </ul>
 * This class defines Venusaur's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Venusaur extends Pokemon {

  /** Array of attacks available to Venusaur. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Venusaur Pokémon with default health, element type,
   * ASCII art, and its single attack.
   */
  public Venusaur() {
    super(
        "Venusaur",
        PokemonAndHealth.VENUSAUR.getHealth(),
        new GrassType(),
        PokemonArt.venusaurArt,
        new AttackInfo[] {
          new AttackInfo(
              "Solarbeam",
              "Venusaur absorbs sunlight and unleashes a powerful beam of solar energy, scorching its target with intense force.",
              60)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Venusaur's {@code Solarbeam} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return total damage dealt by Solarbeam
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Since Venusaur has only one attack, this method always executes
   * {@code Solarbeam}.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Venusaur has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
