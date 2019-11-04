#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static parseJson() {
        def slurper = new JsonSlurper()
        def String jsonText = '[{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""},{"id":2,"name":"Test","created":"2019-11-03T10:20:26Z","alert":false,"alert_chat":""}]'
        def parsedText = slurper.parseText(jsonText)
        def String project = parsedText.find { it.name == 'Test' }.id
        return project
    }
}
