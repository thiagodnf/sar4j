# SAR4J
Statistical Analysis by using R for Java Applications

## Features

- Noparametric Tests
    - Kruskal-Wallis
- Post-Hoc Tests
    - Nemenyi
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

## Contributions

Feel free to fork this project, work on it and then make a pull request.

## Questions or Suggestions

Feel free to create <a href="https://github.com/thiagodnf/sar4j/issues">issues</a> here as you need
