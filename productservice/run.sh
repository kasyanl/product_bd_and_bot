java -jar database/target/database-0.0.1-SNAPSHOT.jar
sleep 10
java -jar webinterface/target/webinterface-0.0.1-SNAPSHOT.jar
sleep 10
java -jar bot/target/bot-1.0.jar
ps ax | grep qs