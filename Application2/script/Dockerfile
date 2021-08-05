FROM ubuntu

RUN apt-get update && apt-get install -y build-essential libssl-dev gcc curl

RUN mkdir -p /scripts
COPY app2.sh /scripts
WORKDIR /scripts
RUN chmod +x app2.sh
ENTRYPOINT ["sh","app2.sh"]

