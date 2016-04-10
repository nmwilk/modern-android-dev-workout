# Modern Android Dev Workout

Working sample app showing usage of [RxJava](https://github.com/ReactiveX/RxJava), [RetroLambda](https://github.com/orfjackal/retrolambda), [RetroFit](http://square.github.io/retrofit/), [Dagger2](http://google.github.io/dagger/) and [Kotlin](https://kotlinlang.org/).

Uses NASA's [NeoWs API](https://api.nasa.gov/api.html#NeoWS) as its data source.
 
Requires JDK 1.8 installed (for RetroLambda) and JAVA_HOME pointing at it. Or you can point the build at your JDK1.8 installation using a line like this - in the app module's build.gradle:

```
retrolambda {
    jdk '/Library/Java/JavaVirtualMachines/<your_jdk1.8_version>/Contents/Home'
}
```
