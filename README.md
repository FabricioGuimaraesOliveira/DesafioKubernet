# DesafioKubernet

Criação de um cluster Kubernetes que deve comportar a seguinte carga de
trabalho:
● Aplicação 1
○ Aplicação Web desenvolvida com um framework de sua escolha que
possui apenas um endpoint (definido em app1-def.yaml anexo a este
desafio) que escreve um log com o payload recebido
○ Esta aplicação deve rodar 100% do tempo que o cluster esteja
rodando
● Aplicação 2
○ Script shell que faz uma requisição para o endpoint da Aplicação 1
○ O container que rodará o script deverá ser executado de 3 em 3
minutos
○ O container da Aplicação 2 deve ser terminado no sucesso da
requisição

● Prometheus (https://prometheus.io/) configurado para rastreamento de
métricas de uso dos containers

### Requisitos

Instalar e startar o Kubernet(Minikube) v1.8.0 para criação de Cluster Localhost.
Instalar o Docker 20.10.8

###Implementação Aplicação 1

#### Build a imagem docker da aplicação 1 desenvolvida utilizando o Spring Boot

Navegue para a pasta application1-api
```
cd Application1/application1-api/

```

Para que o minikube encontre a imagem local do docker é necessário apontar o repositorio do docker e em seguida buildar a imagem da aplicação1.

```
eval $(minikube -p minikube docker-env)
docker build -t spring-app .
```

#### Build um POD kubernet da aplicação 1 

Navegue para a pasta ks8/app1/
```
cd ks8/app1/

sudo kubectl apply -f deployment.yaml
sudo kubectl apply -f service.yaml

```



Verifique se o POD foi criado corretamente com o comando

sudo kubectl get pods
sudo kubectl get service




###Implementação Aplicação 2

#### Build a imagem docker da aplicação 2 desenvolvida via script bash

Navegue para a pasta Application2/script/
```
cd Application2/script/

```

Para que o minikube encontre a imagem local do docker é necessário apontar o repositorio do docker e em seguida buildar a imagem da aplicação2.

```
eval $(minikube -p minikube docker-env)
docker build -t client-app .
```

#### Build um cronjob kubernet da aplicação 2 responsável em fazer requisições a cada 3 minutos


Navegue para a pasta ks8/app2/
```
cd ks8/app2/

```
Execute os comandos para criar o cronJob no minikube
```
sudo kubectl apply -f deployment.yaml
sudo kubectl apply -f service.yaml
```
Obs: Você pode mudar o conteudo do log a ser registrado no arquivo deployment.yaml

Voce pode substituir  campo "testeserver":  args: ["api-spring", "testeserver"]

Verifique se o cronjob foi criado corretamente com o comando

```
sudo kubectl get cronjobs
sudo kubectl get service

```



###Implementação do Monitoramento prometheus/grafana

Para subir a stack de Monitoramento no kubernetes execute:

```
cd monitoramento/monitoring/

kubectl create namespace monitoring

kubectl create -f k8s-prometheus/clusterRole.yaml
kubectl create -f k8s-prometheus/config-map.yaml
kubectl create -f k8s-prometheus/prometheus-deployment.yaml 
kubectl create -f k8s-prometheus/prometheus-service.yaml --namespace=monitoring

kubectl apply -f kube-state-metrics/

kubectl create -f k8s-grafana/
```

Verique se os serviços do prometheus e grafana foram iniciados com sucesso com o comando abaixo:

```
kubectl get service --namespace monitoring
```

Em seguida print o ip utilizado pelo minikube

```
minikube ip
```

Para acessar o prometheus utilize o link:
Depois acesse http://minikube_ip:30000/ (prometheus)

Para acessar o grafana utilize o link:
Depois acesse http://minikube_ip:32000/ (grafana)

Usuario: admin

Senha: admin

Você pode importar o dashboard do GrafanaLabs (https://grafana.com/grafana/dashboards/12740)

Com o ID:12740








