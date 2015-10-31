# scenarioo-java

Scenarioo Writer API for Java to generate Scenarioo Documentations - http://www.scenarioo.org

For documentation please refer to the [wiki](https://github.com/scenarioo/scenarioo-java/wiki).

## Build the Java Writer API

The following command builds the API and also deploys it into the local Maven repository. The deployment is important in order to have the Scenarioo Viewer use the latest snapshot version of the Writer. Furthermore this command also generates an XSD file that documents the structure of the API classes.

```
./gradlew clean build test install
```
