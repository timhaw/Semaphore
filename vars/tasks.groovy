import com.intercress.*
evaluate(new File("httpRequestCookie.groovy"))

def call(String playbook) {
  
  //  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  // retval = wibble.Semapi(playbook)
  retval = httpRequestCookie()
  
  echo "Hello, ${retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
