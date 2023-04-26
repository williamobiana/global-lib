// startfile
/**
* @description Provides utility function to check the nullability and return null values if passed value is null
*
* @param String val - current value
*
* @note Utility returns val or null (return null if val is null)
*
* @required params, paramName
*
* @sample
*   firstNonNull(params['file'], "file.xml")
*/

def call(Object ... maybeValues) {
    for(maybeValue in maybeValues) {
        if(maybeValue != null) {
            return maybeValue
        }
    }
    return null
}