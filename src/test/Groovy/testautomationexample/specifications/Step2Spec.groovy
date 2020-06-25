package testautomationexample.specifications

import spock.lang.Shared
// this is a new addition for reading CSV files - need to add to build.csv too
import com.xlson.groovycsv.*
import com.loyalty.testautomationexample.utilities.Logger

class Step2Spec extends BaseSpec {

    // adding file write + read to step 1
    @Shared
    String filePath = new String(FILE.toString())

    @Shared File planetFile

    def setupSpec(){
        Logger.logMessage("setupSpec() - Runs once per Specification")
        Logger.logMessage("file path/name is: " + filePath)
        //in case the output file already exists from a previous test run, delete it
        new File(filePath).delete()
        //create a file and write some CSV lines to it
        planetFile = new File(filePath)
        planetFile.write("NAME,DIAMETER,CLIMATE,TERRAIN\n")
        planetFile.append("Tatooine,10465,arid,desert\n")
        planetFile.append("Bespin,118000,temperate,gas giant\n")
        planetFile.append("Kamino,19720,temperate,ocean\n")
    }

    def setup() {
        Logger.logMessage("setup() - Runs before every feature method")
    }
    def "TestCase 1 - verify that the file we created in the setupSpec step exists"(){
        Logger.logMessage("TestCase 1 - start")
        given : "the starWarsPlanets file has been created"
        //NA - was created in the setupSpec portion of the Spec
        when: "the existence of the file is verified"
        boolean fileExists = planetFile.exists()
        then: "the file should exist"
        fileExists
    }

    def "TestCase 2 - verify that the file we created in the setupSpec step contains the correct number of lines"(){
        Logger.logMessage("TestCase 2 - start")
        given : "the starWarsPlanets file has been created and lines of text have been written to it"
        //NA - was created in the setupSpec portion of the Spec
        when: "the lines in the file are counted"
        ArrayList lines = planetFile.readLines()
        then: "the number of lines should equal 4"
        lines.size() == 4
    }

    def "TestCase 3 - verify that we can read the contents of the starWarsPlanets file"(){
        given : "a csv file"
        //NA - file was created in setupSpec portion of the Spec
        when: "the file is read and parsed"
        //read the contents of the file into one long string
        String csv_content = planetFile.getText('utf-8')
        then: "the climate of the planet Tatooine should be arid"
        boolean result = false
        //parse the string into key value pairs
        CsvIterator data_iterator = new CsvParser().parse(csv_content);
        //iterate through the data 'records' and see if there's a match
        for(line in data_iterator){
            if(line.NAME == "Tatooine" && line.CLIMATE == "arid"){
                result = true;
            }
        }
        result
    }

    def cleanup(){
        Logger.logMessage("Cleanup method - Runs after every feature method.")
    }
    def cleanupSpec(){
        Logger.logMessage("cleanupSpec() - Runs only once per specification")
    }
}