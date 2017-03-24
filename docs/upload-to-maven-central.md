# Upload to maven central
## Snapshot
1. You will need to specify the following properties in your gradle.properties located in your gradle home directory:

```
signing.keyId=BDCAAE60
signing.password=#private key goes here#
signing.secretKeyRingFile=#secret key file goes here#
ossrhUsername=scenarioo
ossrhPassword=#sonatype password goes here#
```

2. Change the version appropriately in the build.gradle
3. `gradlew clean uploadArchives`

## Releases
Same steps as for snapshot releases and
4. Promote build to maven central:
http://central.sonatype.org/pages/releasing-the-deployment.html