# DBLParser
A parser to parse the DBLP dataset
Please change the settings (i.g., dbUrl, user, password) in db/DBConnection.java accordingly.

4. Run the parser
a. If you are using IDE, e.g., Eclipse.
You need to add mysql-connector-java-6.0-bin.jar to the build path. 
See this post if you need any help.
http://www.wikihow.com/Add-JARs-to-Project-Build-Paths-in-Eclipse-(Java)

And then run Parser.java with proper program argument (the path of dblp.xml) and VM
arguments (-Xmx1G -DentityExpansionLimit=2500000). You may see this post on how to specify 
arguments in Eclipse.
http://www.cs.colostate.edu/helpdocs/eclipseCommLineArgs.html

b. If you are using command line
Similarly, you need to add mysql-connector-java-6.0-bin.jar in the classpath, and set
the arguments. The command will be something like:
java -cp mysql-connector-java-6.0-bin.jar -Xmx1G -DentityExpansionLimit=2500000 Parser [path_of_dblp.xml]

The program will run for a while. For example, it takes 974 seconds to parse dblp-2014.xml using my desktop.

# Known Issues
1. Out Of Memory Error
If the default memory for JVM is too small, please increase the allocation by specifying JVM argument -Xmx[memory size] (e.g., -Xmx1G).

2. Too small entity limit
The parser has encountered more than "64,000" entity expansions in this document; this is the limit imposed by the application
Please specify another JVM argument -DentityExpansionLimit=2500000
