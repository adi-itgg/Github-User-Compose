plugins {
    id (Plugin.Android.App)
    id (Plugin.Android.Kotlin)
    id(Plugin.KotlinXSerialization)
    id(Plugin.Parcelize)
    id(Plugin.Kapt)
}

android {
    namespace = PACKAGE_NAME
    compileSdk = 33

    defaultConfig {
        applicationId = PACKAGE_NAME
        minSdk = 21
        targetSdk = 33
        versionCode = VERSION_CODE
        versionName = VERSION_NAME

        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
        Api.getClassFields { type, name, value ->
            buildConfigField(type, name, value)
        }
    }

    buildTypes {
        getByName(BUILD_TYPE_DEBUG) {
            isMinifyEnabled  = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile(PROGUARD_ANDROID_RULES_PRO), PROGUARD_RULES_PRO)
        }
        getByName(BUILD_TYPE_RELEASE) {
            isMinifyEnabled  = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile(PROGUARD_ANDROID_OPTIMIZED_RULES_PRO), PROGUARD_RULES_PRO)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs.toMutableList().apply {
            add("-P")
            add("plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENSION_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        animationsDisabled = true
    }
}

dependencies {

    implements(Deps)
    implements(DepsKapt, Type.KAPT)
    implements(DepsTest, Type.TEST)
    implements(DepsDebug, Type.DEBUG)
    implements(DepsAndroidTest, Type.ANDROID_TEST)

    testImplementation(kotlin("test"))
    androidTestImplementation(kotlin("test"))
}

