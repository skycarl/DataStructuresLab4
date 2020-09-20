# DataStructuresLab4
This program accepts input files containing integers and sorts them using quicksort and heapsort algorithms. 
Statistics are then printed to an output file. 

#### **Input**  
The user must specify 3 runtime parameters: 
1. The name of the file containing filenames of all input files (this file must be in the project root directory)
2. [Optional] Summary data file name (which contains only summary information on the sorts)

In any given run, the program will overwrite any output files of the same name as output files of the previous run. 

If the two optional parameters are not specified, the program will assign a name. 

Input files must be held in a folder in the root project directory titled "input". The input files must have one value on each line.  
 
Note: input files are assumed to be UTF-8 encoded. Unexpected behavior may occur if others are used.


#### **Execution**
The program may be compiled and run from the command line or terminal as such:

```
javac Lab4.java  
java Lab4 inputFiles.txt sortedData.txt summaryData.txt
```

#### **System information**
**IDE:** IntelliJ IDEA 2019.1 (Community Edition)  
**JRE:** 1.8.0_152-release-1343-b26 x86_64  
**JVM:** OpenJDK 64-Bit Server VM by JetBrains s.r.o  
**OS:** macOS Mojave 10.14.4
