package Pokemon.Grass;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Grass.GrassType;
import Pokemon.Pokemon;
import Pokemon.Attributes.PokemonAndHealth;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Ivysaur, a Grass-type Pokémon.
 * <p>
 * Ivysaur has two available attacks:
 * <ul>
 *   <li>{@code Vine Whip}: Deals base damage plus any elemental bonus
 *       against the opponent.</li>
 *   <li>{@code Poisonpowder}: Deals base damage plus any elemental bonus
 *       and poisons the opponent Pokémon.</li>
 * </ul>
 * This class defines Ivysaur's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Ivysaur extends Pokemon {

  /** Array of attacks available to Ivysaur. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Ivysaur Pokémon with default health, element type,
   * ASCII art, and its two attacks.
   */
  public Ivysaur() {
    super(
        "Ivysaur",
        PokemonAndHealth.IVYSAUR.getHealth(),
        new GrassType(),
        PokemonArt.ivysaurArt,
        new AttackInfo[] {
          new AttackInfo(
              "Vine Whip",
              "Ivysaur lashes out with its sturdy vines, striking the opponent with swift and precise attacks.",
              30),
          new AttackInfo(
                  "Poisonpowder",
                  "The defending player's Pokémon is now poisoned.",
                  20)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Ivysaur's {@code Vine Whip} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Vine Whip}
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Executes Ivysaur's {@code Poisonpowder} attack.
   * <p>
   * Deals damage equal to the attack's base damage plus any elemental
   * bonus against the opponent's type. Additionally, poisons the opponent Pokémon.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Poisonpowder}
   */
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[1].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Executes {@code Vine Whip} if {@code attack} equals 1; otherwise,
   * executes {@code Poisonpowder} and poisons the opponent Pokémon.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (1 for Vine Whip, others for Poisonpowder)
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
