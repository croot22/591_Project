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
![alt text](/ReadMeImages/Junit.PNG "JUnit")

From the dropdown, select JUnit5 and click finish. JUnit will be added to your classpath.
![alt text](/ReadMeImages/Junit2.PNG "JUnit")


3. There are also some dependencies on external jars for this project. These jar files are included in the github submission, but can also be found at the referenced links below:
json-20190722.jar - https://github.com/stleary/JSON-java

In the Project Properties menu, select Classpath and Click "Add External Jars..."
![alt text](/ReadMeImages/AddJar.PNG "Jar")
This opens a file explorer window were you can navigate to the folder where the above .jar files are stored. Shift select the indicated .jar files and click "Open".
![alt text](/ReadMeImages/AddExternalJar.PNG "Jar")

These files will now be on the classpath. CLick "Apply and Close" to exit the properties menu and have these changes take effect.
![alt text](/ReadMeImages/Properties.PNG "Properties")

4. Install the JavaFX library to be able to run the GUI
 1.	In Eclipse, go to Help > Eclipse Marketplace
 2.	In the market place search for “fx”
![alt text](/ReadMeImages/Marketplace.png "Marketplace")
 3.	Install the option called e(fx)clipse 3.6.0
 4.	Accept the License and Agreement and click “Finish”
![alt text](/ReadMeImages/TNC.PNG "Marketplace")
 5.	This will install and ask you to restart eclipse
 6.	Download the JavaFX SDK for your operating system from Gluon
    a.	https://gluonhq.com/products/javafx/
 7.	Extract the files to a directory on your local system
 8.	Once eclipse restarts, Got to Window > Preferences 
 9.	This will open a new window where you should navigate to Java > Build Path > User Libraries
 10.	Once In User Libraries, click “New…” and name the library JavaFX
 ![alt text](/ReadMeImages/UserLib.png "User Library")
 11.	After making the JavaFX library, click “Add External Jars…"
 ![alt text](/ReadMeImages/UserLibExternal.png "User Library")
 12.	This will open the file explorer (Windows) where you should navigate to the folder where all the .jars from Gluon were stored
 13.	Select all of the files and click add
 ![alt text](/ReadMeImages/FileExplorer.png "User Library")
 14.	The jar files should be listed in the user library, click apply and close
 ![alt text](/ReadMeImages/UserLibJars.png "User Library")
 15.	Right click on the Java Project where JavaFx should be added and select properties
 16.	Click on Module Path
 ![alt text](/ReadMeImages/Properties.PNG "Properties")
 17.	Add Library… > JavaFx Library
  ![alt text](/ReadMeImages/JavaFXLib.png "Properties")
  18.	Click “Finish”
 19.	Click on Classpath
 20.	Click on Add Library… > User Library
 21.	Select JavaFX and Click Finish
  ![alt text](/ReadMeImages/UserLib.png "Properties")
  22.	Click apply and close
  23.	Finally, you need to add the path to the jar files to your run configuration. Right click the “main” class that runs the project    (Main_Weather.java) and select Run As > Run Configuration. Ensure that the correct Class is selected and go to the arguments tab
    ![alt text](/ReadMeImages/RunConfig.png "Properties") 
    24.	In the VM Arguments section, enter the below text with the /path/to/files/lib replaced with the location of the downloaded gluon          files on your local desktop.
    a.	--module-path "\path\to\javafx-sdk-14\lib" --add-modules javafx.controls,javafx.fxml
    b.	Ex: --module-path "C:\Users\Bryan Rogers\Dropbox\MCIT\CIT 591\Final Project\openjfx-14_windows-x64_bin-sdk\javafx-sdk-14\lib" --        add-modules javafx.controls,javafx.fxml
25.	Click run


Running the Program:
1. When the Main_Weather.java program runs, it will open a JavaFX controller. Additional instructions describing each button can be found in the hover over text in the controller.
![alt text](/ReadMeImages/WeatherMaticHome.PNG "Properties") 
2. This page gives the option to select a file with saved locations in it, or select a new set of locations.
3. If a file is selected and the button Select Locations File is pressed, the list of locations will appear in the right hand screen. From there, any combination of the locations can be selected and the "Select" button will get the forecast for the selected locations."Select All" will get the forecast for all locations in the right hand menu.
![alt text](/ReadMeImages/WeatherMaticSelectedLocations.PNG "Properties")
4. If the "Create New Locations List" button is pressed, a new window for searching for locations to get the forecast from will open.
![alt text](/ReadMeImages/NewLocationSetup.PNG "Properties")
5. In this window, a location can be searched for and matches will appear in the left menu and can be added to the list on the right
![alt text](/ReadMeImages/LocationSearch.PNG "Properties")
6. Once a series of locations are added to the right hand list, these locations can be added to either a new file of locations by typing in a new name and clicking enter, or to an existing file shown in the dropdown menu. The selected locations will be appended to the locations in the file unless the "Override" tick box is selected, in which case the file will be overwriten with the new locations.
7. Once done adding a new list, the refresh button on the WeatherMatic Home Page will allow the newly created or edited files to be selected from the menu.
8. Now you can select an activity; click on the bottom right button 
![alt text](/ReadMeImages/OutdoorActivityButton.PNG)
9.Select one of the activities, and click the "Set Activity Selection" Button. 
![alt text](/ReadMeImages/SelectActivity.PNG)
