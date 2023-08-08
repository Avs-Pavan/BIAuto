@echo off

rem set COLLECTION=%2
rem set FILE=%1
rem mongoimport -v -d=powerbi --collection=%COLLECTION% --file=%FILE% --headerline --type=csv --mode=merge
@echo off

rem Set the variables
set zero = %0
set url=%1
set collection=%2
set dump_file=%3

echo %url%
echo %zero%

echo mongoimport --uri %url% --collection %collection% --headerline --type=csv --mode=merge --verbose --file %dump_file% -v
rem Execute the command
mongoimport --uri %url% --collection %collection% --headerline --type=csv --mode=merge --verbose --file %dump_file% -v

echo "Import completed"

timeout /t 5