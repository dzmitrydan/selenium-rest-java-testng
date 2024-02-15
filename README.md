# Selenium Project
## Description
- Selenium
- Java
- Maven
- TestNG

#### Test launch options:
- Browser:
  - Google Chrome
  - Firefox
- Browser mode
  - Headless
  - Not headless

#### Using GitHub Actions for CI/CD
- file: .github/workflows/**maven.yml**

![Nodes screenshot](readme-assets/github_action_01.png)
![Nodes screenshot](readme-assets/github_action_02.png)

#### Run tests
```
mvn clean test -Dbrowsermode.headless=true -Dsurefire.suiteXmlFiles=src/test/resources/suites/computeEngineTests.xml
```

```
mvn clean test -Dbrowsermode.headless=false -Dsurefire.suiteXmlFiles=src/test/resources/suites/simpleTest.xml
```