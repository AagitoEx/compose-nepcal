## compose-nepcal (Nepali date picker and converter for compose)
> Current version 0.0.1-alpha01

This is a android compose ui library to nepali (BS) calendar date picker.

#### Step 1: Enable `coreLibraryDesugaring`(can be skipped)

Before getting started, make sure `coreLibraryDesugaring` is enabled as this library depends on `java8 LocalDateTime`. Learn
more about desugaring at [developer.android.com](https://developer.android.com/studio/write/java8-support).
```groovy
compileOptions {
    coreLibraryDesugaringEnabled true
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}

kotlinOptions {
    jvmTarget = "1.8"
}

dependencies {
    //...
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.1.5'
    //...
}
```
#### Step 2: Add dependency
Please also note the library leverages `Compose MaterialTheme` for styling. To get started include
following maven dependency in your app gradle file.
```groovy
implementation 'io.github.aagitoex:compose-nepcal:0.0.1-alpha01'
```

#### Step 3: Display date picker dialog
The library provides a date picker dialog api that is similar to `Dialog` in compose.
```kotlin
Surface() {
    var showDateDialog by remember { mutableStateOf(false) }
    if (showDateDialog) {
        CalendarDialog(
            selectedDate = LocalDate.now()!!,
            onDismissRequest = {
                showDateDialog = false
            },
            onDateChange = { localDate: LocalDate ->
                
            }
        )
    }
}
```
The library includes custom `NepDate()` data class to represent BS dates. It has many helper functions
that can help you to manipulate BS dates.
```kotlin
data class NepDate(
    val year: Int,
    val month: Int,
    val day: Int,
)

//initialize with now()
val nepDateNow = NepDate.now()

//initialize with default constructor
val nepDate = NepDate(2078, 1, 23)

//get AD date from nepDate
val ad = nepDate.adEquivalent

//max and MIN supported dates (*Supported BS dates range from 1975-2100)
val min = NepDate.MIN
val max = NepDate.MAX
```

#### Date converter functions
The library also includes `BS -> AD` and `AD -> BS` date converter functions.
```kotlin
fun fromADToBS(date: LocalDate): NepDate
fun fromBSToAD(date: NepDate): LocalDate
```

**Api docs**
Work in progress will be available soon, contributions are welcomed.
