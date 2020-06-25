# A Simple Spock Test Suite To Illustrate Spring Features, Spock Form And Function And Some Testing Techniques

## Configuration
- Values that will be the same in all environments where the test may run can be specified in the /src/main/resources/application.yml file and then referenced elsewhere with the @Value Spring annotation
- Values that will differ from environment to environment (eg: local Mac or PC) can be specified in the /application-<env>.yml files. You can specify which env you want the tests to run in by modifying the value of the @ActiveProfiles("env") Spring annotation in /src/test/Groovy/testautomationexample/specifications/BaseSpec.groovy

## BaseSpec.groovy
- all other specification extend BaseSpec.groovy. You can set up resources that will be shared amongst all specifications here

## Step1Spec
- a very simple specification that simply illustrates several useful features of the Spock Testing Framework

## Step2Spec
- this specification illustrates an example of how to write to and read from a file

## Step3Spec
- this specification illustrates how to query from a database and validate the results
- an in-memory DB is used for illustration purposes so as to reduce the dependencies necessary for the test to run
- the in-memory DB is created by specifying a datasource and jpa in the /src/main/resources/application.yml
- the tables are automatically created and populated by Spring because of the /schema.sql and /data.sql files Spring found in /src/main/resources

## Step4Spec
- this specification illustrates how to send a 'get' request to a rest API and validate the results

## Logger
- a logging utility is provided in /src/main/groovy/com/loyalty/testautomationexample/utilities/Logger.groovy and is used throughout the specifications to help simplify debugging

