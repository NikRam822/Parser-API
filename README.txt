Test Assignment for Ap Soft company.

The project is a Java Rest-API written using Spring.

In order to use the API, you will need an HTTP client to send requests (e.g. Postman), you can also use a graphical interface.
You will need to clone the repository in order to start the project.  Then, if you are using idea, follow the instructions: File->New->Module from Existing Sources, and select the folder "complete", which is in the current project folder.

To use the API you'll need to install:
Git
JDK 8+.

Project entry point:

Parser-Application\complete\src\main\java\com\parser_app\uploadingfiles\UploadingFilesApplication.java

Requests:

GET: http://localhost:8080/ - main page.

POST: http://localhost:8080/ (BODY: KEY = file, VALUE = test.txt) - request to upload file to server (two parameters must be passed in request body: 1 - KEY, 2 - VALUE. In KEY you must specify the string file, and in VALUE you must pass the file itself).

GET: http://localhost:8080/files/[examle.txt] - request for file processing. The file name should be passed in the header.

Example queries:

Parser-Application\Example_requests - file with example.

1) GET: http://localhost:8080/

2) POST: http://localhost:8080/ (BODY: KEY = file, VALUE = test.txt)

3) GET: http://localhost:8080/files/test.txt

Parser-Application\test.txt - file for tests.
