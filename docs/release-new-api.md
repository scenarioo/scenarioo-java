# Release Scenarioo Writer Library for Java

## Branches

The `scenarioo-java` repository has two branches: `develop` for developing the next release and `master` for all published releases.

## Prepare Release

Precondition: Before considering a release, make sure that the new version is well tested. If you are not 100% sure about the quality of the code, make a release candidate first and test it well in a realistic environment.

1. Finalize the code in the `develop` branch of `scenarioo-java` by setting the correct version number in the `build.gradle` file. This version number must not exist yet in mavem central. Remove `SNAPSHOT` from the version number and add `-RCx` for release candidates where `x` is the RC number for further release candidate testing.
2. You have to manually [Publish the signed snapshot to maven central](upload-to-maven-central.md)) this is currently not automated.
3. Make sure the changelog.md is updated for the new version

## Final Release to master branch
 
1. Precondition: see Prepare Release above.
2. Set the final version number in develop or release branch (without `-SNAPSHOT` or `-RC`) in `build.gradle` and merge it to the develop branch (or the release branch).
3. You have to manually [Publish the signed release to maven central](upload-to-maven-central.md)) this is currently not automated.
4. Promote the final release to maven central: http://central.sonatype.org/pages/releasing-the-deployment.html
5. Add a tag with the release version number to the commit where you set that version number and push the tag.
6. **Write Release Notes** for the new release on GitHub ([here](https://github.com/scenarioo/scenarioo-java/releases))
7. Merge the release branch to master.
8. **IMPORTANT - Increase Version to next snapshot version on Develop Branch:** Checkout `develop` again, change the version in `build.gradle` to the next `x.y.z-SNAPSHOT` version and commit. `x.y` corresponds to the last release version and `z` is incremented by one compared to the last release.
9. **Switch Scenarioo Viewer Webapp to newest version:** Go to repo `scenarioo` and change the version of the writer library used to the newest version and commit and push (--> PR to next release branch or to develop).
