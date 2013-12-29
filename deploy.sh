/usr/local/apache-tomcat-7.0.42/bin/shutdown.sh; 
mvn clean install;
rm -rf /usr/local/apache-tomcat-7.0.42/work/Catalina/localhost/notes; 
rm -rf /usr/local/apache-tomcat-7.0.42/webapps/notes*; 
cp target/notes.war /usr/local/apache-tomcat-7.0.42/webapps/; 
/usr/local/apache-tomcat-7.0.42/bin/startup.sh
