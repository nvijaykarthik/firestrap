$content = [IO.File]::ReadAllText('.\jenkins\firestrap-initial-pipeline-template.xml')
$discovery=Read-Host "Enter the GIT url for discovery service"
$gateway=Read-Host "Enter the GIT url for Gatway service"
$oauth2Server=Read-Host "Enter the GIT url for Oauth2Server service"
$angularUI=Read-Host "Enter the GIT url for angularUI service"
$serviceOne=Read-Host "Enter the GIT url for seviceOne service"

$content -replace '{{discovery}}', $discovery -replace '{{gateway}}', $gateway -replace '{{serviceOne}}', $serviceOne -replace '{{oauth2Server}}',$oauth2Server -replace '{{angularUI}}', $angularUI | Set-Content -Path config.xml
Copy-Item config.xml .\jenkins\config.xml 


Write-Output "settin up git repository for the firstrap"

Set-Location ..\discovery
git init
git add --all
git commit -m "Initial Commit"
git remote add origin $discovery
# git push -u origin master

Set-Location ..\gateway
git init
git add --all
git commit -m "Initial Commit"
git remote add origin $gateway
# git push -u origin master

Set-Location ..\oauthserver
git init
git add --all
git commit -m "Initial Commit"
git remote add origin $oauth2Server
# git push -u origin master

Set-Location ..\service
git init
git add --all
git commit -m "Initial Commit"
git remote add origin $serviceOne
#git push -u origin master

Set-Location ..\AngularUI
git init
git add --all
git commit -m "Initial Commit"
git remote add origin $angularUI
#git push -u origin master


Set-Location ..\setup\jenkins
$jenkinsJobName=Read-Host "Please enter the jenkins job name :"
$jenkinUrl=Read-Host "Please enter the jenkins Url"
$jenkinUser=Read-Host "Please enter the jenkins user"
$jenkinPwd=Read-Host "Please enter the jenkins password"

cmd /c "java -jar jenkins-cli.jar -s $jenkinUrl -auth ${jenkinUser}:$jenkinPwd create-job $jenkinsJobName < config.xml"
cmd /c "java -jar jenkins-cli.jar -s  $jenkinUrl -auth ${jenkinUser}:$jenkinPwd build $jenkinsJobName"
