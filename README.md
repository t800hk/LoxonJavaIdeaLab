# Loxon Java Idea Lab

## Java profiling

Meetup date: 2017-09-26

### Setup local development environment

Perform the following steps to setup local development environment:

  * ensure JDK available (e.g. download and install JDK 8)
  * ensure development IDE available (e.g. download and start latest eclipse IDE)
  * ensure git client available (e.g. download and install git for your platform)
  * ensure JEE7 compilant application server is available (e.g. download and start WildFly 10.1.0.Final)
  * ensure database is available (e.g download and install Oracle 12c)
  * ensure secondary profiler tool is available (e.g. download and install JProfiler 10 with evaluation license)
  
### Setup tools

Perform the following steps to set up the tools being used during the meetup:

  * clone and checkout related sources and files
    * git clone https://github.com/t800hk/LoxonJavaIdeaLab.git
    * git fetch origin
    * git checkout 20170926_Java_Profiling
  * import checked out folder as an existing java project under development IDE
  * start VisualVM (JAVA_HOME/bin/jvisualvm) connect to itself, click on "Sampler" tab, "Settings" checkbox, "Edit..." button for current "Preset" and create new preset named JavaIdeaLab for "com.loxon.javaidealab.\*" (this will be used during the meetup for some cases)
  * set up database (tablespace, datafile, user/pass, objects, initial data)
    * create privileged objects (e.g. run ./tools/oracle/!create-sys-JAVAIDELAB.pdc as sys after changing username and datafile path)
    * create user objects (e.g. run ./tools/oracle/create-objects.pdc in newly created schema, NOT as sys)
    * create initial data (e.g. run ./tools/oracle/create-data.pdc in newly created schema, NOT as sys, amend numbers according to the sub-step in the meetup presentation)
  * set up application server (memory, JDBC driver, JEE datasource)
    * define memory max sizes: -Xmx350M, MaxMetaspaceSize: -XX:MaxMetaspaceSize=150m (e.g. in standalone.conf*)
    * define JDBC driver (e.g. as a module by copying ./tools/wildfly/modules/* into WildFly modules folder)
    * define JEE datasource (e.g. amend WildFly standalone/configuration/standalone.xml based on the content ./tools/wildfly/standalone/configuration/standalone.xml + add module, add datasource, replace database JDBC url, database user/pass as needed)
  * link development IDE and application server (e.g. modify ./pom.xml - write correct path in "toFile" attribute)
  
### Usage

The following steps can be executed when following the meetup presentation:

  * presentation: ./docs/Java_profiling.pdf
  * start/stop applications server (e.g. run standalone* script sot start, Ctrl-C or process kill to stop WildFly)
  * redeploy application modifications (e.g. run maven install in Eclipse - this will hot deploy modified EJB module under WildFly)
  * change initial data in database (e.g. simply run ./tools/oracle/create-data.pdc after modifying the script)
