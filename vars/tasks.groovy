import com.intercress.*

def call(String playbook) {
  
//  def wibble = Semaphore.foo
  def wibble = new Semaphore()
  
  def retval = wibble.Semapi(playbook)
  
  echo "Hello, ${wibble.retval}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
