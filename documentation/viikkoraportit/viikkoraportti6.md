## Viikkoraportti 6

Työtunteja kertyi yhteensä noin 24h.

---

#### Huomioita:

* Kun edelliset viikot meni pitkälti dokumentointiin, testailuun, tietorakenteisiin jne., niin tekikin mieli keskittyä tällä viikolla käyttöliittymään ynnä muuhun. Oma HashMappi olisi vielä listan kärjessä ensi viikolle. Joitain suunnitelmia sitten vaan jää tämän kurssin puitteissa toteuttamatta, kun uudet kurssit puskee päälle.

* Uusi AlgorithmVisualization-luokka myös excludettu jacoco-raportista: Sisältää vain yksinkertaisia gettereitä ja settereitä.

* Mainin GUI:n rakentelu rupeaa paisumaan, etenkin uusimpien työkalupalkin lisäysten myötä. Arvostellaanko sitä koodia millä tavalla, että kannattaako esim. ToolBarin rakentelua refaktoroida luokkaan? Yleisesti ottaen varmaan kannattaisi, mutta kysyn arvostelusta nyt, kun kurssin asioihin on enää vähän aikaa.

---

#### Ma 26.8.

HashMapin teorian kertausta, tarkasteltujen polkujen visualisointi alkuun, n. 4h.

#### Ti 27.8.

GUI-juttuja: Kartan, pisteiden ja polkujen renderöinti uudella, nopeammalla tavalla (GridPanen ja Rectanglejen sijaan Canvas ja GraphicsContext rectFill), napit uuden reitin ja tarkasteltujen polkujen visualisoinnille ym. Ohjelmiston rakenteen dokumentointia, luokkakaavio. N. 6-7h.

#### Ke 28.8.

Vertaispalaute. Pientä kälin säätöä, mm. polun pisteille ääriivivat ja dokumentointia. N. 2h.

#### To 29.8.

Useita karttavaihtoehtoja, vaihtelu napeista. VisualizationAlgorithm-luokka, Mainin ja Scenarion toiminnan ja toimialueen selkeytys. Suorituskykytestit käli-ikkunaan. Sekalaisia GUI-uudistuksia, refaktorointia ja dokumentointia. N. 9h.

#### Pe 30.8.

Aloitettu polkujen pituuksien ja painojen listaus työkalupalkkiin, sekä alku- ja loppupisteiden koordinaattien määrittely tekstikentillä (branch guiInfo). Käyttöohje. N. 2h.