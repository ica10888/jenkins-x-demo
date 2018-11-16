# README

#### project

https://github.com/ica10888/jenkins-x-libraries

#### pipeline script
```groovy

@Library('jxlib') 
import com.example.springcloud.Dev
pipeline {
    agent { label "jenkins-maven" }
    options { disableConcurrentBuilds() }
    
    environment {
    GIT_REPO          = "https://github.com/ica10888/jenkins-x-demo.git"
    APP_NAME          =  "demo"
    BRANCH_NAME       =  "master"     
    PORT              =  8080
    JAVA_COMMAND      = "java -Xms256M -Xmx512M -jar app.jar"
    MAVEN_COMMAND     = "mvn -Dmaven.test.skip=true package"
    KUBERNETES_CPU    = "1000m"
    KUBERNETES_MENORY = "512Mi"
    }
    
    stages {
      stage('init item'){steps{script{Dev.initItem(this)}}}
      stage('library resource'){steps{script{Dev.libraryResource(this)}}}
      stage('maven build'){steps{script{Dev.mavenBuild(this)}}}
      stage('skaffol build'){steps{script{Dev.skaffolBuild(this)}}}
      stage('helm deploy'){steps{script{Dev.helmDeploy(this)}}}
      stage('predestroy'){steps{script{Dev.predestroy(this)}}}
      }
}

```


#### sccessful log

