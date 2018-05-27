pipeline {
   tools {
     maven "M3"
   }
   agent any
   stages {
      stage("Preparation") { 
         steps {
            git 'https://github.com/Moisitin/practica1AIS.git' 
         }
      }
      stage("Test") {
         steps {
            script {
			def mvnHome = tool 'M3';
			env.JAVA_HOME="${tool 'JDK8'}"
			env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
               if(isUnix()) {
                  sh "mvn test"
               } else {
                  bat(/"${mvnHome}\bin\mvn" test/)
               }
            }
         }
      }
   }
   post {
      always {
         junit "**/target/surefire-reports/TEST-*.xml"
      }
   }
}