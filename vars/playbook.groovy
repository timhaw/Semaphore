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

String httpGetTemplates(String cookie, String project) {
    def cookieHeader = [:]
    cookieHeader.name = 'Cookie'
    cookieHeader.value = cookie
    def requestParams = [:]
    requestParams.acceptType = 'APPLICATION_JSON'
    requestParams.consoleLogResponseBody = true
    requestParams.contentType = 'APPLICATION_JSON'
    requestParams.customHeaders = [cookieHeader]
    requestParams.httpMode = 'GET'
    requestParams.url = "http://localhost:3000/api/project/${project}/templates?sort=alias&order=asc"
    def response = httpRequest requestParams
    return response.content
}

String httpSendTask(String cookie, String project, String template, String playbook) {
    def schema = JsonOutput.toJson([template_id: template.toInteger(), debug: false, dry_run: false, playbook: playbook, environment: ''])
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
    requestParams.url = "http://localhost:3000/api/project/${project}/tasks"
    def response = httpRequest requestParams
    return response
}

def call(String project, String playbook) {
    node {
        def String cookie
        def String project_id
        def String template_id
        
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
//            projects = httpGetProjects(cookie)
            response = httpRequest Semaphore.getProjects(cookie)
            projects = response.content
            project_id = Semaphore.FindProject(projects, project)
        }
    
        stage ('template') {
            response = httpRequest Semaphore.getTemplates(cookie, project_id)
            templates = response.content
            template_id = Semaphore.FindTemplate(templates, project_id, playbook)
        }
    
        stage ('playbook') {
            status = httpRequest Semaphore.sendTask(cookie, project_id, template_id, playbook)
        }

        echo "Hello, ${status}"
    }
}
