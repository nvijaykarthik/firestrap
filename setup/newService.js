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
let serviceDir=`${rootDir}/${newService.name}`

