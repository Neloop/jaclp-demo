# JACLP Demo Application

[![Build Status](https://github.com/Neloop/jaclp-demo/workflows/CI/badge.svg)](https://github.com/Neloop/jaclp-demo/actions)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/Neloop/jaclp-demo/blob/master/LICENSE)

Demo Spring Boot project for JACLP library. The demo uses `Spring Security` and 
`Spring Data JPA` and by default runs on `H2` in-memory database.

Most important classes in this example are located in 
[`cz.polankam.jaclp.demo.security.*`](https://github.com/Neloop/jaclp-demo/tree/master/src/main/java/cz/polankam/jaclp/demo/security) 
package. Another interesting class is method security configuration located in 
[`cz.polankam.jaclp.demo.config.MethodSecurityConfig`](https://github.com/Neloop/jaclp-demo/blob/master/src/main/java/cz/polankam/jaclp/demo/config/MethodSecurityConfig.java).

## Run Application

The application can be compiled and run with `maven`, therefore installed 
`maven` on the machine is a must. And of course `JDK` is needed, the application 
can be run on `Java 8+`. After `maven` installation, run the application with:

```
mvn spring-boot:run
```

## Test Application

Project contains something like integration tests which should demonstrate that
the application with ACL/ABAC permissions works and works properly. Tests can be
found in [test](https://github.com/Neloop/jaclp-demo/tree/master/src/test/java/cz/polankam/jaclp/demo/test_integration)
maven directory.

These tests can be run with maven with following command:

```
mvn test
```
