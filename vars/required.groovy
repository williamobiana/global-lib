// startfile
/**
* @description Provides utility function to validate the mandatory arguments
*
* @param Map params - map containing the parameter (mandatory)
* @param String paramName - parameter name that is required to be validated in map object (mandatory)
*
* @note Utility returns defaultVal if val is null or empty
*
* @required params, paramName
*
* @sample
*   required(params, "paramName")
*/

def call(Map params, String paramName) {
    if (params == null) {
        error "params object cannot be null or empty"
        return null
    }

    def val = params["${paramName}"]
    if (val == null || val == "") {
        error "required parameter: ${paramName} not provided or was empty"
    }

    return val
}