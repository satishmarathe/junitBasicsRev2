# sample with Junit5 and Mockito and Jacaco Code Coverage  - Rev #1

#1 Introducing Jacoco
We have used the details from here:
https://www.mkyong.com/maven/maven-jacoco-code-coverage-example/

#1a 
First add the Jacoco Maven plugin 

#1b
Once we trigger the maven build 'test' phase is executed ( which is normal ).
Under 'test' phase all of our Junits are run ( this is normal ) 
while running Junits Java Code Coverage ( Jacoco ) will step in and measure code coverage.
So Junits must be run to get Code Coverage.

#1c
Now we can check the code coverage report generated at 'target/site/jacoco/*'

#1d
Open the target/site/jacoco/index.html file, review the code coverage report

#2 Jacoco code coverage % rule 
This is also quite simple - by simply adding a goal ‘check’ in pom file.
Reference :
https://www.mkyong.com/maven/maven-jacoco-code-coverage-example/

#3 Jacoco package / class exclusion 
This is also specified in pom file - where we can specify exclusion rules.
Reference:
https://stackoverflow.com/questions/27799419/maven-jacoco-configuration-exclude-classes-packages-from-report-not-working

---------------------------------------------------------------------------------------------
# sample with Junit5 and Mockito - Rev #0

Sample application with basics about .

# Example 1
This is an example of mocking a stand alone method 
Take a look at 'MyControllerTest' which is a Junit5 example.
This is an example of how we can test a standalone method / class.
So this sets the groundwork on how to do a simple Junit !

# Example 2
This is an example of mocking Dependant class using Mockito 
Take a look at MyOrderServiceImplTest
This is slightly more involved where we have a service class calling a DAO.
The DAO does the database interaction.

This is a typical scenario in layered web applications :
web >> service >> dao 

So here the Junit sample is exhibiting two important aspects:
1 shows how the DAP implementation is mocked.
  keep note of the fact that we are testing the service class method
  which is why we have to mock the dao behavior
  This is a very critical aspect which when understood will be useful !
  
2 shows how we can use Mockito to Mock

---------------------------------------------------------------------------------------------
# Questions 

#1 How does spring boot start - can you call the rest service ?
xxx

#2 How do we Junit methods that do not return anything ( void ) ?
 
#3 how do we Junit DAO classes ?
 
#4 How do we deploy spring boot app in Weblogic ? 

#5 assertions should they be in production code ?
---------------------------------------------------------------------------------------------
# GOALS
#1 To introduce Logging  

#3 Remove 'new' operator using Spring 

#4 Make use of testing with something  related to Spring's Mock Rest server  
https://thepracticaldeveloper.com/2016/02/06/test-coverage-analysis-for-your-spring-boot-app/

#5 Go through this course on Pluralsight
https://www.pluralsight.com/courses/effective-testing-with-spring

#8 Introduce spring jdbc 

#9 Mock DAO classes

#11 Deploy spring boot application in weblogic

#12 Very important role of Surefire plugin - we need to understand and use this plugin 
	 https://blog.ccbill.com/code-coverage-with-surefire-and-jacoco/

#13 Learn about API Gateways

#14 Learn Spring Data 

#15 Learn Spring Cloud

#16 Learn Docker 

#17 Postman newman integration tests 

#18 Soap UI Mocking 

#19 Jmeter testing 

#20 Profiling 



---------------------------------------------------------------------------------------------
# LEARNINGS

#1 assertThrows 
We learnt how to simulate an exception and to test with this annotation 

#2 Functional Interface : Executable
We learnt that this needs to be provided as a second parameter in the assertion 'assertThrows'

#3 Introducing Jacaco Code coverage into project
We achieved this by simply adding one maven plugin !

---------------------------------------------------------------------------------------------
# DONE

#2 To introduce Code coverage

#6 Configure code coverage % and break build if not achieved

#7 Increase code coverage through more Unit tests and pass build

#10 Exclude package / class from code coverage








 
