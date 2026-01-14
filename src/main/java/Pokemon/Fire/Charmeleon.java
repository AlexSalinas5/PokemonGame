package Pokemon.Fire;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Fire.FireType;
import Pokemon.Pokemon;
import Pokemon.PokemonAndHealth;
import Pokemon.AttackInfo;

/**
 * Represents the Pokémon Charmeleon, a Fire-type Pokémon.
 * <p>
 * Charmeleon has a single attack:
 * <ul>
 *   <li>{@code Flamethrower}: Deals base damage plus any elemental bonus
 *       against the opponent.</li>
 * </ul>
 * This class defines Charmeleon's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Charmeleon extends Pokemon {

  /** Array of attacks available to Charmeleon. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Charmeleon Pokémon with default health, element type,
   * ASCII art, and its single attack.
   */
  public Charmeleon() {
    super(
        "Charmeleon",
        PokemonAndHealth.CHARMELEON.getHealth(),
        new FireType(),
        PokemonArt.charmeleonArt,
        new AttackInfo[] {
          new AttackInfo(
              "Flamethrower",
              "Charmeleon unleashes a stream of scorching flames, burning its target with intense heat.",
              50)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Charmeleon's {@code Flamethrower} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return total damage dealt by Flamethrower
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Since Charmeleon has only one attack, this method always executes
   * {@code Flamethrower}.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Charmeleon has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
