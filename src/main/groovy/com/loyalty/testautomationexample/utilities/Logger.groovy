package com.loyalty.testautomationexample.utilities

import groovy.util.logging.Slf4j


@Slf4j
class Logger {

    final static String LOG_TITLE = "\n\n[TEST AUTOMATION]"

    def static logRequest(def request, String EventName) {
        log.info("\n\n$LOG_TITLE $EventName Request:{}", objToString(request))
    }


    def static logEvent(def request, def response, String EventName, String transactionData) {
        log.info("\n\n$LOG_TITLE $EventName Request:{}", objToString(request))
        log.info("\n\n$LOG_TITLE $EventName Response:{}", objToString(response))
        log.info("\n$LOG_TITLE $transactionData")
    }

    def static logValidation(String actualResult, String expectedResult) {
        log.info("$LOG_TITLE\nTransaction History Event Expected: \n$expectedResult\n")
        log.info("$LOG_TITLE\nTransaction History Event(s) Actual: \n$actualResult")
    }

    def static logError(String error) {
        log.info("$LOG_TITLE\n[ERROR]: $error\n")
    }

    static String objToString(Object obj) {
        String stringObject = ""
        String objectClassName = obj.getClass().toString()
        obj.getProperties().each {
            stringObject += "\n\t${it.getKey().toString()} = ${it.getValue().toString()}"
        }
        stringObject
    }

    static def logEventValidationError(String validationField, def expectedResult, def actualResult) {
        String errorMessage = "\n\tExpected $validationField: ${expectedResult.toString()}"
        errorMessage <<= "\n\tActual   $validationField: ${actualResult.toString()}\n"
        Logger.logError(errorMessage)
    }

    def static logValidationValues(String actualResult, String expectedResult, String message = "") {
        String messageOutput = "$LOG_TITLE\n"
        if (message.length() > 0) {
            messageOutput <<= "$message\n"
        }
        messageOutput <<= "Expected Result Data: \n$expectedResult\n"
        messageOutput <<= "Actual Result Data: \n$actualResult"
        log.info(messageOutput)
    }

    static def logMessage(String message) {
        log.info("$LOG_TITLE\n$message\n")
    }
}