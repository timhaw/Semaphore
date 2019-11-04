import com.intercress.*
import groovy.json.JsonOutput

String httpRequestCookie(String username, String password) {
    def credentials = JsonOutput.toJson([auth: username, password: password])
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.httpMode = 'POST'
    requestParams.requestBody = credentials
    requestParams.url = 'http://localhost:3000/api/auth/login'
    def cookie = httpRequest requestParams
    def cookieContent = cookie.headers.get("Set-Cookie")    
    return cookieContent
}

String httpGetProjects(String cookie) {
    def cookieHeader = [:]
    cookieHeader.name = 'Cookie'
    cookieHeader.value = cookie
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.customHeaders = [cookieHeader]
    requestParams.httpMode = 'GET'
    requestParams.url = 'http://localhost:3000/api/projects'
    def response = httpRequest requestParams
    return response.content
}

String httpGetTemplates(String cookie) {
    def cookieHeader = [:]
    cookieHeader.name = 'Cookie'
    cookieHeader.value = cookie
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.customHeaders = [cookieHeader]
    requestParams.httpMode = 'GET'
    requestParams.url = 'http://localhost:3000/api/project/1/templates?sort=alias&order=asc'
    def response = httpRequest requestParams
    return response.content
}

String httpSendTask(String playbook, String cookie) {
    def schema = JsonOutput.toJson([template_id: 1, debug: false, dry_run: false, playbook: playbook, environment: ''])
    def cookieHeader = [:]
    cookieHeader.name = 'Cookie'
    cookieHeader.value = cookie
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.customHeaders = [cookieHeader]
    requestParams.httpMode = 'POST'
    requestParams.requestBody = schema
    requestParams.url = 'http://localhost:3000/api/project/1/tasks'
    def response = httpRequest requestParams
    return response
}

def call(String playbook) {
    node {
        def String cookie
        stage ('authenticate') {
            def credentials = [:]
            credentials.credentialsId = 'semaphore'
            credentials.usernameVariable = 'username'
            credentials.passwordVariable = 'password'
            withCredentials([usernamePassword(credentials)]) {
                cookie = httpRequestCookie(username, password)[0]
            }
        }
    
        stage ('project') {
            projects = httpGetProjects(cookie)
//            projects = '[{"id":1,"ssh_key_id":1,"project_id":1,"inventory_id":1,"repository_id":1,"environment_id":null,"alias":"Ansible","playbook":"local.yml","arguments":null,"override_args":false},{"id":2,"ssh_key_id":1,"project_id":1,"inventory_id":1,"repository_id":1,"environment_id":null,"alias":"Test","playbook":"test.yml","arguments":null,"override_args":false}]'
            jsonText = readJSON text: projects
            project = Semaphore.FindProject(projects, playbook)
//            assert projects.playbook == 'local.yml'
//            project = jsonText.find { it.value.name == 'Ansible' }
//            project = jsonText.find { it.value.name == 'katone' }     // No such property: name for class: java.lang.String
//            assertTrue(map.find{it.value == "New York"}.key == "city")
//            assertTrue(jsonText.find { it.value == "local.yml"}.key == "playbook")
//            project = jsonText.find { it.key == 'playbook' }
//            id = project.value.id
        }
    
        stage ('template') {
            templates = httpGetTemplates(cookie)
        }
    
        stage ('playbook') {
            status = httpSendTask(playbook, cookie)
        }
    
        echo "Hello, ${project}"
    }
}
