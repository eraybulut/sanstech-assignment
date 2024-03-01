plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.eraybulut.sanstech_assignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eraybulut.sanstech_assignment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(type ="String",name = "BASE_URL", value = "\"https://apivx.misli.com/api/mobile/v2/\"")
        }
        debug {
            buildConfigField(type ="String",name = "BASE_URL", value = "\"https://apivx.misli.com/api/mobile/v2/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding{
        enable = true
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    // Androidx Core
    implementation(libs.androidx.core.ktx)

    //AppCompat
    implementation(libs.androidx.appcompat)

    //Material
    implementation(libs.material)

    //Activity
    implementation(libs.androidx.activity)

    //Constraintlayout
    implementation(libs.androidx.constraintlayout)

    //Lifecycle Extensions
    implementation(libs.lifecycle.extensions)

    //Lifecycle Runtime
    implementation(libs.lifecycle.runtime.ktx)

    //Coroutine Android
    implementation(libs.kotlinx.coroutines.android)

    //Lottie
    implementation(libs.lottie)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitGson)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}