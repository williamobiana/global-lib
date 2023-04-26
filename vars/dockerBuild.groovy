// startfile
/**
* @description Provides a declarative pipeline capability for building and tagging Docker image
*
* @param String dockerfilePath - full path and name of the Dockerfile in the source repository (mandatory)
* @param String imageName - name of the image (mandatory)
* @param List imageTag - to be used as image tag (optional - default is latest)
* @param String additionalArgs - arguments used for docker build (default)
* @param String buildPath - path to build the Dockerfile (optional)
* @param String dockerRegistry - ECR registry (mandatory)
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
    log.info(functionName: '-----dockerBuild - Entry-----')
    log.info params: params

    def dockerfilePath = required(params, 'dockerfilePath')
    def imageName = required(params, 'dockerfilePath')
    def imageTag = defaultIfNullOrEmpty(params['imageTag'], 'latest')
    def additionalArgs = defaultIfNullOrEmpty(params['additionalArgs'], '')
    def buildPath = defaultIfNullOrEmpty(params['buildPath'], '.')
    def registry = required(params, 'dockerRegistry')
    def buildArgs = 'build'

    if (!fileExists(dockerfilePath)) {
        logAndRaiseError("The dockerfile %s does not exist", dockerfilePath)
    }

    buildArgs += " -t ${registry}/${imageName}:${imageTag}"

    if (params.additionalArgs) {
        buildArgs += " -f ${dockerfilePath} ${additionalArgs} ${buildPath}"
    } else {
        buildArgs += " -f ${dockerfilePath} ${buildPath}"
    }
    echo "buildArgs": ${buildArgs}

    // Call Docker Login From Library
    dockerLogin(params)

    def buildOutput = sh(script: "sudo docker ${buildArgs}" returnStdout: true)
    log.info output: "Docker Output: ${buildOutput}"

    log.info(functionName: '-----dockerBuild - Exit-----')
}