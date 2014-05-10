ejb-remote: Remote EJB Client Example
=====================================
Author: Wolf-Dieter Fink
Level: Intermediate
Technologies: JavaEE batch application JSR352
Summary: Shows how to implement batch applications according to the JavaEE7 specification
Target Project: WildFly
Source: <https://github.com/wildfly/quickstart/>

What is it?
-----------

This example shows how to implement different batch jobs. It demonstrate the use of *Batch API 1.0* in *JBoss WildFly*.

There are two components to this example: 

1. A implementation of the different batch artifacts:

    The component is comprised of different Reader/Writer/Processor artifacts which can be used to create batch jobs.

2. A web application that define the batch jobs and run it. 

    The web pages can start the different jobs.

Each component is defined in its own standalone Maven module. The quickstart provides a top level Maven module to simplify the packaging of the artifacts.


System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly.

 
Configure Maven
---------------

If you have not yet done so, you must [Configure Maven](../README.md#mavenconfiguration) before testing the quickstarts.


Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat


Build and Deploy the Quickstart
-------------------------

Since this quickstart builds two separate components, you can not use the standard *Build and Deploy* commands used by most of the other quickstarts. You must follow these steps to build, deploy, and run this quickstart.

1. Make sure you have started the JBoss server. See the instructions in the previous section.
2. Open a command line and navigate to the ejb-remote quickstart directory
3. Build and install the server side component:
    * Build the batch implementation and the web application and install them in your local Maven repository.

            mvn clean install        
    * Deploy the WAR to your server. This maven goal will deploy `web/target/wildfly-batch-web.war`. You can check the JBoss server console to see information messages regarding the deployment.

            mvn wildfly:deploy
4. Access the web pages and start the different batch jobs and investigate the behaviour by following the Explanations below


A first simple batch Job
-------------------------

To start the job access

        localhost:8080/wildfly-batch-web/simplebatch.jsf

1. 
2. 


Undeploy the Archive
--------------------

To undeploy the server side component from the JBoss server:

1. Type the following command:

        mvn wildfly:undeploy



Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](../README.md#useeclipse) 


Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc
