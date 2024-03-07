With this help you will create secret file for environments secret entries.

start encrypt.bat with parameters:

> encrypt.bat input=whatToEncode password=secretKey
secretKey is stored in file src\resources\enc - this file should never be send to repository and can shared only with authorized people.

after starting encryption.bat with correct parameters, you will get log:
----ENVIRONMENT-----------------
Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.231-b11

----ARGUMENTS-------------------
input: whatToEncode  (example userpassword)
password: secretKey

----OUTPUT----------------------
blh6458i6tiYMfIeKaVzQw==


Put output value into environments.csv like that
ENC(blh6458i6tiYMfIeKaVzQw==)



See Framework documentation for more details:
https://github.com/devonfw/mrchecker/blob/develop/documentation/Who-Is-MrChecker/Test-Framework-Modules/Core-Test-Module-Different-Environments.asciidoc
