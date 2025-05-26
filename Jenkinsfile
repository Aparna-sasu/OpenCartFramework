pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                // git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 //sh "mvn -Dmaven.test.failure.ignore=true clean package"
                 echo("build is in progress")
            }
            post 
            {
                success
                {
                   // junit '**/target/surefire-reports/TEST-*.xml'
                  //  archiveArtifacts 'target/*.jar'
                  echo("build done")
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
        
        
        
                
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Aparna-sasu/OpenCartFramework.git'
                    sh "mvn clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_regression.xml -Denv=qa"
                    sh "find target -type f"
                    
                }
            }
            post {
        always {
            // ✅ Archive TestNG results here
            junit 'target/surefire-reports/TEST-*.xml'
        }
    }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
        
        
        stage('Publish ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'target/chaintest', 
                                  reportFiles: 'Index.html', 
                                  reportName: 'HTML Regression ChainTest Report', 
                                  reportTitles: ''])
            }
        }
        
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Aparna-sasu/OpenCartFramework.git'
                    sh "mvn clean test -DsuiteXmlFile=src/test/resources/testrunners/testng_sanity.xml -Denv=stage"
                    
                }
            }
        }
        
        
        
        stage('Publish sanity ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: true, 
                                  reportDir: 'target/chaintest', 
                                  reportFiles: 'Index.html', 
                                  reportName: 'HTML Sanity ChainTest Report', 
                                  reportTitles: ''])
            }
        }
        
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
        
        
    }
}