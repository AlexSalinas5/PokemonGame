package Trainer;

import Pokemon.Pokemon;

/**
 * Represents a Pokémon trainer who can own and manage Pokémon in battles.
 *
 * <p>Each trainer has a name, a current Pokémon, a faint counter, and a life status. The faint
 * counter decreases when the trainer's Pokémon faints, and the alive status indicates whether the
 * trainer can continue participating in battles.
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
   * Constructs a new {@code Trainer} with the specified name.
   *
   * @param trainerName the name of the trainer
   */
  public Trainer(String trainerName) {
    this.trainerName = trainerName;
  }

  /**
   * Returns the name of the trainer.
   *
   * @return the trainer's name
   */
  public String getName() {
    return this.trainerName;
  }

  /**
   * Returns the number of remaining faints before the trainer is considered defeated.
   *
   * @return the faint counter
   */
  public int getFaintCounter() {
    return this.faintCounter;
  }

  /**
   * Checks if the trainer is still alive and able to battle.
   *
   * @return {@code true} if the trainer is alive; {@code false} otherwise
   */
  public boolean getIfTrainerIsAlive() {
    return this.ifTrainerIsAlive;
  }

  /**
   * Sets the trainer's alive status.
   *
   * @param isAlive {@code true} if the trainer is alive; {@code false} if defeated
   */
  public void setIfTrainerIsAlive(boolean isAlive) {
    this.ifTrainerIsAlive = isAlive;
  }

  /**
   * Decreases the faint counter by one.
   *
   * <p>This method is typically called when the trainer's current Pokémon faints.
   */
  public void decreaseFaintCounter() {
    this.faintCounter--;
  }

  /**
   * Returns the Pokémon the trainer is currently using in battle.
   *
   * @return the current {@link Pokemon} instance
   */
  public Pokemon getCurrentPokemon() {
    return this.currentPokemon;
  }

  /**
   * Sets the trainer's current Pokémon.
   *
   * @param newPokemon the {@link Pokemon} to assign as the current Pokémon
   */
  public void setCurrentPokemon(Pokemon newPokemon) {
    this.currentPokemon = newPokemon;
  }
}
