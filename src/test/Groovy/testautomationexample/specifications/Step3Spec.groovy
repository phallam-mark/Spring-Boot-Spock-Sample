package testautomationexample.specifications

import com.loyalty.testautomationexample.repository.PlanetsRepository
import com.loyalty.testautomationexample.utilities.Logger
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Ignore

class Step3Spec extends BaseSpec {
    // a Spock Specification that queries data from an in-memory database and validates it

    // an in-memory DB was created and populated when the Spring application started up, just need to Autowire it here
    // see xxx in readme
    @Autowired
    PlanetsRepository planetsRepository

    def setupSpec(){
        Logger.logMessage("setupSpec() - Runs once per Specification");
    }

    def setup() {
        Logger.logMessage("setup() - Runs before every feature method");
    }

    def "Test Case 1 - test a count of the rows in the DB"(){
        Logger.logMessage ("Test Case 1 - start")
        given : "a database containing statistics regarding planets from Star Wars"
        //NA - the DB is set up when the Spring application starts up
        when: "the number of records in the database is queried"
        int planetCount = planetsRepository.getCountOfPlanets()
        then: "the count should be equal to 3"
        planetCount == 3;
    }

    def "Test Case 2 - the value of 'climate' for the planet Bespin should be 'Temperate'"(){
        Logger.logMessage("Test Case 1 - start");
        given : "a database containing statistics regarding planets from Star Wars"
        //NA - the DB is set up in the setupSpec portion of the Spec
        when: "the climate of the planet named 'Bespin' is queried"
        String result = planetsRepository.getClimateForPlanet("Bespin")
        then: "the result should be equal to Temperate"
        result.equals("temperate")
    }

    def cleanup(){
        Logger.logMessage("Cleanup method - Runs after every feature method.");
    }
    def cleanupSpec(){
        Logger.logMessage("cleanupSpec() - Runs only once per specification");

    }

}