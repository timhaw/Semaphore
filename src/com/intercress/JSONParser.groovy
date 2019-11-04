#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {

    static parseProjects(String text, String name) {
        def slurper = new JsonSlurper()
        def parsedText = slurper.parseText(text)
        def String project = parsedText.find { it.name == name }.id
        return project
    }

    static parseTemplates(String text, String id, String name) {
        def slurper = new JsonSlurper()
        def parsedText = slurper.parseText(text)
        def String template = parsedText.find { it.project_id == id }.id
        return template
    }
}
