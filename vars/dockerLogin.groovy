// startfile
/**
* @description Provides a declarative pipeline capability for logging to Docker registry
*
* @param String repositoryUser - ECR repository user (optional)
* @param String dockerRegistry - ECR repository (mandatory)
*
* @sample
*   dockerLogin(
*       dockerRegistry: '123456789012.dkr.ecr.us-east-1.amazonaws.com',
*       repositoryUser: 'AWS'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----dockerLogin - Entry-----')
    log.info params: params

    def repositoryUser = defaultIfNullOrEmpty(params['repositoryUser'], 'AWS')
    def registry = required(params, 'dockerRegistry')
    log.info(message: "logging into: ${params}")
    log.info(message: "registry: ${registry}")
    log.info(message: "repositoryUser: ${repositoryUser}")

    def script = "aws ecr get-login-password --region us-east-1 | sudo docker login --username ${repositoryUser} --password-stdin ${registry}"
    log.info script: "${script}"

    try{
        retry(1){
            def loginOutput = sh(script: script, returnStdout: true)
            log.info output: "Docker Login Output: ${loginOutput}"
        }
    } catch(Exception e){
        def errorString = currentBuild.rawBuild.getLog(4)[1]
        log.error message: "Error logging to docker repository"
        error(sprintf("\n\n${errorMessage}\n\n${errorSolutionFormatted}\n"))
    }

    log.info(functionName: '-----dockerLogin - Exit-----')
}