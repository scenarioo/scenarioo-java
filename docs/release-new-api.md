# Repository

# Branching strategy
The `scenarioo-java` repository has two branches: `develop` for developing the next release and `master` for all published releases.

# Release a new API version

Precondition: Before considering a release, make sure that the new version is well tested. If you are not 100% sure about the quality of the code, make a release candidate first and test it well in a realistic environment.

## Release to master branch

1. Finalize the code in the `develop` branch of `scenarioo-java` by setting the correct version number in the `build.gradle` file. This version number must not exist yet in the `mvn-repo`. Remove `SNAPSHOT` from the version number and add `-RCx` for release candidates where `x` is the RC number.
2. Make sure the build runs on the `develop` branch by running `./gradlew clean build`. Then commit to `develop`.
3. Now checkout the `master` branch and merge `develop` into it, commit.
4. Still on the `master` branch, add a tag with the release version number and push the tag.
5. Checkout `develop` again, change the version in `build.gradle` to the next `x.y.z-SNAPSHOT` version and commit. `x.y` corresponds to the last release version and `z` is incremented by one compared to the last release.

## Release to maven repository

1. Checkout the `master` branch tag that contains the release you just made.
2. Follow the steps [Publish release to maven central](upload-to-maven-central.md).
3. Change the version used in the webapplication (repository 'scenarioo' on appropriate branch) to use the newer version and commit.

## Write release notes

Write release notes for the new release on GitHub ([here](https://github.com/scenarioo/scenarioo-java/releases))