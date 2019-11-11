#!/bin/bash
kubectl apply -f deployment
kubectl apply -f service
kubectl apply -f autoscaler
kubectl apply -f gateway
kubectl apply -f virtualservice
