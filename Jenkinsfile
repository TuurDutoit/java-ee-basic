pipeline {
agent {
    label 'thomas' 
	}

    stages {
        stage('build') {
	  steps {
              sh 'mvn package -DskipTests'
              sh 'rm -rf /usr/local/tomcat/webapps/ROOT'
              sh 'cp -r /tmp/java-project/out/artifacts/* /usr/local/tomcat/webapps/ROOT'
              sh '/usr/local/tomcat/bin/startup.sh'
              sh 'cd /tmp/java-project'
              sh 'mvn clean install'
          }
        }
    }
}
