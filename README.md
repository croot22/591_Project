Welcome to WeatherMatic 3000! To begin, please ensure you setup your Eclipse environment correctly for optimal results:

Dependencies:
1. Please setup your Java build path to use JRE JavaSE-11 or above. This can be done on a new Project by choosing Execution environment JRE JavaSE-11
![alt text](/ReadMeImages/NewProject.PNG "New Project")

If the project already exists, the JRE execution environment can be configured by Right Clicking on your project in the Eclipse Project Explorer and Selecting "Properties." Once in properties, select "Java Bulid Path" in the left hand menu and go to the Libraries tab as shown below.
![alt text](/ReadMeImages/Properties.PNG "Properties")

Click on Modulepath and click "Add Library..."
![alt text](/ReadMeImages/AddLibrary.PNG "AddLib")

This will open a window to Add a library, select JRE System Library and Click Next
![alt text](/ReadMeImages/AddLibrary2.PNG "AddLib")
Choose "Execution Environment" and select "JavaSE-11 (jdk-XZY)" in the dropdown and Click finish. This will add the correct JavaSE to your buildpath.
![alt text](/ReadMeImages/AddLibrary3.PNG "AddLib")

If JavaSE-11 is not available in your libraries, you will have to download it from Oracle here: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html following the instructions for your Operating system.

2. While in the Project Properties, please add JUnit 5 to the classpath by selecting classpath and clicking "Add Library..." This will open a Window where you should select JUnit and click next.
![alt text](/ReadMeImages/JUnit.PNG "JUnit")

From the dropdown, select JUnit5 and click finish. JUnit will be added to your classpath.
![alt text](/ReadMeImages/JUnit2.PNG "JUnit")


3. There are also some dependencies on external jars for this project. These jar files are included in the github submission, but can also be found at the referenced links below:
json-20190722.jar - https://github.com/stleary/JSON-java
jfxswt.jar https://github.com/jdrich79/WeatherApp_wFX/tree/master/JavaFX_Jar

In the Project Properties menu, select Classpath and Click "Add External Jars..."
![alt text](/ReadMeImages/AddJar.PNG "Jar")
This opens a file explorer window were you can navigate to the folder where the above .jar files are stored. Shift select the indicated .jar files and click "Open".
![alt text](/ReadMeImages/AddExternalJar.PNG "Jar")

These files will now be on the classpath. CLick "Apply and Close" to exit the properties menu and have these changes take effect.
![alt text](/ReadMeImages/Properties.PNG "Properties")

