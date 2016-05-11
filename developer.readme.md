# Developer Documentation

This file contains most important informations on how to develop `scenarioo-java`.

## Build the Java Writer API

The following command builds the API and also deploys it into the local Maven repository.

```
./gradlew clean build test install
```

The local deployment in your local maven repository is also important in order to have the Scenarioo Viewer use 
the latest snapshot version of the Writer. 


## Maven Repository

Releases of the Scenarioo Java Writer are hosted in our own Maven repository on github named `mvn-repo`.

## Branching strategy

The `scenarioo-java` repository has two branches: `develop` for developing the next release and `master` for all published releases.

The `mvn-repo` has just one `master` branch.

## Release a new API version

Precondition: Before considering a release, make sure that the new version is well tested. If you are not 100% sure about the quality of the code, make a release candidate first and test it well in a realistic environment.

### Release to master branch

1. If not done yet: Clone the `mvn-repo` repository and the `scenarioo-java` into two folders that are located in the same parent directory.
2. Make sure you have the latest version of the `mvn-repo` by running `git pull` in this repo.
3. Finalize the code in the `develop` branch of `scenarioo-java` by setting the correct version number in the `build.gradle` file. This version number must not exist yet in the `mvn-repo`. Remove `SNAPSHOT` from the version number and add `-RCx` for release candidates where `x` is the RC number.
4. Make sure the build runs on the `develop` branch by running `./gradlew clean build`. Then commit to `develop`.
5. Now checkout the `master` branch and merge `develop` into it, commit.
6. Still on the `master` branch, add a tag with the release version number and push the tag.
7. Checkout `develop` again, change the version in `build.gradle` to the next `x.y.z-SNAPSHOT` version and commit. `x.y` corresponds to the last release version and `z` is incremented by one compared to the last release.

### Release to maven repository

1. Checkout the `master` branch tag that contains the release you just made.
2. run `./gradlew clean build publish`
3. `cd ../mvn-repo`
4. Commit and push new files and directories to repository with the the following commit message format    
    new scenarioo-java version x.y.z
5. Change the version used in the webapplication (repository 'scenarioo' on appropriate branch) to use the newer version and commit.

### Write release notes

Write release notes for the new release on GitHub ([here](https://github.com/scenarioo/scenarioo-java/releases))
