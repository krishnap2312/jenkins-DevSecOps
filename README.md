# jenkins-DevSecOps


        stage('9. Terraform IaC & Checkov Scan') {
            steps {
                echo 'Checking Infrastructure as Code Security...'
                dir('terraform-k8s') {
                    sh 'export TF_LOG=INFO'
                    sh 'terraform init'
                    sh 'terraform plan -out=tfplan'
                    // టెర్రాఫామ్ కోడ్ లో సెక్యూరిటీ మిస్-కాన్ఫిగరేషన్స్ ని చెక్ చేయడం
                    sh 'checkov -d . --framework terraform' 
                }
            }
        }
