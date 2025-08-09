# FXCompressor


Java File Packer & Unpacker with GUI
This is a Java-based desktop application that provides a graphical user interface for packing multiple files into a single archive and unpacking them back to their original state. The application features a custom UI with a "Grand Theft Auto: Vice City" theme, including a background image and sound effects.

🚀 Features
Pack Files: Select a directory, and the application will pack all its contents into a single file with a custom header for each packed file.

Unpack Files: Select a packed file, and the application will extract all the original files into a directory of your choice.

Graphical User Interface: An easy-to-use GUI built with Java Swing.

Thematic UI: A custom-styled interface inspired by the game "Grand Theft Auto: Vice City", featuring:

A custom background image.

Background music on a loop.

Click sound effects for buttons.

Styled buttons with glow and hover effects.

Draggable Window: The undecorated window can be moved by clicking and dragging the top area.

🛠️ Technologies Used
Java: Core programming language.

Java Swing: For creating the graphical user interface.

JavaFX (JFXPanel): Used for playing the background music (.mp3 files).

Java AWT: For graphics and event handling.

📂 Project Structure
The project is organized into the following main packages and classes:

src/
└── org/
    └── example/
        ├── Main.java                   # Main class, sets up the GUI and event handling.
        └── ShipPackerUnpacker/
            ├── Packer.java             # Handles the logic for initiating the packing process from the GUI.
            ├── ShipPacker.java         # Core logic for reading files and writing them into a packed archive.
            ├── UnPacker.java           # Handles the logic for initiating the unpacking process from the GUI.
            └── ShipUnPacker.java       # Core logic for reading the packed archive and extracting the files.

⚙️ How to Run
Prerequisites:

Java Development Kit (JDK) 8 or higher.

An IDE like IntelliJ IDEA, Eclipse, or VS Code with Java support.

Make sure your project is configured to use JavaFX libraries, as they are required for MediaPlayer.

Setup:

Clone the repository.

Place the required assets in a resources folder within your source directory (src/main/resources for Maven/Gradle projects):

vice_city_background.jpg (Background image)

vice_city_intro.mp3 (Background music)

click.mp3 (Button click sound)

Execution:

Compile and run the Main.java file.

The application window will appear.

Click "PACK" to select a directory and create a packed file.

Click "UNPACK" to select a packed file and extract its contents.
