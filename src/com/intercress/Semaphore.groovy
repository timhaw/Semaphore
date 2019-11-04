#!/usr/bin/env groovy
package com.intercress

// class HelloWorld implements Serializable {
class Semaphore implements Serializable {
       
    static String foo = 'bar'
       
    static String FindProject(String projects, String playbook) {
           
        def people = [
            [name:'Bob', age: 32, gender: 'M'],
            [name:'Johnny', age: 36, gender: 'M'],
            [name:'Claire', age: 21, gender: 'F'],
            [name:'Amy', age: 54, gender:'F']
        ]

        def bob = people.find { it.value.name == 'Bob' } // find a single entry
        def ageOfBob = bob.value.age

           
//        def String parsedJson = readJSON text: '{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""}'
//        def String  project = projects.find { it.value.name == 'Ansible' }
//        def String _projects = [id:1,name:'Ansible',created:'2019-10-29T17:03:53Z',alert:false,alert_chat:'']
//        def String  project = _projects.find { it.value.name == 'Ansible' }
//            project = jsonText.find { it.value.name == 'katone' }     // No such property: name for class: java.lang.String
//            assertTrue(map.find{it.value == "New York"}.key == "city")
//            assertTrue(jsonText.find { it.value == "local.yml"}.key == "playbook")
//            project = jsonText.find { it.key == 'playbook' }
//            id = project.value.id
        return ageOfBob
    }
}
