pipeline {
agent {
    label 'thomas' 
	}

    stages {
        stage('build') {
	  steps {
              sh 'mvn package -DskipTests'
              sh 'rm -rf /usr/local/tomcat/webapps/ROOT'
              sh 'cp -r /tmp/java-project/target/project-ucll-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war'
              sh '/usr/local/tomcat/bin/startup.sh'
              sh 'cd /tmp/java-project'
              sh 'mvn clean install'
          }
        }
    }
}
