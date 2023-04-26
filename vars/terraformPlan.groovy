// startfile
/**
* @description Provides a declarative pipeline capability to execute terraform plan
*
* @param String terraformDir - full path of directory where terraform code resides (optional)
* @param String workspace - name of the image
* @param String terraformVarFiles - list of terraform variables
* @param String terraformPlanFile - name of terraform plan file
*
* @required terraformPlanFile
*
* @sample
*   terraformPlan(
*       terraformVarFiles: ["./variables/var1.tfvars", "./variables/var2.tfvars"],
*       terraformDir: 'path/to/terraform',
*       workspace: 'workspace-name',
*       terraformPlanFile: 'tf.plan'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----terraformPlan - Entry-----')
    log.info params: params

    def terraformDir = firstNonNull(params["terraformDir"], '.')
    def terraformPlanFile = required(params, "terraformPlanFile")
    def terraformVarFiles = defaultIfNullOrEmpty(params["terraformVarFiles"], "")
    def sbOptions = new StringBuilder()

    // run terraform workspace
    terraformWorkspace(params)

    if (terraformVarFiles.size() > 0) {
        terraformVarFiles.each{
            file -> sbOptions.append(" -var-file=${file}")
        }
    }

    def options = sbOptions.toString().trim()
    log.info options: "${options}"

    sh("""
        cd ${terraformDir}
        terraform plan -no-color ${options} -out ${terraformPlanFile}
        """)

    log.info(functionName: '-----terraformPlan - Exit-----')
}