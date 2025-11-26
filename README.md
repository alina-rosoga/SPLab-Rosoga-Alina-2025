# SPLab-Rosoga-Alina-2025

This repository contains lab exercises for Design Patterns (Composite, Strategy) and an exercise demonstrating Spring Dependency Injection (Lab 3).

Build & run (without Maven/Gradle installed):

1. Install JDK (8 or newer) and ensure `javac` and `java` are in PATH.
2. Compile the example (non-Spring demo):

```powershell
cd 'C:\Users\Alina\Desktop\sp'
mkdir out
javac -d out src/main/java/sp/*.java
java -cp out sp.Main
```

Build & run Spring Boot app (requires Gradle or IDE):

```powershell
# Using Gradle wrapper or installed Gradle
gradle bootRun
# or import in IntelliJ and run MySpringApplication
```

Notes:
- The repository was cleaned to remove large local JDK files from history.
- If you don't have Gradle locally, import the project into IntelliJ as a Gradle project.
# SP - Design Patterns Lab 1

Implementare Java a modelului Book folosind Composite pattern. Pentru a construi:

```
mvn package
java -jar target/sp-1.0-SNAPSHOT.jar
```
