// startfile
/**
* @description Provides a declarative pipeline capability to destroy provisioned resources using terraform destroy
*
* @param String terraformDir - full path of directory where terraform code resides (optional)
* @param String terraformVarFiles - list of terraform variables
*
* @required terraformPlanFile
*
* @sample
*   terraformDestroy(
*       terraformVarFiles: ["./variables/var1.tfvars", "./variables/var2.tfvars"],
*       terraformDir: 'path/to/terraform'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----terraformDestroy - Entry-----')
    log.info params: params

    def terraformDir = firstNonNull(params["terraformDir"], '.')
    def terraformVarFiles = defaultIfNullOrEmpty(params["terraformVarFiles"], "")
    def sbOptions = new StringBuilder()

    if (terraformVarFiles.size() > 0) {
        terraformVarFiles.each{
            file -> sbOptions.append(" -var-file=${file}")
        }
    }

    def options = sbOptions.toString().trim()
    log.info options: "${options}"

    sh("""
        cd ${terraformDir}
        terraform destroy -no-color --auto-approve ${options}
        """)

    log.info(functionName: '-----terraformDestroy - Exit-----')
}