import com.intercress.*

def call(String playbook) {
  echo "Hello, ${Semaphore.Semapi(playbook)}"
}

//def call(String playbook = 'local.yml') {
//    echo ${playbook}
//}
