#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    def parsedJson = new JsonSlurper().parseText(String text)
}
