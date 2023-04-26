def call(Map params = [:]) {

  // print entry message
  log.info(functionName: '-----buildDockerImage - Entry-----')

  // log the parameter on the agent
  log.info params: params

  def region = params.AWS_DEFAULT_REGION
  def accountId = params.AWS_ACCOUNT_ID
  def sourceRepoName = params.SOURCE_IMAGE_REPO_NAME
  def sourceTag = params.SOURCE_IMAGE_TAG
  def destRepoName = params.DESTINATION_IMAGE_REPO_NAME
  def destTag = params.DESTINATION_IMAGE_TAG

  if (region == null || accountId == null || sourceRepoName == null || sourceTag == null || destRepoName == null || destTag == null) {
    // log error message
    log.info(functionName: 'buildDockerImage', message: 'The following variables must be present when using this Project: AWS_DEFAULT_REGION, AWS_ACCOUNT_ID, SOURCE_IMAGE_REPO_NAME, SOURCE_IMAGE_TAG, DESTINATION_IMAGE_REPO_NAME, DESTINATION_IMAGE_TAG')
    log.info(functionName: 'buildDockerImage', message: 'Default values are set for: AWS_DEFAULT_REGION, AWS_ACCOUNT_ID')

    // exit function
    return
  }

  // give the parameter a key:value
  params['ecrLoginCmd'] = "aws ecr get-login-password --region $region | docker login --username AWS --password-stdin $accountId.dkr.ecr.$region.amazonaws.com"
  params['pullCmd'] = "docker pull $accountId.dkr.ecr.$region.amazonaws.com/$sourceRepoName:$sourceTag"
  params['tagCmd'] = "docker tag $accountId.dkr.ecr.$region.amazonaws.com/$sourceRepoName:$sourceTag $accountId.dkr.ecr.$region.amazonaws.com/$destRepoName:$destTag"
  params['pushCmd'] = "docker push $accountId.dkr.ecr.$region.amazonaws.com/$destRepoName:$destTag"

  // use the parameter when running the sh() command
  sh(label: 'Logging in to Amazon ECR', script: params.ecrLoginCmd)
  sh(label: 'Pulling source image from Amazon ECR', script: params.pullCmd)
  sh(label: 'Tagging the source image as destination image', script: params.tagCmd)
  sh(label: 'Pushing destination image to Amazon ECR', script: params.pushCmd)

  // print exit message
  log.info(functionName: '-----buildDockerImage - Exit-----')
}

