package Pokemon.Water;

import ASCIIArt.PokemonArt;
import Elements.ElementType;
import Elements.Water.WaterType;
import Pokemon.Pokemon;
import Pokemon.PokemonAndHealth;
import Pokemon.AttackInfo;

/**
 * Represents the Pokémon Blastoise, a Water-type Pokémon.
 * <p>
 * Blastoise has a single attack:
 * <ul>
 *   <li>{@code Hydro Pump}: Deals base damage plus any elemental bonus
 *       against the opponent. After each use, the attack damage increases
 *       by 10 for subsequent uses.</li>
 * </ul>
 * This class defines Blastoise's specific attack logic and overrides
 * the abstract {@link Pokemon#hitOpponent(Pokemon, int)} method.
 * </p>
 */
public class Blastoise extends Pokemon {

  /** Array of attacks available to Blastoise. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Blastoise Pokémon with default health, element type,
   * ASCII art, and its single attack.
   */
  public Blastoise() {
    super(
        "Blastoise",
        PokemonAndHealth.BLASTOISE.getHealth(),
        new WaterType(),
        PokemonArt.blastoiseArt,
        new AttackInfo[] {
          new AttackInfo(
                  "Hydro Pump",
                  "After each attack use, attack damage increases by 10.",
                  40)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Blastoise's {@code Hydro Pump} attack.
   * <p>
   * Damage is calculated as the base attack damage plus any elemental
   * bonus. For each subsequent use of this attack, damage increases by 10.
   * </p>
   *
   * @param elementTypeOfOpponentPokemon the opponent's elemental type
   * @return total damage dealt by Hydro Pump
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    int firstTimeAttacking = 0;

    if (super.getCounterToIncreaseAttackDamage() == firstTimeAttacking) {
      super.IncreaseCounterToIncreaseAttackDamage();
      return attackInfo[0].getAttackDamage()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    } else {
      int bonusDamage = 10;
      int calibratesCounterToIncreaseAttackDamage = 1;

      super.IncreaseCounterToIncreaseAttackDamage();
      return attackInfo[0].getAttackDamage()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon)
          + ((super.getCounterToIncreaseAttackDamage() - calibratesCounterToIncreaseAttackDamage)
              * bonusDamage);
    }
  }

  /**
   * Performs an attack on an opponent Pokémon.
   * <p>
   * Since Blastoise has only one attack, this method always executes
   * {@code Hydro Pump}, applying the increasing damage logic.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (ignored, as Blastoise has only one attack)
   */
  @Override
  public void hitOpponent(Pokemon OpponentPokemon, int attack) {
    OpponentPokemon.setHealth(
        OpponentPokemon.getHealth() - getAttackResult1(OpponentPokemon.getElementType()));
  }
}
