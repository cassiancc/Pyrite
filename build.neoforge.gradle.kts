plugins {
    id("net.neoforged.moddev")
    id ("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
}

val minecraft = stonecutter.current.version
val mcVersion = stonecutter.current.project.substringBeforeLast('-')

tasks.named<ProcessResources>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.WARN
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version") + "+" + prop("deps.minecraft")
        this["minecraft"] = prop("mod.mc_dep_forgelike")
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }
}

version = "${property("mod.version")}+${property("deps.minecraft")}-neoforge"
base.archivesName = property("mod.id") as String

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

neoForge {
    enable {
        version = property("deps.neoforge") as String
        // Disable recompilation for performance reasons.
        isDisableRecompilation = true
    }
    validateAccessTransformers = true

    runs {
        register("client") {
            gameDirectory = file("run/")
            client()
        }
        register("server") {
            gameDirectory = file("run/")
            server()
        }
    }

    mods {
        register(property("mod.id") as String) {
            sourceSet(sourceSets["main"])
        }
    }
    sourceSets["main"].resources.srcDir("src/main/generated")
}


repositories {
    mavenLocal()
    maven {
        name = "cassian's maven"
        url = uri("https://maven.cassian.cc")
        content {
            includeGroupAndSubgroups("cc.cassian")
        }
    }
    maven {
        name = "shedaniel (Cloth Config)"
        url = uri("https://maven.shedaniel.me/")
        content {
            includeGroupAndSubgroups("me.shedaniel")
        }
    }
    maven {
        name = "Terraformers (Mod Menu)"
        url = uri("https://maven.terraformersmc.com/releases/")
        content {
            includeGroupAndSubgroups("com.terraformersmc")
            includeGroupAndSubgroups("dev.emi")
        }
    }
    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroupAndSubgroups("maven.modrinth")
        }
    }
    maven {
        name = "Sisby Maven"
        url = uri("https://repo.sleeping.town/")
        content {
            includeGroupAndSubgroups("folk.sisby")
        }
    }
    maven {
        name = "Xander Maven"
        url = uri("https://maven.isxander.dev/releases")
        content {
            includeGroupAndSubgroups("dev.isxander")
            includeGroupAndSubgroups("org.quiltmc.parsers")
        }
    }
    maven {
        name = "Nucleoid Maven (Polymer)"
        url = uri("https://maven.nucleoid.xyz")
        content {
            includeGroupAndSubgroups("eu.pb4")
            includeGroupAndSubgroups("xyz.nucleoid")
        }
    }
    maven {
        name = "Fuzs Mod Resources"
        url = uri("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/")
        content {
            includeGroupAndSubgroups("fuzs")
        }
    }
    maven {
        name = "Kotlin for Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
        content {
            includeGroupAndSubgroups("thedarkcolour")
        }
    }
    maven {
        name = "Sinytra"
        url = uri("https://maven.su5ed.dev/releases")
        content {
            includeGroupAndSubgroups("org.sinytra")
        }
    }

    flatDir { dirs(file("$rootDir/libs")) }
}

dependencies {

    // YACL
    if (hasProperty("deps.yacl")) {
        implementation("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-neoforge")
        implementation("maven.modrinth:mcqoy:yHGo6VsD")
    }

    implementation("maven.modrinth:more-chest-variants-lieonlion:${property("deps.lolmcv")}")
    implementation("maven.modrinth:quad:${property("deps.quad")}")

    implementation("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    jarJar("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    if (hasProperty("deps.emi")) {
        compileOnly("dev.emi:emi-neoforge:${property("deps.emi")}+${property("deps.minecraft")}:api")
        runtimeOnly("dev.emi:emi-neoforge:${property("deps.emi")}+${property("deps.minecraft")}")
    }
    compileOnly("maven.modrinth:farmers-delight:${property("deps.fd")}")

    if (hasProperty("deps.copper_age_backport")) {
        implementation("maven.modrinth:backport-copper-age:${property("deps.copper_age_backport")}")
    }

    implementation("org.sinytra.forgified-fabric-api:fabric-resource-conditions-api-v1:4.3.0+5bdd099819")
    jarJar("org.sinytra.forgified-fabric-api:fabric-resource-conditions-api-v1:4.3.0+5bdd099819")
    implementation("org.sinytra.forgified-fabric-api:fabric-api-base:0.4.42+d1308ded19")
    jarJar("org.sinytra.forgified-fabric-api:fabric-api-base:0.4.42+d1308ded19")
    implementation("org.sinytra:forgified-fabric-loader:2.5.68+0.18.4+1.21.1:full")
    jarJar("org.sinytra:forgified-fabric-loader:2.5.68+0.18.4+1.21.1:full")

    implementation("org.jspecify:jspecify:1.0.0")

}


stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.21.10")
        replace("ResourceLocation", "Identifier")
    }
}


tasks {
    processResources {
        exclude("**/fabric.mod.json", "**/*.accesswidener", "**/mods.toml")
    }

    named("createMinecraftArtifacts") {
        dependsOn("stonecutterGenerate")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(jar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

java {
    withSourcesJar()
    val javaCompat = if (stonecutter.eval(stonecutter.current.version, ">26")) {
        JavaVersion.VERSION_25
    } else {
        JavaVersion.VERSION_21
    }
    sourceCompatibility = javaCompat
    targetCompatibility = javaCompat
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()


tasks.register<Sync>("syncDatagen") {
    from(project(":1.21.1-fabric").tasks.named("runDatagen"))
    into(file("src/main/generated/"))
}

publishMods {
    file = tasks.jar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.named<org.gradle.jvm.tasks.Jar>("sourcesJar").map { it.archiveFile.get() })

    type = STABLE
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} NeoForge"
    version = "${property("mod.version")}+${property("deps.minecraft")}-neoforge"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("neoforge")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env.MODRINTH_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft").toString())
        minecraftVersions.addAll(additionalVersions)
        optional("mcqoy")
        if (stonecutter.eval(mcVersion, "<1.21.4")) {
            optional("emi")
            optional("vanillabackport")
            optional("backport-copper-age")
        } else {
            optional("rrv")
        }
    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env.CURSEFORGE_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft").toString())
        minecraftVersions.addAll(additionalVersions)
        if (hasProperty("deps.emi")) {
            optional("emi")
        }
        if (hasProperty("deps.rrv")) {
            optional("rrv")
        }
    }
}
