MrChecker template - parent pom
---------------
This is the parent pom. It's a place where you should collect dependencies common for your subprojects, common profiles.

#### How to version it?
Semantic versioning.
#### How to make maven to use the newest version?
Maven is discuraging use of LATEST keyword so the resonable solution is to use range [1.0.0,2.0.0), so major version 1 and as we know from semantic versioning if there is change in major version number there is compatibility break anyways.
#### How to deploy it?
Create special jenkins job for it or if you have a credentials that allow writing to nexus you can do it with mvn.
