// startfile
/**
* @description Provides a declarative pipeline capability to provision infrastructure using terraform
*
* @param String terraformDir - full path of directory where terraform code resides (optional)
* @param String workspace - name of the image
* @param List backendConfig - backend configuration file
* @param String terraformVarFiles - list of terraform variables
* @param String terraformPlanFile - name of terraform plan file
*
* @required terraformPlanFile
*
* @sample
*   provisionInfraUsingTerraform(
*       terraformVarFiles: ["./variables/var1.tfvars", "./variables/var2.tfvars"],
*       terraformDir: 'path/to/terraform',
*       backendConfig: 'path/to/backendConfig-file'
*       workspace: 'workspace-name',
*       terraformPlanFile: 'tf.plan'
*   )
*/

def call(Map params = [:]) {
    log.info(functionName: '-----provisionInfraUsingTerraform - Entry-----')
    log.info params: params

    // run terraformInit
    terraformInit(params)

    // run terraform workspace
    terraformWorkspace(params)

    // run terraform plan
    terraformPlan(params)

    // run terraform apply
    terraformApply(params)

    log.info(functionName: '-----provisionInfraUsingTerraform - Exit-----')
}