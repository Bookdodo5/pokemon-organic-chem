<div align="center">
  <h1>Pokemon Organic Chemistry</h1>
  
  <p>
    <strong>A narrative deck-building RPG that gamifies organic chemistry through Pokemon-style battles</strong>
  </p>
</div>

---

## Why This Project Exists:
#### The answer: **My momentary impulse**

When I started, I went through my **organic chemistry** and **Pokemon** phase of my life, and conveniently at the same time, I discovered that Java is required for **"Programming Methodology"** class. Since I couldn't bring myself to create random meaningless program a first project, I decided on a whim to create this game as a practice to learn OOP and Java.

The game follows normal pokemon narrative structure, but replace pokemon battles with organic chemistry synthesis questions. I hope to cover quite a good portion of **Organic chemistry I and II**, and I will send this game to my friends in medical school when they have to study them.

---

## Key Features

- **Turn-based synthesis battle:** racing to synthesize a molecule through condition cards and reaction moves. 
- **Deck building collect-them-all card game** find cards through exploration or progressing through the story, and build your own deck to counter the obstacles.
- **Pokemon-like RPG story** but you learn organic chemistry along the way.

---

## How to Battle

### **Objective**
Be the first player to synthesize the target molecule given the starting molecule. You have LP (Lab Points) to spend each turn on cards and reactions. It's not always a fair game, so you might need to be a bit cunning to win the battle. 💀

### **Card Types**
- **Condition Cards:** Control the board reaction environment (pH, temperature, solvent, and light)
- **Reagent/Catalyst Cards:** Provide chemical reactants (Cl₂, Br₂, NaBH₄, DMS, etc.)
- **Utility Cards:** Special abilities that doesn't affect the board directly (drawing cards, gaining LP, etc.)

### **Turn Structure**
1. **Condition Play** Reset the turn. Your LP refills. You can play your condition and utility cards to manipulate the environment to be suitable for the reaction.
2. **Condition Result** The board condition (pH, temperature, solvent, light) changes according to the cards played.
3. **Reaction Play** You can play your reagent cards and reaction moves. Condition cards cannot be played anymore.
4. **Reaction Result** The reagent cards are added to the board, and if the reaction requirements are satisfied, your molecule will evolve to whatever product you get from the reaction.

### 🫠 **Evil strategies**
- The smaller molecule goes first in everything, so the one who play cards first (with a chance of the condition being replaced by the other side) can and will always get to play the reaction first and win before the other side.
- The board is shared between 2 sides, so you can predict a reagent and make use of it for your molecule without spending LP. Or maybe, you can add a reagent that will redirect your opponent's synthesis to the other way.

---

## Built With

- **Java** - Main programming language
- **Cursor AI** - The one who guided me through coding this entire thing, even though its writing and code kinda sucks and I need to rewrite everything (My rewritten code still sucks, BTW.)
- **RDKit** - Molecule image generating
- **Tiled** - Program for making tile maps for the game

---

## 🙏 Acknowledgments

- **Slay the spire** - Energy and card system
- **Pokemon Essentials** - Tileset and assets
- **Pokemon Rejuvenation** - Tileset and assets (This game is very good. Thank you for creating it.)
- **ครูไพรินทร์ กาญจนบุตร** - M5 (Grade 11) Chemistry teacher that teaches A-level organic chemistry
- **ETERNAL FLAME (VOID)** - A song that keeps me sane when debugging
- **@RyiSnow youtube channel** - The tutorial that get me started on the project

---