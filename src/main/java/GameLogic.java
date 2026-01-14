import ASCIIArt.PokemonArt;
import Pokemon.PokemonFactory;
import java.util.*;

public class GameLogic {

  private final ArrayList<Trainer> listOfAllPlayers = new ArrayList<>();
  private final PokemonFactory pokemonFactory = new PokemonFactory();
  private Thread fiveSecondTimer;
  private Thread threeSecondTimer;
  private int playerAmount;

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

  public void clearScreen() {
    System.out.println(
        "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }

  public void screenDetail() {
    System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
  }

  public boolean checkStringInputContainsOnlyLetters(String input) {
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

  public boolean checkPlayerNamesToHaveNoDuplicates(
      ArrayList<Trainer> listOfAllPlayers, String newPlayer) {
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

  public void givePlayerTheirPokemon(int specificPlayer) {
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

        if (playerPokemonChoice.equalsIgnoreCase("Squirtle")) {
          System.out.println(PokemonArt.squirtleArt);
        } else if (playerPokemonChoice.equalsIgnoreCase("Charmander")) {
          System.out.println(PokemonArt.charmanderArt);
        } else if (playerPokemonChoice.equalsIgnoreCase("Bulbasaur")) {
          System.out.println(PokemonArt.bulbasaurArt);
        } else {
          System.out.println(PokemonArt.pikachuArt);
        }

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

  public void gameIntroduction() {
    System.out.println("Hi! Welcome to my version of a Pokémon battle game!");
    System.out.println(
        "There is only one game mode: Free-For-All (FFA). You can choose to play with 1 to 4 other players.");
    System.out.println(
        "The game is turn-based and will begin once all players have set their names and chosen their starter Pokémon.");
  }

  public boolean askUserIfTheyWantToPlay() {
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

  public void initThreads() {
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

  public int askHowManyPlayersArePlaying() {
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

  public void populatePlayerInformation() {
    Scanner scanner = new Scanner(System.in);
    boolean pass;
    int minimumCharacterAmount = 2;
    int maximumCharacterAmount = 20;

    for (int specificPlayer = 1; specificPlayer <= playerAmount; specificPlayer++) {
      pass = true;
      System.out.println(
          "Player Number: "
              + specificPlayer
              + ". Please tell me your name. This will be the name of your trainer.");

      String playerUserName = scanner.nextLine();

      while (pass) {
        if (playerUserName.length() > minimumCharacterAmount
            && playerUserName.length() <= maximumCharacterAmount
            && checkStringInputContainsOnlyLetters(playerUserName)
            && checkPlayerNamesToHaveNoDuplicates(listOfAllPlayers, playerUserName)) {
          listOfAllPlayers.add(new Trainer(playerUserName));
          givePlayerTheirPokemon(specificPlayer);
          pass = false;
        } else {
          System.out.println("I don't recognize your input. Please try again.");
          System.out.println(
              "Make sure your name contains only letters, is between 2 and 20 characters long, and does not duplicate another player's name.");
          playerUserName = scanner.nextLine();
        }
      }
    }
  }

  public void gameInstructions() {
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

    System.out.println("Let the games begin!");
  }

  public void gameLogic() {}
}
