#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {

    static parseProjects(String projects, String name) {
        def slurper = new JsonSlurper()
        def parsedText = slurper.parseText(projects)
        def String project = parsedText.find { it.name == name }.id
        return project
    }

    static parseTemplates(String templates, String project, String name) {
        def slurper = new JsonSlurper()
        def parsedText = slurper.parseText(templates)
        def String template = parsedText.find { it.project_id == project }.id
        return template
    }
}
