#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static parseJson(String jsonText, String project) {
        def slurper = new JsonSlurper()
        def parsedText = slurper.parseText(jsonText)
        def String id = parsedText.find { it.name == project }.id
        return id
    }
}
