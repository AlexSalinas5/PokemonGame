# PokemonGame
**Website:** [https://alexsalinas5.github.io/PokemonGame/overview-tree.html](https://alexsalinas5.github.io/PokemonGame/overview-tree.html)

## Turn-Based Pokémon Battle Game (Completed)
**In Console:** A fast-paced, 2-5-player Pokémon-style battle simulator written in Java, featuring ASCII visuals and turn-based combat logic. This project applies core object-oriented programming principles; encapsulation, inheritance, abstraction, interfaces, and polymorphism; and uses the Factory design pattern to implement elemental damage systems, Pokémon evolution, and status effects such as poison and invincibility. The codebase consists of over 3,300 lines across 28 classes, including abstract classes, interfaces, custom exceptions, and enums.

---

## How to Play
1. Start the game and choose the number of trainers (2–5).
2. Each trainer enters their name and selects a Pokémon.
3. Trainers take turns choosing moves to attack opponents.
4. Damage is calculated based on move type and Pokémon stats.
5. Trainers are eliminated when all of their Pokémon faint.
6. The last trainer standing wins.

---

## Point of the Project
To get familiar with basic OOP principles (encapsulation, inheritance, polymorphism, abstraction) while also making a game for me and my friends to play.

---

## Future Improvements
I am interested in potentially adding the following features to enhance gameplay, code quality, and user experience:

- **More Pokémon:** Expand the roster to include additional types and species.  
- **Add Items:** Trainers would have the ability to heal their Pokémon and much more.  
- **Move Accuracy:** Implement hit/miss chances for attacks.  
- **Dodge Mechanics:** Add accuracy/dodge chances for Pokémon to avoid attacks.  
- **Expanded Attacks:** Give every Pokémon at least two attacks, with options for cooldowns or special mechanics; one attack per Pokémon will not have a cooldown. A maximum of four attacks will be given to each Pokémon.  
- **Team Attacks:** Enable multiple Pokémon to attack together or coordinate moves.  
- **AI Opponents:** Introduce computer-controlled players with basic strategy.  
- **JUnit Testing:** Add automated tests to improve reliability and maintainability.  
- **Code Quality Tools:** Consider adding dependencies such as static analyzers or Checkstyle to enforce coding standards and catch potential issues early.  
- **JavaFX Interface:** Add a graphical window with visuals and audio to enhance the gameplay experience.

---

## What I Learned
Planning code is the most important thing in programming (UML diagrams). I found myself, in the beginning, being motivated by an idea and just starting to program without a clear structure of how the code would work—what methods I would have, what parameters they would use, their return values, what classes I would need, and which abstract classes, interfaces, and enums were necessary.
I found myself deleting tons of code just to rewrite it slightly differently because I realized a massive issue with how the logic was handled. I quickly learned the problem with magic numbers, and to deal with some of them, I decided to use enums with methods that returned numbers. I also created local variables in methods equal to specific numbers and replaced the usage of the number with the variable, making my logic clearer instead of wasting time figuring out why a specific number was chosen.

I learned why it’s important to do things the right way first instead of doing them wrong and thinking I’d go back to fix them later. I strengthened my understanding of OOP principles and developed an understanding of why it’s important to create code that is loosely coupled—because if they are tightly coupled, I’ve noticed how easy it is to break everything. I learned the importance of testing (JUnit, but also running the entire program to check if it works as intended).

---

## Final Thoughts
This personal project has taught me a massive amount, and I am proud that I was able to complete my initial goal for starting the project.
I know I will take all of the lessons I’ve learned into the next project, where I believe I will feel completely satisfied with the result. I have made many mistakes with this project but have learned much more because of them.

---

Thank you very much for reading.

