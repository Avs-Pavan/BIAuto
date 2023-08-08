@echo off

rem Download the MongoDB Atlas SQL ODBC driver
echo Downloading the MongoDB Atlas SQL ODBC driver...
curl -o atlas-sql-odbc-driver-win64.msi https://translators-connectors-releases.s3.amazonaws.com/mongosql-odbc-driver/windows/1.0.0/release/mongoodbc.msi

rem Install the MongoDB Atlas SQL ODBC driver
echo Installing the MongoDB Atlas SQL ODBC driver...
msiexec /i atlas-sql-odbc-driver-win64.msi /qn

rem Delete the MongoDB Atlas SQL ODBC driver file
echo Deleting the MongoDB Atlas SQL ODBC driver file...
del atlas-sql-odbc-driver-win64.msi