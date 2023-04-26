// startfile
/**
* @description Provides a declarative pipeline capability to initialize terraform module
*
* @param String terraformDir - full path of directory where terraform code resides (optional)
* @param List backendConfig - backend configuration file
*
* @sample
*   terraformInit(
*       terraformDir: 'path/to/terraform',
*       backendConfig: 'path/to/backendConfig-file'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----terraformInit - Entry-----')
    log.info params: params

    def terraformDir = firstNonNull(params["terraformDir"], '.')
    def backendConfig = firstNonNull(params["backendConfig"], "")

    if(backendConfig == ""){
        def terraformInitOutput = sh(script: 'terraform init ', returnStdout: true)
        log.info output: "Terraform Init Output: ${terraformInitOutput}"
    } else {
        def terraformInitOutput = sh(script: "terraform init --backend-config ${backendConfig}", returnStdout: true)
        log.info output: "Terraform Init Output: ${terraformInitOutput}"
    }

    log.info(functionName: '-----terraformInit - Exit-----')
}