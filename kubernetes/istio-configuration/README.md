gcloud config set project tonal-carving-252503

gcloud container clusters create products-istio \
    --machine-type=n1-standard-2 \
    --num-nodes=4 \
    --zone=us-central1-a

gcloud container clusters get-credentials products-istio --zone us-central1-a

kubectl create clusterrolebinding cluster-admin-binding \
  --clusterrole=cluster-admin \
  --user="$(gcloud config get-value core/account)"

cd istio-1.3.3

export PATH=$PWD/bin:$PATH

kubectl create namespace istio-system

helm template install/kubernetes/helm/istio-init \
  --name istio-init --namespace istio-system | kubectl apply -f -

kubectl -n istio-system wait --for=condition=complete job --all


helm template install/kubernetes/helm/istio \
  --name istio --namespace istio-system --set grafana.enabled=true | kubectl apply -f -

kubectl get service -n istio-system

kubectl get pods -n istio-system