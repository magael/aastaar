## Toteutusdokumentti

---

### Ohjelmiston rakenne

Lähdekoodin juuressa <code>aastaar/src/main/java/mj/aastaar</code> on ohjelman käynnistyksestä ja graafisesta käyttöliittymästä huolehtiva <code>Main</code>, joka hyödyntää luokan <code>Scenario</code> ilmentävää polunetsintäskenaariota. <code>Scenarion</code> avulla kerätään algoritmien palauttamat polut ja ylläpidetään tietoa käytössä olevista lähtö- ja maalipisteestä sekä polunetsintäruudukosta.

Kansiossa <code>aastaar.map</code> on luokka <code>MapCreator</code>, joka luo tiedostonlukijan antaman datan perusteella kartan maastoa kuvaavan merkistötaulukon. Taulukon avulla alustetaan <code>Grid</code>-luokan ilmentymä, jonka jälkeen oliota voidaan käyttää tarkastelemaan polunetsintäruudukon soluja: tietyn solun saavutettavissa olevia naapureita, solujen / ruutujen / solmujen välisen "kaaripainon" laskemiseen ja niin edelleen. <code>Node</code>-luokka ylläpitää yhden ruudun koordinaatteja.

Algoritmi-luokat, sekä niiden lyhyimmän polun tilan tallentamiseen käyttävä <code>Path</code>-rajapinnan toteuttava luokka löytyvät hakemistosta <code>aastaar.algorithms</code>. <code>A*</code> perii <code>UniformCostSarchin</code>, erona on vain solmujen välisen matkan kustannukseen käytetty heuristiikka. Molemmat toteuttavat <code>PathfindingAlgorithm</code>-rajapinnan. Algoritmien käyttämä prioriteettijonototeutus löytyy kansiosta <code>aastaar.datastructures</code>.

<code>aastaar.utils</code>-hakemisto sisältää tiedostonlukijan sekä algoritmien suorituskykytestaukseen käytettävän luokan <code>PathfindingPerformanceTester</code>. Pelikarttojen tiedostot löytyvät hakemistosta <code>aastaar/src/main/resources</code>.

#### Luokkakaavio

![Luokkakaavio](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_no_packages.png)

#### Yksinkertaistettu kaavio pakkauksilla

![Pakkausrakenne](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_simple.png)

#### Pakkauskohtaisia kaavioita

[aastaar](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_main.png)

[aastaar.algorithms](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_algorithms.png)

---

### Algoritmien ja tietorakenteiden toteutus

Algoritmit käyttävät itse toteutettua prioriteettijonoa, eli minimikekoa. Algoritmit lopettavat haun ja palauttavat lyhyimmän reitin pituuden heti, kun maalisolmu löytyy jonosta.

Algoritmien ja prioriteetijonon yksinkertaistamiseksi etäisyysarvioita ei päivitetä jonossa olevaan solmuun, vaan solmuun lisätään kopioita uusilla prioriteeteillä. [Red Blob Games](https://www.redblobgames.com/pathfinding/a-star/implementation.html#algorithm) siteeraa [Chen et al.](https://www3.cs.stonybrook.edu/~rezaul/papers/TR-07-54.pdf) tutkimusta, jonka mukaan tämä on käytännössä myös nopeampaa.

Prioriteettijonojen lisäksi algoritmit hyödyntävät kaksiulotteisia taulukoita, jotka vievät paljon tilaa, mutta joista alkion hakeminen on nopeaa. Tilaa varataan siis jokaiselle verkon/ruudukon solmulle, esim. <code>cameFrom</code> taulukkoon (n * n), missä n on solmujen lukumäärä.

Hajautustaulua hyödyntämällä tilaa varattaisiin vain algoritmin tarkastelemille solmuille. Etenkin etäisyysarviot olisi oletettavasti nopeampaa ja vähemmän tilaa vievää toteuttaa hajautustaululla. Tällä hetkellä <code>cost</code>-taulukkoon alustetaan jokaiselle solmulle lähtöarvo.

Koodin yksinkertaistamiseksi ja tilavaatimuksen minimoimiseksi algoritmit eivät hyödynnä "vierailtu"-tarkistusta (<code>visited</code>). Tästä voi lukea lisää [testausdokumentista](https://github.com/magael/aastaar/blob/master/documentation/testaus.md).

---

### Jatkosuunnitelmia

Seuraavaksi vuorossa on oman HashMapin toteuttaminen ja soveltaminen, algoritmien suorituksen optimoimiseksi (tila- ja aikavaativuus alas).

Ohjelman toteuttaa tällä hetkellä kaikki vaatimusmäärittelyssä määritellyt [perustoiminnallisuudet](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#perustoiminnallisuuksia). BFS-luokka on poistettu repositorion main-branchista, koska sen ei koettu tuovan projektille paljoa lisäarvoa. BFS:n sijaan vertailukohteena on Dijkstran algoritmi.

Vatimusmäärittelyn kohdasta ["edistyneitä / jatkokehityksen toiminnallisuuksia"](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#edistyneit%C3%A4--jatkokehityksen-toiminnallisuuksia) ainakin polun painot, kartan valinta käyttäjän syötteestä ja ruudukon valinnainen skaalaus on jo toteutettu.

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