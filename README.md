# HealthcareAlertSystem
Term project of CSC 540 NC State University. An automated healthcare alert system.

# Description

Considering the our busy daily lives today and with the increasing no. of disease diagnosis in the society, it has really important for us to think about an automated healthcare management system which can take care of the users, no matter they have some diagnosed diseases or not. The central idea of this system hovers around the presence of designated caretakers called Health Supporters(HS)  who helps the users maintain their health. The Health Supporters are also users of the system who can in turn take advantage of the system and add their supporters and so on. There are many features that our system supports and also there are few reasonable assumptions that we have made for our system, as the initial description doesn’t have clear descriptions of every minute detail. The whole system is developed using a Oracle Database and an Application written in java and in this report we present the details of the Database and the application.

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
Finally build and run the project from your IDE.        
Main file to start is Main.java
