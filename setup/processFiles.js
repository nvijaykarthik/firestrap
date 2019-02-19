var fs = require('fs');
var firestrap_props = require("./firestrap.json");
var jenkins_pipeline=fs.readFileSync("./jenkins/firestrap-initial-pipeline-template.xml","utf8")

jenkins_pipeline=jenkins_pipeline.replace("{{discovery}}",firestrap_props.git.discovery);
jenkins_pipeline=jenkins_pipeline.replace("{{gateway}}",firestrap_props.git.gateway);
jenkins_pipeline=jenkins_pipeline.replace("{{oauth2Server}}",firestrap_props.git.oauth2Server);
jenkins_pipeline=jenkins_pipeline.replace("{{angularUI}}",firestrap_props.git.angularUI);
jenkins_pipeline=jenkins_pipeline.replace("{{serviceOne}}",firestrap_props.git.serviceOne);

fs.writeFileSync("./jenkins/config.xml",jenkins_pipeline)

console.log("jenkins job template generated")

var configureGit=fs.readFileSync("./templates/configureGit.template","utf8")

configureGit=configureGit.replace("{{discovery}}",firestrap_props.git.discovery);
configureGit=configureGit.replace("{{gateway}}",firestrap_props.git.gateway);
configureGit=configureGit.replace("{{oauth2Server}}",firestrap_props.git.oauth2Server);
configureGit=configureGit.replace("{{angularUI}}",firestrap_props.git.angularUI);
configureGit=configureGit.replace("{{serviceOne}}",firestrap_props.git.serviceOne);
configureGit=configureGit.replace("{{url}}",firestrap_props.jenkins.url);
configureGit=configureGit.replace("{{user}}",firestrap_props.jenkins.user);
configureGit=configureGit.replace("{{pass}}",firestrap_props.jenkins.password);
configureGit=configureGit.replace("{{jobname}}",firestrap_props.jenkins.jobname);
configureGit=configureGit.replace("{{url}}",firestrap_props.jenkins.url);
configureGit=configureGit.replace("{{user}}",firestrap_props.jenkins.user);
configureGit=configureGit.replace("{{pass}}",firestrap_props.jenkins.password);
configureGit=configureGit.replace("{{jobname}}",firestrap_props.jenkins.jobname);

fs.writeFileSync("./config.bat",configureGit)