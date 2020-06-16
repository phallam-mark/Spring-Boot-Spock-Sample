package testautomationexample.specifications

import com.loyalty.testautomationexample.utilities.Logger
import groovy.sql.GroovyRowResult
import spock.lang.Shared
// NOTE: need to add groovy.sql and h2 dependencies for this spec
import groovy.sql.Sql
//import org.h2.jdbcx.*


class Step3Spec extends BaseSpec {
    // a Spock Specification that queries data from a database and validates it

    //@Shared because this resource will be initialized once in the setupSpec section and shared amongst multiple tests
    //creating a small in-memory db for illustration purposes. In real life, could connect to any DB
    @Shared sql = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver");

    //create the table and insert data (usually DB would already contain the data)
    def setupSpec(){
        Logger.logMessage("setupSpec() - Runs once per Specification");
        sql.execute("create table starWarsPlanets (name varchar(20) primary key, diameter int, climate varchar(20), terrain varchar(20))");
        sql.execute("insert into starWarsPlanets values(:name, :diameter, :climate, :terrain)", [name: 'Tatooine', diameter: 10465, climate: 'arid', terrain: 'desert'])
        sql.execute("insert into starWarsPlanets values(:name, :diameter, :climate, :terrain)", [name: 'Bespin', diameter: 118000, climate: 'temperate', terrain: 'gas giant'])
        sql.execute("insert into starWarsPlanets values(:name, :diameter, :climate, :terrain)", [name: 'Kamino', diameter: 19720, climate: 'temperate', terrain: 'ocean'])
    }

    def setup() {
        Logger.logMessage("setup() - Runs before every feature method");
    }
    def "Test Case 1 - test a count of the rows in the DB"(){
        Logger.logMessage ("Test Case 1 - start");
        given : "a database containing statistics regarding planets from Star Wars"
        //NA - the DB is set up in the setupSpec portion of the Spec
        when: "the number of records in the database is queried"
        GroovyRowResult result = sql.firstRow("select count(*) as cnt from starWarsPlanets");
        long count = result.cnt;
        then: "the count should be equal to 3"
        count == 3;
    }

    def "Test Case 1 - the value of 'climate' for the planet Bespin should be 'Temperate'"(){
        Logger.logMessage("Test Case 1 - start");
        given : "a database containing statistics regarding planets from Star Wars"
        //NA - the DB is set up in the setupSpec portion of the Spec
        when: "the climate of the planet named 'Bespin' is queried"
        List<GroovyRowResult> result = sql.rows("select climate from starWarsPlanets where name = 'Bespin'");
        then: "the number of rows returned should be equal to 1"
        result.size() == 1;
        and: "the value of climate for the planet 'Bespin' should be 'temperate'"
        result[0].getProperty("CLIMATE") == "temperate";
    }

    def cleanup(){
        Logger.logMessage("Cleanup method - Runs after every feature method.");
    }
    def cleanupSpec(){
        Logger.logMessage("cleanupSpec() - Runs only once per specification");
        //housekeeping - close sql connection
        sql.close();
    }

}