```
Started by user admin
Running in Durability level: MAX_SURVIVABILITY
Loading library jxlib@master
Attempting to resolve master from remote references...
 > git --version # timeout=10
using GIT_SSH to set credentials 
 > git ls-remote -h https://github.com/ica10888/jenkins-x-libraries.git # timeout=10
Found match: refs/heads/master revision f7c8a8d3042f3392f20d3671edc508bb709278c1
 > git rev-parse --is-inside-work-tree # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url https://github.com/ica10888/jenkins-x-libraries.git # timeout=10
Fetching without tags
Fetching upstream changes from https://github.com/ica10888/jenkins-x-libraries.git
 > git --version # timeout=10
using GIT_SSH to set credentials 
 > git fetch --no-tags --progress https://github.com/ica10888/jenkins-x-libraries.git +refs/heads/*:refs/remotes/origin/*
Checking out Revision f7c8a8d3042f3392f20d3671edc508bb709278c1 (master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f f7c8a8d3042f3392f20d3671edc508bb709278c1
Commit message: "Update Dockerfile"
 > git rev-list --no-walk d74c4b5b7c834956a8302fec6056b7e84f8249f7 # timeout=10
[Pipeline] node
Still waiting to schedule task
Waiting for next available executor
Agent maven-n6h9k is provisioned from template Kubernetes Pod Template
Agent specification [Kubernetes Pod Template] (jenkins-maven): 
* [jnlp] jenkinsci/jnlp-slave:3.14-1(resourceRequestCpu: 100m, resourceRequestMemory: 128Mi, resourceLimitCpu: , resourceLimitMemory: )
* [maven] jenkinsxio/builder-maven:0.1.24(resourceRequestCpu: 400m, resourceRequestMemory: 512Mi, resourceLimitCpu: 1, resourceLimitMemory: 1024Mi)

Running on maven-n6h9k in /home/jenkins/workspace/app-demo
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (init item)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
[app-demo] Running shell script
+ git clone https://github.com/ica10888/jenkins-x-demo.git ./
Cloning into '.'...
[Pipeline] sh
[app-demo] Running shell script
+ git checkout master
Already on 'master'
Your branch is up-to-date with 'origin/master'.
[Pipeline] container
[Pipeline] {
[Pipeline] sh
[app-demo] Running shell script
+ git config --global credential.helper store
[Pipeline] sh
[app-demo] Running shell script
+ jx step git credentials
Generated Git credentials file /home/jenkins/git/credentials
[Pipeline] }
[Pipeline] // container
[Pipeline] fileExists
[Pipeline] sh
[app-demo] Running shell script
+ mkdir -p /tmp/demo
[Pipeline] sh
[app-demo] Running shell script
+ mv /home/jenkins/workspace/app-demo/pom.xml /home/jenkins/workspace/app-demo/src /tmp/demo
[Pipeline] sh
[app-demo] Running shell script
+ mv /home/jenkins/workspace/app-demo/.git /home/jenkins/workspace/app-demo/.gitignore /tmp/demo
[Pipeline] sh
[app-demo] Running shell script
+ mv /tmp/demo /home/jenkins/workspace/app-demo
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (library resource)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo
[Pipeline] {
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] }
[Pipeline] // dir
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo
[Pipeline] {
[Pipeline] fileExists
[Pipeline] sh
[demo] Running shell script
+ mkdir -p charts/demo/templates
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo/charts/demo
[Pipeline] {
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/demo/demo/ Chart.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/demo/demo/ values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/javacommand/java -Xms256M -Xmx512M -jar app.jar/ values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/500m/1000m/ values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/1000Mi/512Mi/ values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/400m/800m/ values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/800Mi/409Mi/ values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/8080/8080/ values.yaml
[Pipeline] }
[Pipeline] // dir
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo/charts/demo/templates
[Pipeline] {
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // dir
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo
[Pipeline] {
[Pipeline] fileExists
[Pipeline] libraryResource
[Pipeline] writeFile
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/8080/8080/ Dockerfile
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (maven build)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo
[Pipeline] {
[Pipeline] container
[Pipeline] {
[Pipeline] sh
[demo] Running shell script
+ echo 1.3.918-d-SNAPSHOT
[Pipeline] sh
[demo] Running shell script
++ cat VERSION
+ mvn versions:set -DnewVersion=1.3.918-d-SNAPSHOT
Picked up _JAVA_OPTIONS: -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dsun.zip.disableMemoryMapping=true -XX:+UseParallelGC -XX:MinHeapFreeRatio=5 -XX:MaxHeapFreeRatio=10 -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -Xms10m -Xmx192m
[INFO] Scanning for projects...
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-parent/1.5.9.RELEASE/spring-boot-starter-parent-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-parent/1.5.9.RELEASE/spring-boot-starter-parent-1.5.9.RELEASE.pom (7.5 kB at 32 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-dependencies/1.5.9.RELEASE/spring-boot-dependencies-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-dependencies/1.5.9.RELEASE/spring-boot-dependencies-1.5.9.RELEASE.pom (102 kB at 2.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/jackson-bom/2.8.10/jackson-bom-2.8.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/jackson-bom/2.8.10/jackson-bom-2.8.10.pom (10 kB at 134 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/jackson-parent/2.8/jackson-parent-2.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/jackson-parent/2.8/jackson-parent-2.8.pom (8.0 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/oss-parent/27/oss-parent-27.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/oss-parent/27/oss-parent-27.pom (20 kB at 269 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/logging/log4j/log4j-bom/2.7/log4j-bom-2.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/logging/log4j/log4j-bom/2.7/log4j-bom-2.7.pom (5.4 kB at 67 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/9/apache-9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/9/apache-9.pom (15 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-framework-bom/4.3.13.RELEASE/spring-framework-bom-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-framework-bom/4.3.13.RELEASE/spring-framework-bom-4.3.13.RELEASE.pom (5.1 kB at 65 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/data/spring-data-releasetrain/Ingalls-SR9/spring-data-releasetrain-Ingalls-SR9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/data/spring-data-releasetrain/Ingalls-SR9/spring-data-releasetrain-Ingalls-SR9.pom (4.6 kB at 510 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/data/build/spring-data-build/1.9.9.RELEASE/spring-data-build-1.9.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/data/build/spring-data-build/1.9.9.RELEASE/spring-data-build-1.9.9.RELEASE.pom (6.4 kB at 806 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/integration/spring-integration-bom/4.3.12.RELEASE/spring-integration-bom-4.3.12.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/integration/spring-integration-bom/4.3.12.RELEASE/spring-integration-bom-4.3.12.RELEASE.pom (8.5 kB at 944 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/security/spring-security-bom/4.2.3.RELEASE/spring-security-bom-4.2.3.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/security/spring-security-bom/4.2.3.RELEASE/spring-security-bom-4.2.3.RELEASE.pom (4.3 kB at 724 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-help-plugin/2.2/maven-help-plugin-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-help-plugin/2.2/maven-help-plugin-2.2.pom (8.7 kB at 118 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/24/maven-plugins-24.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/24/maven-plugins-24.pom (11 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/23/maven-parent-23.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/23/maven-parent-23.pom (33 kB at 412 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/13/apache-13.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/13/apache-13.pom (14 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-help-plugin/2.2/maven-help-plugin-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-help-plugin/2.2/maven-help-plugin-2.2.jar (68 kB at 929 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/xml-maven-plugin/1.0/xml-maven-plugin-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/xml-maven-plugin/1.0/xml-maven-plugin-1.0.pom (7.3 kB at 93 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/mojo-parent/28/mojo-parent-28.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/mojo-parent/28/mojo-parent-28.pom (26 kB at 3.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/codehaus-parent/3/codehaus-parent-3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/codehaus-parent/3/codehaus-parent-3.pom (4.1 kB at 684 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/xml-maven-plugin/1.0/xml-maven-plugin-1.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/xml-maven-plugin/1.0/xml-maven-plugin-1.0.jar (34 kB at 447 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/build-helper-maven-plugin/1.10/build-helper-maven-plugin-1.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/build-helper-maven-plugin/1.10/build-helper-maven-plugin-1.10.pom (5.8 kB at 728 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/mojo-parent/38/mojo-parent-38.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/mojo-parent/38/mojo-parent-38.pom (33 kB at 430 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/build-helper-maven-plugin/1.10/build-helper-maven-plugin-1.10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/build-helper-maven-plugin/1.10/build-helper-maven-plugin-1.10.jar (51 kB at 6.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-maven-plugin/1.5.9.RELEASE/spring-boot-maven-plugin-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-maven-plugin/1.5.9.RELEASE/spring-boot-maven-plugin-1.5.9.RELEASE.pom (6.7 kB at 82 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-tools/1.5.9.RELEASE/spring-boot-tools-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-tools/1.5.9.RELEASE/spring-boot-tools-1.5.9.RELEASE.pom (1.3 kB at 73 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-parent/1.5.9.RELEASE/spring-boot-parent-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-parent/1.5.9.RELEASE/spring-boot-parent-1.5.9.RELEASE.pom (28 kB at 377 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-maven-plugin/1.5.9.RELEASE/spring-boot-maven-plugin-1.5.9.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-maven-plugin/1.5.9.RELEASE/spring-boot-maven-plugin-1.5.9.RELEASE.jar (66 kB at 4.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-jar-plugin/2.6/maven-jar-plugin-2.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-jar-plugin/2.6/maven-jar-plugin-2.6.pom (5.9 kB at 100 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/27/maven-plugins-27.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/27/maven-plugins-27.pom (11 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/26/maven-parent-26.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/26/maven-parent-26.pom (40 kB at 6.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/16/apache-16.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/16/apache-16.pom (15 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-jar-plugin/2.6/maven-jar-plugin-2.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-jar-plugin/2.6/maven-jar-plugin-2.6.jar (26 kB at 5.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-clean-plugin/2.6.1/maven-clean-plugin-2.6.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-clean-plugin/2.6.1/maven-clean-plugin-2.6.1.pom (5.0 kB at 709 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/26/maven-plugins-26.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/26/maven-plugins-26.pom (11 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/25/maven-parent-25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/25/maven-parent-25.pom (37 kB at 6.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/15/apache-15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/15/apache-15.pom (15 kB at 2.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-clean-plugin/2.6.1/maven-clean-plugin-2.6.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-clean-plugin/2.6.1/maven-clean-plugin-2.6.1.jar (29 kB at 4.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-resources-plugin/2.6/maven-resources-plugin-2.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-resources-plugin/2.6/maven-resources-plugin-2.6.pom (8.1 kB at 451 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/23/maven-plugins-23.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/23/maven-plugins-23.pom (9.2 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/22/maven-parent-22.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/22/maven-parent-22.pom (30 kB at 480 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/11/apache-11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/11/apache-11.pom (15 kB at 644 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-resources-plugin/2.6/maven-resources-plugin-2.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-resources-plugin/2.6/maven-resources-plugin-2.6.jar (30 kB at 422 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-compiler-plugin/3.1/maven-compiler-plugin-3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-compiler-plugin/3.1/maven-compiler-plugin-3.1.pom (10 kB at 601 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-compiler-plugin/3.1/maven-compiler-plugin-3.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-compiler-plugin/3.1/maven-compiler-plugin-3.1.jar (43 kB at 693 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-surefire-plugin/2.18.1/maven-surefire-plugin-2.18.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-surefire-plugin/2.18.1/maven-surefire-plugin-2.18.1.pom (5.7 kB at 809 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire/2.18.1/surefire-2.18.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire/2.18.1/surefire-2.18.1.pom (17 kB at 240 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-surefire-plugin/2.18.1/maven-surefire-plugin-2.18.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-surefire-plugin/2.18.1/maven-surefire-plugin-2.18.1.jar (37 kB at 3.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-install-plugin/2.5.2/maven-install-plugin-2.5.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-install-plugin/2.5.2/maven-install-plugin-2.5.2.pom (6.4 kB at 93 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/25/maven-plugins-25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/25/maven-plugins-25.pom (9.6 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/24/maven-parent-24.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/24/maven-parent-24.pom (37 kB at 543 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/14/apache-14.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/14/apache-14.pom (15 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-install-plugin/2.5.2/maven-install-plugin-2.5.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-install-plugin/2.5.2/maven-install-plugin-2.5.2.jar (33 kB at 442 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-deploy-plugin/2.8.2/maven-deploy-plugin-2.8.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-deploy-plugin/2.8.2/maven-deploy-plugin-2.8.2.pom (7.1 kB at 649 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-deploy-plugin/2.8.2/maven-deploy-plugin-2.8.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-deploy-plugin/2.8.2/maven-deploy-plugin-2.8.2.jar (34 kB at 397 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-site-plugin/3.5.1/maven-site-plugin-3.5.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-site-plugin/3.5.1/maven-site-plugin-3.5.1.pom (18 kB at 997 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/28/maven-plugins-28.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-plugins/28/maven-plugins-28.pom (12 kB at 174 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/27/maven-parent-27.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/27/maven-parent-27.pom (41 kB at 2.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/17/apache-17.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/17/apache-17.pom (16 kB at 2.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-site-plugin/3.5.1/maven-site-plugin-3.5.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-site-plugin/3.5.1/maven-site-plugin-3.5.1.jar (132 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jooq/jooq-codegen-maven/3.9.6/jooq-codegen-maven-3.9.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jooq/jooq-codegen-maven/3.9.6/jooq-codegen-maven-3.9.6.pom (3.0 kB at 381 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jooq/jooq-parent/3.9.6/jooq-parent-3.9.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jooq/jooq-parent/3.9.6/jooq-parent-3.9.6.pom (9.8 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/oss/oss-parent/7/oss-parent-7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/oss/oss-parent/7/oss-parent-7.pom (4.8 kB at 60 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jooq/jooq-codegen-maven/3.9.6/jooq-codegen-maven-3.9.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jooq/jooq-codegen-maven/3.9.6/jooq-codegen-maven-3.9.6.jar (16 kB at 226 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-antrun-plugin/1.8/maven-antrun-plugin-1.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-antrun-plugin/1.8/maven-antrun-plugin-1.8.pom (3.3 kB at 552 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-antrun-plugin/1.8/maven-antrun-plugin-1.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-antrun-plugin/1.8/maven-antrun-plugin-1.8.jar (36 kB at 5.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-assembly-plugin/2.6/maven-assembly-plugin-2.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-assembly-plugin/2.6/maven-assembly-plugin-2.6.pom (16 kB at 209 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-assembly-plugin/2.6/maven-assembly-plugin-2.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-assembly-plugin/2.6/maven-assembly-plugin-2.6.jar (246 kB at 3.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-dependency-plugin/2.10/maven-dependency-plugin-2.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-dependency-plugin/2.10/maven-dependency-plugin-2.10.pom (12 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-dependency-plugin/2.10/maven-dependency-plugin-2.10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-dependency-plugin/2.10/maven-dependency-plugin-2.10.jar (160 kB at 16 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-release-plugin/2.5.3/maven-release-plugin-2.5.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-release-plugin/2.5.3/maven-release-plugin-2.5.3.pom (11 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/release/maven-release/2.5.3/maven-release-2.5.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/release/maven-release/2.5.3/maven-release-2.5.3.pom (5.0 kB at 71 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-release-plugin/2.5.3/maven-release-plugin-2.5.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-release-plugin/2.5.3/maven-release-plugin-2.5.3.jar (53 kB at 7.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-eclipse-plugin/2.10/maven-eclipse-plugin-2.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-eclipse-plugin/2.10/maven-eclipse-plugin-2.10.pom (19 kB at 2.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-eclipse-plugin/2.10/maven-eclipse-plugin-2.10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-eclipse-plugin/2.10/maven-eclipse-plugin-2.10.jar (224 kB at 2.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-enforcer-plugin/1.4/maven-enforcer-plugin-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-enforcer-plugin/1.4/maven-enforcer-plugin-1.4.pom (7.3 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/enforcer/enforcer/1.4/enforcer-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/enforcer/enforcer/1.4/enforcer-1.4.pom (7.2 kB at 716 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-enforcer-plugin/1.4/maven-enforcer-plugin-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-enforcer-plugin/1.4/maven-enforcer-plugin-1.4.jar (31 kB at 392 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-failsafe-plugin/2.18.1/maven-failsafe-plugin-2.18.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-failsafe-plugin/2.18.1/maven-failsafe-plugin-2.18.1.pom (9.0 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-failsafe-plugin/2.18.1/maven-failsafe-plugin-2.18.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-failsafe-plugin/2.18.1/maven-failsafe-plugin-2.18.1.jar (82 kB at 12 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-invoker-plugin/1.10/maven-invoker-plugin-1.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-invoker-plugin/1.10/maven-invoker-plugin-1.10.pom (11 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-invoker-plugin/1.10/maven-invoker-plugin-1.10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-invoker-plugin/1.10/maven-invoker-plugin-1.10.jar (105 kB at 13 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-javadoc-plugin/2.10.4/maven-javadoc-plugin-2.10.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-javadoc-plugin/2.10.4/maven-javadoc-plugin-2.10.4.pom (16 kB at 2.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-javadoc-plugin/2.10.4/maven-javadoc-plugin-2.10.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-javadoc-plugin/2.10.4/maven-javadoc-plugin-2.10.4.jar (415 kB at 41 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.4.3/maven-shade-plugin-2.4.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.4.3/maven-shade-plugin-2.4.3.pom (8.5 kB at 404 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.4.3/maven-shade-plugin-2.4.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.4.3/maven-shade-plugin-2.4.3.jar (104 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-source-plugin/2.4/maven-source-plugin-2.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-source-plugin/2.4/maven-source-plugin-2.4.pom (6.6 kB at 664 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-source-plugin/2.4/maven-source-plugin-2.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-source-plugin/2.4/maven-source-plugin-2.4.jar (31 kB at 3.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-war-plugin/2.6/maven-war-plugin-2.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-war-plugin/2.6/maven-war-plugin-2.6.pom (8.2 kB at 822 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-war-plugin/2.6/maven-war-plugin-2.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-war-plugin/2.6/maven-war-plugin-2.6.jar (88 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/exec-maven-plugin/1.5.0/exec-maven-plugin-1.5.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/exec-maven-plugin/1.5.0/exec-maven-plugin-1.5.0.pom (12 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/exec-maven-plugin/1.5.0/exec-maven-plugin-1.5.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/exec-maven-plugin/1.5.0/exec-maven-plugin-1.5.0.jar (53 kB at 3.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/versions-maven-plugin/2.2/versions-maven-plugin-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/versions-maven-plugin/2.2/versions-maven-plugin-2.2.pom (16 kB at 2.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/mojo-parent/34/mojo-parent-34.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/mojo-parent/34/mojo-parent-34.pom (24 kB at 3.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/codehaus-parent/4/codehaus-parent-4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/codehaus-parent/4/codehaus-parent-4.pom (4.8 kB at 482 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/versions-maven-plugin/2.2/versions-maven-plugin-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/mojo/versions-maven-plugin/2.2/versions-maven-plugin-2.2.jar (256 kB at 4.2 MB/s)
[INFO] 
[INFO] --------------------< com.ica10888:jenkins-x-demo >---------------------
[INFO] Building jenkins-x-demo 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- versions-maven-plugin:2.2:set (default-cli) @ jenkins-x-demo ---
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.2.1/maven-artifact-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.2.1/maven-artifact-2.2.1.pom (1.6 kB at 226 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.2.1/maven-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.2.1/maven-2.2.1.pom (22 kB at 4.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/11/maven-parent-11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/11/maven-parent-11.pom (32 kB at 4.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/5/apache-5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/5/apache-5.pom (4.1 kB at 683 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.15/plexus-utils-1.5.15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.15/plexus-utils-1.5.15.pom (6.8 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.2/plexus-2.0.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.2/plexus-2.0.2.pom (12 kB at 2.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.2.1/maven-artifact-manager-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.2.1/maven-artifact-manager-2.2.1.pom (3.1 kB at 517 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.2.1/maven-repository-metadata-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.2.1/maven-repository-metadata-2.2.1.pom (1.9 kB at 374 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-9-stable-1/plexus-container-default-1.0-alpha-9-stable-1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-9-stable-1/plexus-container-default-1.0-alpha-9-stable-1.pom (3.9 kB at 25 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.0.3/plexus-containers-1.0.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.0.3/plexus-containers-1.0.3.pom (492 B at 70 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.4/plexus-1.0.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.4/plexus-1.0.4.pom (5.7 kB at 956 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/4.11/junit-4.11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/4.11/junit-4.11.pom (2.3 kB at 391 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.pom (766 B at 153 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-parent/1.3/hamcrest-parent-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-parent/1.3/hamcrest-parent-1.3.pom (2.0 kB at 329 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.0.4/plexus-utils-1.0.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.0.4/plexus-utils-1.0.4.pom (6.9 kB at 143 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/classworlds/classworlds/1.1-alpha-2/classworlds-1.1-alpha-2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/classworlds/classworlds/1.1-alpha-2/classworlds-1.1-alpha-2.pom (3.1 kB at 447 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/backport-util-concurrent/backport-util-concurrent/3.1/backport-util-concurrent-3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/backport-util-concurrent/backport-util-concurrent/3.1/backport-util-concurrent-3.1.pom (880 B at 147 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.2.1/maven-core-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.2.1/maven-core-2.2.1.pom (12 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.2.1/maven-settings-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.2.1/maven-settings-2.2.1.pom (2.2 kB at 311 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.2.1/maven-model-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.2.1/maven-model-2.2.1.pom (3.2 kB at 540 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.11/plexus-interpolation-1.11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.11/plexus-interpolation-1.11.pom (889 B at 111 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.14/plexus-components-1.1.14.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.14/plexus-components-1.1.14.pom (5.8 kB at 649 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.2.1/maven-plugin-parameter-documenter-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.2.1/maven-plugin-parameter-documenter-2.2.1.pom (2.0 kB at 17 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.pom (1.9 kB at 380 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-parent/1.5.6/slf4j-parent-1.5.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-parent/1.5.6/slf4j-parent-1.5.6.pom (7.9 kB at 141 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.5.6/slf4j-api-1.5.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.5.6/slf4j-api-1.5.6.pom (3.0 kB at 597 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.5.6/jcl-over-slf4j-1.5.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.5.6/jcl-over-slf4j-1.5.6.pom (2.2 kB at 241 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.2.1/maven-reporting-api-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.2.1/maven-reporting-api-2.2.1.pom (1.9 kB at 463 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting/2.2.1/maven-reporting-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting/2.2.1/maven-reporting-2.2.1.pom (1.4 kB at 361 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.1/doxia-sink-api-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.1/doxia-sink-api-1.1.pom (2.0 kB at 255 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.1/doxia-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.1/doxia-1.1.pom (15 kB at 2.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.1/doxia-logging-api-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.1/doxia-logging-api-1.1.pom (1.6 kB at 225 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-30/plexus-container-default-1.0-alpha-30.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-30/plexus-container-default-1.0-alpha-30.pom (3.5 kB at 46 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.0-alpha-30/plexus-containers-1.0-alpha-30.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.0-alpha-30/plexus-containers-1.0-alpha-30.pom (1.9 kB at 271 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.11/plexus-1.0.11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.11/plexus-1.0.11.pom (9.0 kB at 1.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4.5/plexus-utils-1.4.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4.5/plexus-utils-1.4.5.pom (2.3 kB at 453 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/1.2-alpha-9/plexus-classworlds-1.2-alpha-9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/1.2-alpha-9/plexus-classworlds-1.2-alpha-9.pom (3.2 kB at 460 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.10/plexus-1.0.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.10/plexus-1.0.10.pom (8.2 kB at 2.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.2.1/maven-profile-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.2.1/maven-profile-2.2.1.pom (2.2 kB at 543 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.2.1/maven-error-diagnostics-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.2.1/maven-error-diagnostics-2.2.1.pom (1.7 kB at 342 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.2.1/maven-project-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.2.1/maven-project-2.2.1.pom (2.8 kB at 693 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.2.1/maven-plugin-registry-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.2.1/maven-plugin-registry-2.2.1.pom (1.9 kB at 12 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.2/commons-cli-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.2/commons-cli-1.2.pom (8.0 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/11/commons-parent-11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/11/commons-parent-11.pom (25 kB at 4.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/4/apache-4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/4/apache-4.pom (4.5 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.2.1/maven-plugin-api-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.2.1/maven-plugin-api-2.2.1.pom (1.5 kB at 292 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.2.1/maven-plugin-descriptor-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.2.1/maven-plugin-descriptor-2.2.1.pom (2.1 kB at 516 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-4/plexus-interactivity-api-1.0-alpha-4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-4/plexus-interactivity-api-1.0-alpha-4.pom (7.1 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.2.1/maven-monitor-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.2.1/maven-monitor-2.2.1.pom (1.3 kB at 251 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/classworlds/classworlds/1.1/classworlds-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/classworlds/classworlds/1.1/classworlds-1.1.pom (3.3 kB at 554 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-sec-dispatcher/1.3/plexus-sec-dispatcher-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-sec-dispatcher/1.3/plexus-sec-dispatcher-1.3.pom (3.0 kB at 370 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/12/spice-parent-12.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/12/spice-parent-12.pom (6.8 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/4/forge-parent-4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/4/forge-parent-4.pom (8.4 kB at 158 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.5/plexus-utils-1.5.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.5/plexus-utils-1.5.5.pom (5.1 kB at 858 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-cipher/1.4/plexus-cipher-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-cipher/1.4/plexus-cipher-1.4.pom (2.1 kB at 295 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/3.0/maven-reporting-api-3.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/3.0/maven-reporting-api-3.0.pom (2.4 kB at 475 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/15/maven-shared-components-15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/15/maven-shared-components-15.pom (9.3 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/16/maven-parent-16.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/16/maven-parent-16.pom (23 kB at 4.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/7/apache-7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/7/apache-7.pom (14 kB at 3.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.0/doxia-sink-api-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.0/doxia-sink-api-1.0.pom (1.4 kB at 24 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.0/doxia-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.0/doxia-1.0.pom (9.6 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/10/maven-parent-10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/10/maven-parent-10.pom (32 kB at 5.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-impl/2.2/maven-reporting-impl-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-impl/2.2/maven-reporting-impl-2.2.pom (4.7 kB at 946 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/17/maven-shared-components-17.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/17/maven-shared-components-17.pom (8.7 kB at 2.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/21/maven-parent-21.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/21/maven-parent-21.pom (26 kB at 3.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/10/apache-10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/10/apache-10.pom (15 kB at 3.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.2/doxia-sink-api-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.2/doxia-sink-api-1.2.pom (1.6 kB at 328 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.2/doxia-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.2/doxia-1.2.pom (19 kB at 3.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/19/maven-parent-19.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/19/maven-parent-19.pom (25 kB at 4.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.2/doxia-logging-api-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.2/doxia-logging-api-1.2.pom (1.6 kB at 27 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-core/1.2/doxia-core-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-core/1.2/doxia-core-1.2.pom (4.0 kB at 659 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/2.0.5/plexus-utils-2.0.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/2.0.5/plexus-utils-2.0.5.pom (3.3 kB at 834 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.6/plexus-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.6/plexus-2.0.6.pom (17 kB at 2.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xerces/xercesImpl/2.9.1/xercesImpl-2.9.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xerces/xercesImpl/2.9.1/xercesImpl-2.9.1.pom (1.4 kB at 341 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xml-apis/xml-apis/1.3.04/xml-apis-1.3.04.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xml-apis/xml-apis/1.3.04/xml-apis-1.3.04.pom (1.8 kB at 304 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/3/apache-3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/3/apache-3.pom (3.4 kB at 858 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.4/commons-lang-2.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.4/commons-lang-2.4.pom (14 kB at 3.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/9/commons-parent-9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/9/commons-parent-9.pom (22 kB at 5.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpclient/4.0.2/httpclient-4.0.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpclient/4.0.2/httpclient-4.0.2.pom (7.5 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcomponents-client/4.0.2/httpcomponents-client-4.0.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcomponents-client/4.0.2/httpcomponents-client-4.0.2.pom (9.0 kB at 1.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/project/4.1/project-4.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/project/4.1/project-4.1.pom (16 kB at 4.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcore/4.0.1/httpcore-4.0.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcore/4.0.1/httpcore-4.0.1.pom (4.9 kB at 76 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcomponents-core/4.0.1/httpcomponents-core-4.0.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcomponents-core/4.0.1/httpcomponents-core-4.0.1.pom (9.4 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/project/4.0/project-4.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/project/4.0/project-4.0.pom (13 kB at 3.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.1.1/commons-logging-1.1.1.pom (18 kB at 3.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/5/commons-parent-5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/5/commons-parent-5.pom (16 kB at 2.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-codec/commons-codec/1.3/commons-codec-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-codec/commons-codec/1.3/commons-codec-1.3.pom (6.1 kB at 103 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-site-renderer/1.2/doxia-site-renderer-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-site-renderer/1.2/doxia-site-renderer-1.2.pom (6.2 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sitetools/1.2/doxia-sitetools-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sitetools/1.2/doxia-sitetools-1.2.pom (16 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-decoration-model/1.2/doxia-decoration-model-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-decoration-model/1.2/doxia-decoration-model-1.2.pom (3.1 kB at 49 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-xhtml/1.2/doxia-module-xhtml-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-xhtml/1.2/doxia-module-xhtml-1.2.pom (1.8 kB at 196 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-modules/1.2/doxia-modules-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-modules/1.2/doxia-modules-1.2.pom (2.5 kB at 500 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-fml/1.2/doxia-module-fml-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-fml/1.2/doxia-module-fml-1.2.pom (5.6 kB at 82 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-i18n/1.0-beta-7/plexus-i18n-1.0-beta-7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-i18n/1.0-beta-7/plexus-i18n-1.0-beta-7.pom (1.1 kB at 176 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.12/plexus-components-1.1.12.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.12/plexus-components-1.1.12.pom (3.0 kB at 601 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4.1/plexus-utils-1.4.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4.1/plexus-utils-1.4.1.pom (1.9 kB at 382 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-velocity/1.1.7/plexus-velocity-1.1.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-velocity/1.1.7/plexus-velocity-1.1.7.pom (2.0 kB at 28 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-20/plexus-container-default-1.0-alpha-20.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-20/plexus-container-default-1.0-alpha-20.pom (3.0 kB at 498 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.0-alpha-20/plexus-containers-1.0-alpha-20.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.0-alpha-20/plexus-containers-1.0-alpha-20.pom (1.9 kB at 472 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.3/plexus-utils-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.3/plexus-utils-1.3.pom (1.0 kB at 258 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.8/plexus-1.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.8/plexus-1.0.8.pom (7.2 kB at 66 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/1.2-alpha-7/plexus-classworlds-1.2-alpha-7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/1.2-alpha-7/plexus-classworlds-1.2-alpha-7.pom (2.4 kB at 593 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.9/plexus-1.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/1.0.9/plexus-1.0.9.pom (7.7 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity/1.5/velocity-1.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity/1.5/velocity-1.5.pom (7.8 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.1/commons-collections-3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.1/commons-collections-3.1.pom (6.1 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.1/commons-lang-2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.1/commons-lang-2.1.pom (9.9 kB at 2.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/oro/oro/2.0.8/oro-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/oro/oro/2.0.8/oro-2.0.8.pom (140 B at 28 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.pom (13 kB at 3.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-validator/commons-validator/1.3.1/commons-validator-1.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-validator/commons-validator/1.3.1/commons-validator-1.3.1.pom (9.0 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.pom (357 B at 71 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0.3/commons-logging-1.0.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0.3/commons-logging-1.0.3.pom (866 B at 216 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-digester/commons-digester/1.6/commons-digester-1.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-digester/commons-digester/1.6/commons-digester-1.6.pom (974 B at 244 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-beanutils/commons-beanutils/1.6/commons-beanutils-1.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-beanutils/commons-beanutils/1.6/commons-beanutils-1.6.pom (2.3 kB at 578 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0/commons-logging-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0/commons-logging-1.0.pom (163 B at 41 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/2.0/commons-collections-2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/2.0/commons-collections-2.0.pom (171 B at 43 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/2.1/commons-collections-2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/2.1/commons-collections-2.1.pom (3.3 kB at 477 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xml-apis/xml-apis/1.0.b2/xml-apis-1.0.b2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xml-apis/xml-apis/1.0.b2/xml-apis-1.0.b2.pom (2.2 kB at 562 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.pom (5.3 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.8/plexus-utils-1.5.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.8/plexus-utils-1.5.8.pom (8.1 kB at 576 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-common-artifact-filters/1.4/maven-common-artifact-filters-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-common-artifact-filters/1.4/maven-common-artifact-filters-1.4.pom (3.8 kB at 940 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.8/maven-artifact-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.8/maven-artifact-2.0.8.pom (1.6 kB at 405 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.0.8/maven-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.0.8/maven-2.0.8.pom (12 kB at 3.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/6/maven-parent-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/6/maven-parent-6.pom (20 kB at 5.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4.6/plexus-utils-1.4.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4.6/plexus-utils-1.4.6.pom (2.3 kB at 453 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.8/maven-model-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.8/maven-model-2.0.8.pom (3.1 kB at 449 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.8/maven-project-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.8/maven-project-2.0.8.pom (2.7 kB at 542 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.8/maven-settings-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.8/maven-settings-2.0.8.pom (2.1 kB at 343 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.8/maven-profile-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.8/maven-profile-2.0.8.pom (2.0 kB at 509 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.8/maven-artifact-manager-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.8/maven-artifact-manager-2.0.8.pom (2.7 kB at 386 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.8/maven-repository-metadata-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.8/maven-repository-metadata-2.0.8.pom (1.9 kB at 317 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.8/maven-plugin-registry-2.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.8/maven-plugin-registry-2.0.8.pom (2.0 kB at 400 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.5.5/plexus-container-default-1.5.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.5.5/plexus-container-default-1.5.5.pom (2.8 kB at 459 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.5.5/plexus-containers-1.5.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.5.5/plexus-containers-1.5.5.pom (4.2 kB at 151 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.7/plexus-2.0.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.7/plexus-2.0.7.pom (17 kB at 2.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.2.2/plexus-classworlds-2.2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.2.2/plexus-classworlds-2.2.2.pom (4.0 kB at 41 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.3/plexus-2.0.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.3/plexus-2.0.3.pom (15 kB at 2.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/xbean/xbean-reflect/3.4/xbean-reflect-3.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/xbean/xbean-reflect/3.4/xbean-reflect-3.4.pom (2.8 kB at 468 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/xbean/xbean/3.4/xbean-3.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/xbean/xbean/3.4/xbean-3.4.pom (19 kB at 2.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.12/log4j-1.2.12.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.12/log4j-1.2.12.pom (145 B at 2.5 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging-api/1.1/commons-logging-api-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging-api/1.1/commons-logging-api-1.1.pom (5.3 kB at 382 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/collections/google-collections/1.0/google-collections-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/collections/google-collections/1.0/google-collections-1.0.pom (2.5 kB at 124 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/google/1/google-1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/google/1/google-1.pom (1.6 kB at 311 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/2.1/plexus-utils-2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/2.1/plexus-utils-2.1.pom (4.0 kB at 403 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/16/spice-parent-16.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/16/spice-parent-16.pom (8.4 kB at 178 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/5/forge-parent-5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/5/forge-parent-5.pom (8.4 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/2.5/wagon-provider-api-2.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/2.5/wagon-provider-api-2.5.pom (1.7 kB at 288 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon/2.5/wagon-2.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon/2.5/wagon-2.5.pom (20 kB at 3.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.8/plexus-utils-3.0.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.8/plexus-utils-3.0.8.pom (3.1 kB at 262 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/3.2/plexus-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/3.2/plexus-3.2.pom (19 kB at 2.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/17/spice-parent-17.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/17/spice-parent-17.pom (6.8 kB at 46 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/10/forge-parent-10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/10/forge-parent-10.pom (14 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-file/2.5/wagon-file-2.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-file/2.5/wagon-file-2.5.pom (1.6 kB at 401 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-providers/2.5/wagon-providers-2.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-providers/2.5/wagon-providers-2.5.pom (2.7 kB at 545 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.6/commons-lang-2.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.6/commons-lang-2.6.pom (17 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/17/commons-parent-17.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/17/commons-parent-17.pom (31 kB at 6.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-core/1.4/doxia-core-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-core/1.4/doxia-core-1.4.pom (4.1 kB at 34 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.4/doxia-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.4/doxia-1.4.pom (18 kB at 2.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.4/doxia-sink-api-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.4/doxia-sink-api-1.4.pom (1.5 kB at 31 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.4/doxia-logging-api-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.4/doxia-logging-api-1.4.pom (1.5 kB at 256 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.10/plexus-utils-3.0.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.10/plexus-utils-3.0.10.pom (3.1 kB at 262 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/3.3/plexus-3.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/3.3/plexus-3.3.pom (20 kB at 4.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.pom (815 B at 102 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-site-renderer/1.4/doxia-site-renderer-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-site-renderer/1.4/doxia-site-renderer-1.4.pom (6.1 kB at 873 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sitetools/1.4/doxia-sitetools-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sitetools/1.4/doxia-sitetools-1.4.pom (17 kB at 3.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-decoration-model/1.4/doxia-decoration-model-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-decoration-model/1.4/doxia-decoration-model-1.4.pom (2.7 kB at 668 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-xhtml/1.4/doxia-module-xhtml-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-xhtml/1.4/doxia-module-xhtml-1.4.pom (1.6 kB at 408 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-modules/1.4/doxia-modules-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-modules/1.4/doxia-modules-1.4.pom (2.6 kB at 524 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-fml/1.4/doxia-module-fml-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-fml/1.4/doxia-module-fml-1.4.pom (4.8 kB at 686 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity-tools/2.0/velocity-tools-2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity-tools/2.0/velocity-tools-2.0.pom (18 kB at 3.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-digester/commons-digester/1.8/commons-digester-1.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-digester/commons-digester/1.8/commons-digester-1.8.pom (7.0 kB at 1.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.1/commons-logging-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.1/commons-logging-1.1.pom (6.2 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/logkit/logkit/1.0.1/logkit-1.0.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/logkit/logkit/1.0.1/logkit-1.0.1.pom (147 B at 37 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/avalon-framework/avalon-framework/4.1.3/avalon-framework-4.1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/avalon-framework/avalon-framework/4.1.3/avalon-framework-4.1.3.pom (167 B at 28 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/servlet/servlet-api/2.3/servlet-api-2.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/servlet/servlet-api/2.3/servlet-api-2.3.pom (156 B at 26 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-chain/commons-chain/1.1/commons-chain-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-chain/commons-chain/1.1/commons-chain-1.1.pom (6.0 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.2/commons-collections-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.2/commons-collections-3.2.pom (11 kB at 2.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/dom4j/dom4j/1.1/dom4j-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/dom4j/dom4j/1.1/dom4j-1.1.pom (142 B at 36 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/sslext/sslext/1.2-0/sslext-1.2-0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/sslext/sslext/1.2-0/sslext-1.2-0.pom (653 B at 163 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-core/1.3.8/struts-core-1.3.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-core/1.3.8/struts-core-1.3.8.pom (4.3 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-parent/1.3.8/struts-parent-1.3.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-parent/1.3.8/struts-parent-1.3.8.pom (9.8 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-master/4/struts-master-4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-master/4/struts-master-4.pom (12 kB at 2.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/2/apache-2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/2/apache-2.pom (3.4 kB at 426 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/antlr/antlr/2.7.2/antlr-2.7.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/antlr/antlr/2.7.2/antlr-2.7.2.pom (145 B at 29 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-taglib/1.3.8/struts-taglib-1.3.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-taglib/1.3.8/struts-taglib-1.3.8.pom (3.1 kB at 615 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-tiles/1.3.8/struts-tiles-1.3.8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-tiles/1.3.8/struts-tiles-1.3.8.pom (2.9 kB at 484 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity/1.6.2/velocity-1.6.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity/1.6.2/velocity-1.6.2.pom (11 kB at 2.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.20/plexus-utils-3.0.20.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.20/plexus-utils-3.0.20.pom (3.8 kB at 762 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/3.3.1/plexus-3.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/3.3.1/plexus-3.3.1.pom (20 kB at 4.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-6/plexus-interactivity-api-1.0-alpha-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-6/plexus-interactivity-api-1.0-alpha-6.pom (726 B at 121 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity/1.0-alpha-6/plexus-interactivity-1.0-alpha-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity/1.0-alpha-6/plexus-interactivity-1.0-alpha-6.pom (1.1 kB at 268 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.9/plexus-components-1.1.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.9/plexus-components-1.1.9.pom (2.4 kB at 304 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4/plexus-utils-1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.4/plexus-utils-1.4.pom (1.2 kB at 288 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-i18n/1.0-beta-10/plexus-i18n-1.0-beta-10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-i18n/1.0-beta-10/plexus-i18n-1.0-beta-10.pom (2.1 kB at 519 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/woodstox-core-asl/4.2.0/woodstox-core-asl-4.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/woodstox-core-asl/4.2.0/woodstox-core-asl-4.2.0.pom (1.8 kB at 451 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/xml/stream/stax-api/1.0-2/stax-api-1.0-2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/xml/stream/stax-api/1.0-2/stax-api-1.0-2.pom (962 B at 240 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/stax2-api/3.1.1/stax2-api-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/stax2-api/3.1.1/stax2-api-3.1.1.pom (1.4 kB at 15 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.2.1/maven-artifact-2.2.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.2.1/maven-artifact-manager-2.2.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/backport-util-concurrent/backport-util-concurrent/3.1/backport-util-concurrent-3.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.2.1/maven-repository-metadata-2.2.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.2.1/maven-core-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.2.1/maven-artifact-2.2.1.jar (80 kB at 8.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.2.1/maven-plugin-parameter-documenter-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.2.1/maven-core-2.2.1.jar (178 kB at 903 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.2.1/maven-plugin-parameter-documenter-2.2.1.jar (22 kB at 113 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.5.6/slf4j-api-1.5.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.2.1/maven-repository-metadata-2.2.1.jar (26 kB at 128 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.2.1/maven-artifact-manager-2.2.1.jar (68 kB at 333 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.5.6/jcl-over-slf4j-1.5.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.2.1/maven-profile-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-jdk14/1.5.6/slf4j-jdk14-1.5.6.jar (8.8 kB at 31 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.2.1/maven-error-diagnostics-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.2.1/maven-profile-2.2.1.jar (35 kB at 122 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.2/commons-cli-1.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/backport-util-concurrent/backport-util-concurrent/3.1/backport-util-concurrent-3.1.jar (332 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.2.1/maven-monitor-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.2.1/maven-error-diagnostics-2.2.1.jar (13 kB at 44 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/classworlds/classworlds/1.1/classworlds-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.5.6/jcl-over-slf4j-1.5.6.jar (17 kB at 59 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.5.6/slf4j-api-1.5.6.jar (22 kB at 76 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-cipher/1.4/plexus-cipher-1.4.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-sec-dispatcher/1.3/plexus-sec-dispatcher-1.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.2.1/maven-monitor-2.2.1.jar (10 kB at 35 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.2.1/maven-model-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.2/commons-cli-1.2.jar (41 kB at 136 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/classworlds/classworlds/1.1/classworlds-1.1.jar (38 kB at 125 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.2.1/maven-plugin-descriptor-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-cipher/1.4/plexus-cipher-1.4.jar (13 kB at 44 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.2.1/maven-settings-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.2.1/maven-plugin-descriptor-2.2.1.jar (39 kB at 105 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.11/plexus-interpolation-1.11.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.2.1/maven-plugin-api-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-sec-dispatcher/1.3/plexus-sec-dispatcher-1.3.jar (29 kB at 76 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.11/plexus-interpolation-1.11.jar (51 kB at 134 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.2.1/maven-project-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.2.1/maven-settings-2.2.1.jar (49 kB at 128 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.2.1/maven-plugin-registry-2.2.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/3.0/maven-reporting-api-3.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.2.1/maven-plugin-api-2.2.1.jar (12 kB at 32 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-impl/2.2/maven-reporting-impl-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.2.1/maven-model-2.2.1.jar (88 kB at 227 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-validator/commons-validator/1.3.1/commons-validator-1.3.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-impl/2.2/maven-reporting-impl-2.2.jar (17 kB at 43 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.2.1/maven-project-2.2.1.jar (156 kB at 399 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-digester/commons-digester/1.6/commons-digester-1.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.2.1/maven-plugin-registry-2.2.1.jar (30 kB at 76 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/3.0/maven-reporting-api-3.0.jar (11 kB at 28 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-common-artifact-filters/1.4/maven-common-artifact-filters-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.jar (189 kB at 400 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/2.5/wagon-provider-api-2.5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-common-artifact-filters/1.4/maven-common-artifact-filters-1.4.jar (32 kB at 66 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-file/2.5/wagon-file-2.5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.0.4/commons-logging-1.0.4.jar (38 kB at 80 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-core/1.4/doxia-core-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/2.5/wagon-provider-api-2.5.jar (53 kB at 87 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.4/doxia-logging-api-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-digester/commons-digester/1.6/commons-digester-1.6.jar (168 kB at 278 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-validator/commons-validator/1.3.1/commons-validator-1.3.1.jar (139 kB at 229 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xerces/xercesImpl/2.9.1/xercesImpl-2.9.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.4/doxia-logging-api-1.4.jar (11 kB at 19 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xml-apis/xml-apis/1.3.04/xml-apis-1.3.04.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-file/2.5/wagon-file-2.5.jar (11 kB at 18 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpclient/4.0.2/httpclient-4.0.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.jar (4.2 kB at 6.3 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-codec/commons-codec/1.3/commons-codec-1.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-core/1.4/doxia-core-1.4.jar (165 kB at 244 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcore/4.0.1/httpcore-4.0.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xml-apis/xml-apis/1.3.04/xml-apis-1.3.04.jar (194 kB at 287 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.4/doxia-sink-api-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.4/doxia-sink-api-1.4.jar (11 kB at 16 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-site-renderer/1.4/doxia-site-renderer-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-codec/commons-codec/1.3/commons-codec-1.3.jar (47 kB at 68 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-decoration-model/1.4/doxia-decoration-model-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpclient/4.0.2/httpclient-4.0.2.jar (293 kB at 424 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-xhtml/1.4/doxia-module-xhtml-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/xerces/xercesImpl/2.9.1/xercesImpl-2.9.1.jar (1.2 MB at 1.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-fml/1.4/doxia-module-fml-1.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-site-renderer/1.4/doxia-site-renderer-1.4.jar (53 kB at 76 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-decoration-model/1.4/doxia-decoration-model-1.4.jar (61 kB at 88 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity/1.5/velocity-1.5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-xhtml/1.4/doxia-module-xhtml-1.4.jar (15 kB at 22 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-velocity/1.1.7/plexus-velocity-1.1.7.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/oro/oro/2.0.8/oro-2.0.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/httpcomponents/httpcore/4.0.1/httpcore-4.0.1.jar (173 kB at 224 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity-tools/2.0/velocity-tools-2.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-module-fml/1.4/doxia-module-fml-1.4.jar (38 kB at 49 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-chain/commons-chain/1.1/commons-chain-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/oro/oro/2.0.8/oro-2.0.8.jar (65 kB at 84 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/dom4j/dom4j/1.1/dom4j-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity/1.5/velocity-1.5.jar (392 kB at 501 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/sslext/sslext/1.2-0/sslext-1.2-0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-chain/commons-chain/1.1/commons-chain-1.1.jar (90 kB at 115 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-core/1.3.8/struts-core-1.3.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-velocity/1.1.7/plexus-velocity-1.1.7.jar (7.7 kB at 9.8 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/antlr/antlr/2.7.2/antlr-2.7.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/velocity/velocity-tools/2.0/velocity-tools-2.0.jar (347 kB at 440 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-taglib/1.3.8/struts-taglib-1.3.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/dom4j/dom4j/1.1/dom4j-1.1.jar (457 kB at 574 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-tiles/1.3.8/struts-tiles-1.3.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/sslext/sslext/1.2-0/sslext-1.2-0.jar (26 kB at 33 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-tiles/1.3.8/struts-tiles-1.3.8.jar (120 kB at 137 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.20/plexus-utils-3.0.20.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-taglib/1.3.8/struts-taglib-1.3.8.jar (252 kB at 287 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.5.5/plexus-container-default-1.5.5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/antlr/antlr/2.7.2/antlr-2.7.2.jar (358 kB at 408 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.2.2/plexus-classworlds-2.2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/struts/struts-core/1.3.8/struts-core-1.3.8.jar (329 kB at 373 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/xbean/xbean-reflect/3.4/xbean-reflect-3.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/3.0.20/plexus-utils-3.0.20.jar (243 kB at 241 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.12/log4j-1.2.12.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-collections/commons-collections/3.2.1/commons-collections-3.2.1.jar (575 kB at 571 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging-api/1.1/commons-logging-api-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.2.2/plexus-classworlds-2.2.2.jar (46 kB at 46 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/xbean/xbean-reflect/3.4/xbean-reflect-3.4.jar (134 kB at 132 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/collections/google-collections/1.0/google-collections-1.0.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-6/plexus-interactivity-api-1.0-alpha-6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.5.5/plexus-container-default-1.5.5.jar (217 kB at 214 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-i18n/1.0-beta-10/plexus-i18n-1.0-beta-10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging-api/1.1/commons-logging-api-1.1.jar (45 kB at 44 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/woodstox-core-asl/4.2.0/woodstox-core-asl-4.2.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.12/log4j-1.2.12.jar (358 kB at 333 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-6/plexus-interactivity-api-1.0-alpha-6.jar (12 kB at 11 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/stax2-api/3.1.1/stax2-api-3.1.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/xml/stream/stax-api/1.0-2/stax-api-1.0-2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-i18n/1.0-beta-10/plexus-i18n-1.0-beta-10.jar (12 kB at 11 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.6/commons-lang-2.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/xml/stream/stax-api/1.0-2/stax-api-1.0-2.jar (23 kB at 22 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/stax2-api/3.1.1/stax2-api-3.1.1.jar (182 kB at 166 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-lang/commons-lang/2.6/commons-lang-2.6.jar (284 kB at 260 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/collections/google-collections/1.0/google-collections-1.0.jar (640 kB at 580 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/woodstox/woodstox-core-asl/4.2.0/woodstox-core-asl-4.2.0.jar (482 kB at 437 kB/s)
[INFO] Searching for local aggregator root...
[INFO] Local aggregation root: /home/jenkins/workspace/app-demo/demo
[INFO] Processing change of com.ica10888:jenkins-x-demo:0.0.1-SNAPSHOT -> 1.3.918-d-SNAPSHOT
[INFO] Processing com.ica10888:jenkins-x-demo
[INFO]     Updating project com.ica10888:jenkins-x-demo
[INFO]         from version 0.0.1-SNAPSHOT to 1.3.918-d-SNAPSHOT
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 19.379 s
[INFO] Finished at: 2018-11-16T05:24:21Z
[INFO] ------------------------------------------------------------------------
[0m[0m[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo/charts/demo
[Pipeline] {
[Pipeline] sh
[demo] Running shell script
+ sed -i -e 's/version:.*/version: 1.3.918-d-SNAPSHOT/' Chart.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e 's|repository: .*|repository: 10.68.22.54:5000/ica10888/demo|' values.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e 's/tag: .*/tag: 1.3.918-d-SNAPSHOT/' values.yaml
[Pipeline] }
[Pipeline] // dir
[Pipeline] sh
[demo] Running shell script
+ mvn -Dmaven.test.skip=true package
Picked up _JAVA_OPTIONS: -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Dsun.zip.disableMemoryMapping=true -XX:+UseParallelGC -XX:MinHeapFreeRatio=5 -XX:MaxHeapFreeRatio=10 -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -Xms10m -Xmx192m
[INFO] Scanning for projects...
[INFO] 
[INFO] --------------------< com.ica10888:jenkins-x-demo >---------------------
[INFO] Building jenkins-x-demo 1.3.918-d-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-web/1.5.9.RELEASE/spring-boot-starter-web-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-web/1.5.9.RELEASE/spring-boot-starter-web-1.5.9.RELEASE.pom (1.7 kB at 5.7 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starters/1.5.9.RELEASE/spring-boot-starters-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starters/1.5.9.RELEASE/spring-boot-starters-1.5.9.RELEASE.pom (6.4 kB at 402 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter/1.5.9.RELEASE/spring-boot-starter-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter/1.5.9.RELEASE/spring-boot-starter-1.5.9.RELEASE.pom (1.7 kB at 184 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot/1.5.9.RELEASE/spring-boot-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot/1.5.9.RELEASE/spring-boot-1.5.9.RELEASE.pom (10 kB at 917 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-core/4.3.13.RELEASE/spring-core-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-core/4.3.13.RELEASE/spring-core-4.3.13.RELEASE.pom (2.5 kB at 248 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-context/4.3.13.RELEASE/spring-context-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-context/4.3.13.RELEASE/spring-context-4.3.13.RELEASE.pom (5.0 kB at 70 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-aop/4.3.13.RELEASE/spring-aop-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-aop/4.3.13.RELEASE/spring-aop-4.3.13.RELEASE.pom (2.7 kB at 179 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-beans/4.3.13.RELEASE/spring-beans-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-beans/4.3.13.RELEASE/spring-beans-4.3.13.RELEASE.pom (2.5 kB at 34 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-expression/4.3.13.RELEASE/spring-expression-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-expression/4.3.13.RELEASE/spring-expression-4.3.13.RELEASE.pom (1.7 kB at 214 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-autoconfigure/1.5.9.RELEASE/spring-boot-autoconfigure-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-autoconfigure/1.5.9.RELEASE/spring-boot-autoconfigure-1.5.9.RELEASE.pom (21 kB at 2.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-logging/1.5.9.RELEASE/spring-boot-starter-logging-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-logging/1.5.9.RELEASE/spring-boot-starter-logging-1.5.9.RELEASE.pom (1.3 kB at 17 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.pom (13 kB at 167 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-parent/1.1.11/logback-parent-1.1.11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-parent/1.1.11/logback-parent-1.1.11.pom (18 kB at 2.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.pom (4.4 kB at 438 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.pom (3.8 kB at 426 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-parent/1.7.25/slf4j-parent-1.7.25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-parent/1.7.25/slf4j-parent-1.7.25.pom (14 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.7.25/jcl-over-slf4j-1.7.25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.7.25/jcl-over-slf4j-1.7.25.pom (959 B at 120 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.pom (986 B at 110 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/log4j-over-slf4j/1.7.25/log4j-over-slf4j-1.7.25.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/log4j-over-slf4j/1.7.25/log4j-over-slf4j-1.7.25.pom (1.1 kB at 141 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/yaml/snakeyaml/1.17/snakeyaml-1.17.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/yaml/snakeyaml/1.17/snakeyaml-1.17.pom (28 kB at 4.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-tomcat/1.5.9.RELEASE/spring-boot-starter-tomcat-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-tomcat/1.5.9.RELEASE/spring-boot-starter-tomcat-1.5.9.RELEASE.pom (1.3 kB at 190 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-core/8.5.23/tomcat-embed-core-8.5.23.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-core/8.5.23/tomcat-embed-core-8.5.23.pom (1.6 kB at 21 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/tomcat-annotations-api/8.5.23/tomcat-annotations-api-8.5.23.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/tomcat-annotations-api/8.5.23/tomcat-annotations-api-8.5.23.pom (1.3 kB at 190 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-el/8.5.23/tomcat-embed-el-8.5.23.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-el/8.5.23/tomcat-embed-el-8.5.23.pom (1.3 kB at 167 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.23/tomcat-embed-websocket-8.5.23.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.23/tomcat-embed-websocket-8.5.23.pom (1.6 kB at 21 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hibernate/hibernate-validator/5.3.6.Final/hibernate-validator-5.3.6.Final.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hibernate/hibernate-validator/5.3.6.Final/hibernate-validator-5.3.6.Final.pom (16 kB at 2.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hibernate/hibernate-validator-parent/5.3.6.Final/hibernate-validator-parent-5.3.6.Final.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hibernate/hibernate-validator-parent/5.3.6.Final/hibernate-validator-parent-5.3.6.Final.pom (44 kB at 5.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/arquillian/arquillian-bom/1.1.11.Final/arquillian-bom-1.1.11.Final.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/arquillian/arquillian-bom/1.1.11.Final/arquillian-bom-1.1.11.Final.pom (11 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/shrinkwrap/shrinkwrap-bom/1.2.3/shrinkwrap-bom-1.2.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/shrinkwrap/shrinkwrap-bom/1.2.3/shrinkwrap-bom-1.2.3.pom (4.0 kB at 664 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/shrinkwrap/resolver/shrinkwrap-resolver-bom/2.2.0/shrinkwrap-resolver-bom-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/shrinkwrap/resolver/shrinkwrap-resolver-bom/2.2.0/shrinkwrap-resolver-bom-2.2.0.pom (5.3 kB at 66 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/shrinkwrap/descriptors/shrinkwrap-descriptors-bom/2.0.0-alpha-8/shrinkwrap-descriptors-bom-2.0.0-alpha-8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/shrinkwrap/descriptors/shrinkwrap-descriptors-bom/2.0.0-alpha-8/shrinkwrap-descriptors-bom-2.0.0-alpha-8.pom (5.2 kB at 655 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.pom (7.9 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.pom (5.9 kB at 81 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/jboss-parent/15/jboss-parent-15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/jboss-parent/15/jboss-parent-15.pom (32 kB at 4.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/classmate/1.3.4/classmate-1.3.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/classmate/1.3.4/classmate-1.3.4.pom (5.8 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/oss-parent/24/oss-parent-24.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/oss-parent/24/oss-parent-24.pom (19 kB at 2.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-databind/2.8.10/jackson-databind-2.8.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-databind/2.8.10/jackson-databind-2.8.10.pom (5.4 kB at 905 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-annotations/2.8.0/jackson-annotations-2.8.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-annotations/2.8.0/jackson-annotations-2.8.0.pom (1.8 kB at 25 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-core/2.8.10/jackson-core-2.8.10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-core/2.8.10/jackson-core-2.8.10.pom (5.4 kB at 903 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-web/4.3.13.RELEASE/spring-web-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-web/4.3.13.RELEASE/spring-web-4.3.13.RELEASE.pom (8.0 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-webmvc/4.3.13.RELEASE/spring-webmvc-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-webmvc/4.3.13.RELEASE/spring-webmvc-4.3.13.RELEASE.pom (10.0 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-test/1.5.9.RELEASE/spring-boot-starter-test-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-test/1.5.9.RELEASE/spring-boot-starter-test-1.5.9.RELEASE.pom (3.1 kB at 448 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test/1.5.9.RELEASE/spring-boot-test-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test/1.5.9.RELEASE/spring-boot-test-1.5.9.RELEASE.pom (5.1 kB at 64 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test-autoconfigure/1.5.9.RELEASE/spring-boot-test-autoconfigure-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test-autoconfigure/1.5.9.RELEASE/spring-boot-test-autoconfigure-1.5.9.RELEASE.pom (5.7 kB at 73 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/jayway/jsonpath/json-path/2.2.0/json-path-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/jayway/jsonpath/json-path/2.2.0/json-path-2.2.0.pom (2.4 kB at 34 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/json-smart/2.2.1/json-smart-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/json-smart/2.2.1/json-smart-2.2.1.pom (12 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/accessors-smart/1.1/accessors-smart-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/accessors-smart/1.1/accessors-smart-1.1.pom (2.1 kB at 418 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/minidev-parent/2.2/minidev-parent-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/minidev-parent/2.2/minidev-parent-2.2.pom (9.1 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/asm/asm/5.0.3/asm-5.0.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/asm/asm/5.0.3/asm-5.0.3.pom (1.9 kB at 322 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/asm/asm-parent/5.0.3/asm-parent-5.0.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/asm/asm-parent/5.0.3/asm-parent-5.0.3.pom (5.5 kB at 916 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/ow2/1.3/ow2-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/ow2/1.3/ow2-1.3.pom (9.5 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/4.12/junit-4.12.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/4.12/junit-4.12.pom (24 kB at 3.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/assertj/assertj-core/2.6.0/assertj-core-2.6.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/assertj/assertj-core/2.6.0/assertj-core-2.6.0.pom (7.0 kB at 92 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/assertj/assertj-parent-pom/2.1.4/assertj-parent-pom-2.1.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/assertj/assertj-parent-pom/2.1.4/assertj-parent-pom-2.1.4.pom (15 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.pom (1.3 kB at 263 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/objenesis/objenesis/2.1/objenesis-2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/objenesis/objenesis/2.1/objenesis-2.1.pom (2.5 kB at 423 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/objenesis/objenesis-parent/2.1/objenesis-parent-2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/objenesis/objenesis-parent/2.1/objenesis-parent-2.1.pom (17 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-library/1.3/hamcrest-library-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-library/1.3/hamcrest-library-1.3.pom (820 B at 137 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/skyscreamer/jsonassert/1.4.0/jsonassert-1.4.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/skyscreamer/jsonassert/1.4.0/jsonassert-1.4.0.pom (5.2 kB at 647 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/vaadin/external/google/android-json/0.0.20131108.vaadin1/android-json-0.0.20131108.vaadin1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/vaadin/external/google/android-json/0.0.20131108.vaadin1/android-json-0.0.20131108.vaadin1.pom (2.8 kB at 398 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-test/4.3.13.RELEASE/spring-test-4.3.13.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-test/4.3.13.RELEASE/spring-test-4.3.13.RELEASE.pom (8.2 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-web/1.5.9.RELEASE/spring-boot-starter-web-1.5.9.RELEASE.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter/1.5.9.RELEASE/spring-boot-starter-1.5.9.RELEASE.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot/1.5.9.RELEASE/spring-boot-1.5.9.RELEASE.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-autoconfigure/1.5.9.RELEASE/spring-boot-autoconfigure-1.5.9.RELEASE.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-logging/1.5.9.RELEASE/spring-boot-starter-logging-1.5.9.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-logging/1.5.9.RELEASE/spring-boot-starter-logging-1.5.9.RELEASE.jar (2.3 kB at 12 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter/1.5.9.RELEASE/spring-boot-starter-1.5.9.RELEASE.jar (2.3 kB at 12 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-web/1.5.9.RELEASE/spring-boot-starter-web-1.5.9.RELEASE.jar (2.3 kB at 12 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.7.25/jcl-over-slf4j-1.7.25.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-autoconfigure/1.5.9.RELEASE/spring-boot-autoconfigure-1.5.9.RELEASE.jar (1.1 MB at 3.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot/1.5.9.RELEASE/spring-boot-1.5.9.RELEASE.jar (674 kB at 2.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/log4j-over-slf4j/1.7.25/log4j-over-slf4j-1.7.25.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jcl-over-slf4j/1.7.25/jcl-over-slf4j-1.7.25.jar (17 kB at 60 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/yaml/snakeyaml/1.17/snakeyaml-1.17.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/jul-to-slf4j/1.7.25/jul-to-slf4j-1.7.25.jar (4.6 kB at 16 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-tomcat/1.5.9.RELEASE/spring-boot-starter-tomcat-1.5.9.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-tomcat/1.5.9.RELEASE/spring-boot-starter-tomcat-1.5.9.RELEASE.jar (2.3 kB at 6.3 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-core/8.5.23/tomcat-embed-core-8.5.23.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/log4j-over-slf4j/1.7.25/log4j-over-slf4j-1.7.25.jar (24 kB at 63 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/tomcat-annotations-api/8.5.23/tomcat-annotations-api-8.5.23.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/yaml/snakeyaml/1.17/snakeyaml-1.17.jar (274 kB at 714 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-el/8.5.23/tomcat-embed-el-8.5.23.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/tomcat-annotations-api/8.5.23/tomcat-annotations-api-8.5.23.jar (18 kB at 48 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.23/tomcat-embed-websocket-8.5.23.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-core/1.1.11/logback-core-1.1.11.jar (475 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hibernate/hibernate-validator/5.3.6.Final/hibernate-validator-5.3.6.Final.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-el/8.5.23/tomcat-embed-el-8.5.23.jar (240 kB at 519 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/ch/qos/logback/logback-classic/1.1.11/logback-classic-1.1.11.jar (309 kB at 807 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-websocket/8.5.23/tomcat-embed-websocket-8.5.23.jar (249 kB at 532 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/classmate/1.3.4/classmate-1.3.4.jar (65 kB at 137 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-databind/2.8.10/jackson-databind-2.8.10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/logging/jboss-logging/3.3.1.Final/jboss-logging-3.3.1.Final.jar (66 kB at 137 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-annotations/2.8.0/jackson-annotations-2.8.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/validation/validation-api/1.1.0.Final/validation-api-1.1.0.Final.jar (64 kB at 131 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-core/2.8.10/jackson-core-2.8.10.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-annotations/2.8.0/jackson-annotations-2.8.0.jar (56 kB at 99 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-web/4.3.13.RELEASE/spring-web-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-core/2.8.10/jackson-core-2.8.10.jar (283 kB at 477 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-aop/4.3.13.RELEASE/spring-aop-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hibernate/hibernate-validator/5.3.6.Final/hibernate-validator-5.3.6.Final.jar (727 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-beans/4.3.13.RELEASE/spring-beans-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/tomcat/embed/tomcat-embed-core/8.5.23/tomcat-embed-core-8.5.23.jar (3.1 MB at 4.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-context/4.3.13.RELEASE/spring-context-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-web/4.3.13.RELEASE/spring-web-4.3.13.RELEASE.jar (826 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-webmvc/4.3.13.RELEASE/spring-webmvc-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-aop/4.3.13.RELEASE/spring-aop-4.3.13.RELEASE.jar (380 kB at 559 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-expression/4.3.13.RELEASE/spring-expression-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/fasterxml/jackson/core/jackson-databind/2.8.10/jackson-databind-2.8.10.jar (1.2 MB at 1.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-test/1.5.9.RELEASE/spring-boot-starter-test-1.5.9.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-context/4.3.13.RELEASE/spring-context-4.3.13.RELEASE.jar (1.1 MB at 1.6 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-starter-test/1.5.9.RELEASE/spring-boot-starter-test-1.5.9.RELEASE.jar (2.7 kB at 3.8 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test/1.5.9.RELEASE/spring-boot-test-1.5.9.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-beans/4.3.13.RELEASE/spring-beans-4.3.13.RELEASE.jar (763 kB at 999 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test-autoconfigure/1.5.9.RELEASE/spring-boot-test-autoconfigure-1.5.9.RELEASE.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/jayway/jsonpath/json-path/2.2.0/json-path-2.2.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-expression/4.3.13.RELEASE/spring-expression-4.3.13.RELEASE.jar (264 kB at 339 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/json-smart/2.2.1/json-smart-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test-autoconfigure/1.5.9.RELEASE/spring-boot-test-autoconfigure-1.5.9.RELEASE.jar (123 kB at 157 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/accessors-smart/1.1/accessors-smart-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-test/1.5.9.RELEASE/spring-boot-test-1.5.9.RELEASE.jar (146 kB at 185 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/asm/asm/5.0.3/asm-5.0.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-webmvc/4.3.13.RELEASE/spring-webmvc-4.3.13.RELEASE.jar (918 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/ow2/asm/asm/5.0.3/asm-5.0.3.jar (53 kB at 66 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/4.12/junit-4.12.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar (41 kB at 51 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/assertj/assertj-core/2.6.0/assertj-core-2.6.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/json-smart/2.2.1/json-smart-2.2.1.jar (121 kB at 139 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/net/minidev/accessors-smart/1.1/accessors-smart-1.1.jar (81 kB at 100 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/objenesis/objenesis/2.1/objenesis-2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/jayway/jsonpath/json-path/2.2.0/json-path-2.2.0.jar (207 kB at 239 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/4.12/junit-4.12.jar (315 kB at 380 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-library/1.3/hamcrest-library-1.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-library/1.3/hamcrest-library-1.3.jar (53 kB at 63 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/skyscreamer/jsonassert/1.4.0/jsonassert-1.4.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar (45 kB at 50 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/vaadin/external/google/android-json/0.0.20131108.vaadin1/android-json-0.0.20131108.vaadin1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/skyscreamer/jsonassert/1.4.0/jsonassert-1.4.0.jar (29 kB at 32 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-core/4.3.13.RELEASE/spring-core-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/objenesis/objenesis/2.1/objenesis-2.1.jar (42 kB at 46 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-test/4.3.13.RELEASE/spring-test-4.3.13.RELEASE.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/vaadin/external/google/android-json/0.0.20131108.vaadin1/android-json-0.0.20131108.vaadin1.jar (18 kB at 20 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/mockito/mockito-core/1.10.19/mockito-core-1.10.19.jar (1.2 MB at 1.3 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/assertj/assertj-core/2.6.0/assertj-core-2.6.0.jar (979 kB at 983 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-test/4.3.13.RELEASE/spring-test-4.3.13.RELEASE.jar (602 kB at 597 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/spring-core/4.3.13.RELEASE/spring-core-4.3.13.RELEASE.jar (1.1 MB at 1.1 MB/s)
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ jenkins-x-demo ---
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.6/maven-plugin-api-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.6/maven-plugin-api-2.0.6.pom (1.5 kB at 208 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.0.6/maven-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.0.6/maven-2.0.6.pom (9.0 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/5/maven-parent-5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/5/maven-parent-5.pom (15 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.6/maven-project-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.6/maven-project-2.0.6.pom (2.6 kB at 39 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.6/maven-settings-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.6/maven-settings-2.0.6.pom (2.0 kB at 286 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.6/maven-model-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.6/maven-model-2.0.6.pom (3.0 kB at 254 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.1/junit-3.8.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.1/junit-3.8.1.pom (998 B at 166 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.6/maven-profile-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.6/maven-profile-2.0.6.pom (2.0 kB at 43 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.6/maven-artifact-manager-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.6/maven-artifact-manager-2.0.6.pom (2.6 kB at 291 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.6/maven-repository-metadata-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.6/maven-repository-metadata-2.0.6.pom (1.9 kB at 154 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.6/maven-artifact-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.6/maven-artifact-2.0.6.pom (1.6 kB at 175 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.6/maven-plugin-registry-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.6/maven-plugin-registry-2.0.6.pom (1.9 kB at 278 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.6/maven-core-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.6/maven-core-2.0.6.pom (6.7 kB at 839 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.6/maven-plugin-parameter-documenter-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.6/maven-plugin-parameter-documenter-2.0.6.pom (1.9 kB at 273 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.0.6/maven-reporting-api-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.0.6/maven-reporting-api-2.0.6.pom (1.8 kB at 350 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting/2.0.6/maven-reporting-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting/2.0.6/maven-reporting-2.0.6.pom (1.4 kB at 288 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.0-alpha-7/doxia-sink-api-1.0-alpha-7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.0-alpha-7/doxia-sink-api-1.0-alpha-7.pom (424 B at 85 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.0-alpha-7/doxia-1.0-alpha-7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia/1.0-alpha-7/doxia-1.0-alpha-7.pom (3.9 kB at 245 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.6/maven-error-diagnostics-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.6/maven-error-diagnostics-2.0.6.pom (1.7 kB at 213 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.0/commons-cli-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.0/commons-cli-1.0.pom (2.1 kB at 301 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.6/maven-plugin-descriptor-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.6/maven-plugin-descriptor-2.0.6.pom (2.0 kB at 126 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.6/maven-monitor-2.0.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.6/maven-monitor-2.0.6.pom (1.3 kB at 157 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-filtering/1.1/maven-filtering-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-filtering/1.1/maven-filtering-1.1.pom (5.8 kB at 362 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.12/plexus-interpolation-1.12.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.12/plexus-interpolation-1.12.pom (889 B at 111 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.4/plexus-build-api-0.0.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.4/plexus-build-api-0.0.4.pom (2.9 kB at 33 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/10/spice-parent-10.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/10/spice-parent-10.pom (3.0 kB at 502 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/3/forge-parent-3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/3/forge-parent-3.pom (5.0 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.13/plexus-interpolation-1.13.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.13/plexus-interpolation-1.13.pom (890 B at 148 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.15/plexus-components-1.1.15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.15/plexus-components-1.1.15.pom (2.8 kB at 712 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.6/maven-plugin-api-2.0.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.6/maven-profile-2.0.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.6/maven-project-2.0.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.6/maven-artifact-manager-2.0.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.6/maven-plugin-registry-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.6/maven-plugin-api-2.0.6.jar (13 kB at 165 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.6/maven-core-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.6/maven-project-2.0.6.jar (116 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.6/maven-plugin-parameter-documenter-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.6/maven-core-2.0.6.jar (152 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.0.6/maven-reporting-api-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.6/maven-plugin-parameter-documenter-2.0.6.jar (21 kB at 266 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.0-alpha-7/doxia-sink-api-1.0-alpha-7.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.6/maven-profile-2.0.6.jar (35 kB at 420 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.6/maven-repository-metadata-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.0.6/maven-reporting-api-2.0.6.jar (9.9 kB at 108 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.0-alpha-7/doxia-sink-api-1.0-alpha-7.jar (5.9 kB at 66 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.0/commons-cli-1.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.6/maven-artifact-manager-2.0.6.jar (57 kB at 559 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.6/maven-plugin-descriptor-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.6/maven-repository-metadata-2.0.6.jar (24 kB at 250 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-4/plexus-interactivity-api-1.0-alpha-4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.6/maven-plugin-registry-2.0.6.jar (29 kB at 301 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.6/maven-artifact-2.0.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.6/maven-error-diagnostics-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.6/maven-plugin-descriptor-2.0.6.jar (37 kB at 218 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.6/maven-settings-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-cli/commons-cli/1.0/commons-cli-1.0.jar (30 kB at 178 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.6/maven-model-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interactivity-api/1.0-alpha-4/plexus-interactivity-api-1.0-alpha-4.jar (13 kB at 75 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.6/maven-model-2.0.6.jar (86 kB at 483 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-9-stable-1/plexus-container-default-1.0-alpha-9-stable-1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.6/maven-monitor-2.0.6.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.6/maven-error-diagnostics-2.0.6.jar (14 kB at 74 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.1/junit-3.8.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.6/maven-settings-2.0.6.jar (49 kB at 268 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/2.0.5/plexus-utils-2.0.5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.6/maven-monitor-2.0.6.jar (10 kB at 54 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-filtering/1.1/maven-filtering-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.1/junit-3.8.1.jar (121 kB at 624 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.4/plexus-build-api-0.0.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.6/maven-artifact-2.0.6.jar (87 kB at 448 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.13/plexus-interpolation-1.13.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-filtering/1.1/maven-filtering-1.1.jar (43 kB at 219 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-container-default/1.0-alpha-9-stable-1/plexus-container-default-1.0-alpha-9-stable-1.jar (194 kB at 730 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/2.0.5/plexus-utils-2.0.5.jar (223 kB at 1.1 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.4/plexus-build-api-0.0.4.jar (6.8 kB at 26 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.13/plexus-interpolation-1.13.jar (61 kB at 222 kB/s)
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ jenkins-x-demo ---
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.9/maven-plugin-api-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.9/maven-plugin-api-2.0.9.pom (1.5 kB at 213 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.0.9/maven-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.0.9/maven-2.0.9.pom (19 kB at 44 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/8/maven-parent-8.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/8/maven-parent-8.pom (24 kB at 3.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.9/maven-artifact-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.9/maven-artifact-2.0.9.pom (1.6 kB at 231 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.1/plexus-utils-1.5.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.1/plexus-utils-1.5.1.pom (2.3 kB at 328 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.9/maven-core-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.9/maven-core-2.0.9.pom (7.8 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.9/maven-settings-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.9/maven-settings-2.0.9.pom (2.1 kB at 31 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.9/maven-model-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.9/maven-model-2.0.9.pom (3.1 kB at 628 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.9/maven-plugin-parameter-documenter-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.9/maven-plugin-parameter-documenter-2.0.9.pom (2.0 kB at 393 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.9/maven-profile-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.9/maven-profile-2.0.9.pom (2.0 kB at 186 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.9/maven-repository-metadata-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.9/maven-repository-metadata-2.0.9.pom (1.9 kB at 317 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.9/maven-error-diagnostics-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.9/maven-error-diagnostics-2.0.9.pom (1.7 kB at 26 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.9/maven-project-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.9/maven-project-2.0.9.pom (2.7 kB at 301 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.9/maven-artifact-manager-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.9/maven-artifact-manager-2.0.9.pom (2.7 kB at 450 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.9/maven-plugin-registry-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.9/maven-plugin-registry-2.0.9.pom (2.0 kB at 179 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.9/maven-plugin-descriptor-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.9/maven-plugin-descriptor-2.0.9.pom (2.1 kB at 346 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.9/maven-monitor-2.0.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.9/maven-monitor-2.0.9.pom (1.3 kB at 257 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/1.0/maven-toolchain-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/1.0/maven-toolchain-1.0.pom (3.4 kB at 427 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.1/maven-shared-utils-0.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.1/maven-shared-utils-0.1.pom (4.0 kB at 506 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/18/maven-shared-components-18.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/18/maven-shared-components-18.pom (4.9 kB at 68 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.pom (965 B at 121 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.pom (4.7 kB at 395 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/19/maven-shared-components-19.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/19/maven-shared-components-19.pom (6.4 kB at 84 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-api/2.2/plexus-compiler-api-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-api/2.2/plexus-compiler-api-2.2.pom (865 B at 62 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler/2.2/plexus-compiler-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler/2.2/plexus-compiler-2.2.pom (3.6 kB at 47 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.3.1/plexus-components-1.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.3.1/plexus-components-1.3.1.pom (3.1 kB at 236 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-manager/2.2/plexus-compiler-manager-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-manager/2.2/plexus-compiler-manager-2.2.pom (690 B at 12 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-javac/2.2/plexus-compiler-javac-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-javac/2.2/plexus-compiler-javac-2.2.pom (769 B at 96 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compilers/2.2/plexus-compilers-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compilers/2.2/plexus-compilers-2.2.pom (1.2 kB at 207 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.2/junit-3.8.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.2/junit-3.8.2.pom (747 B at 124 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.9/maven-plugin-api-2.0.9.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.9/maven-artifact-2.0.9.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.9/maven-core-2.0.9.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.1/plexus-utils-1.5.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.9/maven-settings-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/2.0.9/maven-plugin-api-2.0.9.jar (13 kB at 177 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.9/maven-plugin-parameter-documenter-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/2.0.9/maven-core-2.0.9.jar (160 kB at 8.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.9/maven-profile-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/2.0.9/maven-artifact-2.0.9.jar (89 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.9/maven-model-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-parameter-documenter/2.0.9/maven-plugin-parameter-documenter-2.0.9.jar (21 kB at 211 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.9/maven-repository-metadata-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.1/plexus-utils-1.5.1.jar (211 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.9/maven-error-diagnostics-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.9/maven-profile-2.0.9.jar (35 kB at 340 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.9/maven-project-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/2.0.9/maven-settings-2.0.9.jar (49 kB at 413 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.9/maven-plugin-registry-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/2.0.9/maven-model-2.0.9.jar (87 kB at 773 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.9/maven-plugin-descriptor-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.0.9/maven-repository-metadata-2.0.9.jar (25 kB at 181 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.9/maven-artifact-manager-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-error-diagnostics/2.0.9/maven-error-diagnostics-2.0.9.jar (14 kB at 62 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.9/maven-monitor-2.0.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.9/maven-artifact-manager-2.0.9.jar (58 kB at 259 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/1.0/maven-toolchain-1.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.9/maven-project-2.0.9.jar (122 kB at 544 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.1/maven-shared-utils-0.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-descriptor/2.0.9/maven-plugin-descriptor-2.0.9.jar (37 kB at 158 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.1/maven-shared-utils-0.1.jar (155 kB at 515 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar (32 kB at 104 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-api/2.2/plexus-compiler-api-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.9/maven-plugin-registry-2.0.9.jar (29 kB at 94 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-manager/2.2/plexus-compiler-manager-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.jar (14 kB at 43 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-javac/2.2/plexus-compiler-javac-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-api/2.2/plexus-compiler-api-2.2.jar (25 kB at 80 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.2/junit-3.8.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-monitor/2.0.9/maven-monitor-2.0.9.jar (10 kB at 33 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/1.0/maven-toolchain-1.0.jar (33 kB at 103 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-manager/2.2/plexus-compiler-manager-2.2.jar (4.6 kB at 14 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/junit/junit/3.8.2/junit-3.8.2.jar (121 kB at 375 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-compiler-javac/2.2/plexus-compiler-javac-2.2.jar (19 kB at 59 kB/s)
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 2 source files to /home/jenkins/workspace/app-demo/demo/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ jenkins-x-demo ---
[INFO] Not copying test resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ jenkins-x-demo ---
[INFO] Not compiling test sources
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ jenkins-x-demo ---
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/maven-surefire-common/2.18.1/maven-surefire-common-2.18.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/maven-surefire-common/2.18.1/maven-surefire-common-2.18.1.pom (6.3 kB at 697 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugin-tools/maven-plugin-annotations/3.3/maven-plugin-annotations-3.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugin-tools/maven-plugin-annotations/3.3/maven-plugin-annotations-3.3.pom (1.6 kB at 326 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugin-tools/maven-plugin-tools/3.3/maven-plugin-tools-3.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugin-tools/maven-plugin-tools/3.3/maven-plugin-tools-3.3.pom (13 kB at 2.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-api/2.18.1/surefire-api-2.18.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-api/2.18.1/surefire-api-2.18.1.pom (2.3 kB at 457 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-booter/2.18.1/surefire-booter-2.18.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-booter/2.18.1/surefire-booter-2.18.1.pom (2.9 kB at 583 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/2.2.1/maven-toolchain-2.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/2.2.1/maven-toolchain-2.2.1.pom (3.3 kB at 478 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.pom (17 kB at 2.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/22/commons-parent-22.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/22/commons-parent-22.pom (42 kB at 7.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/maven-surefire-common/2.18.1/maven-surefire-common-2.18.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-booter/2.18.1/surefire-booter-2.18.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.15/plexus-utils-1.5.15.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-api/2.18.1/surefire-api-2.18.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-booter/2.18.1/surefire-booter-2.18.1.jar (40 kB at 475 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/2.2.1/maven-toolchain-2.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-utils/1.5.15/plexus-utils-1.5.15.jar (228 kB at 2.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugin-tools/maven-plugin-annotations/3.3/maven-plugin-annotations-3.3.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-toolchain/2.2.1/maven-toolchain-2.2.1.jar (38 kB at 432 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugin-tools/maven-plugin-annotations/3.3/maven-plugin-annotations-3.3.jar (14 kB at 1.1 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/maven-surefire-common/2.18.1/maven-surefire-common-2.18.1.jar (274 kB at 2.8 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar (316 kB at 3.1 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/surefire/surefire-api/2.18.1/surefire-api-2.18.1.jar (148 kB at 1.4 MB/s)
[INFO] Tests are skipped.
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ jenkins-x-demo ---
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-archiver/2.6/maven-archiver-2.6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-archiver/2.6/maven-archiver-2.6.pom (4.3 kB at 54 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/20/maven-shared-components-20.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-components/20/maven-shared-components-20.pom (5.1 kB at 851 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.7/maven-shared-utils-0.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.7/maven-shared-utils-0.7.pom (5.0 kB at 716 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.8.1/plexus-archiver-2.8.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.8.1/plexus-archiver-2.8.1.pom (4.1 kB at 824 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.3/plexus-components-1.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.3/plexus-components-1.3.pom (3.1 kB at 510 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.3.2/plexus-io-2.3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.3.2/plexus-io-2.3.2.pom (2.7 kB at 166 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.2/plexus-components-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.2/plexus-components-1.2.pom (3.1 kB at 612 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-compress/1.9/commons-compress-1.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-compress/1.9/commons-compress-1.9.pom (11 kB at 953 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/34/commons-parent-34.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/34/commons-parent-34.pom (56 kB at 11 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.21/plexus-interpolation-1.21.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.21/plexus-interpolation-1.21.pom (1.5 kB at 385 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.9/plexus-archiver-2.9.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.9/plexus-archiver-2.9.pom (4.4 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.4/plexus-io-2.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.4/plexus-io-2.4.pom (3.7 kB at 746 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/2.2/commons-io-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/2.2/commons-io-2.2.pom (11 kB at 2.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/24/commons-parent-24.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/24/commons-parent-24.pom (47 kB at 9.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.2.1/maven-reporting-api-2.2.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.1/doxia-sink-api-1.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.1/doxia-logging-api-1.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-archiver/2.6/maven-archiver-2.6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.7/maven-shared-utils-0.7.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/reporting/maven-reporting-api/2.2.1/maven-reporting-api-2.2.1.jar (9.8 kB at 109 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.9/plexus-archiver-2.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-archiver/2.6/maven-archiver-2.6.jar (23 kB at 262 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.4/plexus-io-2.4.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-shared-utils/0.7/maven-shared-utils-0.7.jar (170 kB at 2.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/2.2/commons-io-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-logging-api/1.1/doxia-logging-api-1.1.jar (11 kB at 121 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-compress/1.9/commons-compress-1.9.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/doxia/doxia-sink-api/1.1/doxia-sink-api-1.1.jar (13 kB at 123 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.4/plexus-io-2.4.jar (81 kB at 941 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-compress/1.9/commons-compress-1.9.jar (378 kB at 3.8 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/2.2/commons-io-2.2.jar (174 kB at 1.7 MB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.9/plexus-archiver-2.9.jar (145 kB at 1.4 MB/s)
[INFO] Building jar: /home/jenkins/workspace/app-demo/demo/target/jenkins-x-demo-1.3.918-d-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.9.RELEASE:repackage (default) @ jenkins-x-demo ---
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-loader-tools/1.5.9.RELEASE/spring-boot-loader-tools-1.5.9.RELEASE.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-loader-tools/1.5.9.RELEASE/spring-boot-loader-tools-1.5.9.RELEASE.pom (3.8 kB at 375 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.2/commons-logging-1.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.2/commons-logging-1.2.pom (19 kB at 2.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/3.1.1/maven-artifact-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/3.1.1/maven-artifact-3.1.1.pom (2.0 kB at 29 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/3.1.1/maven-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/3.1.1/maven-3.1.1.pom (22 kB at 3.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/3.1.1/maven-model-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/3.1.1/maven-model-3.1.1.pom (4.1 kB at 592 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/3.1.1/maven-core-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/3.1.1/maven-core-3.1.1.pom (7.3 kB at 909 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/3.1.1/maven-settings-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/3.1.1/maven-settings-3.1.1.pom (2.2 kB at 217 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings-builder/3.1.1/maven-settings-builder-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings-builder/3.1.1/maven-settings-builder-3.1.1.pom (2.6 kB at 36 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.19/plexus-interpolation-1.19.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.19/plexus-interpolation-1.19.pom (1.0 kB at 147 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/3.1.1/maven-repository-metadata-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/3.1.1/maven-repository-metadata-3.1.1.pom (2.2 kB at 29 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/3.1.1/maven-plugin-api-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/3.1.1/maven-plugin-api-3.1.1.pom (3.4 kB at 483 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.plexus/0.0.0.M5/org.eclipse.sisu.plexus-0.0.0.M5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.plexus/0.0.0.M5/org.eclipse.sisu.plexus-0.0.0.M5.pom (4.8 kB at 966 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/sisu-plexus/0.0.0.M5/sisu-plexus-0.0.0.M5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/sisu-plexus/0.0.0.M5/sisu-plexus-0.0.0.M5.pom (13 kB at 2.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/enterprise/cdi-api/1.0/cdi-api-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/enterprise/cdi-api/1.0/cdi-api-1.0.pom (1.4 kB at 239 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/weld/weld-api-parent/1.0/weld-api-parent-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/weld/weld-api-parent/1.0/weld-api-parent-1.0.pom (2.4 kB at 589 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/weld/weld-api-bom/1.0/weld-api-bom-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/weld/weld-api-bom/1.0/weld-api-bom-1.0.pom (7.9 kB at 2.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/weld/weld-parent/6/weld-parent-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jboss/weld/weld-parent/6/weld-parent-6.pom (21 kB at 3.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.pom (1.0 kB at 14 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/inject/javax.inject/1/javax.inject-1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/inject/javax.inject/1/javax.inject-1.pom (612 B at 122 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/guava/guava/18.0/guava-18.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/guava/guava/18.0/guava-18.0.pom (5.7 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/guava/guava-parent/18.0/guava-parent-18.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/guava/guava-parent/18.0/guava-parent-18.0.pom (7.7 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/3.1.0/sisu-guice-3.1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/3.1.0/sisu-guice-3.1.0.pom (10 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/inject/guice-parent/3.1.0/guice-parent-3.1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/inject/guice-parent/3.1.0/guice-parent-3.1.0.pom (11 kB at 993 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/aopalliance/aopalliance/1.0/aopalliance-1.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/aopalliance/aopalliance/1.0/aopalliance-1.0.pom (363 B at 91 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.inject/0.0.0.M5/org.eclipse.sisu.inject-0.0.0.M5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.inject/0.0.0.M5/org.eclipse.sisu.inject-0.0.0.M5.pom (2.5 kB at 38 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/sisu-inject/0.0.0.M5/sisu-inject-0.0.0.M5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/sisu-inject/0.0.0.M5/sisu-inject-0.0.0.M5.pom (14 kB at 2.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.4/plexus-classworlds-2.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.4/plexus-classworlds-2.4.pom (3.9 kB at 971 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.1.1/maven-model-builder-3.1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.1.1/maven-model-builder-3.1.1.pom (2.8 kB at 702 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-aether-provider/3.2.1/maven-aether-provider-3.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-aether-provider/3.2.1/maven-aether-provider-3.2.1.pom (4.1 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/3.2.1/maven-3.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/3.2.1/maven-3.2.1.pom (23 kB at 4.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.2.1/maven-model-builder-3.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.2.1/maven-model-builder-3.2.1.pom (2.8 kB at 562 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/3.2.1/maven-repository-metadata-3.2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/3.2.1/maven-repository-metadata-3.2.1.pom (2.2 kB at 372 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-api/1.0.2.v20150114/aether-api-1.0.2.v20150114.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-api/1.0.2.v20150114/aether-api-1.0.2.v20150114.pom (1.8 kB at 229 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether/1.0.2.v20150114/aether-1.0.2.v20150114.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether/1.0.2.v20150114/aether-1.0.2.v20150114.pom (29 kB at 5.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-spi/1.0.2.v20150114/aether-spi-1.0.2.v20150114.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-spi/1.0.2.v20150114/aether-spi-1.0.2.v20150114.pom (2.0 kB at 496 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-util/1.0.2.v20150114/aether-util-1.0.2.v20150114.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-util/1.0.2.v20150114/aether-util-1.0.2.v20150114.pom (2.1 kB at 42 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-impl/1.0.2.v20150114/aether-impl-1.0.2.v20150114.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-impl/1.0.2.v20150114/aether-impl-1.0.2.v20150114.pom (3.4 kB at 672 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.5.1/plexus-classworlds-2.5.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.5.1/plexus-classworlds-2.5.1.pom (5.0 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.17/log4j-1.2.17.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.17/log4j-1.2.17.pom (22 kB at 3.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.pom (3.2 kB at 641 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/15/spice-parent-15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/spice/spice-parent/15/spice-parent-15.pom (8.4 kB at 1.7 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.2/maven-shade-plugin-2.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.2/maven-shade-plugin-2.2.pom (7.8 kB at 1.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-compat/3.0/maven-compat-3.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-compat/3.0/maven-compat-3.0.pom (4.0 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/3.0/maven-3.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/3.0/maven-3.0.pom (22 kB at 3.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/15/maven-parent-15.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-parent/15/maven-parent-15.pom (24 kB at 4.8 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/6/apache-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/apache/6/apache-6.pom (13 kB at 2.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.0/maven-model-builder-3.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.0/maven-model-builder-3.0.pom (2.2 kB at 449 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.14/plexus-interpolation-1.14.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.14/plexus-interpolation-1.14.pom (910 B at 228 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.18/plexus-components-1.1.18.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-components/1.1.18/plexus-components-1.1.18.pom (5.4 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-plexus/1.4.2/sisu-inject-plexus-1.4.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-plexus/1.4.2/sisu-inject-plexus-1.4.2.pom (5.4 kB at 107 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/inject/guice-plexus/1.4.2/guice-plexus-1.4.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/inject/guice-plexus/1.4.2/guice-plexus-1.4.2.pom (3.1 kB at 626 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/inject/guice-bean/1.4.2/guice-bean-1.4.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/inject/guice-bean/1.4.2/guice-bean-1.4.2.pom (2.6 kB at 652 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject/1.4.2/sisu-inject-1.4.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject/1.4.2/sisu-inject-1.4.2.pom (1.2 kB at 416 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-parent/1.4.2/sisu-parent-1.4.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-parent/1.4.2/sisu-parent-1.4.2.pom (7.8 kB at 1.9 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/6/forge-parent-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/forge/forge-parent/6/forge-parent-6.pom (11 kB at 3.6 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-component-annotations/1.5.4/plexus-component-annotations-1.5.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-component-annotations/1.5.4/plexus-component-annotations-1.5.4.pom (815 B at 204 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.5.4/plexus-containers-1.5.4.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-containers/1.5.4/plexus-containers-1.5.4.pom (4.2 kB at 848 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.5/plexus-2.0.5.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus/2.0.5/plexus-2.0.5.pom (17 kB at 4.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.2.3/plexus-classworlds-2.2.3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.2.3/plexus-classworlds-2.2.3.pom (4.0 kB at 999 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-bean/1.4.2/sisu-inject-bean-1.4.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-bean/1.4.2/sisu-inject-bean-1.4.2.pom (5.5 kB at 1.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/2.1.7/sisu-guice-2.1.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/2.1.7/sisu-guice-2.1.7.pom (11 kB at 346 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/1.0-beta-6/wagon-provider-api-1.0-beta-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/1.0-beta-6/wagon-provider-api-1.0-beta-6.pom (1.8 kB at 440 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon/1.0-beta-6/wagon-1.0-beta-6.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon/1.0-beta-6/wagon-1.0-beta-6.pom (12 kB at 3.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm/3.3.1/asm-3.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm/3.3.1/asm-3.3.1.pom (266 B at 38 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-parent/3.3.1/asm-parent-3.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-parent/3.3.1/asm-parent-3.3.1.pom (4.3 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-commons/3.3.1/asm-commons-3.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-commons/3.3.1/asm-commons-3.3.1.pom (417 B at 52 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-tree/3.3.1/asm-tree-3.3.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-tree/3.3.1/asm-tree-3.3.1.pom (406 B at 102 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jdom/jdom/1.1/jdom-1.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jdom/jdom/1.1/jdom-1.1.pom (2.2 kB at 41 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-dependency-tree/2.1/maven-dependency-tree-2.1.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-dependency-tree/2.1/maven-dependency-tree-2.1.pom (6.8 kB at 970 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.2.0/maven-project-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.2.0/maven-project-2.2.0.pom (2.8 kB at 555 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.2.0/maven-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven/2.2.0/maven-2.2.0.pom (22 kB at 4.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.2.0/maven-profile-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.2.0/maven-profile-2.2.0.pom (2.2 kB at 217 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.2.0/maven-artifact-manager-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.2.0/maven-artifact-manager-2.2.0.pom (3.1 kB at 776 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.2.0/maven-repository-metadata-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/2.2.0/maven-repository-metadata-2.2.0.pom (1.9 kB at 312 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.2.0/maven-plugin-registry-2.2.0.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.2.0/maven-plugin-registry-2.2.0.pom (1.9 kB at 386 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/vafer/jdependency/0.7/jdependency-0.7.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/vafer/jdependency/0.7/jdependency-0.7.pom (7.5 kB at 1.5 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/1.3.2/commons-io-1.3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/1.3.2/commons-io-1.3.2.pom (9.7 kB at 2.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/3/commons-parent-3.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/commons/commons-parent/3/commons-parent-3.pom (12 kB at 2.4 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm/3.2/asm-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm/3.2/asm-3.2.pom (264 B at 66 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-parent/3.2/asm-parent-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-parent/3.2/asm-parent-3.2.pom (4.4 kB at 622 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-analysis/3.2/asm-analysis-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-analysis/3.2/asm-analysis-3.2.pom (417 B at 104 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-tree/3.2/asm-tree-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-tree/3.2/asm-tree-3.2.pom (404 B at 101 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-commons/3.2/asm-commons-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-commons/3.2/asm-commons-3.2.pom (415 B at 104 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-util/3.2/asm-util-3.2.pom
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-util/3.2/asm-util-3.2.pom (409 B at 14 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-loader-tools/1.5.9.RELEASE/spring-boot-loader-tools-1.5.9.RELEASE.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.2/commons-logging-1.2.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/3.1.1/maven-artifact-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/springframework/boot/spring-boot-loader-tools/1.5.9.RELEASE/spring-boot-loader-tools-1.5.9.RELEASE.jar (152 kB at 15 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.21/plexus-interpolation-1.21.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/3.1.1/maven-core-3.1.1.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings-builder/3.1.1/maven-settings-builder-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-interpolation/1.21/plexus-interpolation-1.21.jar (62 kB at 753 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/3.1.1/maven-repository-metadata-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings-builder/3.1.1/maven-settings-builder-3.1.1.jar (42 kB at 495 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.1.1/maven-model-builder-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact/3.1.1/maven-artifact-3.1.1.jar (52 kB at 557 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-aether-provider/3.2.1/maven-aether-provider-3.2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-logging/commons-logging/1.2/commons-logging-1.2.jar (62 kB at 618 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-spi/1.0.2.v20150114/aether-spi-1.0.2.v20150114.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-aether-provider/3.2.1/maven-aether-provider-3.2.1.jar (61 kB at 653 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-impl/1.0.2.v20150114/aether-impl-1.0.2.v20150114.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-impl/1.0.2.v20150114/aether-impl-1.0.2.v20150114.jar (173 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-api/1.0.2.v20150114/aether-api-1.0.2.v20150114.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model-builder/3.1.1/maven-model-builder-3.1.1.jar (160 kB at 950 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-util/1.0.2.v20150114/aether-util-1.0.2.v20150114.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-repository-metadata/3.1.1/maven-repository-metadata-3.1.1.jar (25 kB at 146 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.plexus/0.0.0.M5/org.eclipse.sisu.plexus-0.0.0.M5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-core/3.1.1/maven-core-3.1.1.jar (557 kB at 3.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/enterprise/cdi-api/1.0/cdi-api-1.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-util/1.0.2.v20150114/aether-util-1.0.2.v20150114.jar (147 kB at 798 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/enterprise/cdi-api/1.0/cdi-api-1.0.jar (45 kB at 241 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/inject/javax.inject/1/javax.inject-1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.jar (5.8 kB at 31 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/3.1.0/sisu-guice-3.1.0-no_aop.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-spi/1.0.2.v20150114/aether-spi-1.0.2.v20150114.jar (31 kB at 180 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/aopalliance/aopalliance/1.0/aopalliance-1.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.plexus/0.0.0.M5/org.eclipse.sisu.plexus-0.0.0.M5.jar (197 kB at 1.1 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.inject/0.0.0.M5/org.eclipse.sisu.inject-0.0.0.M5.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/aether/aether-api/1.0.2.v20150114/aether-api-1.0.2.v20150114.jar (136 kB at 509 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.5.1/plexus-classworlds-2.5.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/javax/inject/javax.inject/1/javax.inject-1.jar (2.5 kB at 9.3 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/3.1.1/maven-model-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/aopalliance/aopalliance/1.0/aopalliance-1.0.jar (4.5 kB at 16 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/3.1.1/maven-plugin-api-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-classworlds/2.5.1/plexus-classworlds-2.5.1.jar (50 kB at 180 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/3.1.1/maven-settings-3.1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-api/3.1.1/maven-plugin-api-3.1.1.jar (45 kB at 161 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.8/maven-project-2.0.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/eclipse/sisu/org.eclipse.sisu.inject/0.0.0.M5/org.eclipse.sisu.inject-0.0.0.M5.jar (291 kB at 1.0 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.8/maven-profile-2.0.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/3.1.0/sisu-guice-3.1.0-no_aop.jar (357 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.8/maven-artifact-manager-2.0.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-model/3.1.1/maven-model-3.1.1.jar (154 kB at 547 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.8/maven-plugin-registry-2.0.8.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-settings/3.1.1/maven-settings-3.1.1.jar (42 kB at 147 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.17/log4j-1.2.17.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-profile/2.0.8/maven-profile-2.0.8.jar (35 kB at 123 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.8.1/plexus-archiver-2.8.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-project/2.0.8/maven-project-2.0.8.jar (117 kB at 401 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.3.2/plexus-io-2.3.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-artifact-manager/2.0.8/maven-artifact-manager-2.0.8.jar (57 kB at 195 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-archiver/2.8.1/plexus-archiver-2.8.1.jar (143 kB at 476 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.2/maven-shade-plugin-2.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-plugin-registry/2.0.8/maven-plugin-registry-2.0.8.jar (29 kB at 79 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-compat/3.0/maven-compat-3.0.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/codehaus/plexus/plexus-io/2.3.2/plexus-io-2.3.2.jar (74 kB at 201 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/plexus/plexus-build-api/0.0.7/plexus-build-api-0.0.7.jar (8.5 kB at 23 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-bean/1.4.2/sisu-inject-bean-1.4.2.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-plexus/1.4.2/sisu-inject-plexus-1.4.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-bean/1.4.2/sisu-inject-bean-1.4.2.jar (153 kB at 406 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/plugins/maven-shade-plugin/2.2/maven-shade-plugin-2.2.jar (100 kB at 267 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/log4j/log4j/1.2.17/log4j-1.2.17.jar (490 kB at 1.3 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/1.0-beta-6/wagon-provider-api-1.0-beta-6.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/2.1.7/sisu-guice-2.1.7-noaop.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm/3.3.1/asm-3.3.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/wagon/wagon-provider-api/1.0-beta-6/wagon-provider-api-1.0-beta-6.jar (53 kB at 138 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-commons/3.3.1/asm-commons-3.3.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-inject-plexus/1.4.2/sisu-inject-plexus-1.4.2.jar (202 kB at 519 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-tree/3.3.1/asm-tree-3.3.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm/3.3.1/asm-3.3.1.jar (44 kB at 110 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jdom/jdom/1.1/jdom-1.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/sonatype/sisu/sisu-guice/2.1.7/sisu-guice-2.1.7-noaop.jar (472 kB at 1.2 MB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-dependency-tree/2.1/maven-dependency-tree-2.1.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/shared/maven-dependency-tree/2.1/maven-dependency-tree-2.1.jar (60 kB at 128 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/vafer/jdependency/0.7/jdependency-0.7.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-commons/3.3.1/asm-commons-3.3.1.jar (38 kB at 98 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/1.3.2/commons-io-1.3.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/apache/maven/maven-compat/3.0/maven-compat-3.0.jar (285 kB at 730 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-analysis/3.2/asm-analysis-3.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-tree/3.3.1/asm-tree-3.3.1.jar (22 kB at 46 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/jdom/jdom/1.1/jdom-1.1.jar (153 kB at 324 kB/s)
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/guava/guava/18.0/guava-18.0.jar
[INFO] Downloading from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-util/3.2/asm-util-3.2.jar
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/org/vafer/jdependency/0.7/jdependency-0.7.jar (12 kB at 24 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/commons-io/commons-io/1.3.2/commons-io-1.3.2.jar (88 kB at 178 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-util/3.2/asm-util-3.2.jar (37 kB at 74 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/asm/asm-analysis/3.2/asm-analysis-3.2.jar (18 kB at 36 kB/s)
[INFO] Downloaded from nexus-central: http://repo-sonatype-nexus.nexus:8081/repository/maven-central/com/google/guava/guava/18.0/guava-18.0.jar (2.3 MB at 3.8 MB/s)
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 21.823 s
[INFO] Finished at: 2018-11-16T05:24:59Z
[INFO] ------------------------------------------------------------------------
[Pipeline] }
[Pipeline] // container
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (skaffol build)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo
[Pipeline] {
[Pipeline] container
[Pipeline] {
[Pipeline] sh
[demo] Running shell script
+ sed -i -e 's|/repo/image|/ica10888/demo|' skaffold.yaml
[Pipeline] sh
[demo] Running shell script
+ sed -i -e s/demo/demo/ skaffold.yaml
[Pipeline] sh
[demo] Running shell script
++ cat VERSION
+ export VERSION=1.3.918-d-SNAPSHOT
+ VERSION=1.3.918-d-SNAPSHOT
+ skaffold build -f skaffold.yaml
Starting build...
Building [changeme]...
Sending build context to Docker daemon  529.4kB
Sending build context to Docker daemon  14.51MB

Step 1/9 : FROM openjdk:8-jdk-slim
 ---> 44f08b787217
Step 2/9 : ENV PORT 8080
 ---> Using cache
 ---> 20748211c96c
Step 3/9 : ENV CLASSPATH /opt/lib
 ---> Using cache
 ---> 354ccf9520b0
Step 4/9 : EXPOSE 8080
 ---> Using cache
 ---> aa09d5a26f0b
Step 5/9 : COPY pom.xml target/lib* /opt/lib/
 ---> 8c9354f70735
Step 6/9 : COPY target/*SNAPSHOT.jar /opt/app.jar
 ---> f96c828a9e33
Step 7/9 : COPY *.sh /opt/
 ---> 2d719757713a
Step 8/9 : WORKDIR /opt
 ---> Running in c1c33a802ae1
 ---> 7b6238fd58e0
Step 9/9 : CMD $JAVA_COMMAND
 ---> Running in 3afe72fa8ae7
 ---> 6f756102a873
Successfully built 6f756102a873
Successfully tagged 47bfbe566a4406036a706126eb9b1538:latest
The push refers to repository [10.68.22.54:5000/ica10888/demo]
5282057b095a: Preparing
ee8275558f82: Preparing
6b3a47a7419b: Preparing
697ae08e1302: Preparing
d54bde5ef5eb: Preparing
6186fdc55773: Preparing
25fee1b94a24: Preparing
c336d6c4055c: Preparing
237472299760: Preparing
237472299760: Waiting
c336d6c4055c: Waiting
6186fdc55773: Waiting
25fee1b94a24: Waiting
697ae08e1302: Mounted from hanclouds/publish-processor
d54bde5ef5eb: Mounted from hanclouds/publish-processor
6186fdc55773: Mounted from hanclouds/publish-processor
25fee1b94a24: Mounted from hanclouds/publish-processor
c336d6c4055c: Mounted from hanclouds/publish-processor
237472299760: Mounted from hanclouds/publish-processor
5282057b095a: Pushed
6b3a47a7419b: Pushed
ee8275558f82: Pushed
1.3.918-d-SNAPSHOT: digest: sha256:b7b5f83403d59956af4fa6a5f2eda2038cbad4d25eab89ad70f2a802f037ccc3 size: 2201
Build complete in 52.979823325s
changeme -> 10.68.22.54:5000/ica10888/demo:1.3.918-d-SNAPSHOT
[Pipeline] sh
[demo] Running shell script
++ cat VERSION
+ jx step post build --image 10.68.22.54:5000/ica10888/demo:1.3.918-d-SNAPSHOT
no CVE provider running in the current jx namespace so skip adding image to be analysed[Pipeline] }
[Pipeline] // container
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (helm deploy)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /home/jenkins/workspace/app-demo/demo/charts/demo
[Pipeline] {
[Pipeline] container
[Pipeline] {
[Pipeline] sh
[demo] Running shell script
+ helm init --client-only --upgrade -i registry.cn-hangzhou.aliyuncs.com/google_containers/tiller:v2.10.0-rc.1 --stable-repo-url https://kubernetes.oss-cn-hangzhou.aliyuncs.com/charts
Creating /home/jenkins/.helm 
Creating /home/jenkins/.helm/repository 
Creating /home/jenkins/.helm/repository/cache 
Creating /home/jenkins/.helm/repository/local 
Creating /home/jenkins/.helm/plugins 
Creating /home/jenkins/.helm/starters 
Creating /home/jenkins/.helm/cache/archive 
Creating /home/jenkins/.helm/repository/repositories.yaml 
Adding stable repo with URL: https://kubernetes.oss-cn-hangzhou.aliyuncs.com/charts 
Adding local repo with URL: http://127.0.0.1:8879/charts 
$HELM_HOME has been configured at /home/jenkins/.helm.
Not installing Tiller due to 'client-only' flag having been set
Happy Helming!
[Pipeline] sh
[demo] Running shell script
+ jx step helm release
No $CHART_REPOSITORY defined so using the default value of: http://jenkins-x-chartmuseum:8080
Using helmBinary helm with feature flag: template-mode
Adding missing Helm repo: jenkins-x https://chartmuseum.build.cd.jenkins-x.io
Successfully added Helm repository jenkins-x.
Adding missing Helm repo: releases http://jenkins-x-chartmuseum:8080
Successfully added Helm repository releases.
No $CHART_REPOSITORY defined so using the default value of: http://jenkins-x-chartmuseum:8080
Uploading chart file demo-1.3.918-d-SNAPSHOT.tgz to http://jenkins-x-chartmuseum:8080/api/charts
Received 201 response: {"saved":true}
[Pipeline] sh
[demo] Running shell script
+ jx step helm build
No $CHART_REPOSITORY defined so using the default value of: http://jenkins-x-chartmuseum:8080
Using helmBinary helm with feature flag: template-mode
[Pipeline] sh
[demo] Running shell script
+ jx step helm apply --name demo --namespace jx-staging
No $CHART_REPOSITORY defined so using the default value of: http://jenkins-x-chartmuseum:8080
Using helmBinary helm with feature flag: template-mode
Applying helm chart at . as release name demo to namespace jx-staging
Applying generated chart . YAML via kubectl in dir: /tmp/helm-template-workdir-432704539/demo/output
Removing Kubernetes resources from older releases using selector: jenkins.io/chart-release=demo,jenkins.io/version!=1.3.918-d-SNAPSHOT
[Pipeline] }
[Pipeline] // container
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (predestroy)
[Pipeline] script
[Pipeline] {
[Pipeline] echo
 
        =============================================================================================
       ..........       .  ... .        .........      ..........      .........        ........               
       .........................       .. .............. ........   .............      ..........              
       ....@@@@@@@@@@@@@@@`.....       ......@@@@@@@@@@@@@@^.....  ....@@@@@ ...      ./@@@@@...              
       ....@@@@@@@@@@@@@@@@@@........ .. ....@@@@@@@@@@@@@@^....     ..=@@@@@^........=@@@@@^...              
       ....@@@@@^....., @@@@@@@`...  .... ....@@@@@^............       .. @@@@@.... ...@@@@@^....              
       ....@@@@@^........,@@@@@@*........ ....@@@@@^..........         ...@@@@@^......=@@@@@.....              
       ....@@@@@^.........=@@@@@^........ ....@@@@@^............       ...=@@@@@`....*@@@@@`....               
       ....@@@@@^..........@@@@@ ........ ....@@@@@@@@@@@@@^.....       ...=@@@@@....@@@@@^......              
       ....@@@@@^. ........@@@@@^........ ....@@@@@@@@@@@@@^.....       ....@@@@@^..=@@@@/.......              
       ....@@@@@^. .......=@@@@@^........ ....@@@@@^............        ....,@@@@@**@@@@@*.......              
       ....@@@@@^. ....../@@@@@/....... . ....@@@@@^. .........        ......=@@@@  @@@@^........              
        ...@@@@@^.....]/@@@@@@^.......  ......@@@@@^............        ......@@@@@@@@@/... .  .               
        ...@@@@@@@@@@@@@@@@@/....       . ....@@@@@@@@@@@@@@@....       ......,@@@@@@@@..                      
        ...@@@@@@@@@@@@@@[.......       ......@@@@@@@@@@@@@@@....       .......=@@@@@@^..                      
       ..........................      ..........................       .................                      
        .. ...... .     ....  ..         .....          ...  ..            ......  .....        　　

        git仓库                    https://github.com/ica10888/jenkins-x-demo.git
        服务名                     demo          
        分支                       master
        镜像tag                    1.3.918-d-SNAPSHOT
        =============================================================================================
        
[Pipeline] cleanWs
[WS-CLEANUP] Deleting project workspace...[WS-CLEANUP] done
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```
