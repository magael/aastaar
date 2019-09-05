
## Testausdokumentti

Ohjelmaa on testattu JUnit-yksikkötesteillä, ja algoritmien polunetsinnälle on suoritustestausta. Yksikkötestit löytyvät testihakemistosta <code>aastaar/src/test</code> ja suorituskykytestit <code>main</code>-hakemistosta, luokasta <code>PathfindingPerformanceTester.java</code>.

### Yksikkötestit

Testit kattavat kaikki luokat, paitsi käyttöliittymäluokkina raporteista poissuljetut <code>Main</code> ja <code>Scenario</code> -luokat, sekä suorituskykytestauksen.

Yksikkötesteissä käytetään testikarttoja, jotka vastaavat formaatiltaan sovelluksen saamia karttoja. Testit pyrkivät varmistamaan, että kaikki luokat ja metodit toimivat halutulla tavalla sekä oikean-, että vääränlaisilla syötteillä ja rajatapauksissa.

Yksikkötestit voidaan toistaa suorittamalla gradle-projektin juuressa, eli repositorion alihakemistossa <code>aastaar</code> komennon <code>gradle test</code>.

Testikattavuus löytyy komennolla <code>gradle test jacocoTestReport</code> ja navigoimalla <code>build/reports/jacoco/test/html/index.html</code>.

Yksikkötestit löytyvät hakemistosta <code>aastaar/src/test/java/aastaar</code> ja niiden käyttämä testikartta <code>aastaar/src/test/resources/testmaps</code>.

### Suorituskykytestit

  hashjutut:
  - jos haut suoritetaan per algoritmi vain kerran niin, että tietorakenteet alustetaan ainoastaan konstruktorissa, alustus on pari kymmentä kertaa nopeampi, ja itse haku pari sataa kertaa nopempi hashmapilla, vrt. arraytoteutuksiin.
- tällöin oma hashmap on hieman hitaampi alustaa (omien testailujen perusteella kuudesosan-kolmasosan), mutta itse haut ovat käytännössä tasan yhtä nopeita.
- 
- jos alustetaan joka haulla uusiksi, niin hashmapin alustus on jostain syystä puolet-tuplasti hitaampi, kuin arrayden.
  - tällöin oma hashmaptoteutus testattu olevan suunnilleen yhtä nopea (välillä jopa nopeampi, mutta yleensä n. 10% hitaampi), kuin Javan valmiita tietorakenteita käyttävä
- hashmapin pieni vakiokoko, esim. 16 johtaa useisiin taulun kasvatuksiin, mutta yllättäen esim. 250000 alkion kokoinen taulukko, joka pitää lähes taatusti täyttöasteen alle 75%:ssa (eikä silloin tuota taulukon kasvatuksia), on suunnilleen yhtä hidas.
- arrayversio voi käyttää kertaalleen alustettuja tietorakenteita uusiksi, kun cost alustetaan joka kierroksella uusiksi (joka solmulle, paitsi startilla alkuarvoksi miljardi)
  - paitsi haku ilmeisesti joskus erittäin harvoin epäonnistuu, jolloin polku häviää, ja konsoliin tulostuu "Path not found". harmillisesti ei ole aikaa enempää debuggailla ja kehittää edelleen. jätin toteutuksen kuitenkin paikoille, koska mielestäni kertaalleen alustettujen tietorakenteiden uudellenkäyttö on mielenkiintoinen ero. poluista tulee hieman erilaiset (joskin samanpituiset), kun array-versio käyttää edellisten hakujen täydentämää polun cameFrom-taulukkoa.
- koska joissain kartoissa on eristettyjä saarekkeita, joskus erittäin harvoin haut epäonnistuvat tiettyjen pisteiden välillä. tämä saattaa vaikuttaa keskimääräisiin hakujen suoritusaikoihin, etenkin pienillä toistoilla (pisteiden määrillä).

Polunetsinnän suorituskykytestit voidaan ajaa käyttöliittymäikkunassa napista "Run performance tests". Tällä hetkellä testeille annetaan suoritettavaksi kovakoodatun joukon kierroksia {10, 10, 20}. Testit vievät omalla koneellani n. 24 sekuntia.

Kierrosjoukkoa voidaan muokata lähdekoodista, <code>Main</code>-luokan metodista <code>runPerformanceTests</code>. Tulokset voidaan myös esimerkiksi tulostaa konsoliin komennolla <code>System.out.println(runPerformanceTests(scenario.getAlgorithmVisuals()));</code>.

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

Esimerkissä käytettiin Warcraft 3 -pelin Divide and Conquer -karttaa 512 * 512 -ruudukolla (Moving AI Labs:in formaatissa). Testien suorittamiseen kului reilu vartti koneella, jossa on Intel i5-8400 suoritin ja 16 GB keskusmuistia. Toistoja oli 10, 50, 100, 500 ja 1000 niin, että jokaista tulosta varten sama haku toistettiin 50 kertaa, joista otettiin keskiarvo roskienkeruun ym. aiheuttaman hälyn minimoimiseksi.

Suoritustestaamista edeltää alustus, jossa eri toistokerroille arvotaan lähtö- ja maalipisteet. Eri kierroksilla käytetään (arvonnasta riippuen) eri sijainteja, mutta eri algoritmit käyttävät samoja sijainteja.

A* on esimerkin ja muiden testien perusteella noin kolme-neljä kertaa niin nopea, kuin Dijkstra (Uniform cost search).

Algoritmeja on testattu myös "visited"-taulukolla ja ilman (erillisillä kopioilla algoritmiluokista, joissa visited on käytössä). Visited-merkinnällä voidaan luoda tarkistus, onko jonon kärjestä juuri poistetu solmu käsitelty aiemmin. Tarkistus tehdään ennen haun laajennusta seuraaviin vierussolmuihin. Taulukon alustus ja tarkistus vievät tilaa ja aikaa, mutta ilman niitä saatetaan tarkastella turhaan samaa solmua useamman kerran. Suoritusajat vaihtelivat, mutta mitään suurta tai selkeää eroa en onnistunut saamaan. Päätin toistaiseksi poistaa kommentoidut visited-merkinnät koodista. Näin koodi on yksinkertaisempaa ja algoritmit varaavat hieman vähemmän tilaa. Aiempia toteutuksia löytyy repositorion commit-historiasta ja eri brancheista.