#!/usr/bin/env groovy
package com.intercress

class Semaphore {
                  
    static String FindProject(String projects, String playbook) {

        def String jsonText = '[{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""},{"id":2,"name":"Test","created":"2019-11-03T10:20:26Z","alert":false,"alert_chat":""}]'
           
        def String project = new JSONParser().parseJson()
           
        return project
    }
}
