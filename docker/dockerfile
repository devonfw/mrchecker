FROM ubuntu:16.04
MAINTAINER Lukasz Stefaniszyn "lukasz.stefaniszyn@capgemini.com"

LABEL name=ubuntu_java \
            version=v1-8.0 \
            base="ubuntu:16.04" \
            build_date="04-26-2018" \
            java="1.8" \
            maven="3.3.9" \
            description="This basic docker to use in java and maven dev env."

# Update and install needed applications
RUN apt-get update
RUN apt-get install -y \
            wget \
            libfontconfig \
            unzip \
            zip \
            ksh \
            curl \
            git

#Env parameters
ENV M2_HOME=/opt/apache-maven-3.3.9
ENV M2=$M2_HOME/bin/


### JAVA PART ###
#TO UPDATE:  please verify url link to JDK http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
## Download and install Java JDK8
RUN mkdir /opt/jdk
RUN wget -qq --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u172-b11/a58eab1ec242421181065cdc37240b08/jdk-8u172-linux-x64.tar.gz && tar -zxf jdk-8u172-linux-x64.tar.gz -C /opt/jdk && rm jdk-8u172-linux-x64.tar.gz && update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_172/bin/java 100 && update-alternatives --install /usr/bin/javac javac /opt/jdk/jdk1.8.0_172/bin/javac 100 && java -version && chmod 755 -R /opt/jdk/jdk1.8.0_172/
RUN java -version

### MAVEN PART ###
RUN wget -q http://mirror.cogentco.com/pub/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz && tar xzf apache-maven-3.3.9-bin.tar.gz -C /opt && rm apache-maven-3.3.9-bin.tar.gz && ln -s /opt/apache-maven-3.3.9/bin/mvn /usr/bin/mvn && mvn --version && chmod 755 -R /opt/apache-maven-3.3.9/


# Jenkins runs with user `jenkins`, uid = 1000
# If you bind mount a volume from the host or a data container,
# ensure you use the same uid
ARG user=jenkins
ARG group=jenkins
ARG uid=1000
ARG gid=1000
RUN addgroup --gid ${gid} ${group} && adduser --disabled-password  --uid ${uid} --gid ${gid} ${user}


## Copy and Install dependencies
RUN mkdir /opt/tmp
RUN mkdir /usr/share/maven2 && chmod 777 -R /usr/share/maven2
COPY ./template-app-under-test/ /opt/tmp/template-app-under-test/
COPY ./settings.xml /opt/apache-maven-3.3.9/conf/settings.xml

RUN cd /opt/tmp/template-app-under-test/ && mvn dependency:go-offline clean install -U compile test-compile site deploy -DskipTests=true; exit 0

RUN rm -f -r /opt/tmp/template-app-under-test/

RUN chmod 755 /usr/bin/java && chmod 755 /usr/bin/mvn
RUN chmod 777 -R /usr/share/maven2

## Create app folder
RUN mkdir -p -m 777 /app

# Set working directory
WORKDIR /app

USER ${user}

