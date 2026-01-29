package GameLogic;

import ASCIIArt.PokemonArt;
import CustomException.pokemonDiedPermanentlyDuringItsTurnException;
import Pokemon.PokemonFactory;
import Pokemon.Attributes.SecondEvolutionPokemon;
import Pokemon.Attributes.TwoAttackPokemon;
import Pokemon.Attributes.firstEvolutionPokemon;
import Trainer.Trainer;
import java.util.*;

/**
 * Handles the core logic of the Pokémon battle game.
 *
 * <p>This class manages the full gameplay experience, including:
 *
 * <ul>
 *   <li>Tracking all players and their trainers.
 *   <li>Handling Pokémon selection and evolution mechanics.
 *   <li>Managing turn-based attacks, health, poison, and invincibility status.
 *   <li>Implementing revival mechanics and faint counters for trainers.
 *   <li>Displaying ASCII art, game instructions, and round/turn information.
 *   <li>Maintaining timers for in-game delays and animations.
 *   <li>Determining game outcomes, including wins and draws.
 * </ul>
 *
 * <p>The game supports 2–5 players in a Free-For-All (FFA) mode. Each trainer takes turns selecting
 * a player to attack, and attacks are resolved according to Pokémon type, attack selection, and
 * special mechanics like invincibility or poisoning. The class also integrates with {@link
 * PokemonFactory} to instantiate Pokémon dynamically.
 */
public class GameLogic {

  /**
   * List of all trainers participating in the game.
   *
   * <p>Each {@link Trainer} in this list represents a player currently registered and actively
   * taking part in the Free-For-All battle.
   */
  private final ArrayList<Trainer> listOfAllPlayers = new ArrayList<>();

  /**
   * Factory used to create new {@link Pokemon} instances dynamically.
   *
   * <p>This allows assigning Pokémon to trainers and handling evolution during gameplay.
   */
  private final PokemonFactory pokemonFactory = new PokemonFactory();

  /**
   * Thread used to implement a 5-second delay timer during gameplay.
   *
   * <p>Commonly used to give players a short pause to view information or animations before
   * proceeding.
   */
  private Thread fiveSecondTimer;

  /**
   * Thread used to implement a 3-second delay timer during gameplay.
   *
   * <p>Typically used for shorter pauses, such as showing attack results or faint notifications.
   */
  private Thread threeSecondTimer;

  /**
   * Total number of players participating in the current game session.
   *
   * <p>Used to control game loops, turn order, and end-of-game conditions.
   */
  private int playerAmount;

  /**
   * Starts the Pokémon battle game and manages the entire game flow.
   *
   * <p>This method performs the following actions in sequence:
   *
   * <ol>
   *   <li>Displays the Pokémon game logo and decorative screen elements.
   *   <li>Introduces the game and explains the rules and game mode.
   *   <li>Prompts the user to confirm if they want to play the game.
   *   <li>If the user agrees, initializes timing threads for delays and animations.
   *   <li>Asks how many players will participate and collects their names.
   *   <li>Allows each player to choose a starter Pokémon and displays the corresponding ASCII art.
   *   <li>Displays detailed game instructions, including turn-based mechanics and evolution/respawn
   *       rules.
   *   <li>Executes the main game loop, where players take turns attacking, and Pokémon faint,
   *       revive, or evolve.
   * </ol>
   *
   * <p>The method handles player input validation, updates Pokémon and trainer status, and
   * determines the game's winner or if the game ends in a draw.
   */
  public void startGame() {

    System.out.println(PokemonArt.pokemonLogoArt);
    screenDetail();
    gameIntroduction();

    if (askUserIfTheyWantToPlay()) {
      System.out.println("I hope you change your mind! Have a great day!");
      return;
    }

    initThreads();

    playerAmount = askHowManyPlayersArePlaying();
    populatePlayerInformation();

    gameInstructions();

    gameLogic();
  }

  /**
   * Displays an introduction message to the players.
   *
   * <p>Explains the game mode (Free-For-All), the turn-based mechanics, and what players need to do
   * before the game begins, such as setting their names and choosing starter Pokémon.
   */
  private void gameIntroduction() {
    System.out.println("Hi! Welcome to my version of a Pokémon battle game!");
    System.out.println(
        "There is only one game mode: Free-For-All (FFA). You can choose to play with 1 to 4 other players.");
    System.out.println(
        "The game is turn-based and will begin once all players have set their names and chosen their starter Pokémon.");
  }

  /**
   * Collects and registers information for all players participating in the game.
   *
   * <p>For each player, prompts for their trainer name and validates it according to:
   *
   * <ul>
   *   <li>Minimum and maximum length (2–20 characters)
   *   <li>Alphabetical characters only
   *   <li>Uniqueness among all previously entered player names
   * </ul>
   *
   * Once a valid name is entered, the player is added to the game and prompted to select their
   * starter Pokémon.
   */
  private void populatePlayerInformation() {
    Scanner scanner = new Scanner(System.in);
    boolean isValidInput;
    int minimumCharacterAmount = 2;
    int maximumCharacterAmount = 20;

    for (int specificPlayer = 1; specificPlayer <= playerAmount; specificPlayer++) {
      isValidInput = true;
      System.out.println(
          "Player Number: "
              + specificPlayer
              + ". Please tell me your name. This will be the name of your trainer.");

      String playerUserName = scanner.nextLine();

      while (isValidInput) {
        if (playerUserName.length() > minimumCharacterAmount
            && playerUserName.length() <= maximumCharacterAmount
            && checkStringInputContainsOnlyLetters(playerUserName)
            && isNameUnique(listOfAllPlayers, playerUserName)) {
          listOfAllPlayers.add(new Trainer(playerUserName));
          givePlayerTheirPokemon(specificPlayer);
          isValidInput = false;
        } else {
          System.out.println("I don't recognize your input. Please try again.");
          System.out.println(
              "Make sure your name contains only letters, is between 2 and 20 characters long, and does not duplicate another player's name.");
          playerUserName = scanner.nextLine();
        }
      }
    }
  }

