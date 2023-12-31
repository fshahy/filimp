docker build -t helidon-base-app .

kubectl create -f app.yaml

kubectl port-forward svc/helidon-base-app 8080
minikube tunnel -p hba

export HBA_VERSION=1.0

skaffold dev --no-prune=false --cache-artifacts=false

helm install mysql bitnami/mysql --version 9.15.0