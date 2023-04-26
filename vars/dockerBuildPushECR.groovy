// startfile
/**
* @description Provides a declarative pipeline capability for building and tagging and pushing Docker image to Docker registry
*
* @param String dockerfilePath - full path and name of the Dockerfile in the source repository (mandatory)
* @param String imageName - name of the image (mandatory)
* @param List imageTag - to be used as image tag (optional - default is latest)
* @param String dockerRegistry - ECR registry (mandatory)
*
* @required dockerfilePath, imageName, imageTag, dockerRegistry
*
* @sample
*   dockerBuild(
*       dockerfilePath: 'Dockerfile',
*       imageName: 'selfservice-backend',
*       imageTag: 'test-1',
*       additionalArgs: '--build-arg TAG=123456789012.dkr.ecr.us-east-1.amazonaws.com/nodejs:14.0.1'
*       dockerRegistry: '123456789012.dkr.ecr.us-east-1.amazonaws.com'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----dockerBuildPushECR - Entry-----')
    log.info params: params

    dockerBuild(params)
    dockerPush(params)

    log.info(functionName: '-----dockerBuildPushECR - Exit-----')
}