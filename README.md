# ap4b-project

Project for the AP4B class ("Java and UML")
The root directory is quite cluttered, sorry about that.

- [Installing and running](#installing-and-running)
    - [Grabbing the source code](#grabbing-the-source-code)
    - [Compiling the source code](#compiling-the-source-code)
    - [Working with Eclipse](#working-with-eclipse)
    - [Working with Eclipse - alternate version](#working-with-eclipse-alternate-version)
- [Editing the UML files](#editing-the-uml-files)

## Installing and running

### Grabbing the source code

To work on this repository, you will to have [`git`](https://git-scm.com/) (or any git client, like [Github Desktop](https://desktop.github.com/)) installed, and clone this repository before doing anything:

```sh
git clone https://github.com/adri326/ap4b-project/
cd ap4b-project
```

If you don't need to work on the repository, then you can [download the latest zip archive](https://github.com/adri326/ap4b-project/archive/refs/heads/main.zip) and proceed to the next steps.

### Compiling the source code

You can optionally download and install the `gradle` command for your system, but the repository is shipped with scripts that will download and run a version of `gradle` for you already.

You will need a version of [JDK >= 11](http://openjdk.java.net/) (earlier versions haven't been tested) installed on your system and the `JAVA_HOME` [environment variable](https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/) should point to the folder where you installed it.

If you are on **Windows**, you may then execute the `gradlew.bat` script:

```bat
gradlew.bat build
```

If you are on a **Unix** machine, then execute the `gradlew` script:

```sh
./gradlew build
```

If you **already have `gradle` installed**, then you can execute it directly:

```sh
gradle build
```

The `gradle` build system should then download the required libraries and start compiling the project, assuming that your JDK was set up correctly.
If no error were encountered during compilation, you can finally run the project (the lines starting with `#` are comments and shouldn't be pasted in your terminal):

```sh
# On windows:
gradlew.bat run

# On unix:
./gradlew run

# With "gradle" installed:
gradle run
```

### Working with Eclipse

*Before jumping to this section, please first follow the steps in [Compiling the source code](#compiling-the-source-code) to make sure that you can compile and run the project using gradle!*

If you want to use Eclipse for compiling and editing the project, then you will need to run a different command than the above to generate the Eclipse project from the Gradle project:

```sh
# On windows:
gradlew.bat eclipse

# On unix:
./gradlew eclipse

# With "gradle" installed:
gradle eclipse
```

A `.project` file will be generated in the `app/` directory; open it in Eclipse and you'll be good to go!

If changes were made on the Gradle configuration files, then run the above command *after* running `./gradlew cleanEclipse` (or `gradlew.bat cleanEclipse` on Windows).

**If you get the following error:** `Error: JavaFX runtime components are missing, and are required to run this application`, then you may try the following instead:

### Working with Eclipse - alternate version

Delete the project from the workspace and run `gradlew cleanEclipse`, then in Eclipse, do `Import projects` > `Gradle` > `Existing Gradle Project`.
You shouldn't need to change any of the settings and you can press `Finish`.

Two packages will appear: one labeled `ap4b-project`, which only contains the configuration files for `gradle` and `app`, which contains the source code of the project.
The source files are nested in the `src/main/java` > `ap4b.project` directories, make sure that they are here!

Finally, open the drop-down next to the "Run" button, and choose `ap4b-project - run`.
Later, it should appear first in the drop-down and pressing the "Run" button will run it directly.
This will call `gradlew run` and, assuming that you were able to complete the [Compiling the source code](#compiling-the-source-code) section, you should see a window appear with the game running.

## Editing the UML files

Github unfortunately does not have a "download file" button, so you will need to either [download the entire repository](#grabbing-the-source-code) or use one of the handy links here:

<!-- Come on, github, how hard is it for you team to implement this? -->

- [`uml de sequence.drawio`](https://downgit.github.io/#/home?url=https://github.com/adri326/ap4b-project/blob/main/uml%20de%20sequence.drawio)
- [`uml-classes.drawio`](https://downgit.github.io/#/home?url=https://github.com/adri326/ap4b-project/blob/main/uml-classes.drawio)
- [`uml-communication.drawio`](https://downgit.github.io/#/home?url=https://github.com/adri326/ap4b-project/blob/main/uml-communication.drawio)
- [`uml-usecases.drawio`](https://downgit.github.io/#/home?url=https://github.com/adri326/ap4b-project/blob/main/uml-usecases.drawio)
- [`Description textuelle (1).odt`](https://downgit.github.io/#/home?url=https://github.com/adri326/ap4b-project/blob/main/Description%20textuelle%20(1).odt)

For `.drawio` files, you can download [draw.io desktop](https://github.com/jgraph/drawio-desktop/releases) and open it with it, or simply go to [draw.io](https://app.diagrams.net/), choose to save diagrams to the `Device` and then open the file that you downloaded.
