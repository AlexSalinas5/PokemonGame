package Pokemon.Fire;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Fire.FireType;
import Pokemon.Pokemon;
import Pokemon.PokemonAndHealth;
import Pokemon.AttackInfo;

/**
 * Represents the Pokémon Charizard, a Fire-type Pokémon.
 * <p>
 * Charizard has a single attack:
 * <ul>
 *   <li>{@code Fire Spin}: Deals base damage plus any elemental bonus
 *       against the opponent.</li>
 * </ul>
 * This class defines Charizard's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Charizard extends Pokemon {

  /** Array of attacks available to Charizard. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Charizard Pokémon with default health, element type,
   * ASCII art, and its single attack.
   */
  public Charizard() {
    super(
        "Charizard",
        PokemonAndHealth.CHARIZARD.getHealth(),
        new FireType(),
        PokemonArt.charizardArt,
        new AttackInfo[] {
          new AttackInfo(
              "Fire Spin",
              "Charizard engulfs its foe in a swirling vortex of flames, trapping and scorching them with intense heat.",
              100)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Charizard's {@code Fire Spin} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return total damage dealt by Fire Spin
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Since Charizard has only one attack, this method always executes
   * {@code Fire Spin}.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Charizard has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
