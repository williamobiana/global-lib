//startfile
this.tests = [
    "Null map returns error": {
        ->
        def error = false

        try{
            dockerLogin((Map)null)
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Empty map returns error": {
        ->
        def error = false

        try{
            dockerLogin((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Missing parameter dockerRegistry returns error": {
        ->
        def error = false

        try{
            dockerLogin([repositoryUser: "abc"])
        } catch(Exception e){
            error = true
            assert e.getMessage() == "required parameter: dockerRegistry not provided or was empty"
        }

        assert error == true
    },
]

return this