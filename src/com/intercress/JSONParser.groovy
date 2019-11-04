#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static parseJson() {
        def slurper = new JsonSlurper()
//        return slurper.parseText('{"person":{"name":"Guillaume","age":33,"pets":["dog","cat"]}}')
        def String jsonText = '[{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""},{"id":2,"name":"Test","created":"2019-11-03T10:20:26Z","alert":false,"alert_chat":""}]'
        def parsedText = slurper.parseText(jsonText)
        def String project = parsedText.find { it.name == 'Test' }.id
//        def result = project.value.id
        
//        assert slurper.name == 'Ansible'
//        return slurper.parseText('{"id":63,"template_id":1,"status":"waiting","debug":false,"dry_run":false,"playbook":"local.yml","environment":"","user_id":1,"created":"2019-11-04T17:42:19.313545224Z","start":null,"end":null}')
        return project
    }
}
