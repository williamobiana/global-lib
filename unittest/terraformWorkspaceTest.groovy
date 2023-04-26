//startfile
this.tests = [
    "Null map returns error": {
        ->
        def error = false

        try{
            terraformWorkspace((Map)null)
        } catch(Exception e){
            error = true
            assert e.getMessage() == "Cannot get property 'workspace' on null object"
        }

        assert error == true
    },
    "Empty map returns error": {
        ->
        def error = false

        try{
            terraformWorkspace((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Valid map should switch workspace": {
        ->
        def error = false
        def params = [workspace: 'workspace-1']

        try{
            terraformWorkspace((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    }
]

return this