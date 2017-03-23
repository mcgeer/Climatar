# Setting up your Development Environment

Clone the repository, and follow the instructions below for your specific IDE.

## Intellij

1. New project.

2. Select `/src/build.gradle` as the file to open.

3. Intellij will automatically open the file as a Gradle project.

## Eclipse

1. File -> Import.. -> Gradle Project
   (if you don't have Gradle project, install a gradle integration plugin by searching for "Gradle" in the marketplace, and installing one compatible with your eclipse version)

2. For the project root directory, choose `/src` and click the equivalent of "Finish".

# Compiling and Running

Execute `./gradlew desktop:run` in your terminal and it will compile and run the desktop client.

If you want to use your IDE to run the project, see instructions below.

## Eclipse
Right-click on the `Climatar-desktop` project and select `Run As -> Java Application`.

**Troubleshooting**

If you get an error about assets not being found, set the working directory to the `Climatar-android/assets/` folder.

1. Click on `Run -> Run Configurations`,
2. Select the configuration which you use to run the application in the left pane (it's most likely called "DesktopLauncher").
3. Under the arguments tab, click `Other -> Workspace` and select `Climatar-android/assets`.
4. Apply the changes, and try running it again.

## Intellij
Run the `DesktopLauncher` class as a java application.

**Troubleshooting**

If you run into an error about assets not being found, set the working directory to the `/src/android/assets` folder. It should be somewhere in the `Run -> Edit Configurations` menu. See this [link](https://github.com/libgdx/libgdx/wiki/Using-libgdx-with-intellij-idea#alternative-2-working-directory).
