# Svar ligger nederst i denne filen
# Konteeksamen  - PGR301

# Scenario

Du har fått en idé du selv mener er veldig god - et API som lager tilfeldige kakeoppskrifter basert på en rekke ingredienser. Etter en liten helg med koding har du det som ligger i dette repositoryet. Fordi du er helt sikker på at dette kommer til å slå an på et globalt nivå, tenker du det er best å starte med god DevOps praksis fra starten av.

## Krav til leveransen

* Eksamensoppgaven er gitt på GitHub repository ; https://github.com/pgr301-2022/konte-2022
* Du skal ikke lage en fork av dette repositoryet, men kopiere innholdet til et nytt. Årsaken er at sensor vil lage en fork av ditt repo, og arbeidsflyten blir lettere hvis ditt repo ikke er en fork.
* Du kan jobbe i et public-, eller privat repo, og deretter gjøre det public noen timer etter innleveringsfrist hvis du er bekymret for plagiat fra medstudenter.

Når sensor evaluerer oppgaven vil han/hun se på

* Ditt repository og "Actions" fanen i GutHub for å bekrefte at Workflows faktisk virker
* Vurdere drøftelsesoppgavene. Du må lage en  "Readme" for besvarelsen i ditt repo.
* Sensor vil Lage en fork av ditt repo og tester ut pipelines med egen Docker hub/github bruker.

## Evaluering

* Del 1 Prinsipper - 30 poeng
* Del 2 GitHub actions - 30 poeng
* Del 3 Docker - 40 poeng

# Om applikasjonen 

Du kan start applikasjonen lokalt ved å kjøre

```shell
mvn spring-boot:run
```

Og deretter åpne en nettleser med for eksempel - http://localhost:8080/cake-ingredients?numberOfIngredients=23

## Del 1 - Prinsipper

Forklar hvordan et større utviklingsteam kan samarbeide om videreutvikling av denne applikasjonen 
med tanke på:

* Kontinuerlig integrasjon - hva mener vi med dette, og hvorfor er dette viktig?
* Kontinuerlige leveranser - hva mener vi med dette og hvorfor er det viktig?

Når applikasjonen er i drift, ønsker du å ha god innsikt i både forretningsmessige og tekniske aspekter ved 
applikasjonen. Eksempler; antall brukere, antall oppskrifter generert - men også respontider, feilrater, CPU og minnebrukt osv   

* Forklar hvorfor det er enklere å få denne innsikten når man adopterer DevOps, i forhold til Vannfall og et skille mellom drift- og utviklingsteam.
* Forklar hvordand du kan implementere en løsning basert på tjenester i Amazon Webservices for å få denne oversikten. Hva må du konfigurere i AWS, og hva må du gjøre i applikasjonen?

## Del 2 - GitHub actions 

### Oppgave 1 - GitHub actions workflow

Lag en GitHub actions workflow som gjør følgende for hver pull request som lages i ditt repository:

* Kompilerer koden
* Kjører enhetstester

### Oppgave 2

Beskriv med ord eller skjermbilder hvordan man kan konfigurere GitHub på en slik måte at 

* Det ikke er mulig å merge en Pull Request inn i main branch, uten at koden kompilerer og enhetstester er kjørt uten feil.
* Minst en annen person i teamet har godkjent endringen 

## Del 3 Docker 

I denne oppgaven trenger du en konto på Docker Hub https://hub.docker.com/

### Oppgave 1 

Skriv en multi stage ```Dockerfile``` for Java-applikasjonen, slik at kompileringen og byggingen kjører i selvstendige Docker containere.

### Oppgave 2 - Docker hub

Lag en GitHub actions workflow som bygger et container image og pusher det til din Docker 
hub konto hver gang noen pusher en tag til repositoryet. 

For eksempel skal kommandoene under, når det gjøres mot ditt GitHub Repository resultere i et nytt container image med tag 1.0.0 i Docker Hub

```sh
git tag 1.0.0
git push --tags
```

Beskriv hva sensor må gjøre for å få workflowen til å fungere i sin egen GitHub-konto.

### Oppgave 3 

