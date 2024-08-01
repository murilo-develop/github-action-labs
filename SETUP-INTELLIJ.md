# Setting up IntelliJ

In CTW we use the [JetBrains IntelliJ IDE](https://www.jetbrains.com/idea/download/?section=windows)<sup>[1](#note-1)</sup> to code.

Therefore, let's set some features and behaviours in IntelliJ IDE.

> <span style="color: #f03c15">**_WARNING_**</span>
>
> After each change in IntelliJ Project Structure and Settings do not forget to click up on the "Apply" button and then "OK".

#### 1) Set the default JDK and the Java language level for the application.
- Click in the hamburger menu in the top left corner and go to "Project Structure".
  <br/>![IntelliJ's hamburger menu](src/test/resources/assets/img/readme/intellij-hamburger-menu.png)
  <br/>![IntelliJ's Project Structure](src/test/resources/assets/img/readme/intellij-project-structure.png)
- Set the JDK as 21 (if you do not have this version yet, please download it) and set the language level to the same JDK version
  <br/>![IntelliJ's Set JDK and Java language](src/test/resources/assets/img/readme/intellij-set-jdk-java-language.png)

#### 2) Set IntelliJ to use the format specified in the ".editorconfig", line separator and project's encoding
- Click in the hamburger menu in the top left corner and go to "Settings...".
  <br/>![IntelliJ's hamburger menu](src/test/resources/assets/img/readme/intellij-hamburger-menu.png)
- Go to "Settings...".
  <br/>![IntelliJ's Project Settings](src/test/resources/assets/img/readme/intellij-settings.png)
- Go to "Editor | Code Style" and set the "Scheme" as "Project" now in "Line Separator" selector set as "Unix and macOS (\n)", mark the two checkbox bellow the "Line Separator" selector, it they are unchecked.
  <br/>![IntelliJ's Set editor config and line separator](src/test/resources/assets/img/readme/intellij-editor-config-line-separator.png)
- Go to "Editor | File Encodings" and set the "Global Encoding" and the "Project Encoding" as "UTF-8" also set the "Default encoding for properties files" as "UTF-8".
  <br/>![IntelliJ's Set project encoding](src/test/resources/assets/img/readme/intellij-project-encoding.png)

> <span style="color: #008000">**_TIP_**</span>
>
> To help you to save space in your environment set your maven installation path in IntelliJ IDE
> - Click in the hamburger menu in the top left corner and go to "Settings...".
    <br/>![IntelliJ's hamburger menu](src/test/resources/assets/img/readme/intellij-hamburger-menu.png)
> - Go to "Settings...".
    <br/>![IntelliJ's Project Settings](src/test/resources/assets/img/readme/intellij-settings.png)
> - Go to "Build, Execution, Deployment | Build Tools | Maven" and set then Maven home path and uncheck "Use settings from .mvnw/maven.config" if it is checked.
    <br/>![IntelliJ's hamburger menu](src/test/resources/assets/img/readme/intellij-build-settings.png)


> <span style="color: #0000ff">**_NOTE_**</span>
>
> After the changes mentioned above reimport maven project and rebuild the project.
> - Right click in the project root folder and first reimport maven project and next rebuild the module.
    <br/>![IntelliJ's Right click in project root folder and reimport maven project](src/test/resources/assets/img/readme/intellij-maven-reimport.png)
    <br/>![IntelliJ's Right click in project root folder and rebuild module](src/test/resources/assets/img/readme/intellij-rebuild-module.png)

### Running the application's tests

> <span style="color: #800080">**_IMPORTANT_**</span>
>
> Docker must be installed, it should be up and running in the environment before try to run the application.

There is no trick to run the tests in IntelliJ IDE the test can perform a single test or all tests together.

#### 1) All tests together
- Go to src/test/java and click up on it using the right button and choose run tests in Java.
  <br/>![IntelliJ's Right click run all tests in java](src/test/resources/assets/img/readme/intellij-run-all-tests.png)

#### 2) Running single test
- Open a java test class, for instance, [BookingResourceIT.java](src/test/java/com/bmw/ctw/workstation/rack/api/booking/boundary/BookingResourceIT.java), then click up on the play button in the left corner right before the class or method declaration.
  <br/>![IntelliJ's Running single test class](src/test/resources/assets/img/readme/intellij-single-test-class.png)
  <br/>or
  <br/>![IntelliJ's Running single test method](src/test/resources/assets/img/readme/intellij-single-test-method.png)

## Notes
<a id="note-1">[1]</a>
If you do not have a license for it ask your Titan one
