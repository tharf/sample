#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/tharf/sample.git",
                                credentialsId: "tharf"
                        ]
                ],
                branches: [[name: "master"]]
        ], poll: false
    }

    stage('Complete any setup steps') {
        echo "Complete set-up steps"
        
    }

    stage('Execute Performance Tests') {
        dir("${WORKSPACE}") {
            bat "D:/apache-jmeter-5.4.1/apache-jmeter-5.4.1/bin/jmeter.bat -n -t Java_req.jmx -l Shift-Left.jtl"
        }
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
