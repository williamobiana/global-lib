// startfile
/**
* @description Provides a declarative pipeline capability to apply terraform plan
*
* @param String terraformDir - full path of directory where terraform code resides (optional)
* @param String terraformPlanFile - name of terraform plan file
*
* @required terraformPlanFile
*
* @sample
*   terraformApply(
*       terraformDir: 'path/to/terraform',
*       terraformPlanFile: 'tf.plan'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----terraformApply - Entry-----')
    log.info params: params

    def terraformDir = firstNonNull(params["terraformDir"], '.')
    def terraformPlanFile = required(params, "terraformPlanFile")

    sh("""
        cd ${terraformDir}
        terraform apply -no-color --auto-approve ${terraformPlanFile}
        """)

    log.info(functionName: '-----terraformApply - Exit-----')
}