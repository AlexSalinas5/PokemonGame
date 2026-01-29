package Pokemon.Grass;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Grass.GrassType;
import Pokemon.Pokemon;
import Pokemon.Attributes.PokemonAndHealth;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Bulbasaur, a Grass-type Pokémon.
 *
 * <p>Bulbasaur has a single attack:
 *
 * <ul>
 *   <li>{@code Leech Seed}: Deals base damage plus any elemental bonus against the opponent, and
 *       heals 10 HP for Bulbasaur itself.
 * </ul>
 *
 * This class defines Bulbasaur's specific attack logic and overrides the abstract {@link
 * Pokemon#hitOpponent(Pokemon, int)} method.
 */
public class Bulbasaur extends Pokemon {

  /** Array of attacks available to Bulbasaur. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Bulbasaur Pokémon with default health, element type, ASCII art, and its single
   * attack.
   */
  public Bulbasaur() {
    super(
        "Bulbasaur",
        PokemonAndHealth.BULBASAUR.getHealth(),
        new GrassType(),
        PokemonArt.bulbasaurArt,
        new AttackInfo[] {
          new AttackInfo(
              "Leech Seed", "This attack does 20 damage. Heal 10 HP from this Pokémon.", 20)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Bulbasaur's {@code Leech Seed} attack.
   *
   * <p>Deals damage equal to the attack's base damage plus any elemental bonus against the
   * opponent's type. Additionally, heals Bulbasaur for 10 HP.
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Leech Seed}
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    super.setHealthIfPokemonHealsItsSelf(getHealth(), 10);
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   *
   * <p>Since Bulbasaur has only one attack, this method always executes {@code Leech Seed}.
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Bulbasaur has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
