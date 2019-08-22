
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

<code>Average runtime of pathfinding between two random points:

Dijkstra

10 repetitions: 11.494981300000001 ms

50 repetitions: 7.86717338 ms

100 repetitions: 8.46128165 ms

500 repetitions: 8.910111534 ms

1000 repetitions: 8.743191996 ms

A*

10 repetitions: 2.8967281000000002 ms

50 repetitions: 2.3123797799999997 ms

100 repetitions: 2.33421557 ms

500 repetitions: 2.6272582239999998 ms

1000 repetitions: 2.5015800720000003 ms

Performance tests ran in a total of 937.341475022 seconds.</code>

Esimerkin testien suorittamiseen kului reilu vartti koneella, jossa on Intel i5-8400 suoritin ja 16 GB keskusmuistia. Toistoja oli 10, 50, 100, 500 ja 1000 niin, että jokaista tulosta varten sama haku toistettiin 50 kertaa, joista otettiin keskiarvo roskienkeruun ym. aiheuttaman hälyn minimoimiseksi.

Suoritustestaamista edeltää alustus, jossa eri toistokerroille arvotaan lähtö- ja maalipisteet. Eri kierroksilla käytetään (arvonnasta riippuen) eri sijainteja, mutta eri algoritmit käyttävät samoja sijainteja.

A* on esimerkin ja muiden testien perusteella noin kolme-neljä kertaa niin nopea, kuin Dijkstra (Uniform cost search).

Dijkstran suhteessa paljon suuremmat ajat pienillä toistoilla tarkoittavat varmaan, että erittäin hitaita ääritapauksia on runsaasti, ja niiden vaikutus keskiarvoihin hiipuu vasta suuremmilla toistoilla.

Algoritmeja on testattu myös "visited"-taulukolla ja ilman (erillisillä kopioilla algoritmiluokista, joissa visited on käytössä). Visited-merkinnällä voidaan luoda tarkistus, onko jonon kärjestä juuri poistetu solmu käsitelty aiemmin. Tarkistus tehdään ennen haun laajennusta seuraaviin vierussolmuihin. Taulukon alustus ja tarkistus vievät tilaa ja aikaa, mutta ilman niitä saatetaan tarkastella turhaan samaa solmua useamman kerran. Suoritusajat vaihtelivat, mutta mitään suurta tai selkeää eroa en onnistunut saamaan. Päätin toistaiseksi poistaa kommentoidut visited-merkinnät koodista. Aiempia toteutuksia löytyy repositorion commit-historiasta ja eri brancheista.