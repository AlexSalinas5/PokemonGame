import ASCIIArt.PokemonArt;
import Pokemon.Electricity.Raichu;
import Pokemon.Fire.Charizard;
import Pokemon.Fire.Charmeleon;
import Pokemon.Grass.Bulbasaur;
import Pokemon.Fire.Charmander;
import Pokemon.Electricity.Pikachu;
import Pokemon.Grass.Ivysaur;
import Pokemon.Grass.Venusaur;
import Pokemon.Water.Blastoise;
import Pokemon.Water.Squirtle;
import Pokemon.TwoAttackPokemon;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import Pokemon.PokemonAndHealth;
import Pokemon.Water.Wartortle;

public class Main {
  public static void main(String[] args) throws InterruptedException {

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

    clearScreen();

    System.out.println(PokemonArt.pokemonLogoArt);

    screenDetail();
    System.out.println("WELCOME TO MY 3 PLAYER GAME!!!");
    System.out.println("The game will play similar to a old gen Pokémon fighting Game!!\n");

    Trainer player1 = null;
    Trainer player2 = null;
    Trainer player3 = null;
    Trainer[] players = {new Trainer(""), new Trainer(""), new Trainer("")};
    Trainer currentPlayer = null;

    int playersMade = 0;

    while (playersMade < 3) {
      String playerNumber = null;

      if (playersMade == 0) {
        playerNumber = "Player 1";
      } else if (playersMade == 1) {
        playerNumber = "Player 2";
      } else if (playersMade == 2) {
        playerNumber = "Player 3";
      }

      Scanner scanner = new Scanner(System.in);
      System.out.println(
          playerNumber + " please tell me your name. This will be your name as the Trainer.");
      String trainerName = scanner.nextLine();

      String illegalName1 = "";
      boolean pass = true;
      while (pass) {
        try {
          if (playersMade > 0) {
            for (int i = 0; i < 2; i++) {
              if (players[i].getName().equals(trainerName) || trainerName.equals(illegalName1)) {
                throw new IllegalArgumentException();
              }
            }
          }
          pass = false;
        } catch (IllegalArgumentException e) {
          System.out.println(
              "Your name matched someones else's or was null. Please enter a unique name.");
          trainerName = scanner.nextLine();
        }
      }

      if (playersMade == 0) {
        player1 = new Trainer(trainerName);
        players[0] = player1;
        currentPlayer = player1;
      } else if (playersMade == 1) {
        player2 = new Trainer(trainerName);
        players[1] = player2;
        currentPlayer = player2;
      } else if (playersMade == 2) {
        player3 = new Trainer(trainerName);
        players[2] = player3;
        currentPlayer = player3;
      }

      screenDetail();

      System.out.println(
          "Hello " + currentPlayer.getName() + "!!!! Choose of the the following three Pokémon.");
      pass = true;
      while (pass) {
        try {
          System.out.println("Type \"Squirtle\", \"Charmander\", or \"Bulbasaur\".");
          String trainerChoice = scanner.nextLine().toUpperCase();
          switch (trainerChoice) {
            case "BULBASAUR" -> {
              System.out.println(
                  "\nGreat choice " + currentPlayer.getName() + "! Look at your lovely Pokémon!");
              System.out.println(PokemonArt.bulbasaurArt);
              Thread timer = new Thread(fiveSecondCounter);
              timer.start();
              timer.join();
              clearScreen();
              currentPlayer.setCurrentPokemon(new Bulbasaur());
            }
            case "CHARMANDER" -> {
              System.out.println(
                  "\nGreat choice " + currentPlayer.getName() + "! Look at your lovely Pokémon!");
              System.out.println(PokemonArt.charmanderArt);
              Thread timer = new Thread(fiveSecondCounter);
              timer.start();
              timer.join();
              clearScreen();
              currentPlayer.setCurrentPokemon(new Charmander());
            }
            case "SQUIRTLE" -> {
              System.out.println(
                  "\nGreat choice " + currentPlayer.getName() + "! Look at your lovely Pokémon!");
              System.out.println(PokemonArt.squirtleArt);
              Thread timer = new Thread(fiveSecondCounter);
              timer.start();
              timer.join();
              clearScreen();
              currentPlayer.setCurrentPokemon(new Squirtle());
            }
            case "PIKACHU" -> {
              System.out.println(
                  "\nGreat choice " + currentPlayer.getName() + "! Look at your lovely Pokémon!");
              System.out.println(PokemonArt.pikachuArt);
              Thread timer = new Thread(fiveSecondCounter);
              timer.start();
              timer.join();
              clearScreen();
              currentPlayer.setCurrentPokemon(new Pikachu());
            }
            default -> throw new IllegalArgumentException();
          }
          pass = false;
        } catch (IllegalArgumentException e) {
          screenDetail();
          System.out.println("You made a mistake in typing your name. Try again!");
        } catch (InterruptedException e) {
          throw new RuntimeException("Something went wrong. Please try again.");
        }
      }

      if (playersMade == 0) {
        playerNumber = "Player 2";
        screenDetail();
        System.out.println("Please give the computer to " + playerNumber);
      } else if (playersMade == 1) {
        playerNumber = "Player 3";
        screenDetail();
        System.out.println("Please give the computer to " + playerNumber);
      }

      playersMade++;
    }

    System.out.println(PokemonArt.pokemonLogoArt);

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
            + currentPlayer.getName()
            + " entered their information last, they get to go first!");
    Scanner scanner = new Scanner(System.in);
    System.out.println("Type and tell me how ready you are... ;)?");
    String input = scanner.nextLine();

