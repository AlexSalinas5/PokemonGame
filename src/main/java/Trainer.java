import Pokemon.Pokemon;

public class Trainer {
  private final String trainerName;
  private Pokemon currentPokemon;
  private int faintCounter = 10;
  private boolean ifTrainerIsAlive = true;

  public Trainer(String trainerName) {
    this.trainerName = trainerName;
  }

  public String getName() {
    return this.trainerName;
  }

  public int getFaintCounter() {
    return this.faintCounter;
  }

  public boolean getIfTrainerIsAlive() {
    return this.ifTrainerIsAlive;
  }

  public void setIfTrainerIsAlive(boolean input) {
    this.ifTrainerIsAlive = input;
  }

  public void decreaseFaintCounter() {
    this.faintCounter--;
  }

  public Pokemon getCurrentPokemon() {
    return this.currentPokemon;
  }

  public void setCurrentPokemon(Pokemon newPokemon) {
    this.currentPokemon = newPokemon;
  }
}
