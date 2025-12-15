@file:Suppress("UnstableApiUsage")

plugins {
    id("fabric-loom")
    id("dev.kikugie.postprocess.jsonlang")
    id("me.modmuss50.mod-publish-plugin")
}

tasks.named<ProcessResources>("processResources") {
    fun prop(name: String) = project.property(name) as String

    val props = HashMap<String, String>().apply {
        this["version"] = prop("mod.version") + "+" + prop("deps.minecraft")
        this["minecraft"] = prop("mod.mc_dep_fabric")
    }

    filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml", "META-INF/mods.toml")) {
        expand(props)
    }
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
        name = "Greenhouse (Farmer's Delight)"
        url = uri("https://maven.greenhouse.lgbt/releases")
        content {
            includeGroup("vectorwing")
        }
    }
    maven( "https://jitpack.io/") {
        content {
            includeGroup("com.github.Chocohead")
            name = "Jitpack (Fabric ASM)"
        }
    }
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
        name = "Parchment Mappings"
        url = uri("https://maven.parchmentmc.org")
        content {
            includeGroupAndSubgroups("org.parchmentmc")
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
}

dependencies {
    minecraft("com.mojang:minecraft:${property("deps.minecraft")}")
    mappings(loom.layered {
        officialMojangMappings()
        if (hasProperty("deps.parchment"))
            parchment("org.parchmentmc.data:parchment-${property("deps.parchment")}@zip")
    })
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric-loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric-api")}")

    // Mod Menu
    if (hasProperty("deps.modmenu")) {
        modApi("com.terraformersmc:modmenu:${property("deps.modmenu")}") {
            exclude(group = "net.fabricmc")
        }
    }
    modImplementation("maven.modrinth:mcqoy:17CKhwqV")

    // YACL
    if (hasProperty("deps.yacl")) {
        modApi("dev.isxander:yet-another-config-lib:${property("deps.yacl")}-fabric") {
            exclude(group = "net.fabricmc")
        }
    }

    modImplementation("maven.modrinth:more-chest-variants-lieonlion:${property("deps.lolmcv")}-Fabric")
    modImplementation("maven.modrinth:quad:${property("deps.quad")}-Fabric")

    implementation("folk.sisby:kaleido-config:${property("deps.kaleido")}")
    include("folk.sisby:kaleido-config:${property("deps.kaleido")}")

    modCompileOnly("maven.modrinth:columns:${property("deps.columns")}")
    if (hasProperty("deps.eiv"))
        modImplementation("maven.modrinth:eiv:${property("deps.eiv")}-fabric")
    if (hasProperty("deps.emi")) {
        modCompileOnly("dev.emi:emi-fabric:${property("deps.emi")}+${property("deps.minecraft")}:api")
        modLocalRuntime("dev.emi:emi-fabric:${property("deps.emi")}+${property("deps.minecraft")}")
    }
    modCompileOnly("maven.modrinth:totally-lit:${property("deps.totally_lit")}")
    modLocalRuntime("cc.cassian.item-descriptions:item-descriptions-fabric:${property("deps.item_descriptions")}") {
        isTransitive = false
    }

    modImplementation("vectorwing:FarmersDelight:${property("deps.fdrf")}") {
        exclude(group = "net.fabricmc")
    }

    if (hasProperty("deps.copper_age_backport")) {
        modImplementation("maven.modrinth:backport-copper-age:${property("deps.copper_age_backport")}")
    }

    val modules = listOf("transitive-access-wideners-v1", "registry-sync-v0", "resource-loader-v0")
    for (it in modules) modImplementation(fabricApi.module("fabric-$it", property("deps.fabric-api") as String))
}

//fabricApi {
//    configureDataGeneration() {
//        outputDirectory = file("$rootDir/src/main/generated")
//        client = true
//    }
//}

tasks {
    processResources {
        exclude("**/neoforge.mods.toml", "**/mods.toml")
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        from(remapJar.map { it.archiveFile })
        into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
        dependsOn("build")
    }
}

java {
    withSourcesJar()
    val javaCompat = if (stonecutter.eval(stonecutter.current.version, ">=1.21")) {
        JavaVersion.VERSION_21
    } else {
        JavaVersion.VERSION_17
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

publishMods {
    file = tasks.remapJar.map { it.archiveFile.get() }
    additionalFiles.from(tasks.remapSourcesJar.map { it.archiveFile.get() })

    type = STABLE
    displayName = "${property("mod.name")} ${property("mod.version")} for ${stonecutter.current.version} Fabric"
    version = "${property("mod.version")}+${property("deps.minecraft")}-fabric"
    changelog = provider { rootProject.file("CHANGELOG.md").readText() }
    modLoaders.add("fabric")

    modrinth {
        projectId = property("publish.modrinth") as String
        accessToken = env.MODRINTH_API_KEY.orNull()
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
        optional("mcqoy")
    }

    curseforge {
        projectId = property("publish.curseforge") as String
        accessToken = env.CURSEFORGE_API_KEY.orNull()
        minecraftVersions.add(stonecutter.current.version)
        minecraftVersions.addAll(additionalVersions)
        requires("fabric-api")
    }
}
