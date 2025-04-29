rootProject.name = "java-practice"

pluginManagement {
    repositories{
        maven { url=uri ("https://maven.aliyun.com/repository/releases")}
        maven { url=uri ("https://maven.aliyun.com/repository/google")}
        maven { url=uri ("https://maven.aliyun.com/repository/central")}
        maven { url=uri ("https://maven.aliyun.com/repository/gradle-plugin")}
        maven { url=uri ("https://maven.aliyun.com/repository/public")}
        maven { url=uri ("https://www.jitpack.io")}
        maven {
            url = uri("http://110.110.110.100:8081/repository/maven-releases/")
            isAllowInsecureProtocol = true
        }
        google()
        mavenCentral()
        gradlePluginPortal()
    }

}

dependencyResolutionManagement {
    // repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/releases") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven {
            url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
        }
        maven { url = uri("https://www.jitpack.io") }
       // maven {
        //    url = uri("http://110.110.110.100:8081/repository/maven-releases/")
       //     isAllowInsecureProtocol = true
      //  }
        google()
        mavenCentral()
    }
}