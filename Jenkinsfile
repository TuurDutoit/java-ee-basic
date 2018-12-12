pipeline {
agent {
    label 'thomas' 
	}

    stages {
        stage('deploy') {
            steps {
                sshagent(credentials: ['fd562910-3591-41b1-ac16-de98108e2b61']) {
                    sh 'scp -P 22345 -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null target/*.war build@tomcat.projectweek.be:/opt/tomcat/teams/${JOB_NAME}/ROOT.war'
                }
            }
        }
    }
}
