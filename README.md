# Game of Life

[![Latest Release](https://img.shields.io/github/release/vanillaSlice/GameOfLife.svg)](https://github.com/vanillaSlice/GameOfLife/releases/latest)
[![Build Status](https://img.shields.io/travis/vanillaSlice/GameOfLife/master.svg)](https://travis-ci.org/vanillaSlice/GameOfLife)
[![Coverage Status](https://img.shields.io/coveralls/github/vanillaSlice/GameOfLife/master.svg)](https://coveralls.io/github/vanillaSlice/GameOfLife?branch=master)
[![License](https://img.shields.io/github/license/mashape/apistatus.svg)](LICENSE)

Game of Life is a cellular automaton devised by mathematician John Conway.

In the game there exists a grid of cells, each of which can be in one of two states, *alive* or *dead*.
Every cell has eight neighbours which it interacts with (the adjacent cells). After each step in the game, the following
rules apply:

1. Any live cell with fewer than two live neighbours dies, i.e. under population
2. Any live cell with two or three live neighbours lives on to the next generation
3. Any live cell with more than three live neighbours dies, i.e. overpopulation
4. Any dead cell with exactly three live neighbours becomes a live cell, i.e. reproduction

An example of cell interaction:

![Cell Interaction](/images/pattern-1.gif)

See [Wikipedia](https://en.wikipedia.org/wiki/Conway's_Game_of_Life) for more information on The Game of Life.

## Screenshot

![Screenshot](/images/screenshot-1.png)

## Getting Started

### Prerequisites

* Java 8
* Gradle (optional)

### Building

To build the project:

1. Clone the project
2. Navigate to the project directory in your terminal/command prompt
3. If you have Gradle installed locally, run the Gradle Daemon:

    ```
    gradle clean buildFatJar
    ```

   If you don't have Gradle installed locally and are running on a Unix-like platform such as Linux or Mac OS X, run:

    ```
    ./gradlew clean buildFatJar
    ```

   If you don't have Gradle installed locally and are running on Windows, run:

    ```
    gradlew clean buildFatJar
    ```

### Running

Once built, to run the application go to `build/libs` and double-click `GameOfLife-all-1.1.0.jar` or from your
terminal/command prompt run:

```
java -jar GameOfLife-all-1.1.0.jar
```

## Technology Used

For those of you that are interested, the technology used in this project includes:

* Java 8
* JavaFX (for the GUI)
* JUnit, Mockito and TestFX (for testing)
* Gradle (for building and dependency management)

## Useful Links

Resources useful for the completion of this project:

* [Game of Life on Wikipedia](https://en.wikipedia.org/wiki/Conway's_Game_of_Life)
* [JavaFX](http://docs.oracle.com/javase/8/javase-clienttechnologies.htm)
* [Gradle](https://gradle.org)
* [Silkscreen](http://www.kottke.org/plus/type/silkscreen/index.html) - the font used in the GUI

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
