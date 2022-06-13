import org.gradle.api.artifacts.dsl.DependencyHandler

object ApplicationId {
    const val id = "com.loogika.mikroisp"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val domain = ":domain"
    const val network = ":network"
    const val client = ":client"
    const val database = ":database"
    const val login = ":login"
    const val uikit = ":uikit"
    const val clientData = ":clientdata"
    const val dashboard = ":dashboard"
    const val home = ":home"
}

object TestLibraries {
    const val jUnit =
        "junit:junit:${Versions.jUnit}"
}

object AndroidTestLibraries {
    const val extJUnit =
        "androidx.test.ext:junit:${Versions.extJUnit}"
    const val espressoCore =
        "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object AndroidX { // dependencias de androidx

    const val coreKtx =
        "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
}

object AndroidCompose { // dependencia de compose
    const val composeUi =
        "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial =
        "androidx.compose.material:material:${Versions.materialCompose}"
    const val composePreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val activityCompose =
        "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeTest =
        "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeTooling =
        "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    const val coilCompose =
        "io.coil-kt:coil-compose:${Versions.coilCompose}"

    const val lifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"

    const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
}


object Accompanist {
    const val accompanistPager =
        "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val accompanistIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    const val accompanistNavigation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val accompanistNavigationMaterial =
        "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
    const val accompanistPlaceHolder =
        "com.google.accompanist:accompanist-placeholder-material:${Versions.accompanist}"

}

object Hilt {
    const val hiltAndroid =
        "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler =
        "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"
}

object Retrofit {
    const val retrofit =
        "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}
