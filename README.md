# Battleship
Battleship game for 2 players in console

# Description
Game was designed for 2 players. First of all we have to choose names for both players. After that program will display an empty board and instructions for placing ships.  

To place the ship on the board with have to provide two coordinates (letter and number) seperated with a single space. This coordinates represent both ends of the ship.
We have to provide information for 5 ships (5, 4, 3, 3 and 2 segments ship). Application won't allow placing ships too near of each other. There should be one cell gap between every ship.
Application displays error messages if we provide incorrect coordinates and then we have to try again.  

After placing ships phase is over for both players, program will start shooting phase. We will see two empty boards one for each player with instructions for shooting.
We can also see current score of each player.  

To shoot we have to provide single coordinate consisting of letter and number. Black dot will be displayed if we hit enemy ship. If we miss, cell will be marked with hyphen.
If we hit last part of some ship, then all segments will be marked with a crossed dot.  

If one of the players sunk all of the enemy ships, then the game is over and program will display message with name of the winner and his score.

# Launch
- clone repository
- run Battleship file in your IDE

# Requirements
Java 17
