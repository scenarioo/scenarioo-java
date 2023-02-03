# Upload to maven central

## Snapshot

1. You will need to specify the following properties in your `gradle.properties` located in your gradle home directory:

   ```
   signing.keyId=BDCAAE60
   signing.password=#private key goes here#
   signing.secretKeyRingFile=#secret key file goes here#
   ossrhUsername=scenarioo
   ossrhPassword=#sonatype password goes here#
   ```
The credentials can be found in the internal MS Teams Channel.


2. Change the version appropriately in the `build.gradle`
3. `gradlew clean publish`
4.  Promote build to maven central: http://central.sonatype.org/pages/releasing-the-deployment.html  
    Please note, we're using the old server https://oss.sonatype.org.

## Releases

Same steps as for snapshot releases and then promote build to maven central:
http://central.sonatype.org/pages/releasing-the-deployment.html
