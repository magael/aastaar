## Toteutusdokumentti

Ohjelman tämänhetkinen tila vastaa pitkälti vaatimusmäärittelyssä määriteltyjä [perustoiminnallisuuksia](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#perustoiminnallisuuksia).

Lisää toiminnallisuuksia on vielä tavoitteena toteuttaa. Niiden suunnitelmia löytää [vatimusmäärittelyn "edistyneitä"](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#edistyneit%C3%A4--jatkokehityksen-toiminnallisuuksia) kohdasta, sekä [TODO-muistiinpanoista](https://github.com/magael/aastaar/blob/master/documentation/todo.md).

[Lähteet](https://github.com/magael/aastaar/blob/master/documentation/maarittely.md#l%C3%A4hteet) löytyvät myös vaatimusmäärittelystä.

#### Checkstyle

Projekti hyödyntää checkstyleä. Tyylivirheet voidaan tarkastaa komennolla <code>gradle check</code> ja ne löytyvät polulta <code>build/reports/checkstyle/test.html</code>.

#### Javadoc

Javadocin saa generoitua komennolla <code>gradle javadoc</code>. API löytyy polulta <code>build/docs/javadoc/index.html</code>.