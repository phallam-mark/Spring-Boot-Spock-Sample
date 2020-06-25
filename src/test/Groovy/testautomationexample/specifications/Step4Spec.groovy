package testautomationexample.specifications

import com.loyalty.testautomationexample.restClient.SwapiClient
import com.loyalty.testautomationexample.utilities.Logger
import org.apache.http.HttpResponse
import org.apache.http.HttpEntity
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.json.*

class Step4Spec extends BaseSpec {
    // adding a call to a REST API to Step 4

    @Autowired
    SwapiClient swapiClient

    def setupSpec(){
        Logger.logMessage("setupSpec() - Runs once per Specification");
    }

    def setup() {
        Logger.logMessage("setup() - Runs before every feature method");
    }

    def "TestCase 1 - verify that we can successfully make a call to a REST API"(){
        Logger.logMessage("TestCase 1 - start");
        given : "an API endpoint exists that provides data about Star Wars Movies (SWAPI)"
        // api client is automatically created by Spring via @Autowired
        when: "a call is made to the API to get a list of characters"
        HttpResponse response = swapiClient.getCharacters()
        then: "the HTTP response status should be 200"
        response.getStatusLine().statusCode == 200
    }

    def "TestCase 2 - verify the contents of the JSON response"() {
        Logger.logMessage("TestCase 2 - start");
        given: "an API endpoint exists that provides data about Star Wars Movies (SWAPI)"
        // api client is automatically created by Spring via @Autowired
        when: "a call is made to the API requesting data for the planet Tatooine"
        //HttpResponseDecorator JsonResponse = swClient.get(path: 'planets', query: ["search": "tatooine"]);
        HttpResponse httpResponse = swapiClient.getPlanetDetails("Tatooine")
        then: "the value of climate for the planet Tatooine should be arid"
        String climate
        try {
            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity != null) {
                String responseEntityString = EntityUtils.toString(responseEntity)
                // parsing JSON
                JSONObject result = new JSONObject(responseEntityString)
                //Convert String to JSON Object
                JSONArray tokenList = result.getJSONArray("results")
                JSONObject oj = tokenList.getJSONObject(0)
                climate = oj.getString("climate")
            }
        }
        catch (Exception e) {
            Logger.logMessage("unable to parse JSON in response")
        }
        climate.equals("arid")
    }

    def cleanup(){
        Logger.logMessage("Cleanup method - Runs after every feature method.");
    }
    def cleanupSpec(){
        Logger.logMessage("cleanupSpec() - Runs only once per specification");
    }
}