# Fix @Composable Invocations Error

The project is currently failing to compile with the error: `@Composable invocations can only happen from the context of a @Composable function`. This is caused by corrupted source code formatting where random line breaks have been inserted between receivers and properties/functions, and between annotations and their targets. This is particularly problematic for Compose, as it interferes with the compiler's ability to identify `@Composable` contexts.

## Proposed Changes

I will clean up the formatting in all affected Kotlin files to ensure that `@Composable` annotations, imports, and property accesses (like `.current`) are correctly parsed by the compiler.

### [Component Name]

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/app/src/main/java/com/example/myapplication/MainActivity.kt)
- Fix `LocalView.current`
- Fix `SideEffect { ... }`
- Fix `MaterialTheme.colorScheme.background`
- Fix `Scaffold` and `NavHost` lambda formatting.

#### [MODIFY] [Theme.kt](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/app/src/main/java/com/example/myapplication/ui/theme/Theme.kt)
- Fix `import androidx.compose.runtime.Composable`
- Fix `import androidx.compose.ui.platform.LocalContext`
- Fix `LocalContext.current`

#### [MODIFY] [screen.kt](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/app/src/main/java/com/example/myapplication/screen.kt)
- Fix numerous broken property accesses and lambda formatting throughout `HomeScreen` and `GreetingScreen`.

#### [MODIFY] [routes.kt](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/app/src/main/java/com/example/myapplication/routes.kt)
- Fix `import kotlinx.serialization.Serializable`

#### [MODIFY] [Color.kt](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/app/src/main/java/com/example/myapplication/ui/theme/Color.kt)
- Fix `Color.White` and other broken assignments.

## Verification Plan

### Automated Tests
- Run `./gradlew :app:compileDebugKotlin` to verify the build now succeeds.
