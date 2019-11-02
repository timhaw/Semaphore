import com.intercress.Semaphore

def call(String playbook = 'local.yml') {
    new Semaphore().semapi(playbook)
}
