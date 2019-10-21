node {
    def mvnHome
    stage('Clone the project and Preparation') { // for display purposes
        // Get some code from a GitHub repository
        git 'https://github.com/codenation-dev/squad-3-ad-java-banco-inter-1.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.           
        mvnHome = tool 'M3'
    }
    dir('spring-jenkins-pipeline') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            stage("Compilation and Analysis") {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
            }
            parallel 'Compilation': {
                sh "./mvnw clean install -DskipTests"
            }, 'Static Analysis': {
                stage("Checkstyle") {
                    sh '"$MVN_HOME/bin/mvn" checkstyle:checkstyle'

                    step([$class: 'CheckStylePublisher',
                      canRunOnFailed: true,
                      defaultEncoding: '',
                      healthy: '100',
                      pattern: '**/target/checkstyle-result.xml',
                      unHealthy: '90',
                      useStableBuildAsReference: true
                    ])
                }
            }
        }
    }
    stage("Tests and Deployment") {
        parallel 'Unit tests': {
                stage("Runing unit tests") {
                    try {
                        sh "./mvnw test -Punit"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults:
                          '**/target/surefire-reports/TEST-*UnitTest.xml'])
                        throw err
                    }
                   step([$class: 'JUnitResultArchiver', testResults:
                     '**/target/surefire-reports/TEST-*UnitTest.xml'])
                }
            }, 'Integration tests': {
                stage("Runing integration tests") {
                    try {
                        sh "./mvnw test -Pintegration"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults:
                          '**/target/surefire-reports/TEST-'
                            + '*IntegrationTest.xml'])
                        throw err
                    }
                    step([$class: 'JUnitResultArchiver', testResults:
                      '**/target/surefire-reports/TEST-'
                        + '*IntegrationTest.xml'])
                }
            }

            stage("Staging") {
                sh "pid=\$(lsof -i:8989 -t); kill -TERM \$pid "
                  + "|| kill -KILL \$pid"
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh 'nohup ./mvnw spring-boot:run -Dserver.port=8989 &'
                }
            }
        }
    }
}
