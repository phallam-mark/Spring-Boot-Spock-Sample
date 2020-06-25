package com.loyalty.testautomationexample.restClient

import com.loyalty.testautomationexample.utilities.Logger
import org.apache.http.HttpRequest
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SwapiClient {

    @Value('${test.swapi.url}') String BASEURL
    @Value('${test.swapi.character-path}') String CHARACTERS_PATH
    @Value('${test.swapi.planet-path}') String PLANETS_PATH

    HttpResponse getCharacters(){
        String url = BASEURL + CHARACTERS_PATH
        HttpRequest request = getAPIRequest(url)
        HttpResponse response = sendRequest(request)
        return response
    }

    HttpResponse getPlanetDetails(String planetName){
        String url = BASEURL + PLANETS_PATH + "?search=${planetName}"
        HttpRequest request = getAPIRequest(url)
        HttpResponse response = sendRequest(request)
        if(!response.getStatusLine().statusCode == 200){
            Logger.logMessage("unexpected response from API: " + response.getStatusLine().toString())
        }
        return response
    }

    HttpRequest getAPIRequest(String url) {
        HttpRequest request = new HttpGet(url)
        request.addHeader("ACCEPT_LANGUAGE", "EN")
        request.addHeader("Accpet", "application/json")
        request
    }

    HttpResponse sendRequest(HttpRequest request) {
        HttpClient client = HttpClientBuilder.create()
                .build()
        Logger.logMessage("sending get request to: " + request.getURI().toString())
        client.execute(request)
    }
}