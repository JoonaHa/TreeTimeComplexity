## Running the program ##

----
 Make sure you have atleast Java 8 installed on your computer.
### From the jar-file: ###


Enter command:
```bash
 java -jar TreeTimeComplexity.jar
```
Or enter
```bash
 chmod +x TreeTimeComplexity.jar
```
and double click it from your file browser.

### From source: ###
Make sure you are in TreeTimeComplexity folder (not same as root folder).

On linux enter commands:
```bash
 chmod +x gradlew
 ./gradlew run
```
On windows enter command:
```bat
  gradlew.bat run
```

### Using the GUI: ###

* Pick which operations you want to benchmark by ticking the **checkboxes**.
* Enter the **size of generated input data**. Preferably below 10 000 if your iterations are closed 100. Depends how patietnly can you waut
* Enter the number of **iterations** you want the benchmark to run. Makes averages more accurate.

![](https://github.com/JoonaHa/TreeTimeComplexity/blob/master/documentation/mainmenu.png)

* Pick from the dropdown menu do you want the input data to be in **random order or sorted by ascending or descending order**.
* When you are ready press **Run Benchmark**

![](https://github.com/JoonaHa/TreeTimeComplexity/blob/master/documentation/barchart.png)

After a while you should have a barchart of averages and a list of mix-/max-values

