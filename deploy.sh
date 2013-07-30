~/tomcat/apache-tomcat-7.0.37/bin/shutdown.sh; 
mvn clean install;
rm -rf ~/tomcat/apache-tomcat-7.0.37/work/Catalina/localhost/notes; 
rm -rf ~/tomcat/apache-tomcat-7.0.37/webapps/notes*; 
cp target/notes.war ~/tomcat/apache-tomcat-7.0.37/webapps/; 
~/tomcat/apache-tomcat-7.0.37/bin/startup.sh
