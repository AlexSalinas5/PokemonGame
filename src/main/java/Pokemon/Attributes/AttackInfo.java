package Pokemon.Attributes;

import Pokemon.Pokemon;

/**
 * Encapsulates information about a Pokémon attack.
 *
 * <p>An {@code AttackInfo} object stores the attack's name, description, and base damage. Instances
 * of this class are used by {@link Pokemon} objects to define the moves a Pokémon can perform
 * during battle.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * AttackInfo thunderbolt = new AttackInfo("Thunderbolt",
 *                                         "A strong electric attack",
 *                                         40);
 * System.out.println(thunderbolt.getAttackName());
 * }</pre>
 */
public class AttackInfo {

  /** The name of the attack. */
  private final String attackName;

  /** A textual description of the attack's effect. */
  private final String attackDescription;

  /** The base damage dealt by the attack. */
  private int attackDamage;

  /**
   * Constructs a new {@code AttackInfo} instance with the specified attributes.
   *
   * @param attackName the name of the attack
   * @param attackDescription a brief description of the attack's effect
   * @param attackDamage the base damage value of the attack
   */
  public AttackInfo(String attackName, String attackDescription, int attackDamage) {
    this.attackName = attackName;
    this.attackDescription = attackDescription;
    this.attackDamage = attackDamage;
  }

  /**
   * Returns the name of the attack.
   *
   * @return the attack's name
   */
  public String getAttackName() {
    return this.attackName;
  }

  /**
   * Returns the description of the attack.
   *
   * @return the attack's description
   */
  public String getAttackDescription() {
    return this.attackDescription;
  }

  /**
   * Returns the base damage of the attack.
   *
   * @return the attack's damage value
   */
  public int getAttackDamage() {
    return this.attackDamage;
  }
}
