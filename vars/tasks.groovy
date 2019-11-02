import com.intercress.*
import groovy.json.JsonOutput

def myMethod() {
    def credentials = JsonOutput.toJson([auth: 'admin', password: 'admin'])
    def cookie = httpRequest acceptType: 'APPLICATION_JSON', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: credentials, url: "http://localhost:3000/api/auth/login"
    return cookie
}
  
def call(String playbook) {
  
  retval = myMethod()
  
  //  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  // retval = wibble.Semapi(playbook)
  
  echo "Hello, ${retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
