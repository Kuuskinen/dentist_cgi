# dentist_cgi

RAKENDUSE "CAROLINA JA GUSTAVI HAMBAARSTID OÜ" DOKUMENTATSIOON

SISUKORD: 
1) Rakenduse käivitamine - info, aadressid
2) Koodi dokumentatsioon - kirjeldused, kuidas rakenduse protsessid töötavad 
3) Üleüldine info - kirjeldused rakenduse loomise kohta (kuidas lahendasin, mida kasutasin, mis oli raske jne)

========================== 1. RAKENDUSE KÄIVITAMINE ==========================

Rakendus on programmeeritud IntelliJ-ga ning see peaks töötama peaaegu kõigis brauserites. 

Mõned rakenduse osad ei toimi Microsoft Edge'is ning mul kahjuks ei jätkunud aega, et see parandada. 

OLULINE: 
	
Rakendus käivitub localhostis, kasutades porti 8080. Käivitamiseks võib selle näiteks IDEs tööle panna.
Rakenduse andmebaasi konsool asub aadressil localhost:8080/console

Kasutaja nimi: sa
Parool: (tühi)

Rakenduse andmebaasi JDBC url on jdbc:h2:./resources/data

Rakendus on programmeeritud programmeerimiskeeles Java, kasutab mäluga liidestatud andmebaasi H2, mille keeleks on SQL ning andmebaasiga suhtlevad interface'id extendivad JpaRepository't.


========================== 2. KOODI DOKUMENTATSIOON ==========================

1) Hambaarsti visiidi lisamine //URL: localhost8080

Kasutaja saab vormile lisada soovitud arsti, kuupäeva ja kellaaja ning vajutada nuppu "Registreeri visiit". Salvestamise õnnestumisel suunatakse kasutaja lehele results.html.

Vormi salvestamisel tehakse lisatud väärtustest DentistVisitDTO (edaspidi DTO), milles on dentistId ehk valitud hambaarsti id, visitTime ehk valitud kuupäev ja visitClock ehk valitud kellaaeg. Submitiga käivitub ka DentistAppControlleris paiknev meetod postRegisterForm, mis kutsub välja DentistVisitService'i klassis paikneva meetodi addVisit ja annab sellele ette dentistId ja visitTime'i. 

addVisit loob uue DentistVisitEntity, millele lisab dentistId järgi õige DentistEntity. visitTime teisendatakse enne andmebaasi salvestamist Date'iks, sest andmebaas ei toeta LocalDateTime'i salvestamist. Seejärel visitRepository salvestab loodud DentistVisitEntity andmebaasi.




2) Registreeringute vaatamine //URL: localhost:8080/registrations.html

Lehekülje laadimisel renderdatakse registrations.html. Registreeringud küsitakse dentistVisitService'i meetodiga getAllVisits(). Seejärel kuvatakse tabelis registreeringud või siis info registreeringute puudumise kohta.



3) Registreeringu kustutamine //URL: localhost:8080/registrations.html osa

Registreeringut saab kustutada registreeringute lehel vastavat nuppu vajutades. Rakenduse programmeerimisel ei ole ettenähtud võimalust kustutada registreering url riba kasutades.

Registreeringute leheküljelt (punkt 2) valides registreeringu ning vajutades valitud registreeringuga samal real olevale nupule "Kustuta visiit", kutsutakse välja JavaScripti meetod deleteVisit, mis teeb XMLHttpRequesti aadressile /delete_visit, andes requestiga kaasa kustutatava registreeringu id. Päring kutsub välja DentistAppControlleri meetodi, mis teisendab Stringi kujul saadud id longiks ning annab selle visitRepository meetodile delete, mis kustutab antud id'ga kirje andmebaasist. 


NB! Ma ei jõudnud ära parandada olukorda, kus pärast kustutamise nupu vajutamist lehte ei uuendatud. Nupule vajutamise järel võib vajutada F5, mis lehekülje uuendab ning kustutatud kirjet enam ei kuvata.



