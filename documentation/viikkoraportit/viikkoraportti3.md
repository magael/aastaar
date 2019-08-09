## Viikkoraportti 3

Työtunteja kertyi yhteensä noin 32.

---

#### Viikon aikana syntynyttä pohdintaa:

* Projektin edistäminen on niin mielekästä, että tuli tehtyä tällä viikolla aika paljon hommia sen eteen. Ajattelin keskittyä ensi viikolla lähinnä toteuttamaan oman prioriteettijonon. Onkohan mahdollista, että jos muita toiminnallisuuksia ei juuri tulisi lisää, projektin voitaisiin kuitenkin katsoa edistyneen, kun tällä viikolla se eteni sen verran paljon?

* Testikattavuudessa on vähän parantamisen varaa, mutta oikeestaan kaikki paitsi Mainin GUI-jutut ja algoritmiluokat ovat nähdäkseni melko kattavasti testattu. Olen lykännyt algoritmien yksikkötestausta, koska niiden toteutukset ovat muuttuneet jatkuvasti paljon. Osaa nykyisistä luokista ei varmaan tule jäämään ollenkaan lopulliseen palautukseen. Jos siitä ei ole arvostelun kannalta hyötyä, niin en ehkä haluaisi merkitä testeille esim. Main-luokkaa skipattavaksi, vaikka se nostaisi vähän kattavuusprosenttia.

* Onkohan BFS arvostelun kannalta minkään arvoinen? Se myös taitaisi vaatia erikseen oman tietorakenteen toteutuksen.

* BFS palauttaa tällä hetkellä polun painoksi askelten määrän. Jos sitä verrataan painot huomioiviin algoritmeihin, on ehkä vähän harhaanjohtavaa, että sen polun painossa ei oteta huomioon polun "raskaampia" osuuksia.

* Onkohan A* ja Dijkstran vertailu liian alkeellista kurssin tavoitteisiin nähden? Tarkoitus olisi kyllä tehdä A* eri versioita vielä, mutta en tiedä, ehdinkö lähteä tekemään mitään ihan eri algoritmia. Pienempiä variaatioita, mitkä on ensiksi tavoitteena, on diagonaalisuuntiin liikkuminen, closed set / visited -merkkaus ja ilman, erilaiset heurestiikat ja ehkä vieruslistat/-matriisit ja ilman. Olen törmännyt monenlaisiin optimointeihin, mutta en ole vielä opiskellut niitä kovin tarkkaan.


---

#### Sunnuntai 4.8.

Pieniä parannuksia mm. BFS:n, Nodeille prioriteetti ja ensimmäinen yritys Dijkstraa. Noin 3h.

#### Maanantai 5.8.

BFS palauttaa itse polun (solmut taulukkona), GUI-ikkuna, ruudut asiaankuuluvilla väreillä, suunnittelua. Noin 6h.

## Tiistai 6.8.

Dijkstran korjausausyritys ja toinen versio Dijkstrasta. Node-luokan testaustaa. Alku- ja loppupisteiden ja polun piirtäminen kartalle ym. Noin 7h.

## Keskiviikko 7.8.

Toinen ja kolmas versio Dijkstrasta toimimaan ja A*. Polkujen painot, refaktoroitia, dokumentointia ym. Noin 7h.

## Torstai 8.8.

Algoritmirajapinta, ei-GUI-jutut pois Mainista ym. refaktorointia ja koodin siistimistä, visualisoinnin kehittämistä, dokumentointia, testausta ym. Noin 8h.

## Perjantai 9.8.

Viikkoraportti. Noin 1h.

