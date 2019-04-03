const fs = require('fs');
const path = require('path');
const rootDir = path.join(__dirname, '..');

let newService=require('./newservice.json').service
let git=require('./newservice.json').git
let jenkins=require('./newservice.json').jenkins


if (fs.existsSync(`${rootDir}/serviceTemplate/`)) {
    fs.renameSync(`${rootDir}/serviceTemplate/`, `${rootDir}/${newService.name}/`, (err) => {
        if (err) {
            throw err;
        }
        fs.statSync(`${rootDir}/${newService.name}/`, (error, stats) => {
            if (error) {
                throw error;
            }
            console.log(`stats: ${JSON.stringify(stats)}`);
        });
    });
}

if (fs.existsSync(`${rootDir}/${newService.name}/src/main/java/io/firestrap/service/`)) {
    fs.renameSync(`${rootDir}/${newService.name}/src/main/java/io/firestrap/service/`,
    `${rootDir}/${newService.name}/src/main/java/io/firestrap/${newService.name}/`, (err) => {
        if (err) {
            throw err;
        }
        fs.statSync(`${rootDir}/${newService.name}/src/main/java/io/firestrap/${newService.name}/`, (error, stats) => {
            if (error) {
                throw error;
            }
            console.log(`stats: ${JSON.stringify(stats)}`);
        });
    });
}

// replace the place holders.
let serviceDir=`${rootDir}/${newService.name}/src`

const getDirectories=(dir) => {
    const result = [];
  
    const files = [dir];
    do {
      const filepath = files.pop();
      const stat = fs.lstatSync(filepath);
      if (stat.isDirectory()) {
        fs
          .readdirSync(filepath)
          .forEach(f => files.push(path.join(filepath, f)));
      } else if (stat.isFile()) {
        result.push(path.relative(dir, filepath));
      }
    } while (files.length !== 0);
  
    return result;
  };

 console.log(getDirectories(serviceDir));
 String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.replace(new RegExp(search, 'g'), replacement);
};
 getDirectories(serviceDir).forEach(dir=>{
    let processingfile=serviceDir.concat("/").concat(dir);
    console.log("processing "+processingfile);
    let content=fs.readFileSync(processingfile, "utf8");
    content=content.replaceAll('{{service}}',newService.name);
    content=content.replaceAll('{{port}}',newService.port);
    content=content.replaceAll('{{eurekaServerDomainPort}}',newService.eurekaServerDomainPort);
    content=content.replaceAll('{{oauthServerDomainPort}}',newService.oauthServerDomainPort);
    fs.writeFileSync(processingfile, content);
    console.log("processed "+processingfile);
 })

 // configuring git.
 let commands = "";
 let jenkinsConfig = fs.readFileSync("./jenkins/config.xml", "utf8");
  jenkinsConfig = jenkinsConfig.replace("{{JenkinPoller}}", jenkins.JenkinPoller);
  jenkinsConfig = jenkinsConfig.replace("{{GitUrl}}", git.url);
  jenkinsConfig = jenkinsConfig.replace("{{CredentialId}}", git.jenkinCredentialId);
  jenkinsConfig = jenkinsConfig.replace("{{service}}", newService.name);
  fs.writeFileSync("./jenkins/".concat(newService.name).concat(".xml"), jenkinsConfig);
  console.log("Creating config file : " + newService.name);

  let commandLines = 'cd ..\\{{service}}\ngit init\ngit add --all\ngit commit -m "Initial Commit"\ngit remote add origin {{url}}\nrem git push -u origin master\n\n';
  commandLines = commandLines.replace("{{service}}", newService.name)
  commandLines = commandLines.replace("{{url}}", git.url)
  commands = commandLines.concat(commands);
  
  commands = commands.concat("cd ..\\setup\\jenkins\n\n");

  commandLines = 'java -jar jenkins-cli.jar -s {{jenkinurl}} -auth {{user}}:{{pass}} create-job {{jobname}}<{{service-xml}}\n\n';
  commandLines = commandLines.replace("{{user}}", jenkins.user)
  commandLines = commandLines.replace("{{jenkinurl}}", jenkins.url)
  commandLines = commandLines.replace("{{pass}}", jenkins.password)
  commandLines = commandLines.replace("{{jobname}}", newService.name)
  commandLines = commandLines.replace("{{service-xml}}", newService.name.concat(".xml"))
  commands = commands.concat(commandLines);

  fs.writeFileSync("newGitJenkin.bat", commands);