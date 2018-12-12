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
              sh 'mvn clean install'
          }
        }
    }
}
