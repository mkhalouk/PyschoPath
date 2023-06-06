# PyschoPath

## Game Presentation

This is an adventure that puts you in the shoes of Frank Atwell, a private detective, summoned by a murderer to his crime scene in a retirement home to challenge him to unmask him. The goal is to meticulously follow the clues laid out by the psychopath in order to uncover his identity and understand his motivations.

## Using the Interface

Upon launching the game, the player is presented with an initial interface that outlines the theme of the game and their mission in a text field. In the center bottom of the screen is a "Start Game" button to begin playing.
Once the button is pressed, the player is transported to the first room of our world, which is the living room. The interface consists of two main parts:

- The image of the world, where the player can move around and interact with objects,
- The control panel, containing all buttons as well as a large text field for displaying descriptions.

The image of the world is composed of objects on which you can left-click to display their descriptions, or right-click to pick up collectible objects and add them to your inventory.
Doors are also clickable; a right-click on a door will unlock it if it's locked, and if not, a left-click will transport us to the next room of the game.
Movement can be controlled with the keyboard arrow keys, the WASD keys, or by clicking directly on the arrow buttons on the control panel.
Other buttons include:

- "Help": opens a dialog window with instructions on how to use the interface and interact with the game,
- "Inventory": displays all objects in the inventory with images in a panel in the middle of the screen,
- "Pickup dropped items": allows the player to collect objects that they have dropped on the ground,
- "Map": displays a general map of the world to help the player orient themselves,
- "Quit": stops the application from running and closes the window.

## Internal Structure of the Application

We have developed our application using JDK version 8.0.111.

The application is based on the Model-View-Controller design pattern.
The 'model' package contains classes we had previously programmed for the command-line version of the game and represents the core of the game.
The 'view' package contains all .fxml files created from SceneBuilder. Each file graphically represents a scene of the game, i.e., a room in the retirement home.
The 'controller' package, on the other hand, is the brain of the application. It contains all controllers associated with each view as well as game launch files, which serve to link with the model.
We will now detail the content of each model to explain how it works.
