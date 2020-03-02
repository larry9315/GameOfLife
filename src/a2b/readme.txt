Student #: A01035412
Name: Larry Park
Set: E


Description:

This program is a gui program that generates a 2d array of
Cell class objects. Each Cell has-a Lifeform class objects
of either Plants, Herbivores, Carnivores, or Omnivores. If
the cell has Herbivore it will display yellow, if the cell has a Plant, it will 
display green, if the Cell has Carnivores, it will display red, if the Cell has
Omnivoresit will display blue, if it's empty it will display plain white. 
Everytime a click is registered herbivores, Carnivores and Omnivores will move 
one cell at a time and Animals, and plants will reproduce to empty neighbor cells
according to their specific rules described on the assignment instruction page.
The program also implements Saving and Loading serialization method where it saves
the current progress of the maze, saves the progress in a file in your local machines
and then loads that when you press the load button.

Implementation
The way moving works in this assignment changed a little from assignment A2a. The
program first obtains arraylist of food neighbors which depends what animal type it is.
For example if it is Omnivores, it will first get neighbouring cells of food which is 
Plants, Carnivores, and herbivores into an arraylist. If the list is not empty, it will
update it's position according to the randomly chosen cell from the arraylist. If the 
list is empty, it will get neighboring cells of empty cells and add it to the list. Then
it will randomly choose the empty cells and update its position. By doing it this way it 
will always choose the food cells over empty cells when the animals are moving. The birthing
is implemented by counting the neighbor's empty cells, number of food cells, and its fellow
Animal type. Then it checks the condition step by step. Each time a condition is true it moves 
to next condition to check if its true.
