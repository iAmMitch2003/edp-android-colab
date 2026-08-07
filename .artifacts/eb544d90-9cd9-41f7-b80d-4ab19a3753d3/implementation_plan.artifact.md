# Implementation Plan - Fix Kotlin Extension Conflict

The project is failing to sync with the error: `Cannot add extension with name 'kotlin', as there is an extension already registered with that name.` This typically occurs when multiple Kotlin-related plugins are applied with conflicting versions or in a way that causes multiple registrations of the `kotlin` extension (e.g., using `id()` without a version in a submodule while other plugins use versioned `alias()`).

## Proposed Changes

### [gradle/libs.versions.toml](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/gradle/libs.versions.toml)
- Add `kgpAndroid` to the `[plugins]` section to manage the Kotlin Android plugin version centrally.
- Use the existing `kotlin` version reference for consistency.

### [build.gradle.kts (root)](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/build.gradle.kts)
- Add `alias(libs.plugins.kgpAndroid) apply false` to the root `plugins` block. This ensures all modules use the same version of the Kotlin plugin and helps avoid classloader conflicts.

### [app/build.gradle.kts](file:///C:/Users/Wendy/AndroidStudioProjects/MyApplication/app/build.gradle.kts)
- Update the `plugins` block to use `alias()` for all plugins defined in the version catalog:
    - `id("com.android.application")` -> `alias(libs.plugins.android.application)`
    - `id("org.jetbrains.kotlin.android")` -> `alias(libs.plugins.kgpAndroid)`
    - `id("org.jetbrains.kotlin.plugin.compose")` -> `alias(libs.plugins.kotlin.compose)`
- This unifies the plugin application mechanism and ensures they all use the version defined in `libs.versions.toml`.

## Verification Plan

### Automated Tests
- Run `gradlew sync` (or simply trigger a sync in the IDE) to verify the "Cannot add extension with name 'kotlin'" error is resolved.
- Run `./gradlew :app:assembleDebug` to ensure the project builds successfully.
