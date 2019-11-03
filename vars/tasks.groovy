import com.intercress.*
import groovy.json.JsonOutput

String myMethod() {
    def credentials = JsonOutput.toJson([auth: 'admin', password: 'admin'])
    def cookie = httpRequest acceptType: 'APPLICATION_JSON', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: credentials, url: "http://localhost:3000/api/auth/login"
    def cookieContent = cookie.headers.get("Set-Cookie")    
    return cookieContent
}
  
def call(String playbook) {
  
  
  //  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  // retval = wibble.Semapi(playbook)
  retval = myMethod()
  
  echo "Hello, ${retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
