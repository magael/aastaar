
## Testausdokumentti

Ohjelmaa on testattu JUnit-yksikkötesteillä, ja algoritmien polunetsinnälle on suoritustestausta. Yksikkötestit löytyvät testihakemistosta <code>aastaar/src/test</code> ja suorituskykytestit <code>main</code>-hakemistosta, luokasta <code>PathfindingPerformanceTester.java</code>.

### Yksikkötestit

Testit kattavat kaikki luokat, paitsi käyttöliittymäluokkina raporteista poissuljetut <code>Main</code> ja <code>Scenario</code> -luokat, sekä suorituskykytestauksen.

Yksikkötesteissä käytetään testikarttoja, jotka vastaavat formaatiltaan sovelluksen saamia karttoja. Testit pyrkivät varmistamaan, että kaikki luokat ja metodit toimivat halutulla tavalla sekä oikean-, että vääränlaisilla syötteillä ja rajatapauksissa.

Yksikkötestit voidaan toistaa suorittamalla gradle-projektin juuressa, eli repositorion alihakemistossa <code>aastaar</code> komennon <code>gradle test</code>.

Testikattavuus löytyy komennolla <code>gradle test jacocoTestReport</code> ja navigoimalla <code>build/reports/jacoco/test/html/index.html</code>.

### Suorituskykytestit

Polunetsinnän suorituskykytestit ajetaan automaattisesti sen jälkeen, kun käyttäjä on sulkenut visuaalisaatioon käytetyn käyttöliittymäikkunan.

Suorituskykytestit ottavat mallia kurssin testausmateriaaleista, mutta sovellus on erilainen ja toistoja tulee eri määrä.

#### Suorituskykytetauksen analysointia

Esimerkkitulos suorituskykytestauksesta:

<code>
Average runtime of pathfinding between two random points:


Dijkstra

100 repetitions: 8281923.97 ns

2500 repetitions: 4911397.5388 ns

10000 repetitions: 4448395.5014 ns

40000 repetitions: 4506471.535825 ns


A*

100 repetitions: 1587182.68 ns

2500 repetitions: 1348058.5236 ns

10000 repetitions: 1296579.5467 ns

40000 repetitions: 1295177.181425 ns


Performance tests ran in a total of 609.855288603 seconds.
</code>

Esimerkissä toistoja oli 100, 2500, 10000 ja 40000, ja testien suorittamiseen kului reilut 10 minuuttia koneella, jossa on Intel i5-8400 suoritin ja 16 GB keskusmuistia.

A* on testien perusteella noin kolme kertaa yhtä nopea, kuin Dijkstra (Uniform cost search).

Dijkstran suuret ajat pienillä toistoilla tarkoittavat varmaan, että erittäin hitaita ääritapauksia on runsaasti, ja niiden vaikutus keskiarvoihin hiipuu vasta suuremmilla toistoilla.

Toisaalta testien <code>n * n</code> aiheuttaa sen, että jos ymmärsin oikein, niin roskienkeruun ym. aiheuttaman hälyn minimoimiseksi käytetään huomattavasti vähemmän vaivaa. Ehkä toisen muuttujan <code>n</code> sijaan voitaisiin käyttää jotain vakiota, kuten materiaalissa.