import com.intercress.semaphore

def call(String playbook = 'local.yml') {
    new semaphore().semapi(playbook)
}
