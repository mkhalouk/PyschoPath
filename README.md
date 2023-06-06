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

The application is based on the Model-View-Controller design pattern.
The 'model' package contains classes we had previously programmed for the command-line version of the game and represents the core of the game.
The 'view' package contains all .fxml files created from SceneBuilder. Each file graphically represents a scene of the game, i.e., a room in the retirement home.
The 'controller' package, on the other hand, is the brain of the application. It contains all controllers associated with each view as well as game launch files, which serve to link with the model.
We will now detail the content of each model to explain how it works.

-------------
Here is the translated English version with Markdown syntax for sections, subtitles, and lists:

## Game Solution (SPOILERS)
As our application consists of a series of puzzles, we will present here the steps to follow in order to complete each level (room) and reach the end of the game:

### Living Room: 
- Move to the left.
- Right-click on the left chair to pick up the UV pen.
- Move down to the door.
- Click on the door to go to the corridor.

### Corridor:
- Go straight down.
- Click on the dining room door.

### Dining Room:
- Move left twice.
- Pick up the post-it note from the safe with a right-click.
- Pick up the key from the silverware with a right-click as well.
- Return to the door.
- Open the inventory and examine the post-it note, it seems blank.
- Move the UV pen over it to read the message.
- Click to exit.

### Corridor: 
- Go up and then left once.
- Click on the door of room 17 to access it.

### Room 17:
- Double-click on the safe to display it.
- Drag the key onto it to unlock it.
- Exit the panel and right-click on the safe to pick up paper17.
- It is announced that another crime has been committed in room 19.
- Examine it in the inventory by clicking on it to read the message.
- The sentence is a quote from Fibonacci, and the numbers are a part of the famous Fibonacci sequence from which two numbers have been removed: 21 - 34.
- Click on the door to exit.

### Corridor:
- Go right twice.
- Click on door 19.

### Room 19:
- Double-click on the safe to display its image and the code input field.
- Enter the numbers deduced from the previous room: 2134.
- The safe is unlocked. Close the panel.
- Right-click on the safe to pick up paper19.
- It is announced that another crime has been committed in room 21.
- Examine the paper in the inventory. It's a quote that refers to the word "fish".
- Click on the door to exit.

### Corridor: 
- Go right once.
- Click on the door to room 21.

### Room 21:
- Right-click on the nightstand to pick up the fishbowl key.
- Right-click on the desk to pick up the journal.
- Unlock the journal in the inventory by dragging the key onto it.
- Examine the journal, the riddle's solution is: "Candle".
- Click on the door to exit.

### Corridor: 
- Go down once and left three times.
- Click on room 18.

### Room 18:
- Click on the large shelf. The description says there is a candle at the bottom and a book at the top.
- Right-click on it to pick up the book.
- It is announced that another crime has been committed in room 22.
- Examine the book. It contains the keyword "Caesar", as well as a quote that refers to the number 4.
- Click on the door to exit.

### Corridor: 
- Go right three times.
- Click on door 22.

### Room 22: 
- Right-click on the desk to pick up the key and paper22.
- Examine the paper, decode the message with Caesar's encryption key 4 (moving letters 4 positions in the alphabet) which results in the sentence "Room Twenty Three".
- Click on the door to exit.

### Corridor: 
- Go right then up.
- Right-click on the door to unlock it. Drag the key

 onto it.
- Exit the panel and click on the door.

### Room 23: 
- Right-click on the window to pick up the paper.
- Examine it with the UV light pen.
- The scene changes and displays the content of the paper, which is a final message from the murderer. The message explains the criminal's motivation for doing what he did and congratulates the detective on reaching the end of the treasure hunt.