Test din egen workflow, slik at du får minst ett container image i din Docker Hub konto.
* Hvilken docker kommando kan sensor bruke for å laste ned og starte ditt container image fra docker hub? Applikasjonen skal være tilgjengelig på http://localhost:9999 etter oppstart 

Fullfør ```docker ..```








# Svar:
## Del 1:
### Kontinuerlig integrasjon:
Det er en praksis hvor utviklere som jobber på samme prosjekt regelmessig 
merger kode til et repository som automatisk blir sjekket om den kjører samt om testene 
passer. Siden kode merges ofte vil siste kjørbare build være ganske up to date.
Siden man merger ofte vil problemer med koden komme opp ofte men det betyr også at de
er mye lettere å nøste opp i enn om man hadde latt det gå lang tid.

Ved hver merge kan man inkludere praksiser som at noen andre må godkjenne det som 
også hjelper for å opprettholde gode kodepraksiser

### Kontinuerlige leveranser:
Dette bygger direkte på kontinuerlig integrasjon da det er å alltid ha
en versjon av prosjektet som er klart til å bli deployed. Siden nye kode
hyppigt blir merget vil man som nevnt over ha en ganske up to date versjon alltid klar.
Dette gjør det igjen mye raskere å sende ut nye versjoner og hvis det skulle være noe 
problematisk med den deployede versjonen vil ikke være enorme forskjeller fra den forrige.
Samt kan det gjøre brukeren mer fornøyd med løsningen hvis oppdateringer rulles ut ofte.


 ### "Forklar hvorfor det er enklere å få denne innsikten når man adopterer DevOps, i forhold til Vannfall og et skille mellom drift- og utviklingsteam."

Vannfall er en annen måte å gjøre prosjekter på som er linær, og langt mer rigid enn devops.
Utviklingsprosessen behandles som et samlebånd hvor all kode settes sammen til slutt uten testing 
underveis for å se hvordan de forskjellige komponentene jobber sammen. 

Med Devops vil ny funsksjonalitet kontinuerlig merges og eventuelle feil blir da ganske raskt
plukket opp. Siden man også alltid har en kjørende versjon kan man implementere metrikker som 
fanger opp alt mulig av data på den kjørende applikasjonen. For kodeoptimalisering kan man feks
sjekke minnebruk og tid segmenter av koden bruker for å finne bottlenecks.

Forretningsmessig kan man finne på metrikker som viser hvordan kundene bruker applikasjonen. 
Er det feks deler som er lite intuitive, hvor mye brukes den og ikke minst, hvor mange.
En annen viktig ting er å ha sjekker på at alt er oppe å kjører på applikasjonen. 
For kundenes tilfredsstillelse er det viktig at tjenesten er tilfjengelig så mye som mulig.

For å sette opp måling av metrikker kan man bruke cloudwatch og terraform. Terraform hjelper
med å sette infrastrukturen som kode og cloudwatch er tjenesten som brukes for å måle data.
I prosjektet trenger man en mappe i roten til prosjektet som heter infra og har to filer-
dashboard.tf og variables.tf. Dashboard inneholder kode for å sette opp et cloudwatch dashboard.
I prosjektet kjører man terraform init, plan og apply.
I kiledekodemappen lager man en.java fil for å sette opp konfigurasjonen mot cloudwatch.
Via event metoder kan ønsket data sendes til cloudwatch når spesifikke hendelser skjer og i cloudwatch
kan man feks få opp grafer på dataen som sendes inn. Basert på grenser man setter selv kan man
få en alarm til å gå hvis en verdi er utenfor et gitt spenn.

# Del 2 Oppgave 2:
### 1.Det ikke er mulig å merge en Pull Request inn i main branch. 
### 2.uten at koden kompilerer og.
### 3.enhetstester er kjørt uten feil.
### 4.Minst en annen person i teamet har godkjent endringen.

Under Settings/branch_protection_rules/new i repoet kan man huke av

### 1-Require a pull request before merging-Og under denne kan man huke av 
### 4-Require approvals-Hvor man kan sette antall
### 3-Require status checks to pass before merging
### 2-Require deployments to succeed before merging

# Oppgave 3
Forsøkte å sette opp docker men fikk det ikke til å lage image av applikasjonen i tide men jeg lot Dockerfile være igjen i prosjektet
