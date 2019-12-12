# Core Gaming Game Logic Developer - Tech Test 

### Implemented Tasks ###
### Setup and Details
 * Grab repository contents either via zip or git pull.
 * If working with compressed file, unpack file contents to folder of your choice.
 * In command line move to project's root folder.
 * To build all modules execute:
```
mvn clean install -DskipTests
```
 * To run a http web server execute: 
```
java -jar Server\target\Server-1.0.1-jar-with-dependencies.jar
```
 * To run tests, in another command line window move to 'project root'/Client folder and execute: 
```
mvn test
```
 * Watch the output for any test fail/ pass information.
 * All data tables can be found in 'profile.xlsx' file under titled tabs. You do not have to parse the xlsx.

### Required Tasks 

* Find and familiarize yourself with Server, Client and unit tests source files. Hint: The 'test' is structured as a simple client - server application. The server is responsible for performing logic and generating responses to client requests. The client sends requests and contains a test suite which is used to validate given behaviours.
* Have default project up and running. Both defined 'helloRequest*' tests must succeed. Hint: That should be done without any changes to the code by following 'Setup and Details' steps.
* Add a 'table' client request and implement server response which will return randomly chosen 'Value' from 'BasicWeightTable' based on the percentage 'Chance'. Hint: For this step ignore data in '99% confidence level' column.
* Implement a test or tests which would run multiple 'table' requests and check aggregated responses against the expected occurences defined in 'BasicWeightTable'. Hint: How many times do you think 'Value' 3 will be retuned from the server? For this step you can use '100k runs error margin' value to compare the results with 99% confidence. 
* Improve communication protocol to use either Json or XML. Update your tests accordingly.

Steps or changes made to the source code.

1. Done the set up of Client and Server successfully without making any code changes using Maven.
2. Used Apache poi API to fetch data from excel.
3. Used Random API to pass random values to the key to fetch the corresponding chance value percentage.
4. No code has been changed in Client.java
5. Added the poi dependencies to the pom.xml
6. The output of the number of occurrences of each element while Random search happens has been redirected to the output.txt.
7. I have converted profile.xlsx file to profile.xls as my MsOffice was not supporting.

############################# To be implemented #############################


#### Desirable Tasks 

* Add a 'spin' request which will return a 3x3 matrix of symbols. Use 'Symbols' and 'Reels' tables. For every reel, draw one random position and populate the whole matrix column with results starting at that drawn position.
* For each result where middle row of your matrix satisfies one of the rules defined in 'WinRules' return the associated value.
* Implement a test or tests which would run multiple 'table' requests and check aggregated responses against the 'expected chance' defined in 'WinRules' table. Hint: Again you can use '100k runs error margin' value to compare the results with 99% confidence. 

#### Optional Tasks 

* Add 'Return To Player' value in percentage for both 'table' and 'spin'. Assuming each request cost '3.5'. We will not provide you with table to compare your results this time. Hint: To find the requested result compare total cost spent on initiating requests and total value returned in responses. You can use 1% margin error for RTP comparison. 
* For extra fun, can you change the server - client to work with WebSockets?

