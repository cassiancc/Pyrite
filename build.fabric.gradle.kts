@file:Suppress("UnstableApiUsage")

plugins {
    id("net.fabricmc.fabric-loom")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
    id("maven-publish")
}

val minecraft = stonecutter.current.version
val mcVersion = stonecutter.current.project.substringBeforeLast('-')

tasks.named<ProcessResources>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.WARN

    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version") + "+" + prop("deps.minecraft")
        this["minecraft"] = prop("mod.mc_dep_fabric")
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }

}

tasks.named("processResources") {
    dependsOn(":${stonecutter.current.project}:stonecutterGenerate")
}

version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
base.archivesName = property("mod.id") as String

loom {
    accessWidenerPath = rootProject.file("src/main/resources/${property("mod.id")}.accesswidener")
}

jsonlang {
    languageDirectories = listOf("assets/${property("mod.id")}/lang")
    prettyPrint = true
}

repositories {
    mavenLocal()
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
        name = "cassian's maven"
        url = uri("https://maven.cassian.cc")
        content {
            includeGroupAndSubgroups("cc.cassian")
        }
    }
    flatDir { dirs(file("$rootDir/libs")) }
}


fabricApi {
    configureDataGeneration() {
        outputDirectory = file("$rootDir/src/main/generated")
        client = true
        modId = "pyrite"
    }
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    implementation("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")

    implementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric-api")}")

    implementation("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    include("folk.sisby:kaleido-config:${property("deps.kaleido")}")

    compileOnly("maven.local:columns:1.12.0")

    // YACL
    if (hasProperty("deps.yacl")) {
        compileOnly("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-fabric")
        localRuntime("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-fabric")
        localRuntime("maven.modrinth:mcqoy:${property("deps.mcqoy")}")
    } else {
        compileOnly("dev.isxander:yet-another-config-lib:3.7.1+1.21.6-neoforge") {
            isTransitive = false
        }
    }
    // Cloth Config
    if (hasProperty("deps.cloth_version")) {
        compileOnly("me.shedaniel.cloth:cloth-config-fabric:${property("deps.cloth_version")}")
    } else {
        compileOnly("me.shedaniel.cloth:cloth-config-neoforge:19.0.147")
    }
    // Mod Menu
    if (hasProperty("deps.modmenu")) {
        compileOnly("maven.modrinth:modmenu:${property("deps.modmenu")}")
        runtimeOnly("maven.modrinth:modmenu:${property("deps.modmenu")}")
    } else {
        compileOnly("com.terraformersmc:modmenu:18.0.0-alpha.3")
    }

    implementation("maven.modrinth:farmers-delight-refabricated:${property("deps.fd")}") {
        exclude(group = "net.fabricmc")
        exclude(group = "me.shedaniel")
    }

    implementation("cc.cassian.rrv:reliable-recipe-viewer-fabric:${property("deps.rrv")}") {
        isTransitive = false
    }
    implementation("maven.modrinth:more-chest-variants-lieonlion:${property("deps.lolmcv")}") {
        exclude(group = "net.fabricmc")
        exclude(group = "me.shedaniel")
    }
    implementation("maven.modrinth:quad:${property("deps.quad")}")

}

configurations.all {
    resolutionStrategy {
        force("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")
    }
}

stonecutter {
    replacements.string {
        direction = eval(current.version, ">1.21")
        replace("ResourceLocation", "Identifier")
    }
}

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(jar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

loom.runs.named("server") {
    isIdeConfigGenerated = false
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

val additionalVersionsStr = findProperty("publish.additionalVersions") as String?
val additionalVersions: List<String> = additionalVersionsStr
    ?.split(",")
    ?.map { it.trim() }
    ?.filter { it.isNotEmpty() }
    ?: emptyList()

publishMods {
    file = tasks.jar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.named<org.gradle.jvm.tasks.Jar>("sourcesJar").map { it.archiveFile.get() })

    // one of BETA, ALPHA, STABLE
    type = BETA
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} Fabric"
    version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
    changelog = provider { rootProject.file("CHANGELOG-LATEST.md").readText() }
    modLoaders.add("fabric")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env.MODRINTH_API_KEY.orNull()
        minecraftVersions.add(property("deps.minecraft").toString())
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env.CURSEFORGE_API_KEY.orNull()
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
        if (hasProperty("deps.emi")) {
            optional("emi")
        }
        if (hasProperty("deps.rrv")) {
            optional("rrv")
        }
    }
}
