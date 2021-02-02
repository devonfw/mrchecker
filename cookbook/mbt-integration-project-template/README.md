
MrChecker template
-------------------
This branch features a template for integration Model Based Testing with MrChecker project.

**Model Based Testing (MBT)** - testing technique where software under test is checked against predictions made by a model (graph). 

A model is a description of:
*	system's behavior
*	process flow
*	data/information flow
*	modules / application menu traversal

Usages:
*	smoke test - one pass through the graph - quick verification of the whole process / application
*	stability test - multiple passes through the graph - long run - verification of the environment stability

## Setup manual
1.	Prerequisites
*	Existing MrChecker project
*	GraphWalker studio (download from https://graphwalker.github.io/) 

2.	Config
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
