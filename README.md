# JUnit Test Coverage Collection SE4367.501 
## By James Dixon, Quan Pham, and Erik Fraim

The project aims to collect the statement coverage of JUnit tests. The output coverage will show the tests that have been run as well as the statements visited. If a test happens to have a failure, assertion or otherwise, this will also be documented in the stmnt-cov.txt file. 

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

The stmnt-cov.txt will be saved in the project's primary folder. 
