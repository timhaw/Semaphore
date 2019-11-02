import com.intercress.*

def call(String playbook) {
  
    static def myMethod() {
        httpRequest acceptType: 'APPLICATION_JSON', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', url: "http://localhost:3000/api/auth/login"
    }
  
  myMethod()
  
  //  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  retval = wibble.Semapi(playbook)
  
  echo "Hello, ${retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
