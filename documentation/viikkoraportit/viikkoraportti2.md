## Viikkoraportti 2

Työtunteja kertyi yhteensä noin 23.

---

#### Vastaus viime viikon palautteeseen:

Tl;dr: Kiitos vastauksista kysymyksiini! A* ym. määrittelyn suunnitelmat ovat vielä uutta ja jännittävää. Yritän parhaani, katsotaan mihin asti pääsen.

Mikäli pääsen siihen asti, että dynaaminen esteiden asettelu toimii, ja aikaa on vielä jäljellä, niin voisin syventyä D*:n.
D* on ilmeisesti erittäin hyvä yhden agentin, kuten robotin, dynaamiseen polunetsintään. Toisaalta se ei ilmeisesti skaalaudu hyvin simulaatioihin, joissa on paljon liikkuvia yksiköitä.

Monen agentin simulaatio kiinnostaa kovasti, mutta toisaalta vaikuttaa, että sen järkevää toteuttamista varten pitäisi mielellään ollut opiskellut rinnakkaisohjelmointia. Toisaalta voisi olla hauskaa tätäkin projektia varten kokeilla, miten pahasti suoritus kärsii, jos lisätään enemmän ja enemmän itsenäisiä yksiköitä.

Lähtökohtaisesti keskityn kuitenkin A*:n ja pyrin rakentamaan projektia niin, että jonkilainen polunetsintä toimii mahdollisimman aikaisin. Sen päälle voi sitten mahdollisuuksien mukaan rakentaa lisää variaatioita ja toiminnallisuuksia.

En ole soveltanut A*:a aiemmin, joten koen tärkeäksi saada siitä ensin kelvollisen ja toimivan, ennen optimointien suohon sukeltamista. Selityksenä paikoittaiselle yleiselle kädettömyydelleni voisin myös mainita, että Tiran suorittamisesta on kohta kaksi vuotta.

---

#### Huomioita ja askarruttavia asioita:

* Sain Gradlen asennettua, testaus toimii NetBeansissa ja Jacoco-raportti generoituu oikein, mutta kun yritän käyttää gradlea konsolin kautta, se kertoo, että taskeja ei löydy.

* Onkohan ok varata jokaisella reitinhakualgoritmin suorituksen aikana tapahtuvalla naapurien tarkistuksella esim. 4-alkioinen taulukko (tarkasteltavan alkion ala- ja yläpuolella sekä sivuilla oleville alkioille), jos ei tule muita oleellisempia tarpeita omalle yksinkertaiselle dynaamisen taulukkorakenteen toteutukselle? Tarkoitan siis Grid-luokan metodia getNeighbours().

* Saakohan Stringin metodia split() käyttää?

---

#### Lauantai 27.7.

Karttatiedostojen lukeminen, luokkahierarkian jäsentelyä, gitin kanssa säätöä, refaktorointia ym.

#### Sunnuntai 28.7.

Karttadata ArrayListista taulukkorakenteeseen, projektin suunnittelua ym. Muutama tunti Gradlen asennuksien kanssa tappelemista.

#### Ma-ke 29.-31.7.

Aiheesta lukemista.

#### Torstai 1.8.

Karttojen tallennus 2D-ruudukkoon. BFS teorian kertausta. Oleellisten luokkien (paitsi jonon ja hashmapin) ja algoritmin toteutus. Refaktorointia ym.

#### Perjantai 2.8.

Grid-luokka kartoille ym. refaktorointia. Naapurilista ArrayListista taulukoksi. Filereaderille testejä.