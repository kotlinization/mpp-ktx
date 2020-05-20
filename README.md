# mpp-ktx
[![](https://jitpack.io/v/kotlinizer/mpp-ktx.svg)](https://jitpack.io/#kotlinizer/mpp-ktx)

Library for small functionalities usable in various settings.

# Coroutines

Extension function *throwIfCanceled* checks and throws corotuine is canceled.
Example:
```kotlin
GlobalScope.launch {
  try {
      //Long operation which can be canceled
  } catch (t: Throwable) {
      t.throwIfCanceled()      
      //Log throwable
  }
}
```

# Android

* Bound service [Example](./androidExample/src/main/java/com/github/kotlinizer/android/example/service/ExampleServiceOneImpl.kt)