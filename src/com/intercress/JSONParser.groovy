#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    def jsonSlurper = new JsonSlurper()
    static String parsedJson = jsonSlurper().parseText(String text) {
        return parsedJson
    }
}
