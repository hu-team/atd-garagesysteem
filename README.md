# TO4

Welkom Bij Themaopdracht 4
Test

## Installatie instructies (ook voor docenten tijdens nakijken)

Er zijn 2 mogelijke installatie opties.

#### Via de installer (aanbevolen)

Deploy het project naar de Tomcat server toe, zorg voor goede permissies voor schrijfrechten en volg de volgende stappen:

1. Start tomcat, deploy het project.
2. Zorg dat er een database is aangemaakt in MySQL.
3. Zorg dat er een gebruiker toegang heeft tot de MySQL Database.
4. Surf naar basis url van de gedeployde TO4 context.
5. Er verschijnt een installatiepagina.
6. Vul de host in, eventueel poort door :3389 achter de host neer te zetten.
7. Vul gebruikersnaam wachtwoord en database in.
8. Kies om basisdata toe te voegen, dit zorgt voor het snel deployen en begrijpen van de applicatie.
9. Klik op Installatie starten!
10. Nu klik je op opslaan.

Installatie is voltooid, er wordt een bestand 'config.xml' aangemaakt onder war/WEB-INF/ waar de gegevens over de database connectie in staan.
Let op bij redeployment (volledige redeployment), de config kan overschreven of verwijdert worden. In dit gevan kunt u het config.xml ook toevoegen aan uw deployment versie in Eclipse.



#### Handmatig

Handmatige installatie heeft niet alle voordelen en duurt langer.
Maar is noodzakelijk bij het ontwikkelen. Bovendien zit de config.xml in de gitignore!

1. Maak een MySQL database aan.
2. Importeer onder /war/WEB-INF/install.sql de sql queries.
3. Maak een MySQL gebruiker aan met toegang tot de database.
4. Kopieer config-default.xml naar config.xml.
5. Open config.xml.
6. Haal de <!-- en --> weg (de laatste voorkomende).
7. Verander de waarde zoals ze moeten voor de database host + connectie
8. Sla het bestand op, herlaad de deployment context.
9. Test
10. Klaar


## Testing
Belangrijk bij testing zijn de volgende punten:

### Database instellingen
De database instellingen kunnen helaas niet van config.xml opgehaald worden, en bovendien wil je dit ook niet.
Helaas moet in elke testcase file waar de database gebruikt wordt de settings aangepast worden.

De settings zien er als volgt uit en meestal in een van de setUp methodes: 

```java
ConfigHelper.getProperties().put("installed", "true");
ConfigHelper.getProperties().put("mysql.host", "localhost"); // Of eventueel met poort (:poort)
ConfigHelper.getProperties().put("mysql.database", "atd_test");
ConfigHelper.getProperties().put("mysql.username", "root");
ConfigHelper.getProperties().put("mysql.password", "");
```
