// startfile
/**
* @description Provides a declarative pipeline capability to create or select terraform workspace
*
* @param String terraformDir - full path of directory where terraform code resides (optional)
* @param List backendConfig - backend configuration file
*
* @sample
*   terraformWorkspace(
*       workspace: 'workspace-name'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----terraformWorkspace - Entry-----')
    log.info params: params

    def terraformWorkspace = firstNonNull(params["terraformWorkspace"], 'default')

    def terraformInitOutput = sh(script: "terraform workspace new ${terraformWorkspace} || terraform workspace select ${terraformWorkspace}", returnStdout: true)
    log.info output: "${terraformInitOutput}"

    log.info(functionName: '-----terraformWorkspace - Exit-----')
}