
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

<code>Dijkstra

10 repetitions: 77.3481342 ms

50 repetitions: 221.90776054 ms

100 repetitions: 435.38649045 ms

500 repetitions: 2311.442512906 ms

1000 repetitions: 4411.7864732279995 ms

A*

10 repetitions: 13.640928800000001 ms

50 repetitions: 70.80646542 ms

100 repetitions: 109.17883205 ms

500 repetitions: 659.588384864 ms

1000 repetitions: 1333.773475862 ms

Performance tests ran in a total of 968.585246135 seconds.</code>

Esimerkin testien suorittamiseen kului reilu vartti koneella, jossa on Intel i5-8400 suoritin ja 16 GB keskusmuistia. Toistoja oli 10, 50, 100 ja 500 ja 1000 niin, että jokaista tulosta varten sama haku toistettiin 50 kertaa, joista otettiin keskiarvo roskienkeruun ym. aiheuttaman hälyn minimoimiseksi.

Suoritustestaamista edeltää alustus, jossa eri toistokerroille arvotaan lähtö- ja maalipisteet. Eri kierroksilla käytetään (arvonnasta riippuen) eri sijainteja, mutta eri algoritmit käyttävät samoja sijainteja.

A* on esimerkin ja muiden testien perusteella noin neljä kertaa yhtä nopea, kuin Dijkstra (Uniform cost search).

Dijkstran suhteessa paljon suuremmat ajat pienillä toistoilla tarkoittavat varmaan, että erittäin hitaita ääritapauksia on runsaasti, ja niiden vaikutus keskiarvoihin hiipuu vasta suuremmilla toistoilla.

Algoritmeja on testattu myös "visited"-taulukolla ja ilman. Visited-merkinnällä voidaan luoda tarkistus, onko jonon kärjestä juuri poistetu solmu käsitelty aiemmin, joka käydään ennen haun laajennusta seuraaviin vierussolmuihin. Suoritusajat vaihtelivat, mutta mitään suurta tai selkeää eroa en onnistunut saamaan. Päätin toistaiseksi poistaa kommentoidut visited-merkinnät koodista.