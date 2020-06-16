package com.loyalty.testautomationexample

import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@Slf4j
@SpringBootApplication()
class TestAutomationExample {
    static void main(String[] args) {
        String testStarted = "\n\n\n\t\t===============================================================================\n\t\t" +
                "======                         Test Automation Example Started                       ======\n\t\t" +
                "===============================================================================\n"
        log.info(testStarted)
        SpringApplication.run(TestAutomationExample.class, args)
    }
}
