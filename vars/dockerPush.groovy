// startfile
/**
* @description Provides a declarative pipeline capability to push docker image to docker registry
*
* @param String imageName - name of the image (mandatory)
* @param List imageTag - to be used as image tag (optional - default is latest)
* @param String dockerRegistry - ECR registry (mandatory)
*
* @required imageName, dockerRegistry
*
* @sample
*   dockerPush(
*       imageName: 'selfservice-backend',
*       imageTag: 'test-1',
*       dockerRegistry: '123456789012.dkr.ecr.us-east-1.amazonaws.com'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----dockerPush - Entry-----')
    log.info params: params

    def imageName = required(params, 'imageName')
    def imageTag = defaultIfNullOrEmpty(params['imageTag'], 'latest')
    def registry = required(params, 'dockerRegistry')

    def imagePushUrl = "${registry}/${imageName}:${imageTag}"
    log.info imagePushUrl: "${imagePushUrl}"

    def buildOutput = sh(script: "sudo docker push ${imagePushUrl}", returnStdout: true)
    log.info output: "Docker Push Output: ${buildOutput}"

    log.info(functionName: '-----dockerPush - Exit-----')
}