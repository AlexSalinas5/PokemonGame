package Pokemon.Electricity;

import ASCIIArt.PokemonArt;
import Elements.Electric.ElectricType;
import Elements.ElementType;
import Pokemon.Pokemon;
import Pokemon.Attributes.PokemonAndHealth;
import java.util.Random;
import Pokemon.Attributes.AttackInfo;

/**
 * Represents the Pokémon Pikachu, an Electric-type Pokémon.
 *
 * <p>Pikachu has two available attacks:
 *
 * <ul>
 *   <li>{@code Gnaw}: Deals base damage plus any elemental bonus against the opponent.
 *   <li>{@code Thunder Jolt}: Deals base damage plus any elemental bonus. Performs a coin flip; if
 *       tails, Pikachu takes 10 damage itself.
 * </ul>
 *
 * This class defines Pikachu's specific attack logic and overrides the abstract {@link
 * Pokemon#hitOpponent(Pokemon, int)} method.
 */
public class Pikachu extends Pokemon {

  /** Array of attacks available to Pikachu. */
  private final AttackInfo[] attackInfo;

  /**
   * Constructs a new Pikachu Pokémon with default health, element type, ASCII art, and its two
   * attacks.
   */
  public Pikachu() {
    super(
        "Pikachu",
        PokemonAndHealth.PIKACHU.getHealth(),
        new ElectricType(),
        PokemonArt.pikachuArt,
        new AttackInfo[] {
          new AttackInfo(
              "Gnaw",
              "Pikachu uses its sharp teeth to bite down on its target, delivering a quick and painful strike.",
              10),
          new AttackInfo(
              "Thunder Jolt", "Flip a coin. If tails, Pikachu does 10 damage to itself.", 30)
        });
    attackInfo = super.getAttackInfo();
  }

  /**
   * Executes Pikachu's {@code Gnaw} attack.
   *
   * <p>Deals damage equal to the attack's base damage plus any elemental bonus against the
   * opponent's type.
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Gnaw}
   */
  public int getAttackResult1(ElementType elementTypeOfOpponentPokemon) {
    return attackInfo[0].getAttackDamage()
        + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
  }

  /**
   * Executes Pikachu's {@code Thunder Jolt} attack.
   *
   * <p>Deals damage equal to the attack's base damage plus any elemental bonus against the
   * opponent's type. A coin flip determines whether Pikachu damages itself for 10 HP.
   *
   * @param elementTypeOfOpponentPokemon the element type of the opponent Pokémon
   * @return the total damage dealt by {@code Thunder Jolt}
   */
  public int getAttackResult2(ElementType elementTypeOfOpponentPokemon) {
    Random random = new Random();
    int decidesIfDamageYourself = random.nextInt(2);

    if (decidesIfDamageYourself == 0) {
      int damageAmount = 10;
      System.out.println("Unfortunately, you rolled tails, so you damage yourself!");
      super.setHealthIfPokemonDamagesItsSelf(damageAmount);
      return attackInfo[1].getAttackDamage()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    } else {
      System.out.println("You rolled heads, so you do not damage yourself!");
      return attackInfo[1].getAttackDamage()
          + this.elementType.tellerOfBonusAttackDamage(elementTypeOfOpponentPokemon);
    }
  }

  /**
   * Performs an attack on an opponent Pokémon.
   *
   * <p>Executes {@code Gnaw} if {@code attack} equals 1; otherwise, executes {@code Thunder Jolt}
   * and may damage Pikachu itself based on a coin flip.
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack to use (1 for {@code Gnaw}, others for {@code Thunder
   *     Jolt})
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
