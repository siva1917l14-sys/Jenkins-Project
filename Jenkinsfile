pipeline {
    agent any

    options {
        skipDefaultCheckout()
        timestamps()
    }

    // parameters {
    //     string(name: 'USER_NAME', defaultValue: 'Jenkins Learner', description: 'Name printed by the Java program')
    //     choice(name: 'ENVIRONMENT', choices: ['DEV', 'QA', 'PROD'], description: 'Select the demo environment')
    //     booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Run the Java smoke test')
    // }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Show Parameters') {
            steps {
                echo "User: ${params.USER_NAME}"
                echo "Environment: ${params.ENVIRONMENT}"
                echo "Run tests: ${params.RUN_TESTS}"
            }
        }

        // stage('Build') {
        //     steps {
        //         bat '''
        //             if not exist build mkdir build
        //             javac -d build src\\HelloJenkins.java
        //         '''
        //     }
        // }

        stage('Test') {
            when {
                expression { params.RUN_TESTS }
            }
            steps {
                bat 'java -cp build HelloJenkins --test'
            }
        }

        stage('Package') {
            steps {
                bat 'jar --create --file build\\parameterized-demo.jar --main-class HelloJenkins -C build HelloJenkins.class'
            }
        }

        stage('Run') {
            steps {
                withEnv(["DEMO_USER_NAME=${params.USER_NAME}", "DEMO_ENVIRONMENT=${params.ENVIRONMENT}"]) {
                    bat 'java -jar build\\parameterized-demo.jar "%DEMO_USER_NAME%" "%DEMO_ENVIRONMENT%"'
                }
            }
        }
    }

    post {
        success {
            archiveArtifacts artifacts: 'build/parameterized-demo.jar', fingerprint: true
            echo 'Demo completed successfully; JAR archived.'
        }
        failure {
            echo 'Demo failed. Review the Console Output.'
        }
    }
}
