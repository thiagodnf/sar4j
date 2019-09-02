# SAR4J
Statistical Analysis by using R for Java Applications

## Features

- Noparametric Tests
    - Kruskal-Wallis
- Post-Hoc Tests
    - Nemenyi
- Effect Size
    - Vargha and Delaney A measure 
- Works on Java 1.8

## How to install

This project uses GitHub as a Maven Repository. Then you have just add the following section to your repositories tag in pom.xml

```xml
<repository>
    <id>mvn-repo</id>
    <url>https://github.com/thiagodnf/mvn-repo/raw/master/releases</url>
    <releases>
        <enabled>true</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

Then add a dependency into tag of your pom.xml

```xml
<dependency>
	<groupId>thiagodnf.sar4j</groupId>
	<artifactId>sar4j</artifactId>
	<version>1.0.0</version>
</dependency>
```

## How to use

```java
List<Observation> observations = new ArrayList<>();
        
observations.add(new Observation("A", Arrays.asList(0.285, 0.338, 0.088, 0.205, 0.363)));
observations.add(new Observation("B", Arrays.asList(0.521, 0.763, 0.325, 0.425, 0.378)));
observations.add(new Observation("C", Arrays.asList(0.989, 1.192, 0.788, 0.549, 0.544)));
observations.add(new Observation("D", Arrays.asList(1.267, 1.625, 1.266, 1.154, 1.268)));

KruskalWallisTest kruskal = new KruskalWallisTest();

TestResult testResult = kruskal.test(observations);

System.out.println(testResult);
```
The output will be:

```json
{
	"chiSquared": 10.58,
	"df": 2.0,
	"pValue": 0.00504176,
	"postHocResult": {
		"rows": ["B", "C"],
		"columns": ["A", "B"],
		"values": [0.234479781329244, 0.00327823491935975, NaN, 0.234479781329244],
		"dimensions": [2, 2]
	}
}
```

## Contributions

Feel free to fork this project, work on it and then make a pull request.

## Questions or Suggestions

Feel free to create <a href="https://github.com/thiagodnf/sar4j/issues">issues</a> here as you need
