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

  * checkout branch 20170926_Java_Profiling of this git repo
  * open checked out folder as an existing java project under development IDE
  * start VisualVM (JAVA_HOME/bin/jvisualvm) connect to itself, click on "Sampler" tab, "Settings" checkbox, "Edit..." button for current "Preset" and create new preset named JavaIdeaLab for "com.loxon.javaidealab.\*" (this will be used during the meetup for some cases)
  * TODO: DB init steps
  * TODO: WF init steps
