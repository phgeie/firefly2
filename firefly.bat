@echo off
setlocal enabledelayedexpansion
REM Eingabeaufforderung für die Anzahl der Zeilen
set /p row=Bitte gib die Anzahl der Zeilen ein: 

REM Prüfen, ob die Eingabe eine Zahl ist
for /f "delims=0123456789" %%i in ("%row%") do (
    echo Fehler: Die Eingabe für Zeilen muss eine Zahl sein.
    exit /b 1
)

REM Eingabeaufforderung für die Anzahl der Spalten
set /p col=Bitte gib die Anzahl der Spalten ein: 

REM Prüfen, ob die Eingabe eine Zahl ist
for /f "delims=0123456789" %%i in ("%col%") do (
    echo Fehler: Die Eingabe für Spalten muss eine Zahl sein.
    exit /b 1
)

set /p coupling=Bitte gib das Coupling ein: 
set /p time=Bitte gib die Threadsleeptime ein: 

cd Spring-Boot-GRPC-Observer || (
    echo Fehler: Verzeichnis "Spring-Boot-GRPC-Observer" konnte nicht gefunden werden.
    exit /b 1
)

echo Starte Observer mit Port: 8080
start cmd.exe /k mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=8080 -Dspring-boot.run.arguments="%row% %col% %time%"

cd ..
REM In das Projektverzeichnis wechseln
cd Spring-Boot-GRPC-Client-ff || (
    echo Fehler: Verzeichnis "Spring-Boot-GRPC-Client-ff" konnte nicht gefunden werden.
    exit /b 1
)
REM Server starten
set /A total=%row%*%col%-1
set a=0

for /l %%i in (0, 1, %total%) do (
    timeout /t 10 /nobreak >nul
    set /A PORTID=!a!+50051
    set /A a=!a!+1
    echo Starte !a!-ten Server mit Port: !PORTID!
    start cmd.exe /k mvn spring-boot:run -Dspring-boot.run.arguments=--grpc.server.port=!PORTID! -Dspring-boot.run.arguments="%%i %row% %col% %coupling% %time%"
)


cd ..
cd angular || (
    echo Fehler: Verzeichnis "angular" konnte nicht gefunden werden.
    exit /b 1
)

echo Starte Angular Visualisierung mit Port: 4200
start cmd.exe /k ng serve Firefly

REM Hinweis zum Beenden
echo Alle Server wurden gestartet. Druecke eine beliebige Taste, um alle Skript zu schliessen (java.exe, node.exe und cmd.exe werden geschlossen).

pause
taskkill /f /im "node.exe" >nul 2>&1
taskkill /f /im "java.exe" >nul 2>&1
taskkill /f /im "cmd.exe" >nul 2>&1
exit /b
