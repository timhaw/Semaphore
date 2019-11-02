import com.intercress.*

def call(String playbook) {
  echo "Hello, ${Semaphore.Semapi(String wibble = playbook)}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
