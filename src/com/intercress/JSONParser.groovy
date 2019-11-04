#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static parseJson(String jsonText, String jobName) {
        def slurper = new JsonSlurper()
        def parsedText = slurper.parseText(jsonText)
        def String id = parsedText.find { it.name == jobName }.id
        return id
    }
}
