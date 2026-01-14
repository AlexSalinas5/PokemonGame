package Pokemon;

/**
 * Represents information about a Pokémon attack.
 * <p>
 * An {@code AttackInfo} object encapsulates the name, description, and
 * base damage of an attack. Instances of this class are used by
 * {@link Pokemon} objects to define their available moves.
 * </p>
 */
public class AttackInfo {

  /** The name of the attack. */
  private final String attackName;

  /** A textual description of the attack's effect. */
  private final String attackDescription;

  /** The base damage dealt by the attack. */
  private int attackDamage;

  /**
   * Constructs a new {@code AttackInfo} with the specified attributes.
   *
   * @param attackName the name of the attack
   * @param attackDescription a description of what the attack does
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
   * @return the attack name
   */
  public String getAttackName() {
    return this.attackName;
  }

  /**
   * Returns the description of the attack.
   *
   * @return the attack description
   */
  public String getAttackDescription() {
    return this.attackDescription;
  }

  /**
   * Returns the base damage of the attack.
   *
   * @return the attack damage value
   */
  public int getAttackDamage() {
    return this.attackDamage;
  }
}
