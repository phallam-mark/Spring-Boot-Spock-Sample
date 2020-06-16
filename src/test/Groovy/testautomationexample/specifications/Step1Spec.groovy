package testautomationexample.specifications

import com.loyalty.testautomationexample.utilities.Logger
import spock.lang.Shared
//import spock.lang.Specification

class Step1Spec extends BaseSpec {

    // a very simple Spock Specification comparing two strings to illustrate the form and function of the framework

    //@Shared because this resource will be initialized once in the setupSpec section and shared amongst multiple Feature Methods within the spec
    @Shared String string1
    String string2
    String string3

    def setupSpec(){
        Logger.logMessage("setupSpec() - Runs once per Specification")
        string1 = "StringyStringString"
    }
    def setup() {
        Logger.logMessage("setup() - Runs once before every feature method")
    }
    def "Feature method 1 - a demonstration of a test that passes"(){
        Logger.logMessage("Feature method 1 - start");
        given : "2 strings to be compared, string 1 and string 2"
        //NA - string 1 has alredy been initialized in setupSpec
        when: "the second string is set to be equal to the first string"
        string2 = new String(string1)
        then: "when the two string are compared, they should be equal"
        string1.equals(string2)
        and: "if they are compared this way, they should also be equal"
        string1 == string2
    }

    def "Feature method 2 - a demonstration of a test that fails" () {
        Logger.logMessage("Feature method 2 - start");
        given : "2 strings to be compared, string 1 and string 3"
        //NA - string 1 has alredy been initialized in setupSpec
        when: "the second string is set to be a lower-case version of the first string"
        string3 = new String(string1.toLowerCase())
        then: "when the two strings are compared, they will not be equal and the test will fail"
        string1.equals(string3)
    }

    def cleanup(){
        Logger.logMessage("Cleanup method - Runs after every feature method.")
    }
    def cleanupSpec(){
        Logger.logMessage("cleanupSpec() - Runs only once per specification")
    }
}