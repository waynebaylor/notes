export CATALINA_OPTS=-Dnotes.config=/home/wtaylor/tomcat/apache-tomcat-7.0.37/webapps/notes.properties
~/tomcat/apache-tomcat-7.0.37/bin/shutdown.sh; 
mvn clean install;
rm -rf ~/tomcat/apache-tomcat-7.0.37/work/Catalina/localhost/notes;
rm ~/tomcat/apache-tomcat-7.0.37/webapps/notes.war; 
rm -rf ~/tomcat/apache-tomcat-7.0.37/webapps/notes/; 
cp target/notes.war ~/tomcat/apache-tomcat-7.0.37/webapps/; 
~/tomcat/apache-tomcat-7.0.37/bin/startup.sh
