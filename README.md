# JUnit Test Invariant Detection based on the Daikon method SE4367.501 
## By James Dixon (Trend1x), Quan Pham (Quanicus), and Erik Fraim (shenrav)

The project aims to detect the possible invariants to that could be later used as testing oracles. The output coverage will show the tests run, the method being executed, the value of the variables at the start of execution for each test case, the set tha covers the variables, and the candidate invariant pattern. This is all documented and output into a file named local_vars.txt.

## Getting Started
 Before compiling the Jar you will need to add the package path of the project our program will be run against to the 
 TestingClassFileTransformer
 
 ```
 if(s.startsWith([package name goes here]) {
 ```

### Installing
To install our program run the following command in the Testing_Project folder

```
mvn clean install
```

### Running 
You will need to add the following items to the pom.xml file of the project you are running our software against. 

**Under the dependencies**
```
<dependency>
  <groupId>Testing_Project</groupId>
  <artifactId>Testing_Project</artifactId>
   <version>1.0-SNAPSHOT</version>
</dependency>
```
**Under the plugins** 
```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <configuration>
  <parallel>methods</parallel>
  <useUnlimitedThreads>true</useUnlimitedThreads>
    <argLine>-javaagent:[path to the jar]Testing_Project-1.0-SNAPSHOT.jar</argLine>
    <properties>
      <property>
        <name>listener</name>
        <value>com.se4367.agents.JUnitListener</value>
      </property>
    </properties>
  </configuration>
</plugin>
```

Once the dependency and plugin have been added and saved, you will navigate to the project with the JUnit tests and run the following:

```
mvn test
```

The stmnt-cov.txt and local_vars.txt will be saved in the project's primary directory. 
