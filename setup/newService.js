const fs = require('fs');
const path = require('path');
const rootDir = path.join(__dirname, '..');

let newService=require('./newservice.json').service


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