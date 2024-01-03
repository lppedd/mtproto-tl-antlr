import com.strumenta.antlrkotlin.gradle.AntlrKotlinTask
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.antlr.kotlin)
  alias(libs.plugins.kotlinx.resources)
}

kotlin {
  explicitApiWarning()

  @OptIn(ExperimentalKotlinGradlePluginApi::class)
  compilerOptions {
    apiVersion.set(KotlinVersion.KOTLIN_1_9)
    languageVersion.set(KotlinVersion.KOTLIN_1_9)
  }

  jvm {
    compilations.configureEach {
      compilerOptions.configure {
        jvmTarget.set(JvmTarget.JVM_1_8)
        freeCompilerArgs.add("-Xjvm-default=all")
      }
    }

    testRuns.configureEach {
      executionTask.configure {
        useJUnitPlatform()
        filter.isFailOnNoMatchingTests = true
      }
    }
  }

  js {
    nodejs {
      testTask {
        useMocha {
          // Override default 2s timeout
          timeout = "30s"
        }

        filter.isFailOnNoMatchingTests = true
      }
    }

    browser {
      testTask {
        // We cannot load big text files
        enabled = false
        filter.isFailOnNoMatchingTests = false
      }
    }
  }

  //
  // Native targets
  //

  // Tier 1
  // macOS host only
  macosX64()
  macosArm64()
  iosSimulatorArm64()
  iosX64()

  // Tier 2
  linuxX64()
  linuxArm64()

  // macOS host only
  watchosSimulatorArm64()
  watchosX64()
  watchosArm32()
  watchosArm64()
  tvosSimulatorArm64()
  tvosX64()
  tvosArm64()
  iosArm64()

  // Tier 3
  mingwX64()

  //
  // Dependencies
  //

  sourceSets {
    commonMain {
      kotlin {
        srcDir(layout.buildDirectory.dir("generatedAntlr"))
      }

      dependencies {
        api(libs.antlr4.kotlin)
      }
    }

    commonTest {
      dependencies {
        implementation(kotlin("test"))
        implementation(libs.kotlinx.resources)
      }
    }
  }
}

tasks {
  val generateKotlinGrammarSource = register<AntlrKotlinTask>("generateKotlinGrammarSource") {
    dependsOn("cleanGenerateKotlinGrammarSource")

    // Only include *.g4 files.
    // This allows tools (e.g., IDE plugins) to generate temporary files inside the base path
    source = fileTree(layout.projectDirectory.dir("antlr")) {
      include("**/*.g4")
    }

    val pkgName = "com.github.lppedd.mtproto.tl.parser.generated"
    packageName = pkgName

    // We want visitors alongside listeners
    arguments = listOf("-visitor")

    // Generated files are outputted inside build/generatedAntlr
    val outDir = "generatedAntlr/${pkgName.replace(".", "/")}"
    outputDirectory = layout.buildDirectory.dir(outDir).get().asFile
  }

  withType<KotlinCompile<*>> {
    dependsOn(generateKotlinGrammarSource)
  }
}