4) Registreeringu uuendamine //URL: localhost:8080/change_registration/[id] näiteks localhost:8080/change_registration/2

Vajutades registreeringute leheküljel (punkt 2) mõne kirje juures lingile "Muuda visiiti", viiakse kasutaja leheküljele change_registration, kus on table, mille read on täidetud senise registreeringu andmetega. 

Pärast andmete muutmist vajutades nupule "Registreeri visiidile", tehakse POST-päring, mis kutsub välja meetodi postChangeForm. Leheküljel muudetud (või siis muutmata) andmetest tehakse DentistVisitDTO (edaspidi DTO), milles on dentistId ehk valitud hambaarsti id, visitTime ehk valitud kuupäev ja visitClock ehk valitud kellaaeg. Seejärel edastatakse DTO ja uuendatava visiidi id DentistVisitService'i meetodile changeVisit. 

changeVisit küsib andmebaasist muudetava DentistVisitEntity ja ühendab kuupäeva (visitTime) ja kellaaja (visitClock) ühte muutujasse, mille tüüp on LocalDateTime. See on vajalik, et kuupäev ja kellaaeg oleksid koos. LocalDateTime muutuja teisendatakse andmebaasi salvestamise jaoks Date'iks ning seejärel lisatakse see andmebaasist küsitud DentistVisitEntity'le. DentistVisitEntity'le lisatakse ka uus DentistEntity, sest arsti vahetumisel ei luba Spring Boot varasemalt salvestatud DentistEntity't muuta ning see tuleb täielikult üle kirjutada. Viimaks antakse uute andmetega üle kirjutatud DentistVisitEntity VisitRepository meetodile save, mis salvestab selle baasi.




========================== 3. ÜLEÜLDINE INFO ==========================

1) Etteantud ülesande punktide täitmine

Etteantud ülesande punktidest jäid realiseerimata otsing ja registreerimisaegade kontroll, ehk ei takistata olukorda, kus baasi üritatakse lisada registreeringut, mis kattub teisega.

Otseselt mujalt kopeeritud kood on märgistatud kommentaariga stiilis "//REF: [lehekülje nimi] 


2) Üldinfo rakenduse loomise protsessi kohta

Programmeerisin selle rakenduse umbes 35-40 tunniga. Tööd aeglustasid tunduvalt asjaolud, et ma polnud varem kasutanud mäluga liidestatud andmebaasi ega Thymeleafi. Andmebaasi puhul tekkis olukord, kus IntelliJ ja projekt kasutasid erinevaid H2 andmebaasi versioone, mille tulemusel corrupt'iti andmebaas alati ära, kui üritasin seda otse IntelliJs vaadata ning vea olemuse kindlaks tegemiseks kulus mitu tundi. Thymeleafi piisavalt selgeks õppimine võttis aega ja avaldas ajakulule otsest negatiivset mõju.

Kuna ma ei tahtnud kasutada paljaid SQL päringuid, vaid proovida midagi teistsugust, oli mõnevõrra raske andmete baasi kirjutamine ja sealt lugemine, kuid leidsin abi, uurides Thymeleafi dokumentatsiooni ja uurides Google'i pakutud vasteid Baeldungis, StackOverflow's ja W3Schoolsis. Kujunduse tegemisel kasutasin abi otsimisel ka Bootstrapi dokumentatsiooni.

Kõige raskem oli kellaaja ja kuupäeva kokkupanek ja andmebaasi lisamine. Avastasin viimasel hetkel, et sobivaks andmetüübiks on LocalDateTime, kuid selle kasutamiseks pidin palju varasemat koodi ümber kirjutama ning otsima, kuidas andmeid töödelda ja andmebaasi kirjutada. Abi sain Hibernate'i dokumentatsioonist ja taas ka Baeldungist.
