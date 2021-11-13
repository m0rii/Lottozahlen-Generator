# Lottozahlen-Generator
Dieser kleinen Java-Applikation generiert ein Zufall zahlen für Lottospielen Eurojackpot und Lotto6aus46. 
Der Benutzer kann seine Unglückszahlen eingeben, um die Zufall Zahlen zu filtern.
Es wird auch die Unglückzahlen bei Erstellen der Superzahlen berücksichtigt.
Die Anwendung speichert die Unglückzahlen in eine Mysql DB und bietet dem Benutzer die Möglichkeit, seine letzten Unglückszahlen bearbeiten oder Löschen (CRUD)
Die Kommunikation mit der Applikation ist per Kommando Zeile möglich.

Anleitung zur Nutzung der Lottozahlen-Generator Applikation

Git

Das Repository der Applikation befindet sich unter https://github.com/m0rii/Lottozahlen-Generator.git

Entwicklungsumgebung
IntelliJ Plugins
https://plugins.jetbrains.com/plugin/7499-gittoolbox
https://plugins.jetbrains.com/plugin/15075-jpa-buddy


Benutzer Anleitung:
Am Anfang wird nach Spiele Type gefragt. Der Benutzer kann entweder die ganzen Namen der Spiel „Eurojackpot“ oder „Lotto6aus49“ eingeben oder mit dem Drück der Enter Tastatur die Default Wert für den Speile „Lotto6aus40“ auswählen.
In dem nächsten Schritt werden die letzten Unglückszahlen von DB angezeigt, wenn es vorhanden sein.
Dem Benutzer wird gefragt, wie er mit Unglückzahlen weiter gehen möchte. Er kann die Unglückzahlen bearbeiten(update), löschen oder damit weiter gehen.
In dem Fall keine Unglückzahlen vorhanden ist, wird nach der Eingabe mit ja/nein gefragt. Bein eintippen „ja“ kann der Benutzer die Zahlen im Bereich der ausgewählte Spiel Type eingeben.
Das Heißt, 1 bis 50 für Eurojackpot oder 1 bis 46 für Lotto6aus46.
Bis zu 6 Zahlen kann eingegeben werden oder mir Eingabe „end“ die Abfrage beenden.
Jetzt wird ein Tippreihe mit Superzahlen nur für Spiel Type „Lotto6aus46“ generiert.
