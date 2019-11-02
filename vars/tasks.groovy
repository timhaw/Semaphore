import com.intercress.*

def call(String) {
  echo "Hello, ${GlobalVars.foo}"
}

//def call(String playbook = 'local.yml') {
//    def newString = new Semaphore().Semapi(playbook)
//    echo ${playbook}
//}
