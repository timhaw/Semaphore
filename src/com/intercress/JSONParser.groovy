#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonSlurper

class JSONParser {
    static parseJson() {
        def slurper = new JsonSlurper()
//        return slurper.parseText('{"person":{"name":"Guillaume","age":33,"pets":["dog","cat"]}}')
        return slurper.parseText('{"id":63,"template_id":1,"status":"waiting","debug":false,"dry_run":false,"playbook":"local.yml","environment":"","user_id":1,"created":"2019-11-04T17:42:19.313545224Z","start":null,"end":null}'
    }
}
