import Pokemon.Pokemon;

/**
 * Represents a Pokémon trainer who can own and manage Pokémon in battles.
 * <p>
 * Each trainer has a name, a current Pokémon, a faint counter, and a life status.
 * The faint counter decreases when the trainer's Pokémon faints, and the
 * alive status reflects whether the trainer can continue battling.
 * </p>
 */
public class Trainer {

  /** The name of the trainer. */
  private final String trainerName;

  /** The Pokémon the trainer is currently using in battle. */
  private Pokemon currentPokemon;

  /** Counts how many times the trainer's Pokémon have fainted. */
  private int faintCounter = 10;

  /** Indicates whether the trainer is still considered alive in the battle. */
  private boolean ifTrainerIsAlive = true;

  /**
   * Constructs a Trainer with the given name.
   *
   * @param trainerName the name of the trainer
   */
  public Trainer(String trainerName) {
    this.trainerName = trainerName;
  }

  /**
   * Returns the trainer's name.
   *
   * @return the name of the trainer
   */
  public String getName() {
    return this.trainerName;
  }

  /**
   * Returns the number of remaining faints before the trainer is defeated.
   *
   * @return the faint counter
   */
  public int getFaintCounter() {
    return this.faintCounter;
  }

  /**
   * Checks whether the trainer is still alive.
   *
   * @return true if the trainer is alive, false otherwise
   */
  public boolean getIfTrainerIsAlive() {
    return this.ifTrainerIsAlive;
  }

  /**
   * Sets the trainer's alive status.
   *
   * @param input true if the trainer is alive, false if defeated
   */
  public void setIfTrainerIsAlive(boolean input) {
    this.ifTrainerIsAlive = input;
  }

  /**
   * Decreases the faint counter by one.
   * Typically called when one of the trainer's Pokémon faints.
   */
  public void decreaseFaintCounter() {
    this.faintCounter--;
  }

  /**
   * Returns the Pokémon the trainer is currently using.
   *
   * @return the current Pokémon
   */
  public Pokemon getCurrentPokemon() {
    return this.currentPokemon;
  }

  /**
   * Sets the trainer's current Pokémon.
   *
   * @param newPokemon the Pokémon to set as current
   */
  public void setCurrentPokemon(Pokemon newPokemon) {
    this.currentPokemon = newPokemon;
  }
}
