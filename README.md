# Dominoes & Carcassonne Game Implementation

## Launch the game

To launch the game, run the following command in the terminal:
```bash
javac Menu.java && java Menu
```

## Game instructions

1. First, choose the number of players and the number of AIs among them.
2. Click on the desired game to start a game.

### Game sequence

- The first player is always Player 1, but the order after him is random.
- The first player draws a tile (or gives up), then enters the number of the square to be filled in the text field.
- In the event of an error, a window appears to specify the error.
- The game passes automatically to the next player if the first player has placed his tile.
- If the game includes AIs, they play automatically as soon as it's their turn, and a message indicates whether the AI has successfully laid its tile or not. The AI procedure is as follows:
  - The AI has a basic 50 chances (modifiable at will) of placing its tile. It tries to place the tile on a random square, rotated to a random position.
  - If, after 50 attempts, it still hasn't found a suitable position, it discards its tile and moves on to the next player.
- Once there are no more tiles in the bag, or only one player remains in the game (the other(s) having given up), the game ends with the winner and his or her number of points.
