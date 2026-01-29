**IMPORTANT NOTE:** I made a significant update to the code on Jan. 14, 2026. Once I finish implementing the `gameLogic()` method in the `GameLogic.GameLogic` class, I will update this README with the latest changes.

--

# PokemonGame

## Turn-Based Pokémon Battle Game (Completed)

**In Terminal:** A fast-paced, 3-player Pokémon-style battle simulator written in Java, featuring ASCII visuals and turn-based combat logic. This project applies core object-oriented programming principles; encapsulation, inheritance, abstraction, interfaces, and polymorphism; to implement elemental damage systems, Pokémon evolution, and status effects such as poison and invincibility. The codebase consists of over 2,500 lines across 25 classes, including abstract classes, interfaces, custom exceptions, and enums.

---

## Point of the Project
To get familiar with basic OOP principles (encapsulation, inheritance, polymorphism, abstraction) while also making a game for me and my friends to play.

---

## Improvements to the Project
- I am very aware of my repetition of code in the `hitOpponent` method for the `Pokemon` class, as well as the large amount of repeated code in the `main` method (a violation of the DRY principle).  
- I am also aware of my use of magic numbers.
- I should have added method documentation that clearly states what the parameters are for a method, how to use it, and its return value.
- I should have written JUnit tests.
- I should have made my `main` method smaller. For example, the first half of my `main` method was just an introduction. I should have created a separate class called `IntroductionToGame`, divided it into even smaller methods within that class, and called it in the `main` method, followed by another class called `GameLogic.GameLogic`, which would also be divided into smaller methods, instead of having one massive `main` method where I sometimes got lost in my own code.

Ideally, I envision my `main` method looking something like:

```java
import GameLogic.GameLogic;

public class Main.Main {
    public static void main(String[] args) {
        IntroductionToGame.start();
        GameLogic.start();
    }
}
```

---

## What I Learned
Planning code is the most important thing in programming (UML diagrams). I found myself, in the beginning, being motivated by an idea and just starting to program without a clear structure of how the code would work—what methods I would have, what parameters they would use, their return values, what classes I would need, and which abstract classes, interfaces, and enums were necessary.
I found myself deleting tons of code just to rewrite it slightly differently because I realized a massive issue with how the logic was handled. I quickly learned the problem with magic numbers, and to deal with some of them, I decided to use enums with methods that returned numbers. I also created local variables in methods equal to specific numbers and replaced the usage of the number with the variable, making my logic clearer instead of wasting time figuring out why a specific number was chosen.

I learned why it’s important to do things the right way first instead of doing them wrong and thinking I’d go back to fix them later. I strengthened my understanding of OOP principles and developed an understanding of why it’s important to create code that is loosely coupled—because if they are tightly coupled, I’ve noticed how easy it is to break everything. I learned the importance of testing (JUnit, but also running the entire program to check if it works as intended).

---

## Final Thoughts
This personal project has taught me a massive amount, and although I know I can absolutely fix every issue I see with my program (with my most upsetting offense being my main method where I repeated a lot of code, but I stated a potential idea above to fix this), I am proud that I was able to complete my initial goal for starting the project.
I know I will take all of the lessons I’ve learned into the next project, where I believe I will feel completely satisfied with the result. I have made many mistakes with this project but have learned much more because of them.

---

Thank you very much for reading.
