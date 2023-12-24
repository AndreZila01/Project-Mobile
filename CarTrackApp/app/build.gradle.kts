plugins {
    id("com.android.application")
}


android {
    namespace = "pt.iade.andre.diogo.cartrackapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "pt.iade.andre.diogo.cartrackapp"
        minSdk = 30
        targetSdk = 33
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


}


dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.mediarouter:mediarouter:1.6.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("androidx.media3:media3-common:1.2.0")
    testImplementation("junit:junit:4.13.2")
    implementation("com.squareup.okhttp3:okhttp:4.8.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}