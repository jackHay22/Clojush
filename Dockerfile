FROM clojure
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY project.clj /usr/src/app/
RUN lein deps
COPY . /usr/src/app
VOLUME /server_logs
CMD lein run clojush.problems.software.poolgp | tee -a /server_logs/engine.log
