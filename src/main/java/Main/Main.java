package Main;

import GameLogic.GameLogic;

/**
 * Entry point for the Pokémon-inspired game.
 *
 * <p>This class contains the {@code main} method which initializes the game logic and starts the
 * game. It is responsible for launching the application.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * java Main
 * }</pre>
 */
public class Main {

  /**
   * The main method of the application.
   *
   * <p>Creates an instance of {@link GameLogic} and calls {@link GameLogic#startGame()} to begin
   * gameplay.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    GameLogic a = new GameLogic();
    a.startGame();
  }
}