  /**
   * Determines if a given string contains only alphabetical letters (A-Z), ignoring case.
   *
   * <p>This method is used to validate player names during registration to ensure that they do not
   * contain numbers, spaces, or special characters.
   *
   * @param input the string to validate
   * @return {@code true} if the string consists solely of letters (A-Z or a-z), {@code false}
   *     otherwise
   */
  private boolean checkStringInputContainsOnlyLetters(String input) {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String upperCaseInput = input.toUpperCase();
    int makesItEqualToLastLetter = 1;

    for (int i = 0; i < upperCaseInput.length(); i++) {
      for (int j = 0; j < alphabet.length(); j++) {
        if (upperCaseInput.charAt(i) == alphabet.charAt(j)) {
          break;
        } else if (j == alphabet.length() - makesItEqualToLastLetter) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Determines if a given trainer name is unique among the currently registered trainers.
   *
   * <p>This method is used during player registration to prevent duplicate trainer names. The
   * comparison is case-insensitive.
   *
   * @param listOfAllPlayers the list of trainers already registered in the game
   * @param newPlayer the trainer name to validate for uniqueness
   * @return {@code true} if no existing trainer has the same name (case-insensitive), {@code false}
   *     if the name is already taken
   */
  private boolean isNameUnique(ArrayList<Trainer> listOfAllPlayers, String newPlayer) {
    if (listOfAllPlayers.isEmpty()) {
      return true;
    }

    for (Trainer trainer : listOfAllPlayers) {
      if (trainer.getName().equalsIgnoreCase(newPlayer)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Prompts a specific player to select their Pokémon and assigns it using the {@link
   * PokemonFactory}.
   *
   * <p>The player is presented with a choice of starter Pokémon (Squirtle, Charmander, Bulbasaur,
   * or Pikachu). Input is validated to ensure a valid selection. Once a valid choice is made:
   *
   * <ul>
   *   <li>The chosen Pokémon is assigned to the player's {@link Trainer#currentPokemon}.
   *   <li>The ASCII art of the selected Pokémon is displayed.
   *   <li>A 5-second timer delay is executed before clearing the screen.
   * </ul>
   *
   * @param specificPlayer the 1-based index of the player currently selecting a Pokémon
   * @throws RuntimeException if the 5-second timer thread is interrupted unexpectedly
   */
  private void givePlayerTheirPokemon(int specificPlayer) {
    int makesItEqualToCurrentPlayer = 1;
    clearScreen();
    screenDetail();
    System.out.println(
        "Hello "
            + listOfAllPlayers.get(specificPlayer - makesItEqualToCurrentPlayer).getName()
            + "!!!! Choose of the the following three Pokémon.");
    System.out.println("Type \"Squirtle\", \"Charmander\", or \"Bulbasaur\".");

    Scanner scanner = new Scanner(System.in);
    String playerPokemonChoice = scanner.nextLine();

    while (true) {
      if (playerPokemonChoice.equalsIgnoreCase("Squirtle")
          || playerPokemonChoice.equalsIgnoreCase("Charmander")
          || playerPokemonChoice.equalsIgnoreCase("Bulbasaur")
          || playerPokemonChoice.equalsIgnoreCase("Pikachu")) {
        listOfAllPlayers
            .get(specificPlayer - makesItEqualToCurrentPlayer)
            .setCurrentPokemon(pokemonFactory.pokemonFactory(playerPokemonChoice));

        System.out.println(
            "\nGreat choice "
                + listOfAllPlayers.get(specificPlayer - makesItEqualToCurrentPlayer).getName()
                + "! Look at your lovely Pokémon!");

        printAsciiArtPokemon(playerPokemonChoice);

        try {
          fiveSecondTimer.start();
          fiveSecondTimer.join();
          initThreads();
        } catch (InterruptedException e) {
          throw new RuntimeException("Something went wrong. Please try again.");
        }

        clearScreen();
        screenDetail();
        return;

      } else {
        System.out.println("I don't recognize your input. Please try again.");
        System.out.println("Type \"Squirtle\", \"Charmander\", or \"Bulbasaur\".");
        playerPokemonChoice = scanner.nextLine();
      }
    }
  }

  /**
   * Displays the ASCII art representation of a given Pokémon.
   *
   * <p>The method converts the Pokémon name to uppercase and matches it against known Pokémon
   * names. If a match is found, the corresponding ASCII art from {@link PokemonArt} is printed to
   * the console.
   *
   * <p>If the provided Pokémon name is not recognized, a message is printed indicating that ASCII
   * art for the Pokémon is not yet available.
   *
   * @param pokemon the name of the Pokémon whose ASCII art should be displayed
   */
  private void printAsciiArtPokemon(String pokemon) {
    String input = pokemon.toUpperCase();

    switch (input) {
      case "CHARMANDER" -> System.out.println(PokemonArt.charmanderArt);
      case "CHARMELEON" -> System.out.println(PokemonArt.charmeleonArt);
      case "CHARIZARD" -> System.out.println(PokemonArt.charizardArt);
      case "BULBASAUR" -> System.out.println(PokemonArt.bulbasaurArt);
      case "IVYSAUR" -> System.out.println(PokemonArt.ivysaurArt);
      case "VENUSAUR" -> System.out.println(PokemonArt.venusaurArt);
      case "SQUIRTLE" -> System.out.println(PokemonArt.squirtleArt);
      case "WARTORTLE" -> System.out.println(PokemonArt.wartortleArt);
      case "BLASTOISE" -> System.out.println(PokemonArt.blastoiseArt);
      case "PIKACHU" -> System.out.println(PokemonArt.pikachuArt);
      case "RAICHU" -> System.out.println(PokemonArt.raichuArt);
      default ->
          System.out.println(
              "printAsciiArtPokemon() need to be updated to ASCII print desired Pokémon!");
    }
  }

  /**
   * Displays the game instructions and important gameplay information to the players.
   *
   * <p>This includes rules about:
   *
   * <ul>
   *   <li>How to select a player to attack during a turn
   *   <li>The revival mechanics of Pokémon after fainting, including evolution chances
   *   <li>The order of play based on player registration
   * </ul>
   *
   * After displaying the instructions, the method waits for player input to acknowledge they are
   * ready, then clears the screen and begins the game.
   */
  private void gameInstructions() {
    screenDetail();
    System.out.println("HELLO WELCOME AND GET READY!!!");
    System.out.println("When it's your turn you can:");
    System.out.println("CHOOSE WHO TO ATTACK!!!!!");
    System.out.println(
        "\nIf a player's Pokémon faints for the first time, there is a 100% chance of it reviving. Each subsequent time it faints, the revival chance decreases by 10%!");
    System.out.println(
        "If the Pokémon comes back to life, there is a 50% chance it will evolve to its first evolution and a 33% chance it will evolve to its final evolution.");
    System.out.println(
        "\nSince "
            + listOfAllPlayers.getLast().getName()
            + " entered their information last, they get to go first!");
    System.out.println("Type and tell me how ready you are... ;)?");

    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();

    clearScreen();
    screenDetail();

    System.out.println("Let the games begin! \n");
  }

  /**
   * Executes the main game loop for the Pokémon battle game.
   *
   * <p>This method handles all rounds and turns, including:
   *
   * <ul>
   *   <li>Checking which trainers are alive and eligible to take a turn
   *   <li>Updating Pokémon status effects such as invincibility and poison
   *   <li>Displaying round information and prompting players to select opponents
   *   <li>Resolving attacks, including damage, fainting, revival, and evolution
   *   <li>Determining when the game ends (either a single winner or a draw)
   * </ul>
   *
   * <p>The method repeatedly loops through all registered trainers until only one trainer remains
   * alive, reversing the order of play at the start to give the last-registered player the first
   * turn. Status updates and round information are displayed between turns, and ASCII art is
   * printed for Pokémon as needed.
   *
   * <p>Exceptions related to Pokémon permanently dying during a turn are caught internally to allow
   * the loop to continue without crashing.
   */
  private void gameLogic() {
    int onlyOnePlayerLives = 1;
    int roundNumber = 1;
    Collections.reverse(listOfAllPlayers);

    while (!(playerAmount <= onlyOnePlayerLives)) {
      if (roundNumber != 1) {
        clearScreen();
        screenDetail();
      }

      try {

        for (Trainer currentTrainer : listOfAllPlayers) {

          if (currentTrainer.getIfTrainerIsAlive()) {

            updatePlayerInvincibleStatus(currentTrainer);

            dealWithPlayerPoisonStatus(currentTrainer);

            roundIntroductionForPlayerTurn(currentTrainer, roundNumber);

            selectPlayerToAttack(currentTrainer);
          }
        }
      } catch (pokemonDiedPermanentlyDuringItsTurnException e) {
        // Nothing to do. Turn should have ended early.
      }
      roundNumber++;
    }

    for (int i = 0; i < listOfAllPlayers.size(); i++) {
      if (listOfAllPlayers.get(i).getIfTrainerIsAlive()) {
        clearScreen();
        screenDetail();
        listOfAllPlayers.get(i).getCurrentPokemon().getAsciiArt();
        System.out.println(
            "CONGRATULATIONS TO "
                + listOfAllPlayers.get(i).getName()
                + " FOR BEATING EVERYONE!!!! YOU WON!");
        break;
      } else if (listOfAllPlayers.size() - 1 == i) {
        clearScreen();
        screenDetail();
        System.out.println("ITS A DRAW!!! Thank you all for playing!");
      }
    }
  }

  /**
   * Prompts the user to confirm whether they want to play the Free-For-All (FFA) Pokémon game.
   *
   * <p>Continuously asks the user until a valid response is entered:
   *
   * <ul>
   *   <li>"Yes" (case-insensitive) – the game will start
   *   <li>"No" (case-insensitive) – the game will not start
   * </ul>
   *
   * Any other input will trigger a reprompt until a recognized response is given.
   *
   * @return {@code true} if the user declines to play (responds "No"); {@code false} if the user
   *     agrees to play (responds "Yes")
   */
  private boolean askUserIfTheyWantToPlay() {
    System.out.println("Would you like to play FFA? Type \"Yes\" or \"No\".");

    Scanner scanner = new Scanner(System.in);
    String entryResponse = scanner.nextLine();

    while (true) {
      if (entryResponse.equalsIgnoreCase("No")) {
        return true;
      } else if (entryResponse.equalsIgnoreCase("Yes")) {
        return false;
      } else {
        System.out.println("I don't recognize your input. Please try again.");
        System.out.println("Would you like to play FFA? Type \"Yes\" or \"No\".");
        entryResponse = scanner.nextLine();
      }
    }
  }

  /**
   * Prompts the user to enter the number of players for the Free-For-All (FFA) game.
   *
   * <p>Continuously requests input until the user enters a valid number between 2 and 5
   * (inclusive), representing the total players including themselves. Invalid input will trigger a
   * reprompt with guidance.
   *
   * @return the total number of players participating in the game (2-5)
   */
  private int askHowManyPlayersArePlaying() {
    clearScreen();
    screenDetail();
    System.out.println("I hope you enjoy my little game! :)");
    System.out.println("How many players in FFA, including yourself?");
    System.out.println("Type any number \"2-5\"");

    Scanner scanner = new Scanner(System.in);
    int playerAmount = scanner.nextInt();
    int minimumPlayerAmount = 2;
    int maximumPlayerAmount = 5;

    while (true) {
      if (playerAmount >= minimumPlayerAmount && playerAmount <= maximumPlayerAmount) {
        clearScreen();
        screenDetail();
        System.out.println("Welcome all " + playerAmount + " players! Let's have a fun game!");
        return playerAmount;
      } else {
        System.out.println("I don't recognize your input. Please try again.");
        System.out.println("How many players in FFA, including yourself?");
        System.out.println("Type any number \"2-5\"");
        playerAmount = scanner.nextInt();
      }
    }
  }

  /**
   * Initializes the 3-second and 5-second timer threads used for gameplay pauses.
   *
   * <p>The 5-second timer is used when displaying a newly chosen Pokémon to the player, giving them
   * time to view it. The 3-second timer is used for short delays, such as between turns or after an
   * attack. Each timer runs on a separate {@link Thread}.
   *
   * <p>Both timers print a countdown to the console, pausing for 1.5 seconds between each number.
   */
  private void initThreads() {
    Runnable fiveSecondCounter =
        () -> {
          System.out.print("Wait for timer to complete.\n");
          for (int i = 1; i <= 5; i++) {
            if (i != 5) {
              System.out.print(i + ", ");
            } else {
              System.out.println(i);
            }
            try {
              Thread.sleep(1500);
            } catch (InterruptedException e) {
              throw new RuntimeException("Something went wrong. Please try again.");
            }
          }
        };

    fiveSecondTimer = new Thread(fiveSecondCounter);

    Runnable threeSecondCounter =
        () -> {
          for (int i = 1; i <= 3; i++) {
            if (i != 3) {
              System.out.print(i + ", ");
            } else {
              System.out.println(i);
            }
            try {
              Thread.sleep(1500);
            } catch (InterruptedException e) {
              throw new RuntimeException("Something went wrong. Please try again.");
            }
          }
        };

    threeSecondTimer = new Thread(threeSecondCounter);
  }

  /**
   * Clears the console screen by printing multiple new lines.
   *
   * <p>This method simulates a screen clear in the console to improve readability between different
   * sections of the game, such as rounds, Pokémon selection, and attack results.
   */
  private void clearScreen() {
    System.out.println(
        "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }

  /**
   * Prints a consistent screen divider for UI formatting.
   *
   * <p>This method is used to separate sections of the game in the console, such as between rounds,
   * player turns, or informational messages, providing a clear visual structure for the user.
   */
  private void screenDetail() {
    System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
  }

  /**
   * Updates the invincibility status of the current trainer's Pokémon at the start of their turn.
   *
   * <p>If the Pokémon was previously invincible, this method removes the invincibility and notifies
   * the player that their Pokémon is now vulnerable to attacks.
   *
   * @param currentTrainer the trainer whose Pokémon status is being updated
   */
  private void updatePlayerInvincibleStatus(Trainer currentTrainer) {
    if (currentTrainer.getCurrentPokemon().getTellsIfPokemonIsInvincible()) {
      currentTrainer.getCurrentPokemon().setTellsIfPokemonIsInvincible(false);
      System.out.println("Update: Your Pokémon is no longer invincible!");
    }
  }

  /**
   * Applies poison effects to the current trainer's Pokémon at the start of their turn.
   *
   * <p>If the Pokémon is poisoned, it loses health according to poison rules. If the Pokémon's
   * health drops to zero, the faint counter for the trainer is decreased, and the Pokémon's death
   * is handled (including potential permanent death or revival). Otherwise, a message is displayed
   * indicating the health loss due to poison.
   *
   * @param currentTrainer the trainer whose Pokémon is affected by poison
   * @throws pokemonDiedPermanentlyDuringItsTurnException if the Pokémon dies permanently during
   *     this turn
   */
  private void dealWithPlayerPoisonStatus(Trainer currentTrainer)
      throws pokemonDiedPermanentlyDuringItsTurnException {
    if (currentTrainer.getCurrentPokemon().getTellsIfPokemonIsPoisoned()) {
      currentTrainer.getCurrentPokemon().ifPoisonedChangeHealth();

      if (currentTrainer.getCurrentPokemon().getHealth() <= 0) {
        currentTrainer.decreaseFaintCounter();

        playerPokemonDied(currentTrainer, "poisoned");
      } else {
        System.out.println("Your Pokémon lost 10 health because it is poisoned!");
      }
    }
  }

  /**
   * Displays information about the current round and the active player's turn.
   *
   * <p>This includes the round number, the trainer's name, and the attack and damage details of the
   * trainer's current Pokémon. Prompts the player to choose an opponent to attack.
   *
   * @param currentTrainer the trainer whose turn is being displayed
   * @param roundNumber the current round number in the game
   */
  private void roundIntroductionForPlayerTurn(Trainer currentTrainer, int roundNumber) {
    System.out.println("\nRound: " + roundNumber + "\n");
    System.out.println("Your Name: " + currentTrainer.getName());
    currentTrainer.getCurrentPokemon().getAttackAndDamageInfo();
    System.out.println("\nType the name of the player you want to attack!:");
  }

  /**
   * Allows the current trainer to select an opponent to attack during their turn.
   *
   * <p>This method first generates a list of all alive opponents (excluding the current trainer)
   * and displays their names in a readable format. It then prompts the player to type the name of
   * the trainer they wish to attack. Input is validated to ensure it matches an available target.
   * Once a valid opponent is selected, the method proceeds to handle the attack logic for the
   * current trainer's Pokémon.
   *
   * @param currentTrainer the trainer whose turn it is to attack
   */
  private void selectPlayerToAttack(Trainer currentTrainer) {
    String attackablePlayerNamesText = "";
    ArrayList<String> attackablePlayerNames = new ArrayList<>();
    boolean isValidInput = true;
    String playerToBeAttacked;
    Scanner scanner = new Scanner(System.in);
    String nameOfLastPlayerToBePrinted = "";

    for (int i = 0; i < listOfAllPlayers.size(); i++) {
      if (listOfAllPlayers.get(i).getIfTrainerIsAlive()
          && !listOfAllPlayers.get(i).getName().equalsIgnoreCase(currentTrainer.getName())) {
        if (attackablePlayerNamesText.isEmpty()
            || attackablePlayerNamesText.lastIndexOf(nameOfLastPlayerToBePrinted)
                == attackablePlayerNamesText.lastIndexOf("or")) {
          System.out.print("\"" + listOfAllPlayers.get(i).getName() + "\"");
          attackablePlayerNamesText =
              attackablePlayerNamesText.concat("\"" + listOfAllPlayers.get(i).getName() + "\"");
        } else {
          System.out.print(" or \"" + listOfAllPlayers.get(i).getName() + "\"");
          attackablePlayerNamesText =
              attackablePlayerNamesText.concat(" or \"" + listOfAllPlayers.get(i).getName() + "\"");
        }
        nameOfLastPlayerToBePrinted = listOfAllPlayers.get(i).getName();
        attackablePlayerNames.add(listOfAllPlayers.get(i).getName());
      }
    }

    System.out.println();
    playerToBeAttacked = scanner.nextLine();

    while (isValidInput) {
      if (checkPlayerInputChoiceToAttackPlayer(playerToBeAttacked, attackablePlayerNames)) {
        attackablePlayerNamesText = "";
        attackablePlayerNames.clear();
        isValidInput = false;
        Trainer targetTrainer = findTrainerByName(playerToBeAttacked);

        checkPokemonAttacks(currentTrainer, targetTrainer);

      } else {
        System.out.println("\nI don't recognize your input. Please try again.");
        System.out.println("Type the name of the player you want to attack!: ");
        System.out.println(attackablePlayerNamesText);
        playerToBeAttacked = scanner.nextLine();
      }
    }
  }

  /**
   * Searches for and returns a {@link Trainer} object by name from the list of all registered
   * players.
   *
   * <p>The search is case-insensitive. If no trainer with the specified name exists in {@code
   * listOfAllPlayers}, this method throws a {@link NoSuchElementException}.
   *
   * @param currentTrainer the name of the trainer to find
   * @return the {@link Trainer} instance whose name matches {@code currentTrainer}
   * @throws NoSuchElementException if no trainer with the given name exists in {@code
   *     listOfAllPlayers}
   */
  private Trainer findTrainerByName(String currentTrainer) {
    for (Trainer trainer : listOfAllPlayers) {
      if (trainer.getName().equalsIgnoreCase(currentTrainer)) {
        return trainer;
      }
    }
    throw new NoSuchElementException(
        "Trainer.Trainer could not be found from listOfAllPlayers ArrayList.");
  }

  /**
   * Determines whether the selected target for an attack is valid.
   *
   * <p>The check is case-insensitive. The method compares the provided player name against a list
   * of currently attackable player names to ensure the selection is allowed.
   *
   * @param playerToBeAttacked the name of the player the current trainer wants to attack
   * @param attackablePlayerNames the list of valid player names that can be attacked
   * @return {@code true} if {@code playerToBeAttacked} is in {@code attackablePlayerNames}; {@code
   *     false} otherwise
   */
  private boolean checkPlayerInputChoiceToAttackPlayer(
      String playerToBeAttacked, ArrayList<String> attackablePlayerNames) {
    for (String attackOption : attackablePlayerNames) {
      if (playerToBeAttacked.equalsIgnoreCase(attackOption)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Handles a trainer selecting an attack for their Pokémon and executes it on the target trainer.
   *
   * <p>If the current trainer's Pokémon has two available attacks, the user is prompted to choose
   * between attack 1 or attack 2. The method validates the input to ensure only "1" or "2" is
   * accepted. If the Pokémon has only one attack, it is automatically used.
   *
   * @param currentTrainer the trainer whose Pokémon is performing the attack
   * @param targetTrainer the trainer whose Pokémon is being targeted by the attack
   */
  private void checkPokemonAttacks(Trainer currentTrainer, Trainer targetTrainer) {
    Scanner scanner = new Scanner(System.in);
    int numInput;

    if (hasTwoAttacks(currentTrainer)) {
      boolean isValidInput = true;
      while (isValidInput) {
        System.out.println("Type in \"1\" or \"2\" to decide which attack to use");
        numInput = scanner.nextInt();
        if (numInput != 1 && numInput != 2) {
          System.out.println("\nI don't recognize your input. Please try again.");
        } else {
          isValidInput = false;

          System.out.println();
          playerWasChosenToBeAttacked(currentTrainer, targetTrainer, numInput);
        }
      }
    } else {
      playerWasChosenToBeAttacked(currentTrainer, targetTrainer, 0);
    }
  }

  /**
   * Determines whether the current trainer's Pokémon has two distinct attacks.
   *
   * <p>This method checks the Pokémon's name against the {@link TwoAttackPokemon} enum, which
   * contains all Pokémon that have two possible attacks. If the Pokémon is found in this enum, it
   * is considered to have two attacks; otherwise, it has only one.
   *
   * @param currentTrainer the trainer whose Pokémon is being checked
   * @return {@code true} if the Pokémon has two attacks; {@code false} otherwise
   */
  private boolean hasTwoAttacks(Trainer currentTrainer) {
    for (TwoAttackPokemon pokemon : TwoAttackPokemon.values()) {
      if (currentTrainer.getCurrentPokemon().getName().equalsIgnoreCase(pokemon.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Executes an attack from the current trainer's Pokémon on a target trainer's Pokémon.
   *
   * <p>This method handles all aspects of a Pokémon attack:
   *
   * <ul>
   *   <li>Checks if the target Pokémon is invincible. If so, the attack does nothing and a 3-second
   *       delay is applied for game pacing.
   *   <li>If the target is not invincible, the current Pokémon attacks using the specified attack
   *       index. Single-attack Pokémon use {@code numInput = 0}.
   *   <li>After the attack, the results are handled, including updating health, fainting, and
   *       displaying ASCII art of the Pokémon.
   *   <li>Applies a 3-second pause after the attack to allow players to see the results, then
   *       clears the screen and displays the consistent UI divider.
   * </ul>
   *
   * @param currentTrainer the trainer whose Pokémon is performing the attack
   * @param targetTrainer the trainer whose Pokémon is being attacked
   * @param numInput the attack index to use; 0 if the Pokémon has only one attack
   * @throws RuntimeException if the thread sleep is interrupted during the 3-second pause
   */
  private void playerWasChosenToBeAttacked(
      Trainer currentTrainer, Trainer targetTrainer, int numInput) {
    if (targetTrainer.getCurrentPokemon().getTellsIfPokemonIsInvincible()) {
      System.out.println("They are invincible! (Your attack did nothing :()");
      try {
        threeSecondTimer.start();
        threeSecondTimer.join();
        initThreads();
      } catch (InterruptedException e) {
        throw new RuntimeException("Something went wrong. Please try again.");
      }
      clearScreen();
      screenDetail();
    } else {
      currentTrainer.getCurrentPokemon().hitOpponent(targetTrainer.getCurrentPokemon(), numInput);
      handleAttackResult(
          targetTrainer, "You attacked " + targetTrainer.getName() + "!", "attacked");
      handleAttackResult(currentTrainer, "killed when it tried attacking");

      if (currentTrainer.getIfTrainerIsAlive()) {
        currentTrainer.getCurrentPokemon().getAsciiArt();
        try {
          threeSecondTimer.start();
          threeSecondTimer.join();
          initThreads();
        } catch (InterruptedException e) {
          throw new RuntimeException("Something went wrong. Please try again.");
        }
        clearScreen();
        screenDetail();
      }
    }
  }

  /**
   * Handles the outcome of an attack on a trainer's Pokémon.
   *
   * <p>This method is a convenience overload that allows the caller to provide a description of how
   * the Pokémon died without specifying a custom message prefix. It delegates to {@link
   * #handleAttackResult(Trainer, String, String)} with an empty prefix.
   *
   * @param currentTrainer the trainer whose Pokémon is affected by the attack
   * @param howPokemonDied a description of the cause of death or fainting event
   */
  private void handleAttackResult(Trainer currentTrainer, String howPokemonDied) {
    handleAttackResult(currentTrainer, "", howPokemonDied);
  }

  /**
   * Handles the outcome of an attack on a trainer's Pokémon, including fainting and possible
   * permanent death.
   *
   * <p>If the Pokémon's health is zero or below, this method:
   *
   * <ul>
   *   <li>Decreases the trainer's faint counter.
   *   <li>Calls {@link #playerPokemonDied(Trainer, String)} to handle revival, evolution, or
   *       permanent death.
   *   <li>Catches {@link pokemonDiedPermanentlyDuringItsTurnException} and wraps it in a {@link
   *       RuntimeException} if something goes wrong.
   * </ul>
   *
   * Otherwise, it prints the provided message describing the attack or event.
   *
   * @param currentTrainer the trainer whose Pokémon is affected
   * @param message a custom message to display if the Pokémon survives the attack
   * @param howPokemonDied a description of how the Pokémon fainted or died (used if health ≤ 0)
   */
  private void handleAttackResult(Trainer currentTrainer, String message, String howPokemonDied) {
    if (currentTrainer.getCurrentPokemon().getHealth() <= 0) {
      currentTrainer.decreaseFaintCounter();

      try {
        playerPokemonDied(currentTrainer, howPokemonDied);
      } catch (pokemonDiedPermanentlyDuringItsTurnException e) {
        throw new RuntimeException("Something went wrong. Please try again.");
      }

    } else {
      System.out.println(message);
    }
  }

  /**
   * Determines the outcome when a trainer's Pokémon faints during the game.
   *
   * <p>This method uses a random chance to decide whether the Pokémon revives or dies permanently.
   * The probability of revival decreases as the trainer's faint counter increases:
   *
   * <ul>
   *   <li>If the randomly generated number is less than or equal to the trainer's faint counter,
   *       the Pokémon revives via {@link #pokemonRevivedAfterDying(Trainer, String)}.
   *   <li>Otherwise, the Pokémon dies permanently via {@link #pokemonDiedPermanently(Trainer,
   *       String)}, and the trainer is eliminated from the game.
   * </ul>
   *
   * @param currentTrainer the trainer whose Pokémon fainted
   * @param message a description of the cause of the Pokémon's fainting
   * @throws pokemonDiedPermanentlyDuringItsTurnException if the Pokémon dies permanently during
   *     this turn
   */
  private void playerPokemonDied(Trainer currentTrainer, String message)
      throws pokemonDiedPermanentlyDuringItsTurnException {
    Random random = new Random();
    int revivalChance = random.nextInt(10);

    if (revivalChance <= currentTrainer.getFaintCounter()) {
      pokemonRevivedAfterDying(currentTrainer, message);
    } else {
      pokemonDiedPermanently(
          currentTrainer,
          " is now eliminated and can no longer play because their Pokémon was "
              + message
              + "! Chance was not on their side to respawn :(");
    }
  }

  /**
   * Handles the permanent death of a trainer's Pokémon.
   *
   * <p>This method performs the following actions:
   *
   * <ul>
   *   <li>Decrements the total number of active players.
   *   <li>Marks the trainer as no longer alive in the game.
   *   <li>Prints a message describing the permanent elimination.
   * </ul>
   *
   * <p>If the Pokémon dies permanently during its turn due to poison, the method also triggers a
   * 3-second pause before throwing a {@link pokemonDiedPermanentlyDuringItsTurnException} to signal
   * that the turn ended early.
   *
   * @param currentTrainer the trainer whose Pokémon has permanently fainted
   * @param message a descriptive message explaining the cause of permanent death
   * @throws pokemonDiedPermanentlyDuringItsTurnException if the Pokémon died permanently during its
   *     turn due to poison
   */
  private void pokemonDiedPermanently(Trainer currentTrainer, String message)
      throws pokemonDiedPermanentlyDuringItsTurnException {
    playerAmount--;
    System.out.println(currentTrainer.getName() + message);
    currentTrainer.setIfTrainerIsAlive(false);
    if (message.equalsIgnoreCase(
        " is now eliminated and can no longer play because their Pokémon was poisoned! Chance was not on their side to respawn :(")) {
      try {
        threeSecondTimer.start();
        threeSecondTimer.join();
        initThreads();
      } catch (InterruptedException e) {
        throw new RuntimeException("Something went wrong. Please try again.");
      }
      throw new pokemonDiedPermanentlyDuringItsTurnException();
    }
  }

  /**
   * Handles the revival of a trainer's Pokémon after fainting.
   *
   * <p>When a Pokémon faints, this method determines whether it revives and whether it evolves.
   * Revival and evolution chances are random:
   *
   * <ul>
   *   <li>Pokémon in its first evolution may evolve to the next stage with a 50% chance.
   *   <li>Pokémon in its second evolution may evolve to its final stage with a ~33% chance.
   *   <li>Otherwise, the Pokémon revives at its current stage without evolving.
   * </ul>
   *
   * The method prints messages indicating the faint, revival, and any evolution events.
   *
   * @param currentTrainer the trainer whose Pokémon has fainted and may revive
   * @param message a descriptive message explaining the cause of fainting
   */
  private void pokemonRevivedAfterDying(Trainer currentTrainer, String message) {
    Random random = new Random();
    int secondEvolutionChance = random.nextInt(3);
    int firstEvolutionChance = random.nextInt(2);

    if (checkIfPokemonIsFirstEvolution(currentTrainer)) {
      if (firstEvolutionChance == 0) {
        printOutPlayersPokemonFaintStats(
            currentTrainer,
            1,
            "'s Pokémon died because it was " + message + "! But came back evolved!");
      } else {
        printOutPlayersPokemonFaintStats(
            currentTrainer,
            0,
            "'s Pokémon died because it was " + message + "! But came back to life!");
      }
    } else if (checkIfPokemonIsSecondEvolution(currentTrainer)) {
      if (secondEvolutionChance == 0) {
        printOutPlayersPokemonFaintStats(
            currentTrainer,
            2,
            "'s Pokémon died because it was " + message + "! But came back evolved!");
      } else {
        printOutPlayersPokemonFaintStats(
            currentTrainer,
            0,
            "'s Pokémon died because it was " + message + "! But came back to life!");
      }
    } else {
      printOutPlayersPokemonFaintStats(
          currentTrainer,
          0,
          "'s Pokémon died because it was " + message + "! But came back to life!");
    }
  }

  /**
   * Checks whether the current Pokémon is in its first evolution stage.
   *
   * <p>The method compares the name of the trainer's current Pokémon against all Pokémon defined in
   * the {@link firstEvolutionPokemon} enum. This helps determine if the Pokémon is eligible for
   * first-stage evolution upon revival.
   *
   * @param currentTrainer the trainer whose Pokémon is being checked
   * @return {@code true} if the Pokémon is in the first evolution stage; {@code false} otherwise
   */
  private boolean checkIfPokemonIsFirstEvolution(Trainer currentTrainer) {
    for (firstEvolutionPokemon name : firstEvolutionPokemon.values()) {
      if (currentTrainer.getCurrentPokemon().getName().equalsIgnoreCase(name.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether the current Pokémon is in its second (final) evolution stage.
   *
   * <p>The method compares the name of the trainer's current Pokémon against all Pokémon defined in
   * the {@link SecondEvolutionPokemon} enum. This is used to determine if the Pokémon can evolve
   * further upon revival or for display purposes.
   *
   * @param currentTrainer the trainer whose Pokémon is being checked
   * @return {@code true} if the Pokémon is in the second evolution stage; {@code false} otherwise
   */
  private boolean checkIfPokemonIsSecondEvolution(Trainer currentTrainer) {
    for (SecondEvolutionPokemon name : SecondEvolutionPokemon.values()) {
      if (currentTrainer.getCurrentPokemon().getName().equalsIgnoreCase(name.toString())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Updates a trainer's Pokémon after it faints and handles potential revival and evolution.
   *
   * <p>Depending on the {@code evolutionStage}, this method uses the {@link PokemonFactory} to
   * generate the correct Pokémon instance. It then prints an informative message including:
   *
   * <ul>
   *   <li>The trainer's name
   *   <li>The cause of fainting or revival
   *   <li>The Pokémon's updated name after evolution (if any)
   *   <li>The trainer's current chance for their Pokémon to revive again
   * </ul>
   *
   * @param currentTrainer the trainer whose Pokémon is being updated
   * @param evolutionStage the evolution stage to assign: 0 = no evolution, 1 = first evolution, 2 =
   *     second evolution
   * @param message a descriptive message explaining the faint or revival event
   */
  private void printOutPlayersPokemonFaintStats(
      Trainer currentTrainer, int evolutionStage, String message) {
    currentTrainer.setCurrentPokemon(
        pokemonFactory.pokemonFactory(
            tellsFactoryWhichPokemonToCreate(currentTrainer, evolutionStage)));
    System.out.println(
        currentTrainer.getName()
            + message
            + " Welcome "
            + currentTrainer.getCurrentPokemon().getName()
            + " TO THE WORLD!!!! "
            + currentTrainer.getName()
            + "'s Pokémon now has a "
            + currentTrainer.getFaintCounter() * 10
            + "% chance of coming back to life.");
  }

  /**
   * Determines the correct Pokémon name to create in the factory based on the trainer's current
   * Pokémon and the desired evolution stage.
   *
   * <p>This method is used by {@link PokemonFactory#pokemonFactory(String)} to generate a new
   * Pokémon instance that corresponds to either the current stage, the first evolution, or the
   * second evolution stage. If no valid Pokémon exists for the given evolution stage, an {@link
   * IllegalArgumentException} is thrown.
   *
   * @param currentTrainer the trainer whose Pokémon is being evolved or revived
   * @param evolutionStage the evolution stage to create:
   *     <ul>
   *       <li>0 = default (current Pokémon)
   *       <li>1 = first evolution
   *       <li>2 = second (final) evolution
   *     </ul>
   *
   * @return the name of the Pokémon corresponding to the desired stage, as a {@link String}
   * @throws IllegalArgumentException if no valid Pokémon exists for the given evolution stage
   */
  private String tellsFactoryWhichPokemonToCreate(Trainer currentTrainer, int evolutionStage) {
    if (evolutionStage == 0) {
      return switch (currentTrainer.getCurrentPokemon().getName().toUpperCase()) {
        case "PIKACHU" -> "PIKACHU";
        case "RAICHU" -> "RAICHU";
        case "CHARMANDER" -> "CHARMANDER";
        case "CHARMELEON" -> "CHARMELEON";
        case "CHARIZARD" -> "CHARIZARD";
        case "BULBASAUR" -> "BULBASAUR";
        case "IVYSAUR" -> "IVYSAUR";
        case "VENUSAUR" -> "VENUSAUR";
        case "SQUIRTLE" -> "SQUIRTLE";
        case "WARTORTLE" -> "WARTORTLE";
        case "BLASTOISE" -> "BLASTOISE";
        default ->
            throw new IllegalArgumentException(
                "The default evolution of the Pokémon you want to evolve doesn't exist!");
      };
    } else if (evolutionStage == 1) {
      return switch (currentTrainer.getCurrentPokemon().getName().toUpperCase()) {
        case "CHARMANDER" -> "CHARMELEON";
        case "BULBASAUR" -> "IVYSAUR";
        case "SQUIRTLE" -> "WARTORTLE";
        default ->
            throw new IllegalArgumentException(
                "The first evolution of the Pokémon you want to evolve doesn't exist!");
      };
    } else if (evolutionStage == 2) {
      return switch (currentTrainer.getCurrentPokemon().getName().toUpperCase()) {
        case "PIKACHU" -> "RAICHU";
        case "CHARMELEON" -> "CHARIZARD";
        case "IVYSAUR" -> "VENUSAUR";
        case "WARTORTLE" -> "BLASTOISE";
        default ->
            throw new IllegalArgumentException(
                "The second evolution of the Pokémon you want to evolve doesn't exist!");
      };
    }
    throw new IllegalArgumentException(
        "Something went wrong and tellsFactoryWhichPokemonToCreate() did not know what to return.");
  }
}
