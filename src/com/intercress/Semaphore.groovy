#!/usr/bin/env groovy
package com.intercress

// class HelloWorld implements Serializable {
class Semaphore {
       
   /**
    * Get the nearest object or array end
    */
    def getNearestEnd(String json, int start, String head, String tail) {
        def end = start
        def count = 1
        while (count > 0) {
            end++
            def c = json.charAt(end)
            if (c == head) {
                count++
            } else if (c == tail) {
                count--
            }
        }
        return end;
    }

    /**
     * Parse the object
     */
    def parseObject(String json) {
        def map = [:]
        def length = json.length()
        def index = 1
        def state = 'none' // none, string-value, other-value
        def key = ''
        while (index < length -1) {
            def c = json.charAt(index)
            switch(c) {
                case '"':
                    if (state == 'none') {
                        def keyStart = index + 1;
                        def keyEnd = keyStart;
                        while (json.charAt(keyEnd) != '"') {
                            keyEnd++
                        }
                        index = keyEnd
                        def keyValue = json[keyStart .. keyEnd - 1]
                        key = keyValue
                    } else if (state == 'value') {
                        def stringStart = index + 1;
                        def stringEnd = stringStart;
                        while (json.charAt(stringEnd) != '"') {
                            stringEnd++
                        }
                        index = stringEnd
                        def stringValue = json[stringStart .. stringEnd - 1]
                        map.put(key, stringValue)
                    }
                    break

                case '{':
                    def objectStart = index
                    def objectEnd = getNearestEnd json, index, '{', '}'
                    def objectValue = json[objectStart .. objectEnd]
                    map.put(key, parseObject(objectValue))
                    index = objectEnd
                    break

                case '[':
                    def arrayStart = index
                    def arrayEnd = getNearestEnd(json, index, '[', ']')
                    def arrayValue = json[arrayStart .. arrayEnd]
                    map.put(key, parseArray(arrayValue))
                    index = arrayEnd
                    break

                case ':':
                    state = 'value'
                    break

                case ',':
                    state = 'none'
                    key = ''
                    break;

                case ["\n", "\t", "\r", " "]:
                    break

                default:
                    break
            }
            index++
        }

    return map
}

    /**
     * Parse the array
     */
    def parseArray(String json) {
        def list = []
        def length = json.length()
        def index = 1
        def state = 'none' // none, string-value, other-value
        while (index < length -1) {
            def c = json.charAt(index)
            switch(c) {
                case '"':
                    def stringStart = index + 1;
                    def stringEnd = stringStart;
                    while (json.charAt(stringEnd) != '"') {
                        stringEnd++
                    }
                    def stringValue = json[stringStart .. stringEnd - 1]
                    list.add(stringValue)
                    index = stringEnd
                    break

                case '{':
                    def objectStart = index
                    def objectEnd = getNearestEnd(json, index, '{', '}')
                    def objectValue = json[objectStart .. objectEnd]
                    list.add(parseObject(objectValue))
                    index = objectEnd
                    break

                case '[':
                    def arrayStart = index
                    def arrayEnd = getNearestEnd(json, index, '[', ']')
                    def arrayValue = json[arrayStart .. arrayEnd]
                    list.add(parseArray(arrayValue))
                    index = arrayEnd
                    break

                case ["\n", "\t", "\r", " "]:
                    break

                case ',':
                    state = 'none'
                    key = ''
                    break;

                default:
                    break
            }
            index++
        }

        return list
    }

    /**
     * Parse the JSON, object or array
     */
    def parseJson(String json) {
        def start = json[0]
        if (start == '[') {
            return parseArray(json)
        } else if (start == '{') {
            return parseObject(json)
        } else {
            return null
        }
    }

       
    static String foo = 'bar'
       
    static String FindProject(String projects, String playbook) {
           
        def people = [1: [name:'Bob', age: 32, gender: 'M'], 2: [name:'Johnny', age: 36, gender: 'M'], 3: [name:'Claire', age: 21, gender: 'F'], 4: [name:'Amy', age: 54, gender:'F']]
        def Claire = people.find { it.value.name == 'Claire' } // find a single entry
        def ageOfClaire = Claire.value.age

//        [{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""},{"id":2,"name":"Test","created":"2019-11-03T10:20:26Z","alert":false,"alert_chat":""}]
        def _projects = [1: [id:1,name:'Ansible',created:'2019-10-29T17:03:53Z',alert:false,alert_chat:'']]
                      
           //        def project = _projects.find { it.value.name == 'Ansible' }
//        def id = project.value.id
           
//        def String parsedJson = readJSON text: '{"id":1,"name":"Ansible","created":"2019-10-29T17:03:53Z","alert":false,"alert_chat":""}'
//        def String  project = projects.find { it.value.name == 'Ansible' }
//            project = jsonText.find { it.value.name == 'katone' }     // No such property: name for class: java.lang.String
//            assertTrue(map.find{it.value == "New York"}.key == "city")
//            assertTrue(jsonText.find { it.value == "local.yml"}.key == "playbook")
//            project = jsonText.find { it.key == 'playbook' }
//            id = project.value.id
           
        // Test code
        def project = parseJson('{"abdef":["Jim","Tom","Sam",["XYZ","ABC"]],{"namek":["adbc","cdef"]}}')
           
        return project
    }
}
