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
<repositories>
    <repository>
        <id>jvalidation-github</id>
        <url>https://github.com/jvalidation/mvn-repo/raw/master/releases</url>
    </repository>
</repositories>
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
