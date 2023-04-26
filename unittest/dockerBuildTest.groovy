//startfile
this.tests = [
    "Null map returns error": {
        ->
        def error = false

        try{
            dockerBuild((Map)null)
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Empty map returns error": {
        ->
        def error = false

        try{
            dockerBuild((Map)"")
        } catch(Exception e){
            error = true
        }

        assert error == true
    },
    "Missing parameter imageName returns error": {
        ->
        def error = false

        try{
            dockerBuild([buildPath: ".", dockerfilePath: "./Dockerfile"])
        } catch(Exception e){
            error = true
            assert e.getMessage() == "required parameter: imageName not provided or was empty"
        }

        assert error == true
    },
    "Missing parameter dockerRegistry returns error": {
        ->
        def error = false

        try{
            dockerBuild([buildPath: ".", dockerfilePath: "./Dockerfile", imageName: "test-image"])
        } catch(Exception e){
            error = true
            assert e.getMessage() == "required parameter: dockerRegistry not provided or was empty"
        }

        assert error == true
    },
]

return this