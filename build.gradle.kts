plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "org.ethanvannlibrary"
version = "1.0-SNAPSHOT"
var runeLiteVersion = "1.10.16.1"
repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://repo.runelite.net")
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    compileOnly(group = "net.runelite", name = "client", version = runeLiteVersion)
    compileOnly("org.projectlombok:lombok:1.18.20")
    implementation("org.roaringbitmap:RoaringBitmap:0.9.44")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
}

tasks.test {
    useJUnitPlatform()
}