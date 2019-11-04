#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static String parsedJson = new JsonSlurper().parseText(String text)
}
