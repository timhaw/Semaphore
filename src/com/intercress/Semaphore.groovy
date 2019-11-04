#!/usr/bin/env groovy
package com.intercress

class Semaphore {
       
    static String foo = 'bar'
       
    static String FindProject(String projects, String playbook) {
        parsedJson = readJSON text: projects
//            project = jsonText.find { it.value.name == 'Ansible' }
//            project = jsonText.find { it.value.name == 'katone' }     // No such property: name for class: java.lang.String
//            assertTrue(map.find{it.value == "New York"}.key == "city")
//            assertTrue(jsonText.find { it.value == "local.yml"}.key == "playbook")
//            project = jsonText.find { it.key == 'playbook' }
//            id = project.value.id
        return parsedJson
    }
}
