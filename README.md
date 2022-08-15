# How To Build it ?
1. make sure maven already installed
2. clone this repo
3. open command prompt (cmd) and enter the repo directory throught the cmd
4. run `mvn clean install` for build the jar file
5. the jar file will save in target folder

# How to use it
`java -jar app.jar --config=kafka_config.config listener_port
`

# Optinoal
there are some ways to run the service:
- create systemctl services for runing the services in background
- run with `nohup` command