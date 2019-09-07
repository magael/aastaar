## Toteutusdokumentti

### Ohjelmiston rakenne

Lähdekoodin juuressa <code>aastaar/src/main/java/mj/aastaar</code> on ohjelman käynnistyksestä ja graafisesta käyttöliittymästä huolehtiva <code>Main</code>, joka hyödyntää luokan <code>Scenario</code> ilmentävää polunetsintäskenaariota. <code>Scenarion</code> avulla kerätään algoritmien palauttamat polut ja ylläpidetään tietoa käytössä olevista lähtö- ja maalipisteestä sekä polunetsintäruudukosta.

Kansiossa <code>aastaar.map</code> on luokka <code>MapCreator</code>, joka luo tiedostonlukijan antaman datan perusteella kartan maastoa kuvaavan merkistötaulukon. Taulukon avulla alustetaan <code>Grid</code>-luokan ilmentymä, jonka jälkeen oliota voidaan käyttää tarkastelemaan polunetsintäruudukon soluja: tietyn solun saavutettavissa olevia naapureita, solujen / ruutujen / solmujen välisen "kaaripainon" laskemiseen ja niin edelleen. <code>Node</code>-luokka ylläpitää yhden ruudun koordinaatteja.

Algoritmi-luokat, sekä niiden lyhyimmän polun tilan tallentamiseen käyttävä <code>Path</code>-rajapinnan toteuttavat luokat löytyvät hakemistosta <code>aastaar.algorithms</code>. <code>A*</code>-luokat perivät <code>Dijkstra</code>-luokat, erona on vain solmujen välisen matkan kustannukseen käytetty heuristiikka. Molemmat toteuttavat <code>PathfindingAlgorithm</code>-rajapinnan. Algoritmien käyttämät tietorakenteet: itse toteutetut prioriteettijono ja hajautustaulu, sekä hajautustaulun alkiona käyttämä "pari"-luokka <code>CustomEntry</code> löytyvät kansiosta <code>aastaar.datastructures</code>.

<code>aastaar.utils</code>-hakemisto sisältää tiedostonlukijan sekä algoritmien suorituskykytestaukseen käytettävän luokan <code>PathfindingPerformanceTester</code>. Pelikarttojen tiedostot löytyvät hakemistosta <code>aastaar/src/main/resources</code>.

#### Luokkakaavio pakkausrakenteella

HUOM: Hajautustaulutoteutuksia ei ole päivitetty kaavioihin, eli luokat CustomHashMap, CustomEntry, PathWithHashMap, DijkstraWithHashMap sekä AStarWithHashMap puuttuvat kaavioista. Lisäksi algoritmiluokat on nimetty uudelleen: UniformCostSearch -> DijkstraWithArray ja AStar -> AStarWithArray.

![Luokkakaavio pakkauksilla](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_simple.png)

#### Kaavio kaikilla luokkien välisillä yhteyksillä

![Luokkakaavio kaikilla yhteyksillä](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_no_packages.png)

#### Pakkauskohtaisia kaavioita

[aastaar](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_main.png)

[aastaar.algorithms](https://github.com/magael/aastaar/blob/master/documentation/luokkakaavio/kaavio_algorithms.png)

---

### Algoritmien ja tietorakenteiden toteutus

Algoritmit käyttävät itse toteutettua prioriteettijonoa, eli minimikekoa. Algoritmit lopettavat haun ja palauttavat lyhyimmän reitin pituuden heti, kun maalisolmu löytyy jonosta.

Algoritmien ja prioriteetijonon yksinkertaistamiseksi etäisyysarvioita ei päivitetä jonossa olevaan solmuun, vaan solmuun lisätään kopioita uusilla prioriteeteillä. [Red Blob Games](https://www.redblobgames.com/pathfinding/a-star/implementation.html#algorithm) siteeraa [Chen et al.](https://www3.cs.stonybrook.edu/~rezaul/papers/TR-07-54.pdf) tutkimusta, jonka mukaan tämä on käytännössä myös nopeampaa.

Prioriteettijonojen lisäksi algoritmeista on versiot, jotka hyödyntävät kaksiulotteisia taulukoita, sekä hajautustaulua käyttävät variaatiot.

Taulukot vievät paljon tilaa, mutta alkion hakeminen niistä on nopeaa. Tietorakenteisiin varataan tilaa jokaiselle verkon/ruudukon solmulle. Algoritmien taulukkoversiossa <code>cost</code>-taulukkoon alustetaan jokaiselle solmulle lähtöarvo. Näin ollen taulukkovariaatioiden aikavaativuus on O(n), missä n on verkon solmujen lukumäärä.

Pienemmäksi alustettua hajautustaulua hyödyntämällä tilaa varataan parhaimmassa tapauksessa vain algoritmin tarkastelemille solmuille. Hajautustaulun tehokkuus riippuu siitä, kuinka pienenä taulukko pysyy, ja mikä on sen täyttösuhde. Etenkin etäisyysarviot olisi oletettavasti nopeampaa toteuttaa hajautustaululla. Suorituskykytestien mukaan hajautustaulut ovat kuitenkin taulukkototeutuksia hitaampia (myös Javan valmiilla hajautustaululla). Testailusta voi lukea lisää [testausdokumentista](https://github.com/magael/aastaar/blob/master/documentation/testaus.md)

---

### Jatkosuunnitelmia

HashMapin suoritusta voisi todennäköisesti parantaa testailemalla  lisää eri toteutuksia <code>Node</code>-luokan <code>hashCode</code>-metodille, sekä eri aloituskokoja ja hajautusfunktioita hajautustaululle. Myös prioriteettijonon kokoa voisi optimoida.

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