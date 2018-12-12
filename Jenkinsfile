pipeline {
agent {
    label 'thomas' 
	}

    stages {
        stage('build') {
	        steps {
                sh 'mvn package -DskipTests'
                sh 'rm -rf /usr/local/tomcat/webapps/ROOT'
                sh 'cp -r target/*.war /usr/local/tomcat/webapps/ROOT.war'
                sh '/usr/local/tomcat/bin/startup.sh'
                sh 'mvn clean verify'
            }
        }

        stage('deploy') {
            steps {
                sshagent(credentials: ['fd562910-3591-41b1-ac16-de98108e2b61']) {
                    sh 'scp -P 22345 -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null target/*.war build@tomcat.projectweek.be:/opt/tomcat/teams/team1/ROOT.war'
                }
            }
        }
    }
}
