Ingress NGNIX set up for local development using Docker Desktop's Kubernetes
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/mandatory.yaml
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud-generic.yaml


Ingress with NGINX controller on Google Kubernetes Engine
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
reference -> https://cloud.google.com/community/tutorials/nginx-ingress-gke

1. Install helm
---------------------------------------------------------------------------------
	curl -o get_helm.sh https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get
	chmod +x get_helm.sh
	./get_helm.sh
	
2. Setup service-account and Tiller
---------------------------------------------------------------------------------
	kubectl create serviceaccount --namespace kube-system tiller
	kubectl create clusterrolebinding tiller-cluster-rule --clusterrole=cluster-admin --serviceaccount=kube-system:tiller
	helm init --service-account tiller --upgrade
	helm install stable/nginx-ingress --name my-nginx --set rbac.create=true