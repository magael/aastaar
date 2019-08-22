## Toteutusdokumentti

---

### Ohjelman suoritus

Projekti toteuttaa polunetsintää kahden pisteen välillä pelikartoilla. Ohjelmointikieli on Java ja graafinen käyttöliittymä on tehty Java FX:llä.

Ohjelma lukee resurssikansiosta [Moving AI Labs:in 2D Pathfinding Benchmark -karttoja](https://movingai.com/benchmarks/grids.html) ja muuntaa niistä polunetsintään soveltuvia kaksiulotteisia taulukoita.

Kartta on tällä hetkellä kovakoodattuna ja vaihdettavissa Scenario-luokan init-medoteissa. Lähtö- ja maalipisteet generoidaan joka suorituskerralla satunnaisesti.

Karttoihin sovelletaan Dijkstran algoritmin Uniform cost search -variaatiota (jonoon alustetaan vain lähtösolmu) sekä A*-algoritmia. Ohjelma kertoo tulostuksilla konsoliin, kun algoritmi on suorittanut toimintonsa.

Algoritmien suoritusten jälkeen avataan graafisen käyttöliittymän ikkuna, jossa kartan maasto on visualisoitu kuvaavin värein. Eri algoritmien löytämät lyhyimmät polut, sekä alku- ja maalipisteet ovat meritty kirkkain värein.

Käyttöliittymäikkunan sulkemisen jälkeen aloitetaan suorituskykytestaus. Kun testit on suoritettu, tulokset tulostetaan konsoliin.

---

### Algoritmien ja tietorakenteiden toteutus tarkemmin

Algoritmit käyttävät itse toteutettua prioriteettijonoa, eli minimikekoa. Algoritmit lopettavat haun ja palauttavat lyhyimmän reitin pituuden heti, kun maalisolmu löytyy jonosta.

Algoritmien ja prioriteetijonon yksinkertaistamiseksi etäisyysarvioita ei päivitetä jonossa olevaan solmuun, vaan solmuun lisätään kopioita uusilla prioriteeteillä. [Red Blob Games](https://www.redblobgames.com/pathfinding/a-star/implementation.html#algorithm) siteeraa [Chen et al.](https://www3.cs.stonybrook.edu/~rezaul/papers/TR-07-54.pdf) tutkimusta, jonka mukaan tämä on käytännössä myös nopeampaa.

Prioriteettijonojen lisäksi algoritmit hyödyntävät kaksiulotteisia taulukoita, jotka vievät paljon tilaa, mutta joista alkion hakeminen on nopeaa. Tilaa varataan siis jokaiselle verkon/ruudukon solmulle, esim. <code>cameFrom</code> taulukkoon (n * n), missä n on solmujen lukumäärä.

Hajautustaulua hyödyntämällä tilaa varattaisiin pahimmassa tapauksessa yhtä paljon, mutta yleisesti vain algoritmin tarkastelemille solmuille. Etenkin etäisyysarviot olisi oletettavasti nopeampaa ja vähemmän tilaa vievää toteuttaa hajautustaululla. Tällä hetkellä <code>cost</code>-taulukkoon alustetaan jokaiselle solmulle lähtöarvo.

Koodin yksinkertaistamiseksi ja tilavaatimuksen minimoimiseksi algoritmit eivät hyödynnä "vierailtu"-tarkistusta (<code>visited</code>). Tästä voi lukea lisää [testausdokumentista](https://github.com/magael/aastaar/blob/master/documentation/testaus.md).

---

### Jatkosuunnitelmia

Tarkoitus on vielä toteuttaa algoritmeista eri variaatioita ja optimointeja: Esimerkiksi viistosuuntainen liikkuminen ja eri heuristiikkoja.

Toivottavasti aikaa jää myös tekemään käyttöliittymästä joustavamman, ettei pisteiden, karttojen ja variaatioiden muokkaamiseksi tarvitse muokata lähdekoodia.

Ohjelman tämänhetkinen tila vastaa pitkälti vaatimusmäärittelyssä määriteltyjä [perustoiminnallisuuksia](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#perustoiminnallisuuksia).

Lisää suunnitelmia löytyy [vatimusmäärittelyn "edistyneitä"](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#edistyneit%C3%A4--jatkokehityksen-toiminnallisuuksia) kohdasta. Niistä ainakin polun painot on jo toteutettu.

Koodin seasta on siistitty pois erilliseen tiedostoon tiettyihin metodeihin liittyviä [TODO-muistiinpanoja](https://github.com/magael/aastaar/blob/master/documentation/todo.md).

---

### Lähteet

https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/

https://en.wikipedia.org/wiki/A*_search_algorithm

https://en.wikipedia.org/wiki/Binary_heap

https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

https://movingai.com

https://www.redblobgames.com

---

#### Checkstyle

Projekti hyödyntää checkstyleä. Tyylivirheet voidaan tarkastaa komennolla <code>gradle check</code> ja ne löytyvät polulta <code>build/reports/checkstyle/</code>. Checkstyle valittaa ainakin tällä hetkellä myös getterien ja setterien Javadoceista, ynnä muusta turhasta.

#### Javadoc

Javadocin saa generoitua komennolla <code>gradle javadoc</code>. API löytyy polulta <code>build/docs/javadoc/index.html</code>.