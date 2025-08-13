package Pokemon;

import Elements.ElementType;

public abstract class Pokemon {
  private final String name;
  private int health;
  protected final ElementType elementType;
  private final String asciiArt;
  private int counterToIncreaseAttackDamage = 0;
  protected boolean tellsIfPokemonIsPoisoned = false;
  protected boolean tellsIfPokemonIsInvincible = false;

  public Pokemon(String name, int health, ElementType elementType, String asciiArt) {
    this.name = name;
    this.health = health;
    this.elementType = elementType;
    this.asciiArt = asciiArt;
  }

  public void getAsciiArt() {
    System.out.println(this.asciiArt);
  }

  public ElementType getElementType() {
    return elementType;
  }

  public String getName() {
    return this.name;
  }

  public int getHealth() {
    return this.health;
  }

  public void setHealth(int newHealth) {
    this.health = newHealth;
  }

  public int getCounterToIncreaseAttackDamage() {
    return this.counterToIncreaseAttackDamage;
  }

  public void IncreaseCounterToIncreaseAttackDamage() {
    this.counterToIncreaseAttackDamage = getCounterToIncreaseAttackDamage() + 1;
  }

  public void setHealthIfPokemonDamagesItsSelf(int damageToSelf) {
    setHealth((this.getHealth() - damageToSelf));
  }

  public void setHealthIfPokemonHealsItsSelf(int currentHealth, int healthToSelf) {
    setHealth((currentHealth + healthToSelf));
  }

  public boolean getTellsIfPokemonIsPoisoned() {
    return this.tellsIfPokemonIsPoisoned;
  }

  public void setTellsIfPokemonIsPoisoned(boolean tellsIfPokemonIsPoisoned) {
     this.tellsIfPokemonIsPoisoned = tellsIfPokemonIsPoisoned;
  }

  public void ifPoisonedChangeHealth() {
    if (this.tellsIfPokemonIsPoisoned) {
      setHealth(getHealth() - 10);
    }
  }

  public boolean getTellsIfPokemonIsInvincible() {
    return this.tellsIfPokemonIsInvincible;
  }

  public void setTellsIfPokemonIsInvincible(boolean updater) {
    this.tellsIfPokemonIsInvincible = updater;
  }

  public abstract void hitOpponent(Pokemon OpponentPokemon, int attack);

  public abstract void getAttackAndDamageInfo();

  protected void printBuffsAndDebuffs() {
    if (this.tellsIfPokemonIsPoisoned == true) {
      System.out.println("You are poisoned!");
    }
    if (this.tellsIfPokemonIsInvincible == true) {
      System.out.println("You are invincible!");
    }
  }
}
