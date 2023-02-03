# Scenarioo Writer Library for Java Release Notes

## Version 3.0.0

### Upgrade to Java 8

* [#725 - Migrate to JUnit 5](https://github.com/scenarioo/scenarioo-java/issues/20)

With version 3, the Java Writer Library needs at least Java 8 to run.

### Readiness for Java 11

* [#22 - Runtime errors when using the writer with Java 11](https://github.com/scenarioo/scenarioo-java/issues/22)

Java 11 does not include Java EE libraries anymore. Since scenarioo-java relies on JAXB for writing the XML files, 
we need to provide the dependencies out of the box. Users of Java 11+ should switch to this version, if they don't want 
to supply the missing dependencies themselves.


### Improvements to the details interface

* [#1 - Improve details interface & API documentation](https://github.com/scenarioo/scenarioo-java/issues/1)

The possibility to add details via the API has been improved by a few more method overloads. For more details see the [Details class](https://github.com/scenarioo/scenarioo-java/blob/develop/src/main/java/org/scenarioo/model/docu/entities/generic/Details.java)
