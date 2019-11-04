#!/usr/bin/env groovy
package com.intercress

// class HelloWorld implements Serializable {
class Semaphore implements Serializable {
       
    static String foo = 'bar'
       
    static String FindProject(String projects, String playbook) {
           
        def people = [1: [name:'Bob', age: 32, gender: 'M'], 2: [name:'Johnny', age: 36, gender: 'M'], 3: [name:'Claire', age: 21, gender: 'F'], 4: [name:'Amy', age: 54, gender:'F']]
        def Claire = people.find { it.value.name == 'Claire' } // find a single entry
        def ageOfClaire = Claire.value.age

        def String _projects = [1: [id:1, name:'Ansible', created:'2019-10-29T17:03:53Z', alert:false, alert_chat:'']]
        def String  project = _projects.find { it.value.name == 'Ansible' }


           
//        def String parsedJson = readJSON text: '{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""}'
//        def String  project = projects.find { it.value.name == 'Ansible' }
//            project = jsonText.find { it.value.name == 'katone' }     // No such property: name for class: java.lang.String
//            assertTrue(map.find{it.value == "New York"}.key == "city")
//            assertTrue(jsonText.find { it.value == "local.yml"}.key == "playbook")
//            project = jsonText.find { it.key == 'playbook' }
//            id = project.value.id
        return project
    }
}
