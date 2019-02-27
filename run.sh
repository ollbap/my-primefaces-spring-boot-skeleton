java -jar ./target/*.jar\
  --spring.datasource.driver-class-name=org.h2.Driver\
  --spring.datasource.url=jdbc:h2:mem:localhost\;DB_CLOSE_ON_EXIT=FALSE\
  --spring.datasource.username=admin\
  --logging.level.org.springframework.web=DEBUG\
  

