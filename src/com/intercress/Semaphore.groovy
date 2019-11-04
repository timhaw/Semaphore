#!/usr/bin/env groovy
package com.intercress

// class HelloWorld implements Serializable {
class Semaphore implements Serializable {
       
    static String foo = 'bar'
       
    static String FindProject(String projects, String playbook) {
        def String parsedJson = readJSON text: '{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""}'
//            project = jsonText.find { it.value.name == 'Ansible' }
//            project = jsonText.find { it.value.name == 'katone' }     // No such property: name for class: java.lang.String
//            assertTrue(map.find{it.value == "New York"}.key == "city")
//            assertTrue(jsonText.find { it.value == "local.yml"}.key == "playbook")
//            project = jsonText.find { it.key == 'playbook' }
//            id = project.value.id
        return parsedJson
    }
}
