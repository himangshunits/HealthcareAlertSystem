# HealthcareAlertSystem
Term project of CSC 540 NC State University. An automated healthcare alert system.

# Steps to build the project

Clone the repository. 
Need to add jdbc driver. The steps would be:

1. First check if maven in present by using mvn --version.
2. Then you need to download jdbc driver and install in local maven using:
   mvn install:install-file -DgroupId=groupId -DartifactId=artifactId -Dversion=1.0 -Dpackaging=jar -Dfile=/Link/to/jdbc/
3. Edit pom.xml and add:
```xml
        <dependency>
            <groupId>groupId</groupId>
            <artifactId>artifactId</artifactId>
            <version>1.0</version>
        </dependency>
 ```       
Finally build and run the project from your ide.        
        
