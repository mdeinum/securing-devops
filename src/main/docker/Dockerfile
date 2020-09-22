FROM java:8-alpine
RUN addgroup -g 10001 app && \
    adduser -G app -u 10001 \
    -D -h /app -s /sbin/nologin app
ARG DEPENDENCY=target/dependencies

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

USER app
WORKDIR /app
EXPOSE 8443
ENTRYPOINT ["java","-cp","app:app/lib/*","biz.deinum.InvoicerApplication"]
