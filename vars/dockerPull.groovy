// startfile
/**
* @description Provides a declarative pipeline capability to pull docker image from docker registry
*
* @param String imageName - name of the image (mandatory)
* @param List imageTag - to be used as image tag (optional - default is latest)
* @param String dockerRegistry - ECR registry (mandatory)
*
* @required imageName, dockerRegistry
*
* @sample
*   dockerPull(
*       imageName: 'selfservice-backend',
*       imageTag: 'test-1',
*       dockerRegistry: '123456789012.dkr.ecr.us-east-1.amazonaws.com'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----dockerPull - Entry-----')
    log.info params: params

    def imageName = required(params, 'imageName')
    def imageTag = defaultIfNullOrEmpty(params['imageTag'], 'latest')
    def registry = required(params, 'dockerRegistry')

    def imagePullUrl = "${registry}/${imageName}:${imageTag}"
    log.info imagePullUrl: "${imagePullUrl}"

    // Call Docker Login From Library
    dockerLogin(params)

    def pullOutput = sh(script: "sudo docker pull ${imagePullUrl}", returnStdout: true)
    log.info output: "Docker Push Output: ${pullOutput}"

    log.info(functionName: '-----dockerPull - Exit-----')
}