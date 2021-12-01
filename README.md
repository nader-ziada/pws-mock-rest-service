Archived Repo


# PWS Mock REST Service

Mock REST service works on PWS (Cloud Foundry) to provide a mock JSON response to a REST api call. Project is developed using Java and Spring Boot. Spring Cloud connector is used to get information about the application instance running on Cloud Foundry.

## Pushing the app to PWS

Create a account on PWS http://run.pivotal.io

After you compile the application using maven, use the following push command to deploy to PWS

```sh
$ cf push mock-rest-service-{random-word} -p target/mock-rest-service-0.1.0.jar 
```

You have to create a mongo service and bind it to your application on PWS.
Though it's less preferable, if you are using bundler in a Ruby

## Usage

* create: saves a mock service and returns the url used to call this mock call.
* list all: returns all the existing mock instances in the database
* getByName: returns the JSON file for the given service name

## License

LicenseFinder is released under the MIT License. http://www.opensource.org/licenses/mit-license
