# Developer Documentation

This file contains most important informations on how to develop `scenarioo-java`.

## Build the Java Writer API

The following command builds the API and also deploys it into the local Maven repository.

```
./gradlew clean build test install
```

The local deployment in your local maven repository is also important in order to have the Scenarioo Viewer use 
the latest snapshot version of the Writer. 
