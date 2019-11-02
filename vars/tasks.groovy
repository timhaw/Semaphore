import com.intercress.*

def call(String name = 'human') {
  echo "Hello, ${GlobalVars.foo}"
   // println GlobalVars.foo
}

//def call(String playbook = 'local.yml') {
//    def newString = new Semaphore().Semapi(playbook)
//    echo ${playbook}
//}
