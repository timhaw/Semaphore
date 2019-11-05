import com.intercress.*

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
                response = httpRequest Semaphore.requestCookie(username, password)
                cookie = response.headers.get("Set-Cookie").join(', ')
            }
        }
    
        stage ('project') {
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

        echo "Task result: ${status}"
    }
}
