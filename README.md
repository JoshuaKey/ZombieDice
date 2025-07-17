# Zombie Dice

This is a digital implementation of the game [Zombie Dice](https://en.wikipedia.org/wiki/Zombie_Dice) using JavaFX.

Zombie Dice is a party dice game where players play as Zombies and try to get as many brains as they can.

Players randomly pick 3 dice from a bag of 13. Dice can be Green, Yellow or Red.

Each side of a die can be a Brains, Runner, or Shotgun.
- Green Dice have 3 Brains, 2 Runners and 1 Shotgun
- Yellow Dice have 2 Brains, 2 Runners and 2 Shotguns
- Red Dice have 1 Brains, 2 Runners and 3 Shotguns

If a player accumulates 13 Brains, they win.

If a player rolls 3 Shotguns, their turn ends and any brains they rolled this turn are ignored. 

If a player rolls a Runner, they can choose to put the dice back in the cup and reroll a new die. This repeats until the player chooses not to reroll or gets a Brain or Shotgun.

Once a player has finalized their roll, they can choose to end their turn or pull another random 3 dice from the bag and repeat their turn. This repeats until the player ends their turn, earns 13 brains, or rolls 3 shotguns.

When a player's turn ends, if they have not rolled 3 shotguns, they record how many brains they earned. On their next turn, they start with that amount of brains.

## Features
- An Info button to help players learn the game
- Images from the original Zombie Dice
- Supports up to 20 players
- Varying difficulties which change the amount and ratio of dies avaiable.
