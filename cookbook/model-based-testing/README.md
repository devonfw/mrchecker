

MrChecker integrated with Model Base Testing
-------------------
This part of cookbook features a template for integration GraphWalker with MrChecker project.

GraphWalker is an Model-Based testing tool. It reads models in the shape of directed graphs and generates test paths from these graphs ([See more details](https://github.com/GraphWalker/graphwalker-project/wiki)).

Then the path could be provided to the MrChecker to execute the test 

**Model Based Testing (MBT)** - testing technique where software under test is checked against predictions made by a model (graph). 

Examples of the model: 
*	Data Flow
*	Control Flow
*	Dependency Graphs
*	Decision Tables
*	State transition machines

Types of MBT:
*	Offline / a priori: Generation of Test Suites before executing it. A test suite is nothing but a collection of test cases.
*	Online / on-the-fly: Generation of Test Suites during test execution


## Setup manual
### 1.	Prerequisites
*	Existing MrChecker project
*	GraphWalker studio (download from https://graphwalker.github.io/) 

### 2.	Config
Used: ``graphwalker.version: 4.2.0``, but please consider using the latest.
*	Add GraphWalker dependencies to pom file:
```
        <dependency>
            <groupId>org.graphwalker</groupId>
            <artifactId>graphwalker-core</artifactId>
            <version>${graphwalker.version}</version>
        </dependency>
        <dependency>
            <groupId>org.graphwalker</groupId>
            <artifactId>graphwalker-io</artifactId>
            <version>${graphwalker.version}</version>
        </dependency>
        <dependency>
            <groupId>org.graphwalker</groupId>
            <artifactId>graphwalker-java</artifactId>
            <version>${graphwalker.version}</version>
        </dependency>
        <dependency>
            <groupId>org.graphwalker</groupId>
            <artifactId>graphwalker-maven-plugin</artifactId>
            <version>${graphwalker.version}</version>
        </dependency>
        <dependency>
            <groupId>org.graphwalker</groupId>
            <artifactId>graphwalker-websocket</artifactId>
            <version>${graphwalker.version}</version>
        </dependency>
```

*	Add GraphWalker generate-sources to build plugins in pom file:
	
```
            <plugin>
                <groupId>org.graphwalker</groupId>
                <artifactId>graphwalker-maven-plugin</artifactId>
                <version>${graphwalker.version}</version>
                <!-- Bind goals to the default lifecycle -->
                <executions>
                    <execution>
                        <id>generate-sources</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>generate-sources</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```
            


##	Implementation
1. Create graph using GraphWalker Studio:
	* Run GraphWalker Studio ``java -jar graphwalker-studio-4.2.0-SNAPSHOT.jar``
	* Create graph
	* Save as json file
	* Add graph implementation file (``SampleGraph.json``) to ``src/main/resources/graphs``
2.	Generate graph java interface: run Maven command 
		```mvn graphwalker:generate-sources```
	Generated interface (``SampleGraph.java``) will be available in ``target/generated-sources/graphwalker/graphs``
3.	Create new java class that implements generated interface (see ``SampleGraphImplementation.java``) located in ``main/java/graphs``
4.	Create test class (see ``SampleMBT.java``)
5.	Run the test case (model base test is executing) ``mvn test -Dtest=SampleMBT``