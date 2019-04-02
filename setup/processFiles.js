var fs = require('fs');

var firestrap = require("./firestrap.json");
let commands = "";

firestrap.git.forEach(fire => {
  let jenkinsConfig = fs.readFileSync("./jenkins/config.xml", "utf8");
  jenkinsConfig = jenkinsConfig.replace("{{JenkinPoller}}", firestrap.jenkins.JenkinPoller);
  jenkinsConfig = jenkinsConfig.replace("{{GitUrl}}", fire.url);
  jenkinsConfig = jenkinsConfig.replace("{{CredentialId}}", fire.jenkinCredentialId);
  jenkinsConfig = jenkinsConfig.replace("{{service}}", fire.service);
  fs.writeFileSync("./jenkins/".concat(fire.service).concat(".xml"), jenkinsConfig);
  console.log("Creating config file : " + fire.service);
  let commandLines = 'cd ..\\{{service}}\ngit init\ngit add --all\ngit commit -m "Initial Commit"\ngit remote add origin {{url}}\nrem git push -u origin master\n\n';
  commandLines = commandLines.replace("{{service}}", fire.service)
  commandLines = commandLines.replace("{{url}}", fire.url)
  commands = commandLines.concat(commands);
});

commands = commands.concat("cd ..\\setup\\jenkins\n\n");
firestrap.git.forEach(fire => {
  let commandLines = 'java -jar jenkins-cli.jar -s {{jenkinurl}} -auth {{user}}:{{pass}} create-job {{jobname}}<{{service-xml}}\n\n';
  commandLines = commandLines.replace("{{user}}", firestrap.jenkins.user)
  commandLines = commandLines.replace("{{jenkinurl}}", firestrap.jenkins.url)
  commandLines = commandLines.replace("{{pass}}", firestrap.jenkins.password)
  commandLines = commandLines.replace("{{jobname}}", fire.service)
  commandLines = commandLines.replace("{{service-xml}}", fire.service.concat(".xml"))
  commands = commands.concat(commandLines);
})


fs.writeFileSync("config.bat", commands);