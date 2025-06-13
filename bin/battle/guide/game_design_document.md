# PokeChem Game Design Document

## 🎯 Core Concept
PokeChem is a competitive card game that gamifies organic chemistry learning by combining Pokemon-style progression with authentic chemical synthesis challenges. Players battle using different starting molecules to synthesize the same target compound, managing reaction conditions and resources strategically.

---

## 🎮 Core Mechanics

### Victory Conditions
- **Primary:** First player to synthesize the target molecule wins

### Turn Structure (4 Phases)
1. **LP Refill Phase:** Restore LP to maximum (default: 3 LP)
2. **Condition Phase:** Both players play their condition modifier cards
3. **Reaction Phase:** Execute chemical reactions using reagents/catalysts cards + reaction moves (like pokemon moves). Normally, only allow one reaction per turn. Reagents/catalysts cards are added to the shared condition board.
4. **Result Phase:** The reaction chosen in previous phase is played. If the required reagents/catalysts isn't present, do nothing.

### Phase Order Priority
- Player with the smaller starting molecule (by molecular weight) goes first after both player's action is decided
- Final tiebreaker: random determination
- Condition modifiers can be replaced (Player that goes last has an advantage)
- First player to synthesize in Reaction Phase win (Player that goes first has an advantage)

---

## 💡 Resource System

### LP (Lab Points) System
- **Base LP per turn:** 3
- **LP sources:**
  - Turn refill
  - Card effects (e.g., Disulfide Bridge, Polar Bear)
  - Successful reaction bonuses +1
  
### Condition / Reagent Card Economy
- **Hand limit:** 8 cards
- **Draw:** Discard hand, and draw 6 card per turn (after LP refill)
- **Deck size:** Base deck = 15 cards (customizable) + 4-5 Battle Specific Cards to make the battle possible
- **Starting hand:** 6 cards

---

## ⚗️ Reaction System

### Reaction Requirements
Each reaction requires specific conditions:
- **pH Level:** Strong Acid, Weak Acid, Neutral, Weak Base, Strong Base
- **Temperature:** Cold, Room Temperature, Warm, Hot
- **Solvent Type:** Polar Protic, Polar Aprotic, Nonpolar
- **Light Conditions:** Radical Available/Unavailable

### Reaction Costs
- **Base cost:** 1-3 LP depending on reaction complexity
- **Modifiers:** Some condition cards can increase/decrease costs

### Selectivity & Stereochemistry
- **Regioselectivity:** Major/minor products determined by conditions and reaction
- **Stereoselectivity:** Non-existence due to the game world building
- **Rearrangements:** Carbocation rearrangements follow stability rules

---

## 🧪 Condition System

### Environmental Conditions
**Current State Tracking:**
- pH: Strong Acid ↔ Weak Acid ↔ Neutral ↔ Weak Base ↔ Strong Base
- Temperature: Cold ↔ Room ↔ Hot
- Solvent: Polar Protic / Polar Aprotic / Nonpolar
- Light: Radical Allowed / Blocked

### Condition Interactions
- **Conflicting effects:** Latest card overrides previous unless special effects are in action
- **Duration effects:** Track remaining turns for timed effects

---

## 🃏 Card Types

### 1. Condition Modifier Cards
**Function:** Alter reaction environment
**Subcategories:**
- pH Modifiers (7 cards)
- Temperature Modifiers (5 cards)  
- Light Modifiers (3 cards)
- Solvent Modifiers (4 cards)

### 2. Reagent / Catalyst Cards
**Function:** Provide chemical reactants
**Examples:** Cl₂, Br₂, H₂, HBr, NaBH₄, mCPBA
**Properties:** 
- Discarded upon use

### 3. Utility Cards
**Function:** Special game mechanics
**Examples:** Bullet Time, Eye for an Eye, Clock Rewind
**Properties:**
- Removed from deck for the combat

---

## 🎲 Advanced Mechanics

### Chain Reactions
- Some reactions can trigger follow-up reactions automatically
- Example: Ozonolysis → Reductive workup with DMS
- Used to synthesize what is usually a multi-step reaction without getting the intermediate

### Competitive Elements
- **Hand disruption:** Eye for an Eye swaps reagents
- **Resource denial:** Weather Report destroys non-oxygen reagents
- **Tempo plays:** Clock Rewind resets opponent progress

### Skill-Based Elements
- **Synthesis planning:** Multi-step reaction sequences
- **Condition management:** Balancing LP vs. optimal conditions
- **Deck building:** Synergy between condition cards and reaction strategies

---