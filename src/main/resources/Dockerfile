FROM jboss/keycloak:latest

RUN mkdir /tmp/keycloak

ADD keycloak /tmp/keycloak

RUN cp /tmp/keycloak/fairlogin.zip /opt/jboss/keycloak/themes && cd /opt/jboss/keycloak/themes && unzip fairlogin.zip && rm fairlogin.zip

RUN cd /opt/jboss/keycloak && bin/jboss-cli.sh --command="module add --name=fairlogin --resources=/tmp/keycloak/fairlogin.zip"

RUN /opt/jboss/keycloak/bin/add-user-keycloak.sh -u admin -p admin

#docker run -p 8081:8080 -e DB_VENDOR=H2 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -e KEYCLOAK_IMPORT=/tmp/realm-export.json -v /Users/carlos/Downloads/realm-export.json:/tmp/realm-export.json jboss/keycloak

EXPOSE 8081

CMD ["-b", "0.0.0.0", "-Dkeycloak.import=/tmp/keycloak/realm-export.json", "-Djboss.http.port=8081"]