# Dominos & Carcassonne - Tile-Based Board Games

A Java implementation of two classic tile-placement board games: **Dominos** and **Carcassonne**. This project features both graphical (Swing GUI) and terminal-based interfaces, with support for human players and AI opponents.

## 📖 About the Games

### Dominos
A tile-matching game where players place numbered tiles on a grid board. Each tile has four sides with three numbers per side. Players earn points by matching tile edges with adjacent tiles on the board.

**Rules:**
- Players take turns drawing and placing tiles from a shared bag
- Tiles can only be placed if their edges match adjacent tiles
- Each matching edge awards points equal to the sum of the three numbers on that edge
- The game ends when all tiles are placed or no valid moves remain
- The player with the most points wins

### Carcassonne
A strategic tile-placement game where players build a medieval landscape by placing tiles featuring cities, roads, fields, and monasteries. Players can also place followers (meeples) to claim features and score points.

**Rules:**
- Players draw tiles and place them to extend the game board
- Tiles must be placed so edges match (road to road, city to city, etc.)
- Players can place followers on unclaimed features to score points
- Each player has a limited number of followers (2 in this implementation)
- Strategic placement and feature completion determine the winner

## 🎮 Game Modes

The application offers three distinct play modes:

1. **Dominos (GUI)** - Graphical interface with Swing
2. **Carcassonne (GUI)** - Graphical interface with images and follower placement
3. **Dominos Terminal** - Text-based console version for Dominos

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)** 8 or higher
- **javac** compiler (included with JDK)
- Terminal or command prompt access

### Installation

1. **Download or clone the project**
```bash
git clone <repository-url>
cd <project-directory>
```

2. **Verify the images folder exists**
Ensure the `images/` directory contains:
- `menu.png` - Menu icon
- `domino.png` - Dominos game icon
- `carcassonneImg.png` - Carcassonne game icon
- Carcassonne tile images (various `.png` files for different tile types)

### Running the Game

**Compile and run in one command:**
```bash
javac Menu.java && java Menu
```

**Or compile and run separately:**
```bash
# Compile all Java files
javac *.java

# Run the game
java Menu
```

## 🎯 How to Play

### Starting a Game

1. **Launch the menu** - Run the `Menu` class
2. **Select number of players** - Choose 2, 3, or 4 players from the dropdown
3. **Select number of AI players** - Choose how many players should be AI-controlled
   - Note: You must have at least one human player
4. **Choose a game mode** - Click one of the three buttons:
   - **Dominos** - GUI version of Dominos
   - **Carcassonne** - GUI version of Carcassonne
   - **Dominos Terminal** - Console version of Dominos

### Gameplay (GUI Modes)

#### Turn Structure
1. **Draw a tile** - Click the "Piocher" (Draw) button to draw a tile from the bag
2. **Rotate tile** (optional) - Click "Tourner" (Rotate) to rotate the tile 90° clockwise
3. **Place the tile**:
   - Enter the cell number where you want to place the tile (format: XY, e.g., "23" for row 2, column 3)
   - For Carcassonne: optionally check the box to place a follower and select a direction
   - Click "Valider" (Validate) to confirm placement
4. **Pass** (optional) - Click "Passer" (Pass) to skip your turn without placing the tile

#### Special Actions
- **Give up** - Click "Abandonner" (Give Up) to forfeit the game
- **AI turns** - AI players automatically take their turns when it's their time

### Gameplay (Terminal Mode)

When playing Dominos in terminal mode:

1. **On your turn**, you'll see:
   - Current board state (ASCII representation)
   - Your name and score
   - Prompt to `<piocher>` (draw) or `<abandonner>` (give up)

2. **After drawing**, you can:
   - `<placer>` - Place the tile (then enter X and Y coordinates)
   - `<tourner>` - Rotate the tile
   - `<passer>` - Skip your turn

3. **AI behavior**:
   - AI players attempt up to 1000 random placements
   - Success or failure messages are displayed
   - AI automatically passes if no valid placement is found

## 📁 Project Structure

```
project/
├── Menu.java                   # Main menu and game launcher
├── Model.java                  # Abstract base model for game logic
├── ModelDomino.java            # Dominos-specific game logic
├── ModelCarcassonne.java       # Carcassonne-specific game logic
├── View.java                   # Base GUI view class
├── ViewDomino.java             # Dominos GUI view
├── ViewCarcassonne.java        # Carcassonne GUI view
├── Controler.java              # MVC controller for game actions
├── DominoTerm.java             # Terminal-based Dominos implementation
├── Tuile.java                  # Abstract base class for tiles
├── TuileDomino.java            # Dominos tile with numeric values
├── TuileCarcassonne.java       # Carcassonne tile with terrain types
├── Case.java                   # Board cell representation
├── Joueur.java                 # Player class with name, score, followers
├── Pion.java                   # Follower/meeple placement tracker
├── Abandonner.java             # Give up dialog window
├── README.md                   # This file
└── images/                     # Image assets directory
    ├── menu.png
    ├── domino.png
    ├── carcassonneImg.png
    └── *.png                   # Carcassonne tile images
```

