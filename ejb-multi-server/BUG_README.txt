
Use a fresh EAP6.2
run:
    bin/add-user.sh -a -u quickuser -p quick-123
    bin/add-user.sh -a -u quickuser1 -p quick123+ --role Intern
    bin/add-user.sh -a -u quickuser2 -p quick+123 --role AppTwo
    bin/domain.sh

from the project
    mvn clean install
    $JBOSS_HOME/bin/jboss-cli.sh -c --file=install-domain.sh
    $JBOSS_HOME/bin/jboss-cli.sh -c --file=deploy-domain.sh
    cd client
    mvn exec:java [-DUseScopedContext=true]  // SC is not necessary
    ==> result within server log; show that ClientInterceptor1 is invoked

    run jboss-cli:
    deployment-overlay add --name=MainAppClientInterceptor --content=/ejb.jar//META-INF/services/org.jboss.ejb.client.EJBClientInterceptor=../app-main/org.jboss.ejb.client.EJBClientInterceptor --deployments=jboss-ejb-multi-server-app-main.ear --server-groups=quickstart-ejb-multi-main-server --redeploy-affected
     ==> result within server log; show that ClientInterceptor2 is invoked

     rm app-main/ejb/src/main/resources/META-INF/services/org.jboss.ejb.client.EJBClientInterceptor
     mvn clean install
     $JBOSS_HOME/bin/jboss-cli.sh -c --file=undeploy-domain.sh
     $JBOSS_HOME/bin/jboss-cli.sh -c --file=deploy-domain.sh

     now the application did not include a server/org.jboss.ejb.client.EJBClientInterceptor file
     cd client
     mvn exec:java
     ==> result within server log; no ClientInterceptor2 is invoked



