package Pokemon.Fire;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Fire.FireType;
import Pokemon.Pokemon;
import Pokemon.Attributes.PokemonAndHealth;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Charmander, a Fire-type Pokémon.
 * <p>
 * Charmander has a single attack:
 * <ul>
 *   <li>{@code Ember}: Deals base damage plus any elemental bonus
 *       against the opponent.</li>
 * </ul>
 * This class defines Charmander's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Charmander extends Pokemon {

  /** Array of attacks available to Charmander. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Charmander Pokémon with default health, element type,
   * ASCII art, and its single attack.
   */
  public Charmander() {
    super(
        "Charmander",
        PokemonAndHealth.CHARMANDER.getHealth(),
        new FireType(),
        PokemonArt.charmanderArt,
        new AttackInfo[] {
          new AttackInfo(
              "Ember",
              "Charmander releases small but fierce flames, singeing its opponent with burning embers.",
              30)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Charmander's {@code Ember} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Ember}
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Since Charmander has only one attack, this method always executes
   * {@code Ember}.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Charmander has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
