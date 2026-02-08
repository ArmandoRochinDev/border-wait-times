import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.borderwaittimes"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.borderwaittimes"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {
    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    ksp(libs.hilt.android.compiler)

    //Retrofit
    implementation(libs.retrofit)

    //TikXml
    implementation(libs.tikXml.annotation)
    implementation(libs.tikXml.core)
    implementation(libs.retrofit.converter)
    kapt(libs.tikXml.processor)

    //ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //activity
    implementation(libs.androidx.activity.ktx)

    //Testing
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockwebserver)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}