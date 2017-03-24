The Scenarioo Writer Library for Java helps you produce correct Scenarioo documentations in your file system, out of your UI tests, such that the Scenarioo web application can read those documentations and present them to your stakeholders and let them browse it.

## Getting the Java library as a JAR

The JAR is available on Maven Central:
http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.scenarioo%22

We recommend to always use the latest version of the library or the highest available major version number that is equal or less to the major version number of the viewer web application you are using.

The following example shows the correct gradle dependency for using scenarioo in your Java project:
 
      repositories {
        mavenCentral()
      }

      dependencies {
         compile 'org.scenarioo:scenarioo-java:2.1.1'
      }

## Basic usage

Use instances of the `ScenarioDocuWriter` to write Scenarioo files. Each instance can be used to write multiple objects but in the end it has to be flushed using the `flush()` method. After using `flush()` the `ScenarioDocuWriter` instance can not be used anymore and you need to create a new instance for further API calls.

In one CI build run, you should store the following objects using the Scenarioo Writer API:

* Call `saveBranchDescription(branch)` once to store the *branch* information
* Call `saveBuildDescription(build)` once to store the *build* information
* Call `saveUseCase(useCase)` once for each *use case* you want to document
* Call `saveScenario(useCaseName, scenario)` once for each *scenario* you want to document inside a use case
* Step:
  * Call `saveScreenshotAsPng(useCaseName, scenarioName, stepIndex, screenshot)` to store the *screenshot* image as a PNG file for each step you want to document
* Call `saveStep(useCaseName, scenarioName, step)` once for each *step* you want to document inside a scenario (use `getScreenshotFile(useCaseName, scenarioName, stepIndex)` to retrieve the filename of the PNG screenshot file to set it in the Step object)


## Tutorial

Our tutorial on our webpage describes basic usage of the Writer library:
http://scenarioo.github.io/tutorial.html


## Example Sources

There are currently three examples:

* **Pizza Delivery Example at https://github.com/scenarioo/pizza-delivery**: This is the latest example and it contains an integration of Scenarioo that is quite close to what you would use in a real project. This is a good starting point for integrating Scenarioo into your JUnit / Selenium based project.

* **Hello Scenarioo Example at https://github.com/scenarioo/scenarioo-hello-world**: A small minimal integration of scenarioo with real selenium webtests for a static dummy webpage example.

* **Full and abstract Scenarioo Docu Generation Example at https://github.com/scenarioo/scenarioo/tree/master/scenarioo-docu-generation-example**: Extensive and detailed example on how to produce the same documentation content data, that you can browse in the demo scenarioo application, using the Java library of scenarioo. The example contains no real UI tests. The application and the testing toolkit is just simulated to demonstrate the concept, how you would use the Scenarioo Writer library in a usual JUnit-UI-Test-Setup (no matter which UI testing toolkit you are using).
This demo demonstrates most of the scenarioo features.

## Integrate Scenarioo in JUnit tests

Have a look at the class [FindPageUITest](https://github.com/scenarioo/scenarioo/blob/develop/scenarioo-docu-generation-example/src/test/java/org/scenarioo/uitest/example/testcases/FindPageUITest.java) which demonstrates a simple example of a UI test class with several UI testing scenarios.

The important part is in its base class [UITest](https://github.com/scenarioo/scenarioo/blob/develop/scenarioo-docu-generation-example/src/test/java/org/scenarioo/uitest/example/infrastructure/UITest.java). In there you can see the setup of some JUnit rules to intercept the execution of each test class and each test scenario, such that each time a new use case description or scenario description is written using the scenarioo java API. 

## Capturing step data in your UI tests

Capture step data from UI tests using one of three strategies

* **Capture steps from UI Toolkit events**: Given your UI toolkit has some kind of event listening mechanism, you could listen for any click event, and simply record a step on each such click event. This is for example possible using Selenium's [EventFiringWebDriver](http://www.google.ch/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0CBwQFjAA&url=http%3A%2F%2Fselenium.googlecode.com%2Fgit%2Fdocs%2Fapi%2Fjava%2Forg%2Fopenqa%2Fselenium%2Fsupport%2Fevents%2FEventFiringWebDriver.html&ei=TrisU8DuK-3n7Aa18oD4Aw&usg=AFQjCNHNlEkBp02XLMHIg4wkw5e5pRlOQQ&sig2=wvJ_AYBCvQSqGlLK5-9uHA&bvm=bv.69837884,d.ZGU). 
* **Use a UI toolkit abstraction layer**: As in the example you could define your own abstraction layer for your UI toolkit. You can then hook yourself into this abstraction layer and record a step with a screenshot in side each method where a click is done, see all click-methods in class [UITestToolkitAbstraction](https://github.com/scenarioo/scenarioo/blob/develop/scenarioo-docu-generation-example/src/test/java/org/scenarioo/uitest/example/infrastructure/UITestToolkitAbstraction.java).
* **Explicitly add some record-Step-statements into your test code** to record a step explicitly, only when you want to record a step. This is not recommended, as test writers should not always have to think and care about it, when to take a screenshot and record a step. This should be the responsibility of some cross cutting testing infrastructre code to take care that on each click or other important interaction of the test, a recording of a step is done.

## More ...

Just study the example code, there are a lot of comments in the code, that should help you a lot!
