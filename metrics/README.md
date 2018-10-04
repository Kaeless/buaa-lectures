- http://localhost:8080/actuator
- http://localhost:8080/actuator/metrics
- http://localhost:8080/actuator/prometheus


#### prometheus http://localhost:9090
```
PATH_TO_PROMETHEUS_YML_FILE=/Users/han/cooding/github/buaa-lectures/metrics/src/main/resources/prometheus.yml
docker run -d --name=prometheus -p 9090:9090 -v $PATH_TO_PROMETHEUS_YML_FILE:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
```


#### grafana http://localhost:3000 admin:admin
```
docker run -d --name=grafana -p 3000:3000 grafana/grafana
```