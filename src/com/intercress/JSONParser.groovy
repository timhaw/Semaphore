#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static String parseJson(String jsonText) {
        def slurper = new JsonSlurper()
        def String parsedJson = slurper().parseText(jsonText)
        return parsedJson
    }
}
