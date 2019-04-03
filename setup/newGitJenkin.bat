cd ..\service2
git init
git add --all
git commit -m "Initial Commit"
git remote add origin https://velocity.cognizant.com/bitbucket/scm/dep/service.git
rem git push -u origin master

cd ..\setup\jenkins

java -jar jenkins-cli.jar -s http://localhost:8080 -auth admin:admin create-job service2<service2.xml

