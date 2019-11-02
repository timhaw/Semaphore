import com.intercress.*

def call(String playbook) {
  
//  def wibble = Semaphore.foo
  def wibble = new Semaphore().Semapi(playbook)
  
  echo "Hello, ${wibble}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
