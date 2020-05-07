### Getting Started

Gradle is the only supported build configuration, so add the library folder and the dependency to your project `build.gradle` file:

```groovy
dependencies {
  implementation project(":average-library")
}
```

Make sure the library is listed at the top of your settings.gradle file.

```groovy
  include ':app', ':average-library'
```

Click Sync Project with Gradle Files.

### Initialisation

You should subclass Application and call `Average.init(context)` on `onCreate()` method.

```kotlin
  class YourApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Average.init(this)
    }
}
```

Make sure you initialise the library before using other functions.

### Library usage

#### Add numbers

Use the `addNumber` function to include integers to the stored array.

```kotlin
    fun someMethod() {
        ...
        Average.addNumber(10)
        ...
    }
```

#### Get average

Use the `getAverage` function to retrieve the array's average.

```kotlin
    fun someMethod() {
        ...
        println(Average.getAverage())
        ...
    }
```