    clearScreen();

    screenDetail();

    System.out.println("Let the games begin!");

    int roundNumber = 1;
    int locationInArrayOfCurrentPlayer = 2;
    Trainer randomTrainer1 = player1;
    Trainer randomTrainer2 = player2;
    Trainer trainerThatIsGettingAttacked;

    while (playersMade != 1) {

      if (currentPlayer.getIfTrainerIsAlive()) {
        System.out.println("Round: " + roundNumber + "\n");
        if (currentPlayer.getCurrentPokemon().getTellsIfPokemonIsInvincible() == true) {
          currentPlayer.getCurrentPokemon().setTellsIfPokemonIsInvincible(false);
        }
        System.out.println("Your Name: " + currentPlayer.getName());

        if (currentPlayer.getCurrentPokemon().getTellsIfPokemonIsPoisoned()) {
          currentPlayer.getCurrentPokemon().ifPoisonedChangeHealth();

          if (currentPlayer.getCurrentPokemon().getHealth() <= 0) {
            Random random = new Random();
            int revivalChance = random.nextInt(10);
            int lastEvolutionChance = random.nextInt(3);
            int firstEvolutionChance = random.nextInt(2);
            currentPlayer.decreaseFaintCounter();

            if (revivalChance <= currentPlayer.getFaintCounter()) {
              if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.PIKACHU.toString())) {
                if (lastEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Raichu());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");
                } else {
                  currentPlayer.setCurrentPokemon(new Pikachu());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.RAICHU.toString())) {
                currentPlayer.setCurrentPokemon(new Raichu());
                System.out.println(
                    currentPlayer.getName()
                        + "'s Pokémon died because it was poisoned! But came back back to life!"
                        + currentPlayer.getName()
                        + "'s Pokémon now has a "
                        + currentPlayer.getFaintCounter() * 10
                        + " chance of coming back to life.");

              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.CHARMANDER.toString())) {
                if (firstEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Charmeleon());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");

                } else {
                  currentPlayer.setCurrentPokemon(new Charmander());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.CHARMELEON.toString())) {
                if (lastEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Charizard());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");

                } else {
                  currentPlayer.setCurrentPokemon(new Charmeleon());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.CHARIZARD.toString())) {
                currentPlayer.setCurrentPokemon(new Charizard());
                System.out.println(
                    currentPlayer.getName()
                        + "'s Pokémon died because it was poisoned! But came back back to life!"
                        + currentPlayer.getName()
                        + "'s Pokémon now has a "
                        + currentPlayer.getFaintCounter() * 10
                        + " chance of coming back to life.");

              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.BULBASAUR.toString())) {
                if (firstEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Venusaur());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");

                } else {
                  currentPlayer.setCurrentPokemon(new Bulbasaur());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.IVYSAUR.toString())) {
                if (lastEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Venusaur());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");

                } else {
                  currentPlayer.setCurrentPokemon(new Ivysaur());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.VENUSAUR.toString())) {
                currentPlayer.setCurrentPokemon(new Venusaur());
                System.out.println(
                    currentPlayer.getName()
                        + "'s Pokémon died because it was poisoned! But came back back to life!"
                        + currentPlayer.getName()
                        + "'s Pokémon now has a "
                        + currentPlayer.getFaintCounter() * 10
                        + " chance of coming back to life.");

              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.SQUIRTLE.toString())) {
                if (firstEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Wartortle());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");

                } else {
                  currentPlayer.setCurrentPokemon(new Squirtle());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.WARTORTLE.toString())) {
                if (lastEvolutionChance == 0) {
                  currentPlayer.setCurrentPokemon(new Blastoise());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back evolved! Welcome "
                          + currentPlayer.getCurrentPokemon().getName()
                          + " TO THE WORLD!!!!");

                } else {
                  currentPlayer.setCurrentPokemon(new Wartortle());
                  System.out.println(
                      currentPlayer.getName()
                          + "'s Pokémon died because it was poisoned! But came back back to life!"
                          + currentPlayer.getName()
                          + "'s Pokémon now has a "
                          + currentPlayer.getFaintCounter() * 10
                          + " chance of coming back to life.");
                }
              } else if (currentPlayer
                  .getCurrentPokemon()
                  .getName()
                  .toUpperCase()
                  .equals(PokemonAndHealth.BLASTOISE.toString())) {
                currentPlayer.setCurrentPokemon(new Blastoise());
                System.out.println(
                    currentPlayer.getName()
                        + "'s Pokémon died because it was poisoned! But came back back to life!"
                        + currentPlayer.getName()
                        + "'s Pokémon now has a "
                        + currentPlayer.getFaintCounter() * 10
                        + " chance of coming back to life.");

              } else {
                throw new RuntimeException("Pokémon could not be found.");
              }

            } else {
              System.out.println(
                  currentPlayer.getName()
                      + " is now eliminated and can no longer play because they died to poison! Chance was not on their side to respawn :(");
              playersMade--;
              currentPlayer.setIfTrainerIsAlive(false);
              throw new pokemonDiedPermanentlyBecauseItWasPoisonedException();
            }
          }
          System.out.println("Your Pokémon lost 10 health because it is poisoned!");
        }

        currentPlayer.getCurrentPokemon().getAttackAndDamageInfo();

        if (randomTrainer1.getIfTrainerIsAlive() && randomTrainer2.getIfTrainerIsAlive()) {
          System.out.println(
              "\nType the name of the player you want to attack!: \""
                  + randomTrainer1.getName()
                  + "\" or \""
                  + randomTrainer2.getName()
                  + "\"");
        } else if (!randomTrainer1.getIfTrainerIsAlive()) {
          System.out.println(
              "\""
                  + randomTrainer2.getName()
                  + "\" is the last player alive! Type the name of the player to attack them!");
        } else {
          System.out.println(
              "\""
                  + randomTrainer1.getName()
                  + "\" is the last player alive! Type the name of the player to attack them!");
        }

        input = scanner.nextLine().toUpperCase();

        boolean pass = true;
        while (pass) {
          try {
            if ((!input.equals(randomTrainer1.getName().toUpperCase()))
                && (!input.equals(randomTrainer2.getName().toUpperCase()))) {
              throw new IllegalArgumentException();
            } else if (input.equals(randomTrainer1.getName().toUpperCase())) {
              trainerThatIsGettingAttacked = randomTrainer1;
            } else if (input.equals(randomTrainer2.getName().toUpperCase())) {
              trainerThatIsGettingAttacked = randomTrainer2;
            } else {
              throw new RuntimeException("Trainer could not be found");
            }

            if (currentPlayer
                    .getCurrentPokemon()
                    .getName()
                    .toUpperCase()
                    .equals(TwoAttackPokemon.PIKACHU.toString())
                || currentPlayer
                    .getCurrentPokemon()
                    .getName()
                    .toUpperCase()
                    .equals(TwoAttackPokemon.RAICHU.toString())
                || currentPlayer
                    .getCurrentPokemon()
                    .getName()
                    .toUpperCase()
                    .equals(TwoAttackPokemon.IVYSAUR.toString())
                || currentPlayer
                    .getCurrentPokemon()
                    .getName()
                    .toUpperCase()
                    .equals(TwoAttackPokemon.WARTORTLE.toString())) {

              boolean pass1 = true;
              while (pass1) {
                try {
                  System.out.println("Type in \"1\" or \"2\" to decide which attack to use");
                  int numInput = scanner.nextInt();
                  scanner.nextLine();
                  if (numInput != 1 && numInput != 2) {
                    throw new InputMismatchException();
                  }

                  if (trainerThatIsGettingAttacked
                      .getCurrentPokemon()
                      .getTellsIfPokemonIsInvincible()) {
                    System.out.println("They are invincible! (Your attack did nothing :()");
                  } else {

                    currentPlayer
                        .getCurrentPokemon()
                        .hitOpponent(trainerThatIsGettingAttacked.getCurrentPokemon(), numInput);
                    currentPlayer.getCurrentPokemon().getAsciiArt();

                    System.out.println(
                        "You attacked player " + trainerThatIsGettingAttacked.getName() + "!");
                    if (trainerThatIsGettingAttacked.getCurrentPokemon().getHealth() <= 0) {
                      Random random = new Random();
                      int revivalChance = random.nextInt(10);
                      int lastEvolutionChance = random.nextInt(3);
                      int firstEvolutionChance = random.nextInt(2);
                      trainerThatIsGettingAttacked.decreaseFaintCounter();

                      if (revivalChance <= trainerThatIsGettingAttacked.getFaintCounter()) {
                        if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.PIKACHU.toString())) {
                          if (lastEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Raichu());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");
                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Pikachu());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.RAICHU.toString())) {
                          trainerThatIsGettingAttacked.setCurrentPokemon(new Raichu());
                          System.out.println(
                              trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon died! But came back back to life! "
                                  + trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon now has a "
                                  + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                  + " chance of coming back to life.");

                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.CHARMANDER.toString())) {
                          if (firstEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Charmeleon());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");

                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Charmander());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.CHARMELEON.toString())) {
                          if (lastEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Charizard());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");

                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Charmeleon());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.CHARIZARD.toString())) {
                          trainerThatIsGettingAttacked.setCurrentPokemon(new Charizard());
                          System.out.println(
                              trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon died! But came back back to life! "
                                  + trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon now has a "
                                  + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                  + " chance of coming back to life.");

                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.BULBASAUR.toString())) {
                          if (firstEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Venusaur());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");

                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Bulbasaur());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.IVYSAUR.toString())) {
                          if (lastEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Venusaur());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");

                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Ivysaur());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.VENUSAUR.toString())) {
                          trainerThatIsGettingAttacked.setCurrentPokemon(new Venusaur());
                          System.out.println(
                              trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon died! But came back back to life! "
                                  + trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon now has a "
                                  + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                  + " chance of coming back to life.");

                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.SQUIRTLE.toString())) {
                          if (firstEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Wartortle());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");

                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Squirtle());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.WARTORTLE.toString())) {
                          if (lastEvolutionChance == 0) {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Blastoise());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back evolved! Welcome "
                                    + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                    + " TO THE WORLD!!!!");

                          } else {
                            trainerThatIsGettingAttacked.setCurrentPokemon(new Wartortle());
                            System.out.println(
                                trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon died! But came back back to life! "
                                    + trainerThatIsGettingAttacked.getName()
                                    + "'s Pokémon now has a "
                                    + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else if (trainerThatIsGettingAttacked
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(PokemonAndHealth.BLASTOISE.toString())) {
                          trainerThatIsGettingAttacked.setCurrentPokemon(new Blastoise());
                          System.out.println(
                              trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon died! But came back back to life! "
                                  + trainerThatIsGettingAttacked.getName()
                                  + "'s Pokémon now has a "
                                  + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                  + " chance of coming back to life.");

                        } else {
                          throw new RuntimeException("Pokémon could not be found.");
                        }

                      } else {
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + " is now eliminated and can no longer play!");
                        playersMade--;
                        trainerThatIsGettingAttacked.setIfTrainerIsAlive(false);
                      }
                    }

                    if (currentPlayer
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(TwoAttackPokemon.PIKACHU.toString())
                        || currentPlayer
                            .getCurrentPokemon()
                            .getName()
                            .toUpperCase()
                            .equals(TwoAttackPokemon.RAICHU.toString())) {

                      if (currentPlayer.getCurrentPokemon().getHealth() <= 0) {
                        Random random = new Random();
                        int revivalChance = random.nextInt(10);
                        int lastEvolutionChance = random.nextInt(3);
                        currentPlayer.decreaseFaintCounter();

                        if (revivalChance <= currentPlayer.getFaintCounter()) {
                          if (currentPlayer
                              .getCurrentPokemon()
                              .getName()
                              .toUpperCase()
                              .equals(PokemonAndHealth.PIKACHU.toString())) {
                            if (lastEvolutionChance == 0) {
                              currentPlayer.setCurrentPokemon(new Raichu());
                              System.out.println(
                                  currentPlayer.getName()
                                      + "'s Pokémon died during its attack! But came back evolved! Welcome "
                                      + currentPlayer.getCurrentPokemon().getName()
                                      + " TO THE WORLD!!!!");
                            } else {
                              currentPlayer.setCurrentPokemon(new Pikachu());
                              System.out.println(
                                  currentPlayer.getName()
                                      + "'s Pokémon died during its attack! But came back back to life! "
                                      + currentPlayer.getName()
                                      + "'s Pokémon now has a "
                                      + currentPlayer.getFaintCounter() * 10
                                      + " chance of coming back to life.");
                            }
                          } else if (currentPlayer
                              .getCurrentPokemon()
                              .getName()
                              .toUpperCase()
                              .equals(PokemonAndHealth.RAICHU.toString())) {
                            currentPlayer.setCurrentPokemon(new Raichu());
                            System.out.println(
                                currentPlayer.getName()
                                    + "'s Pokémon died during its attack! But came back back to life! "
                                    + currentPlayer.getName()
                                    + "'s Pokémon now has a "
                                    + currentPlayer.getFaintCounter() * 10
                                    + " chance of coming back to life.");
                          }
                        } else {
                          System.out.println(
                              currentPlayer.getName()
                                  + " is now eliminated and can no longer play because their Pokémon died during its attack!!");
                          playersMade--;
                          currentPlayer.setIfTrainerIsAlive(false);
                          throw new pokemonDiedPermanentlyBecauseItWasPoisonedException();
                        }
                      }
                    }
                  }

                  pass1 = false;
                } catch (InputMismatchException e) {
                  System.out.println(
                      "You made an error in typing which attack you want to use! Try again.");
                }
              }

            } else {
              if (trainerThatIsGettingAttacked
                  .getCurrentPokemon()
                  .getTellsIfPokemonIsInvincible()) {
                System.out.println("They are invincible! (Your attack did nothing :(");
              } else {
                currentPlayer
                    .getCurrentPokemon()
                    .hitOpponent(trainerThatIsGettingAttacked.getCurrentPokemon(), 1);
                currentPlayer.getCurrentPokemon().getAsciiArt();
                System.out.println(
                    "You attacked player " + trainerThatIsGettingAttacked.getName() + "!");
                if (trainerThatIsGettingAttacked.getCurrentPokemon().getHealth() <= 0) {
                  Random random = new Random();
                  int revivalChance = random.nextInt(10);
                  int lastEvolutionChance = random.nextInt(3);
                  int firstEvolutionChance = random.nextInt(2);
                  trainerThatIsGettingAttacked.decreaseFaintCounter();

                  if (revivalChance <= trainerThatIsGettingAttacked.getFaintCounter()) {
                    if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.PIKACHU.toString())) {
                      if (lastEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Raichu());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");
                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Pikachu());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.RAICHU.toString())) {
                      trainerThatIsGettingAttacked.setCurrentPokemon(new Raichu());
                      System.out.println(
                          trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon died! But came back back to life! "
                              + trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon now has a "
                              + trainerThatIsGettingAttacked.getFaintCounter() * 10
                              + " chance of coming back to life.");

                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.CHARMANDER.toString())) {
                      if (firstEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Charmeleon());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");

                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Charmander());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.CHARMELEON.toString())) {
                      if (lastEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Charizard());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");

                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Charmeleon());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.CHARIZARD.toString())) {
                      trainerThatIsGettingAttacked.setCurrentPokemon(new Charizard());
                      System.out.println(
                          trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon died! But came back back to life! "
                              + trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon now has a "
                              + trainerThatIsGettingAttacked.getFaintCounter() * 10
                              + " chance of coming back to life.");

                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.BULBASAUR.toString())) {
                      if (firstEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Venusaur());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");

                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Bulbasaur());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.IVYSAUR.toString())) {
                      if (lastEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Venusaur());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");

                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Ivysaur());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.VENUSAUR.toString())) {
                      trainerThatIsGettingAttacked.setCurrentPokemon(new Venusaur());
                      System.out.println(
                          trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon died! But came back back to life! "
                              + trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon now has a "
                              + trainerThatIsGettingAttacked.getFaintCounter() * 10
                              + " chance of coming back to life.");

                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.SQUIRTLE.toString())) {
                      if (firstEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Wartortle());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");

                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Squirtle());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.WARTORTLE.toString())) {
                      if (lastEvolutionChance == 0) {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Blastoise());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back evolved! Welcome "
                                + trainerThatIsGettingAttacked.getCurrentPokemon().getName()
                                + " TO THE WORLD!!!!");

                      } else {
                        trainerThatIsGettingAttacked.setCurrentPokemon(new Wartortle());
                        System.out.println(
                            trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon died! But came back back to life! "
                                + trainerThatIsGettingAttacked.getName()
                                + "'s Pokémon now has a "
                                + trainerThatIsGettingAttacked.getFaintCounter() * 10
                                + " chance of coming back to life.");
                      }
                    } else if (trainerThatIsGettingAttacked
                        .getCurrentPokemon()
                        .getName()
                        .toUpperCase()
                        .equals(PokemonAndHealth.BLASTOISE.toString())) {
                      trainerThatIsGettingAttacked.setCurrentPokemon(new Blastoise());
                      System.out.println(
                          trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon died! But came back back to life! "
                              + trainerThatIsGettingAttacked.getName()
                              + "'s Pokémon now has a "
                              + trainerThatIsGettingAttacked.getFaintCounter() * 10
                              + " chance of coming back to life.");

                    } else {
                      throw new RuntimeException("Pokémon could not be found.");
                    }

                  } else {
                    System.out.println(
                        trainerThatIsGettingAttacked.getName()
                            + " is now eliminated and can no longer play!");
                    playersMade--;
                    trainerThatIsGettingAttacked.setIfTrainerIsAlive(false);
                  }
                }
              }
            }

            pass = false;

          } catch (IllegalArgumentException e) {
            if (randomTrainer1.getIfTrainerIsAlive() && randomTrainer2.getIfTrainerIsAlive()) {
              System.out.println(
                  "\nType the name of the player you want to attack!: \""
                      + randomTrainer1.getName()
                      + "\" or \""
                      + randomTrainer2.getName()
                      + "\"");
            } else if (!randomTrainer1.getIfTrainerIsAlive()) {
              System.out.println(
                  "\""
                      + randomTrainer2.getName()
                      + "\" is the last player alive! Type the name of the player to attack them!");
            } else {
              System.out.println(
                  "\""
                      + randomTrainer1.getName()
                      + "\" is the last player alive! Type the name of the player to attack them!");
            }
            input = scanner.nextLine().toUpperCase();
          } catch (pokemonDiedPermanentlyBecauseItWasPoisonedException _) {
            pass = false;
          }
        }
      }

      if (locationInArrayOfCurrentPlayer == 2) {

        currentPlayer = player2;
        randomTrainer1 = player1;
        randomTrainer2 = player3;
        locationInArrayOfCurrentPlayer = 1;

      } else if (locationInArrayOfCurrentPlayer == 1) {

        currentPlayer = player1;
        randomTrainer1 = player3;
        randomTrainer2 = player2;
        locationInArrayOfCurrentPlayer = 0;

      } else if (locationInArrayOfCurrentPlayer == 0) {

        currentPlayer = player3;
        randomTrainer1 = player2;
        randomTrainer2 = player1;
        locationInArrayOfCurrentPlayer = 2;

      } else {
        throw new RuntimeException(
            "\"locationInArrayOfCurrentPlayer\" variable has a unintended value!");
      }

      if (currentPlayer.getIfTrainerIsAlive()) {
        Thread timer = new Thread(threeSecondCounter);
        timer.start();
        timer.join();
        clearScreen();
        screenDetail();
        roundNumber++;
      }
    }

    for (int i = 0; i < 3; i++) {
      if (players[i].getIfTrainerIsAlive() == true) {
        clearScreen();
        screenDetail();
        players[i].getCurrentPokemon().getAsciiArt();
        System.out.println(
            "CONGRATULATIONS TO " + players[i].getName() + " FOR BEATING EVERYONE!!!! YOU WON!");
        break;
      } else if (i == 2 && players[i].getIfTrainerIsAlive() == false) {
        clearScreen();
        screenDetail();
        System.out.println("ITS A DRAW!!! Thank you all for playing!");
      }
    }
  }

  public static void clearScreen() {
    System.out.println(
        "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  }

  public static void screenDetail() {
    System.out.println("\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
  }
}
