# Scenarioo Writer Library for Java Release Notes

## Version 3.0.0

### Upgrade to Java 11

* [#22 - Runtime errors when using the writer with Java 11](https://github.com/scenarioo/scenarioo-java/issues/22)
* [#20 - Migrate to JUnit 5](https://github.com/scenarioo/scenarioo-java/issues/20)

Users of Java version 11 and above need to use this version of the writer library.

The writer library relies on JAXB to generate the XML files.
With the release of Java 11, JAXB along with other Java EE modules has been removed from the standard library.
Therefore a new version of scenarioo-java had to be built which supplies those dependencies out of the box.

