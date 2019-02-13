@echo off
set /p  discovery="Enter the GIT url for discovery service :"
set /p  gateway="Enter the GIT url for Gatway service :"
set /p  oauth2Server="Enter the GIT url for Oauth2Server service :"
set /p  angularUI="Enter the GIT url for angularUI service :"
set /p  serviceOne="Enter the GIT url for seviceOne service :"

powershell -Command "(gc .\jenkins\firestrap-initial-pipeline-template.xml) -replace '{{discovery}}', '%discovery%' | Out-File firestrap-initial-pipeline-template.xml"
powershell -Command "(gc .\firestrap-initial-pipeline-template.xml) -replace '{{gateway}}', '%gateway%' | Out-File firestrap-initial-pipeline-template.xml"
powershell -Command "(gc .\firestrap-initial-pipeline-template.xml) -replace '{{oauth2Server}}', '%oauth2Server%' | Out-File firestrap-initial-pipeline-template.xml"
powershell -Command "(gc .\firestrap-initial-pipeline-template.xml) -replace '{{angularUI}}', '%angularUI%' | Out-File firestrap-initial-pipeline-template.xml"
powershell -Command "(gc .\firestrap-initial-pipeline-template.xml) -replace '{{serviceOne}}', '%serviceOne%' | Out-File firestrap-initial-pipeline-completed.xml"

copy firestrap-initial-pipeline-completed.xml jenkins\firestrap-initial-pipeline-processed.xml

echo "settin up git repository for the firstrap"

cd ..\discovery
git init
git add --all
git commit -m "Initial Commit"
git remote add origin %1%
rem git push -u origin master

cd ..\gateway
git init
git add --all
git commit -m "Initial Commit"
git remote add origin %1%
rem git push -u origin master

cd ..\oauthserver
git init
git add --all
git commit -m "Initial Commit"
git remote add origin %1%
rem git push -u origin master

cd ..\service
git init
git add --all
git commit -m "Initial Commit"
git remote add origin %1%
rem git push -u origin master


cd ..\setup\jenkins
set /p  jenkinsJobName="Please enter the jenkins job name"

java -jar jenkins-cli.jar -s http://localhost:8080 -auth admin:admin create-job %jenkinsJobName%< firestrap-initial-pipeline-processed.xml

java -jar jenkins-cli.jar -s http://localhost:8080 -auth admin:admin build %jenkinsJobName%