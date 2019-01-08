cd discovery
start gradle bootRun
cd ../gateway
TIMEOUT /T 10
start gradle bootRun
cd ../oauthserver
TIMEOUT /T 10
start gradle bootRun
cd ../service
TIMEOUT /T 10
start gradle bootRun
