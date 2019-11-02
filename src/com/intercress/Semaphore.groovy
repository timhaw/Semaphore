#!/usr/bin/env groovy
package com.intercress
import groovy.json.JsonOutput

class Semaphore {
    def Semapi(playbook) {

        def credentials = JsonOutput.toJson([auth: 'admin', password: 'admin'])    
        def schema = JsonOutput.toJson([template_id: 1, debug: false, dry_run: false, playbook: playbook, environment: ''])
        
        println(playbook)

//        def cookie = httpRequest( \
//            acceptType: 'APPLICATION_JSON', \
//            consoleLogResponseBody: true, \
//            contentType: 'APPLICATION_JSON', \
//            httpMode: 'POST', \
//            requestBody: credentials, \
//            url: "http://localhost:3000/api/auth/login")

//        def cookieContent = cookie.headers.get("Set-Cookie")    

//        httpRequest( \
//            acceptType: 'APPLICATION_JSON', \
//            consoleLogResponseBody: true, \
//            contentType: 'APPLICATION_JSON', \
//            customHeaders: [[name: 'Cookie', value: cookieContent[0]]], \
//            httpMode: 'POST', \
//            requestBody: schema, \
//            url: "http://localhost:3000/api/project/1/tasks")
//    }
}
