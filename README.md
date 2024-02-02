# ECE NTUA FACULTY ANNOUNCEMENT EMAIL ALERTS

Due to some upcoming reforms in the tertiary education in Greece,
the secretary of the Electrical & Computer Engineering Faculty of the NTUA
has been uploading a series of announcements on the faculty's website. However,
this does not include any notifications via any channels. That's why in one night I
decided to make a simple and **not elegant** solution that polls for new announcements and
notifies me in my email address, so that I don't have to manually check the website regularly.

I hope others can benefit from this :)

## Requirements
Before trying to install and deploy the application confirm that you have the following installed and configured in your system:

1. **A Java 1.8 or higher JDK**
2. <a href="https://maven.apache.org/install.html">**Maven**</a> 

## Build and Run
'''bash
git clone https://github.com/LePanayotis/ece-alerts.git/
cd ece-alerts
'''

'''bash
mvn clean package
'''

'''bash
nano .env
'''

'''bash
java -jar ./target/ece-alerts-0-fat.jar
'''

'''bash
java -jar ./target/ece-alerts-0-fat.jar >> /path/to/your/output/file 2>&1 & disown
'''


##License
[MIT]
