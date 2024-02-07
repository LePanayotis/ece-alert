# ECE NTUA FACULTY ANNOUNCEMENT EMAIL ALERTS

Due to some upcoming reforms in the tertiary education in Greece,
the secretary of the Electrical & Computer Engineering Faculty of the NTUA
has been uploading a series of announcements on the faculty's website. However,
this does not include any notifications via any channels. That's why in one night I
decided to make a simple and **not elegant** solution that polls for new announcements and
notifies me in my email address, so that I don't have to manually check the website regularly.

I hope others can benefit from this 😊

## Requirements

Before trying to install and deploy the application confirm that you have the following installed and configured in your system:

1. **A Java 1.8 or higher JDK** ![java](https://img.shields.io/badge/Java-1.8-blue)

2. [**Maven**](https://maven.apache.org/install.html)   ![maven](https://img.shields.io/badge/Maven-blue)

## Build and Run

Download repo and switch to its directory

```bash
git clone https://github.com/LePanayotis/ece-alerts.git
cd ece-alerts
```

Produce .jar files from Maven project

```bash
mvn clean package
```

Provide the configuration for the application

```bash
nano .env
```

Execute the './target/ece-alerts-0-fat.jar' which contains the dependencies

```bash
java -jar ./target/ece-alerts-0-fat.jar
```

Alternatively, if you want to run the process in the background and direct `stdout` and `stderr` to a particular file execute (after adapting) the following command

```bash
java -jar ./target/ece-alerts-0-fat.jar >> /path/to/your/output/file 2>&1 & disown
```

## Environment Variables

In the `.env` file, you have to povide the following environment variables:

```env
##BEFORE RUNNING CHANGE THESE PROPERTIES
#sender's email address
SENDER_EMAIL=someone@example.com

#recipient's email address
RECIPIENT_EMAIL=someoneelse@example.com

#sender's password
PASSWORD=senderspassword

#smtp server
SMTP_SERVER=smtp.example.com

#smtp starttls port
SMTP_PORT=587

#previous announcement id on webpage
PREV_TAG=1665

#polling interval in milliseconds
POLL_INTERVAL=10000
```

This file must be located in the directory from which you call the `java -jar ece-alert-0-fat.jar`

## Contributors

* [**Panagiotis Papagiannakis**](mailto:el19055@mail.ntua.gr)
    9th term student, School of Electrical and Computer Engineering, National Technical University of Athens, Greece

## License

The program is licensed under the MIT license, for details see LICENSE.md

## Disclaimer

![It ain't much, but it's honest work](https://media.npr.org/assets/img/2023/05/26/honest-work-meme-cb0f0fb2227fb84b77b3c9a851ac09b095ab74d8-s1100-c50.jpg)
