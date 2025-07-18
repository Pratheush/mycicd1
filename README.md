# MY CICD USING GITHUB ACTIONS


### Testing 
| Layer       | Test Type      | Class Name                |
| ----------- | -------------- | ------------------------- |
| Controller  | Unit (MockMVC) | `CustomerControllerTest`  |
| Service     | Unit           | `CustomerServiceTest`     |
| Full System | Integration    | `CustomerIntegrationTest` |


### TESTING SOME CASES ::
| Feature                                    | Location                              | Notes                                  |
| ------------------------------------------ | ------------------------------------- | -------------------------------------- |
| Exception handling (`404`)                 | `@ControllerAdvice` + test            | Returns user-friendly message on error |
| Integration testing via `TestRestTemplate` | `CustomerRestTemplateIntegrationTest` | Better coverage of full HTTP lifecycle |
| Edge cases: empty list, duplicates         | Unit test in `CustomerServiceTest`    | Tests for real-world data issues       |


#### GitHub Actions
* opt to Java with Maven click configure ::

```
# This workflow will build a Java project with Maven, and cache/restore any dependencies to improve the workflow execution time
# For more information see: https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven

# This workflow uses actions that are not certified by GitHub.
# They are provided by a third-party and are governed by
# separate terms of service, privacy policy, and support
# documentation.

name: Java CI with Maven

on:
  push:
    branches: [ "master" ]
  pull_request:
    branches: [ "master" ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    - name: Build with Maven
      run: mvn -B package --file pom.xml

    # Optional: Uploads the full dependency graph to GitHub to improve the quality of Dependabot alerts this repository can receive
    - name: Update dependency graph
      uses: advanced-security/maven-dependency-submission-action@571e99aab1055c2e71a1e2309b9691de18d6b7d6

```

