pipeline {
    agent any

    environment {
        // Repository URL and branch
        REPO_URL = 'git@github.com:Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1.git'
        GIT_BRANCH = 'main'

        // Paths
        FRONTEND_DIR = 'frontend'
        MAVEN_DIR = '.'
        DEMO_WAR_PATH = '/var/lib/tomcat10/webapps/smarthome.war'
        DEMO_FRONTEND_PATH = '/var/www/frontend/dist'
        BACKUP_DIR = 'backup'
        TAR_FILE_PATH = "${env.BACKUP_DIR}/frontend_dist.tar.gz"

        // Limit maven to 2gb of memory to avoid OOM errors
        MAVEN_OPTS = '-Xmx2g'
    }


    stages {
        stage('Clone Repository') {
            steps {
                git branch: "${env.GIT_BRANCH}", url: "${env.REPO_URL}", credentialsId: 'grupo1'
            }
        }

        stage('Verify Directories') {
            steps {
                script {
                    if (!fileExists(env.FRONTEND_DIR)) {
                        error "Directory ${env.FRONTEND_DIR} does not exist."
                    }
                    if (!fileExists(env.MAVEN_DIR)) {
                        error "Directory ${env.MAVEN_DIR} does not exist."
                    }
                    if (!fileExists(env.BACKUP_DIR)) {
                        sh "mkdir -p ${env.BACKUP_DIR}"
                    }
                }
            }
        }
    // THE STAGE REGARDING TESTING SHOULD BE HERE HOWEVER TO AVOID OVERLOAD OF DEI SERVERS IT WAS OMITTED
        stage('Install Frontend Dependencies') {
            steps {
                script {
                    sh "npm --prefix ${env.FRONTEND_DIR} install"
                    sh "npm --prefix ${env.MAVEN_DIR} install"
                }
            }
        }

        stage('Build Frontend') {
            steps {
                script {
                    sh "npm --prefix ${env.FRONTEND_DIR} run build -- --mode demo --emptyOutDir"
                }
            }
        }

        stage('Package Frontend') {
            steps {
                script {
                    sh "tar -czf ${env.TAR_FILE_PATH} -C ${env.FRONTEND_DIR}/dist ."
                }
            }
        }

        stage('Build Backend') {
            steps {
                script {
                    if (fileExists("${env.MAVEN_DIR}/mvnw")) {
                        sh "${env.MAVEN_DIR}/mvnw package -DskipTests -Dspring.profiles.active=demo"
                    } else {
                        error "mvnw file does not exist in ${env.MAVEN_DIR}."
                    }
                }
            }
        }

        stage('Package Backend') {
            steps {
                script {
                    sh "tar -czf ${env.BACKUP_DIR}/backend_dist.tar.gz -C ${env.MAVEN_DIR}/target switch2023project_g1-1.0-SNAPSHOT.war"
                }
            }
        }

        stage('Deploy WAR to Tomcat') {
            steps {
                script {
                    sh "cp ${env.MAVEN_DIR}/target/switch2023project_g1-1.0-SNAPSHOT.war ${env.DEMO_WAR_PATH}"
                    sh "systemctl restart tomcat10"
                }
            }
        }

        stage('Deploy Frontend to Nginx') {
            steps {
                script {
                    sh "mkdir -p ${env.DEMO_FRONTEND_PATH}"
                    sh "rm -rf ${env.DEMO_FRONTEND_PATH}/*"
                    sh "cp -R ${env.FRONTEND_DIR}/dist/* ${env.DEMO_FRONTEND_PATH}/"
                    sh "systemctl restart nginx"
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                script {
                    archiveArtifacts "${env.BACKUP_DIR}/*"
                }
            }
        }
    }

    post {
        failure {
            script {
                echo 'Build failed.'
            }
        }

        success {
            script {
                echo 'Build completed successfully.'
            }
        }

        always {
            cleanWs()
        }
    }
}