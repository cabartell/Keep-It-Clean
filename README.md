# Keep it Clean

## Getting Started

## Prerequisites

* JDK 8 or higher.
* Install [maven](https://maven.apache.org/download.cgi) and add it to your PATH.
* To develop in IntelliJ: Install IntelliJ lombok plugin.

## Compile project

To compile the project.
1. Open a terminal window.
2. Traverse to the project root.
3. Execute: `mvn compile`

## JavaFX

### From command line

To run the application:
1. Open a terminal window.
1. Traverse to the project root.
1. Execute: `mvn clean javafx:run -f ./JavaFX/pom.xml`

### From IntelliJ

1. Open the Maven window.
1. Under module "JavaFX", expand the Plugins section.
1. Expand "javafx".
1. Double-click on "javafx:run".

## CLI

### Build and run from command line

To build the jar file and run it.
1. Open a terminal window.
1. Traverse to the project root.
1. Build the jar file: `mvn package`
1. To run the application, execute: `java -jar target/keepitclean-1.0-SNAPSHOT.jar`

### Build and run in IntelliJ

1. Launch IntelliJ
1. Select: File -> Open...
1. Open: _pom.xml_ from the project root.
1. Go to: _Start.java_
1. Press the "play" button on the `main` method.
1. It will compile the application and run it. From then onwards, you will have a run configuration set-up.
  Subsequent invocations can be done by using `Shift+F10` (or pressing that play button in the top command bars).
