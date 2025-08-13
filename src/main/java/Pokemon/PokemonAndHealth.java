package Pokemon;

public enum PokemonAndHealth {
  PIKACHU(40),
  RAICHU(80),
  CHARIZARD(120),
  CHARMANDER(50),
  CHARMELEON(80),
  BULBASAUR(40),
  IVYSAUR(60),
  VENUSAUR(100),
  BLASTOISE(100),
  SQUIRTLE(50),
  WARTORTLE(70);

  private final int health;

  PokemonAndHealth(int health) {
    this.health = health;
  }

  public int getHealth() {
    return health;
  }
}