## 🏗️ Architecture

The project follows a **Model-View-Controller (MVC)** design pattern:

### Model Layer
- **Model.java** - Core game state management
  - 12×12 game board (10×10 playable area)
  - Tile bag management
  - Player management
  - Turn rotation logic
- **ModelDomino.java** - Extends Model with Dominos-specific validation and scoring
- **ModelCarcassonne.java** - Extends Model with Carcassonne-specific validation and follower tracking

### View Layer
- **View.java** - Base GUI implementation with Swing
  - Grid-based board display
  - Player statistics panel
  - Tile drawing and placement interface
- **ViewDomino.java** - Dominos-specific visual representation
- **ViewCarcassonne.java** - Carcassonne-specific view with image-based tiles and follower UI
- **DominoTerm.java** - Terminal-based interface for Dominos

### Controller Layer
- **Controler.java** - Mediates between Model and View
  - Validates tile placements
  - Updates game state
  - Refreshes display

### Data Classes
- **Tuile** - Abstract tile with rotation logic
- **TuileDomino** - Tile with four sides of three random integers (0-3)
- **TuileCarcassonne** - Tile with terrain types and associated images
- **Case** - Board cell with references to adjacent cells
- **Joueur** - Player with name, score, and follower count
- **Pion** - Follower placement tracking

## 🎲 Game Mechanics

### Board Grid System
- 12×12 grid with playable area from rows/columns 1-10
- Cells numbered from 11 to 110 (row*10 + column)
- Each cell tracks adjacent cells (north, south, east, west)

### Tile Validation
Both games validate tile placement by:
1. Checking if the cell is empty
2. Ensuring at least one adjacent cell has a tile (except first tile)
3. Verifying all adjacent tile edges match

**Dominos:**
- Each side has 3 integers that must exactly match adjacent sides
- Points awarded for each matching edge

**Carcassonne:**
- Each side has a terrain type (city, road, field, monastery)
- Terrain types must match at all adjacent edges

### AI Implementation
AI players use a **random placement strategy**:
1. Draw a tile from the bag
2. Attempt random placements with random rotations
3. Try up to 1000 placement attempts
4. Place tile on first valid position found
5. Pass turn if no valid placement after all attempts

## 🎨 Customization

### Adjusting AI Difficulty
Modify the `x` variable in bot methods to change AI attempts:
```java
int x = 1000;  // Number of placement attempts
```

### Modifying Tile Count
Edit the model constructors to change the number of tiles:
```java
// ModelDomino.java
for(int i=0; i<20; i++){  // Change 20 to desired count
    sac.add(new TuileDomino());
}
```

### Changing Board Size
Modify the board dimensions in `Model.java`:
```java
plateau = new Case[12][12];  // Change dimensions (keep borders for adjacency)
```

## 🐛 Known Issues & Limitations

- **Image rotation**: Carcassonne tile images rotate via transformation, which may cause slight quality degradation
- **No save/load**: Games cannot be saved and resumed
- **Limited scoring**: Carcassonne scoring is simplified (no feature completion bonuses)
- **Fixed follower count**: Players have exactly 2 followers with no retrieval mechanism
- **AI strategy**: AI uses purely random placement, not strategic decision-making
- **No multiplayer networking**: All players must use the same computer

## 🔮 Future Enhancements

Potential improvements for the project:
- Implement proper Carcassonne scoring (completed cities, roads, monasteries)
- Add save/load game functionality
- Create strategic AI with scoring heuristics
- Network multiplayer support
- Undo/redo functionality
- Game replay and move history
- Additional tile sets and game variants
- Sound effects and animations

## 🤝 Contributing

Contributions are welcome! Areas for contribution:
- Bug fixes and code optimization
- Enhanced AI algorithms
- Additional game modes or variants
- Improved graphics and UI/UX
- Documentation improvements
- Unit tests

## 📝 Technical Notes

### Compilation Requirements
- All `.java` files must be in the same directory
- Images must be in an `images/` subdirectory
- Compiling `Menu.java` automatically compiles all dependencies

### Java Version Compatibility
- Developed for Java 8+
- Uses Swing for GUI (included in standard JDK)
- No external dependencies required

### Memory Considerations
- Image loading uses `BufferedImage` which can consume memory
- Consider image optimization for better performance
- Tile bag uses `LinkedList` for efficient removal operations

## 📄 License

Educational project - free to use and modify for learning purposes.

---

**Enjoy the games! 🎲🎮**
