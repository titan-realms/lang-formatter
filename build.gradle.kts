plugins {
    `java-library`
    `maven-publish`
}

group = "net.titanrealms.lang.formatter"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("net.kyori:adventure-api:4.7.0")
    // below is used just for printing out in dev
    api("net.kyori:adventure-text-serializer-plain:4.7.0")
    api("net.kyori:adventure-text-serializer-gson:4.7.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
