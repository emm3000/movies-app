object Dependencies {

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitInterceptor}"

    // Dagger
    const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerCompiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"

    // Navigation Component
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"

    // Coil
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coilCompose}"

    // Jetpack compose
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    const val composeTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
    const val composeToolingDebug = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"

    // Android
    const val material = "com.google.android.material:material:${Versions.material}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidCore = "androidx.core:core-ktx:${Versions.androidCore}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val androidSplash = "androidx.core:core-splashscreen:${Versions.androidSplash}"

    const val java8Compatibility = "com.android.tools:desugar_jdk_libs:${Versions.java8Compatibility}"


    // Test Libraries
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val truth = "com.google.truth:truth:${Versions.truth}"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"

}