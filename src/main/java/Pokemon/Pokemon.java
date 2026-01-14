package Pokemon;

import Elements.ElementType;

/**
 * Abstract base class representing a Pokémon in the game.
 * <p>
 * A {@code Pokemon} encapsulates shared state and behavior such as name,
 * health, element type, available attacks, status effects, and combat-related
 * mechanics. Concrete Pokémon subclasses define specific behavior for
 * attacking opponents.
 * </p>
 */
public abstract class Pokemon {

  /** The display name of the Pokémon. */
  private final String name;

  /** The current health points of the Pokémon. */
  private int health;

  /** ASCII art representation of the Pokémon. */
  private final String asciiArt;

  /** The list of attacks available to this Pokémon. */
  private final AttackInfo[] attackInfo;

  /** Counter used to track attack-based damage increases. */
  private int counterToIncreaseAttackDamage = 0;

  /** The elemental type of the Pokémon (e.g., Fire, Water, Grass). */
  protected final ElementType elementType;

  /** Indicates whether the Pokémon is currently poisoned. */
  protected boolean tellsIfPokemonIsPoisoned = false;

  /** Indicates whether the Pokémon is currently invincible. */
  protected boolean tellsIfPokemonIsInvincible = false;

  /**
   * Constructs a new {@code Pokemon} with the specified attributes.
   *
   * @param name the name of the Pokémon
   * @param health the starting health of the Pokémon
   * @param elementType the elemental type of the Pokémon
   * @param asciiArt the ASCII art representation of the Pokémon
   * @param attackInfos the attacks available to the Pokémon
   */
  public Pokemon(
      String name, int health, ElementType elementType, String asciiArt, AttackInfo[] attackInfos) {
    this.name = name;
    this.health = health;
    this.elementType = elementType;
    this.asciiArt = asciiArt;
    this.attackInfo = attackInfos;
  }

  /**
   * Prints the Pokémon's ASCII art to the console.
   */
  public void getAsciiArt() {
    System.out.println(this.asciiArt);
  }

  /**
   * Returns the attacks available to this Pokémon.
   *
   * @return an array of {@link AttackInfo} objects
   */
  public AttackInfo[] getAttackInfo() {
    return this.attackInfo;
  }

  /**
   * Returns the elemental type of the Pokémon.
   *
   * @return the {@link ElementType} of the Pokémon
   */
  public ElementType getElementType() {
    return elementType;
  }

  /**
   * Returns the name of the Pokémon.
   *
   * @return the Pokémon's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the current health of the Pokémon.
   *
   * @return the current health value
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Updates the Pokémon's health to a new value.
   *
   * @param newHealth the new health value
   */
  public void setHealth(int newHealth) {
    this.health = newHealth;
  }

  /**
   * Returns the counter tracking increased attack damage.
   *
   * @return the current attack damage counter
   */
  public int getCounterToIncreaseAttackDamage() {
    return this.counterToIncreaseAttackDamage;
  }

  /**
   * Increments the counter used to increase attack damage.
   */
  public void IncreaseCounterToIncreaseAttackDamage() {
    this.counterToIncreaseAttackDamage = getCounterToIncreaseAttackDamage() + 1;
  }

  /**
   * Reduces the Pokémon's health when it damages itself.
   *
   * @param damageToSelf the amount of damage dealt to itself
   */
  public void setHealthIfPokemonDamagesItsSelf(int damageToSelf) {
    setHealth((this.getHealth() - damageToSelf));
  }

  /**
   * Increases the Pokémon's health when it heals itself.
   *
   * @param currentHealth the Pokémon's current health
   * @param healthToSelf the amount of health restored
   */
  public void setHealthIfPokemonHealsItsSelf(int currentHealth, int healthToSelf) {
    setHealth((currentHealth + healthToSelf));
  }

  /**
   * Indicates whether the Pokémon is poisoned.
   *
   * @return {@code true} if poisoned; {@code false} otherwise
   */
  public boolean getTellsIfPokemonIsPoisoned() {
    return this.tellsIfPokemonIsPoisoned;
  }

  /**
   * Sets the poisoned status of the Pokémon.
   *
   * @param tellsIfPokemonIsPoisoned {@code true} to poison the Pokémon;
   *     {@code false} otherwise
   */
  public void setTellsIfPokemonIsPoisoned(boolean tellsIfPokemonIsPoisoned) {
    this.tellsIfPokemonIsPoisoned = tellsIfPokemonIsPoisoned;
  }

  /**
   * Applies poison damage to the Pokémon if it is poisoned.
   */
  public void ifPoisonedChangeHealth() {
    if (this.tellsIfPokemonIsPoisoned) {
      setHealth(getHealth() - 10);
    }
  }

  /**
   * Indicates whether the Pokémon is invincible.
   *
   * @return {@code true} if invincible; {@code false} otherwise
   */
  public boolean getTellsIfPokemonIsInvincible() {
    return this.tellsIfPokemonIsInvincible;
  }

  /**
   * Sets the invincibility status of the Pokémon.
   *
   * @param updater {@code true} to make the Pokémon invincible;
   *     {@code false} otherwise
   */
  public void setTellsIfPokemonIsInvincible(boolean updater) {
    this.tellsIfPokemonIsInvincible = updater;
  }

  /**
   * Prints the current buffs and debuffs affecting the Pokémon.
   * <p>
   * This includes status effects such as poison and invincibility.
   * </p>
   */
  protected void printBuffsAndDebuffs() {
    if (this.tellsIfPokemonIsPoisoned) {
      System.out.println("You are poisoned!");
    }
    if (this.tellsIfPokemonIsInvincible) {
      System.out.println("You are invincible!");
    }
  }

  /**
   * Prints the Pokémon's current health, status effects, and available attacks.
   */
  public void getAttackAndDamageInfo() {
    System.out.println("Your Pokémon: " + getName() + "\nYour health: " + getHealth());

    printBuffsAndDebuffs();

    for (AttackInfo attack : attackInfo) {
      System.out.println(
          "\nAttack: "
              + attack.getAttackName()
              + "\nDamage: "
              + attack.getAttackDamage()
              + "\nAttack Description: "
              + attack.getAttackDescription());
    }
  }

  /**
   * Executes an attack against an opponent Pokémon.
   * <p>
   * Concrete subclasses define how damage is calculated and applied.
   * </p>
   *
   * @param OpponentPokemon the Pokémon being attacked
   * @param attack the index of the attack being used
   */
  public abstract void hitOpponent(Pokemon OpponentPokemon, int attack);
}
