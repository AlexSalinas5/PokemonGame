package CustomException;

/**
 * Thrown to indicate that a Pokémon has permanently fainted during its own turn.
 *
 * <p>This exception is intended to signal a terminal game state for a Pokémon, where recovery or
 * revival is no longer possible as a result of actions taken during that Pokémon’s turn.
 *
 * <p>This exception may be used by battle logic to immediately halt further actions for the
 * affected Pokémon and trigger appropriate end-of-turn or end-of-battle handling.
 */
public class pokemonDiedPermanentlyDuringItsTurnException extends Exception {

  /** Constructs a new exception with no detail message. */
  public pokemonDiedPermanentlyDuringItsTurnException() {
    super();
  }

  /**
   * Constructs a new exception with the specified detail message.
   *
   * @param message a human-readable description of the cause of the exception
   */
  public pokemonDiedPermanentlyDuringItsTurnException(String message) {
    super(message);
  }
}
