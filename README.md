Sonar PDF Report Plugin
=========================


This is a fork from a fork of the Opensource version.

For the sake of traceability, I kept the package renaming of the previous fork from [(https://github.com/somasuraj3/test-p-6.7)](https://github.com/somasuraj3/test-p-6.7).

I kept tthe license and the code.

The plugin has been tested on SonarQube 8.0, on Linux 64 bits.

At that time, the plugin worked but I cannot say I have done a proper exhaustive testing. If you find a bug, creates an issue. However since I am very busy, unless **you are a company and with the possibility to donate**, the fixes will be treated with a low priority.



## Description / Features

Generate a project quality report in PDF format with the most relevant information from SonarQube web interface. The report aims to be a deliverable as part of project documentation.

The report contains:

* Dashboard
* Violations by categories
* Hotspots:
  * Most violated rules
  * Most violated files
  * Most complex classes
  * Most duplicated files
* Dashboard, violations and hotspots for all child modules (if they exists)

## Installation

1. Install the plugin through the [Update Center](http://docs.sonarqube.org/display/SONAR/Update+Center) or download it into the SONARQUBE_HOME/extensions/plugins directory
1. Restart SonarQube

## Usage

SonarQube PDF works as a post-job task. In this way, a PDF report is generated after each analysis in SonarQube.

### Configuration

You can skip report generation or select report type (executive or workbook) globally or at the project level. You can also provide an username/password if your project is secured by SonarQube user management:

In the previous version, you  Sonar Scanner configuration should contains the following property :

```
sonar.leak.period=NUMBER_OF_DAYS
```  



### Download the report

PDF report can be downloaded from the SonarQube GUI or from the SONAR output folder ( example target/sonar with a Maven project).


Issue tracking:
https://jira.codehaus.org/browse/SONARPLUGINS/component/14372

CI builds:
https://sonarplugins.ci.cloudbees.com/job/report-pdf
