# mpp-ktx
[![](https://jitpack.io/v/MikibeMiki/mpp-ktx.svg)](https://jitpack.io/#MikibeMiki/mpp-ktx)

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

* Bound service [Example](./androidExample/src/main/java/com/github/mikibemiki/android/example/service/ExampleServiceOneImpl.kt)