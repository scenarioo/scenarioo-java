def gradle(tasks) {
	 sh "./gradlew --info -s --no-daemon $tasks"
}

properties([
		  pipelineTriggers([
					 [$class: 'GitHubPushTrigger']
		  ])
])

timestamps {

    node {

	    stage('Clean Checkout') {
            checkout scm
            // Clean build: ensure no unversioned files disturbing the build
            sh "git clean -x -d -f"
        }

        stage('Build & Test') {

            try {

                gradle '-x signArchives clean build'

            } finally {

                junit '**/build/test-results/*.xml'

                archiveArtifacts artifacts: 'build/documentation.zip'

            }

        }

    }

}
