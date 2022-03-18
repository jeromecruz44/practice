Jupiter Toys Test Automation

Framework Components
- Maven Project Management
- Selenium
- TestNG
- JDK


Webdrivers Notes:
- Repository folder path - \jerome.carlo.cruz\TestAutoProj\src\test\java\Resources\drivers
- Update the driver files (if necessary) based from the installed browser version in the machine to be used for script execution
  - Chromedriver (for Google Chrome) - https://chromedriver.chromium.org/downloads
  - Geckodriver (for FireFox) - https://github.com/mozilla/geckodriver/releases
  - MSEdgedriver (for MS Edge) - https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/


Global Properties:
- Property file - \jerome.carlo.cruz\TestAutoProj\src\test\java\Resources\data.properties
- Properties (can be modified if necessary)
  - browser - CHROME/FIREFOX/EDGE
  - url - http://jupiter.cloud.planittesting.com
  - driverpath - src/test/java/Resources/drivers/
  - datapath - src/test/java/TestData/


Run Notes:
- All dependencies were already defined.
- To run the tests, after cloning project repository, run "mvn test" command in CLI.
