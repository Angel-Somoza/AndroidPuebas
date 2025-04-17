plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.androidpuebas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.androidpuebas"
        minSdk = 35
        targetSdk = 35
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")  // Última versión estable
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")  // Última versión estable
    implementation("com.google.android.gms:play-services-maps:18.1.0")  // Última versión conocida
    implementation("com.google.dagger:hilt-android:2.50")  // Última versión estable
    kapt("com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")  // Última versión estable

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21") // Actualizar a la última versión estable

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")  // Última versión estable
    implementation("com.google.code.gson:gson:2.8.9")  // Última versión estable
    implementation("com.android.volley:volley:1.2.1")  // Última versión estable
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}