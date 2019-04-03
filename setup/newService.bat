echo d | xcopy "template/service" "../serviceTemplate" /s /e
node newService.js
call newGitJenkin.bat