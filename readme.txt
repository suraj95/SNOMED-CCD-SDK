********************************************************************************
	         How to build source code and other prerequisite.
********************************************************************************
1.Download the source code from cdac website.

2.Download jars listed below of ThirdPartyComponents and keep them Shared/Binaries/ThirdPartyComponents
	Jars needed:
		jaxb-api-2.2.jar
		csnolib.jar
		lucene-core-4.7.1.jar
		lucene-queryparser-4.7.1.jar

3.SDK can be build using JDK.For building SDK JDK 1.8 or above should be installed on machine.

4. Set the environment variables for Java.

	e.g : Setting the environment variables for Java considering AdoptOpenJDK 1.8 is installed.
		JAVA_HOME - C:\Program Files\Java\jdk8u202-b08(System Environment Variable in case of Windows)
		JAVA_HOME - export JAVA_HOME=/usr/lib/jvm/jdk8u202-b08 (modify '/etc/profile' - in case of LINUX)

5.Append the path value for JAVA in the PATH environment variable.
	e.g :  C:\Program Files\Java\jdk8u202-b08\bin
	       export PATH=/usr/lib/jvm/jdk8u202-b08/bin:$PATH (modify '/etc/profile' - in case of LINUX)

6. Apache Ant should be installed on machine.

7. set the environment variables for Ant.

     E.g. ANT_HOME -  C:\Program Files\apache-ant-1.9.7 (System Environment Variable in case of Windows)
	  ANT_HOME -  export JAVA_HOME=/usr/java/apache-ant-1.9.7 (modify '/etc/profile' - in case of LINUX)


8.Build code using Ant-Scripts by below command :
	ant -f Master_Build.xml

NOTE:After Successful build All the Jars get stored in Shared/Binaries/
     This SDK is developed, build & tested on AdoptOpenJDK 1.8(202).

********************************************************************************
				  FINISH
********************************************************************************




















 In case of any queries / feedback please write to us at:  sdk-enq@cdac.in
        
