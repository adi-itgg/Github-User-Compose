// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id (Plugin.Android.App) version AndroidAppVersion apply false
    id (Plugin.Library) version AndroidAppVersion apply false
    id (Plugin.Android.Kotlin) version kotlinVersion apply false
    kotlin(Plugin.Serialization) version kotlinVersion apply false
}

tasks.register("clean") {
    delete(rootProject.buildDir)
}
