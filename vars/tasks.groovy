import com.intercress.*

def call(String name = 'human') {
  echo "Hello, ${name}."
}

//def call(String playbook = 'local.yml') {
//    def newString = new Semaphore().Semapi(playbook)
//    echo ${playbook}
//}
