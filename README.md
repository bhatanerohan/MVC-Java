# Image Processing Application README

## Overview

This image processing application allows you to load, manipulate, and save images. It provides both
a text-based scripting interface for your convenience.

## Features

### Text-Based Scripting

To use the text-based scripting interface, you can create and run scripts with the following
commands:

- `load image-path image-name`: Load an image from the specified path and refer to it by the given
  image name.
- `save image-path image-name`: Save the image with the given name to the specified path, which
  should include the file's format.
- `red-component image-name dest-image-name`: Create an image with only the red component of the
  given image.
- `green-component image-name dest-image-name`: Create an image with only the green component of the
  given image.
- `blue-component image-name dest-image-name`: Create an image with only the blue component of the
  given image.
- `value-component image-name dest-image-name`: Create an image to visualize the value component of
  the given image.
- `intensity-component image-name dest-image-name`: Create an image to visualize the intensity
  component of the given image.
- `luma-component image-name dest-image-name`: Create an image to visualize the luma component of
  the given image.
- `horizontal-flip image-name dest-image-name`: Flip an image horizontally.
- `vertical-flip image-name dest-image-name`: Flip an image vertically.
- `brighten increment image-name dest-image-name`: Brighten an image by adding the given increment.
- `darken decrement image-name dest-image-name`: Darken an image by subtracting the given decrement.
- `rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue`: Split the
  given image into its red, green, and blue components.
- `rgb-combine image-name red-image green-image blue-image`: Combine three grayscale images into a
  single color image.
- `blur image-name dest-image-name`: Apply a blur effect to the given image.
- `sharpen image-name dest-image-name`: Apply a sharpening effect to the given image.
- `sepia image-name dest-image-name`: Convert an image into a sepia-toned version.

## GUI Features

We have built a graphical user interface for our application which is a user-friendly way to perform operations on the image.
- To load an image, select the "File" menu and then "Load File" option from the menu. Select the file using the file chooser. We support loading "jpg", "png" and "ppm" files.
- To save an image, select the "File" menu and then "Save File" option from the menu. Select the path using the file chooser.
- We support resetting and image to previous image as well as the original image using the Reset menu.
- We support the ability to flip an image horizontally and vertically using the Flip horizontal and Flip vertical buttons.
- We support the ability to get red, green, and blue components of an image using the Red Component, Green Component, and Blue Component buttons.
- We support getting sepia and gray scale images using the Sepia and Gray Scale buttons.
- We support blurring and sharpening an image using the Blur and Sharpen buttons.
- We can also compress the image using compress button. We can specify the compression ratio(1-100) using the pop-up window.
- We can also color correct the image using the Color Correct button.
- We can also adjust the levels of an image using the Levels Adjust button.
- The application constantly displays an histogram of the image which is displayed in the main panel.
- The split view toggle button can give you the preview of the previous image and the current image in split window style.

## Running from JAR
The program can be run with the jar file given in the res folder in the following ways.
- `java -jar assignment6.jar` -> runs the program in GUI mode
- `java -jar assignment6.jar -file filepath` -> runs the program and executes the commands in the file given and exits
- `java -jar assignment6.jar -text` -> runs the program in interactive mode

## File Format Support

The application supports the following image file formats for import and export:

- ASCII PPM
- JPG
- PNG

## Getting Started

To run the image processing application:

1. Clone or download the code to your local repository.
2. Run the main function from Main.java class.
3. There are two ways to execute commands. The user first will have to enter 1 or 2 which will
   select either "run from script" or "manual command" option.
4. If you select 1, you can simple enter the path to your script.txt file containing the commands.
   All the commands will be executed sequentially.
5. If you select 2, you can enter your commands one by one, and even run a script file using the
   command "run script.txt".
6. You can also run a script using command line after compiling the program
   as `java Main -file filepath`

## Design of the Program

The program is written following the MVC architecture. You can refer the following class diagram to
understand the structure.
![img.png](assignment6.png)

#### Model

### Updates (Assignment 6)

We have not made many changes to the design of the whole application compared to assignment 5. This assignment focuses on implementing the functionalities of the application in a GUI.
To do the same we have used Java Swing library. The program now supports running the commands from a text file, running commands in interactive mode and running the program in GUI so that the user can interact in a user-friendly way.

1. We have created a class ImageView to handle the view of the image.
2. All the main display components are present in the ImageView class.
3. We do have some additional classes which are used to build additional components or functionalities of the GUI.
4. We have kept the controller in charge of the view and the model. That is controller handsover the control to view based on the user request to perform the required task. And the controller gives signal to the view to update itself. The design implemented in our program adheres to the MVC architecture as per the requirements.
5. To do the above we make another method in the constructor which runs the GUI. This method is invoked from Main class.
6. We have shifted the code from the controller for command-line interface(the switch statements) to the ProcessCommand class. Controller stays in control of the same.
7. Apart from the additions required for this assignment there are not any major changes in the design and structure.

### Updates (Assignment 5)

We have made a couple of changes to the structure and design of the code. They are listed as
follows:-

1. We have created separate classes for all the individual functionalities to make the code more
   modular and clean.
2. All the image operations code is placed in imageops package inside model.
3. This will help in future when adding new functionalities to the program.
4. We have also added utils, which contains classes/code for utility functions like loading,
   padding, saving etc.
5. We have also decoupled the image storage from the model, in a separate class ImageStorage. All
   the data can be accessed using its object.
6. We have added the functionality to run a file from command line argument
   as `java Main -file filepath`. Make sure you have the data in the appropriate folder before
   executing this command.

#### Previous version

1. The ImageModel class contains all the functionalities and operations to be performed on the
   image.
2. The model package also has classes to handle loading and saving and images.

#### Controller

1. The controller has ImageController class which interacts with the user and the model to execute
   the commands given by the user.

#### View

1. The view package contains all the functionalities to operate GUI made by Java Swing with which 
    the user will interact to perform all the operations that are in the model through the 
    controller.

## Example Script

Here's an example script that demonstrates various functionalities, more command options can be
found in USEME.md

```plaintext
# load fallneu file and call it 'fallneu'
load fallneu.jpg fallneu

# save fallneu
save fallneu.png fallneu

# seperate components
red-component fallneu fallneured
save res/fallneured.png fallneured

green-component fallneu fallneugreen
save res/fallneugreen.ppm fallneugreen

blue-component fallneu fallneublue
save res/fallneublue.jpg fallneublue

# flip fallneu horizontally
horizontal-flip fallneu fallneuhoriz
save res/fallneu-horizontal.png fallneuhoriz

# flip fallneu vertically
vertical-flip fallneu fallneuvert
save res/fallneu-vertical.png fallneuvert

# flip fallneu horizontally and then vertically
horizontal-flip fallneu fallhor
vertical-flip fallhor fallhorver
save res/fallneu-horizontical-vertical.png fallhorver

# brighten fallneu by adding 10
brighten 10 fallneu fallneub
```



