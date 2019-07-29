


Execute the db dbscript on mySql DB

Please update the root, password, url is application.yml of directory,admin.directory

The Application consists of 4 projects


The application must be started in this order

1.DirectoryEureka
	
	This is the discovery client to which the microservcies registers to

	Deployed on port 2222


2.DirectoryZuul
	
	This is the API gateway for the the microservcies 
	Using DirectoryEureka redirects to appropraiate MS
	Deployed on port 3333

3.directory
	
	this microservice consists of api whic used for read-only operation

	Deployed on random port and registers to eureka

4.admin.directory
	
	this microservice consists of api whic used for create/update operation

	Deployed on random port and registers to eureka





The testing is done  using Junit5 Mockito (Only tested for validation of the service, not a data-based test)

Run with Junit Test