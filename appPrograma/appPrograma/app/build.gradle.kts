plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
//nolo pongas
    //id("com.android.application")
    //
}

android {
    namespace = "com.example.appprograma"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appprograma"
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //librerias para json retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.5.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")
    //tener acceso a fire base
    implementation("com.google.firebase:firebase-bom:33.1.1")
    //conectarse a la base de datos de firebase
    implementation("com.google.firebase:firebase-database-ktx:20.0.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}