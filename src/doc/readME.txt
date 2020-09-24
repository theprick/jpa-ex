 Modelul:
                    +---------+
                    |Contact  |
                    +---------+
                    |id       |
                    |firstName|
                    |lastName |
                    +---------+
                        ^
                        |
           +----------------------------+
           |                            |
           |                            |
    +------------------+            +---------+
    |ContactUk         |            |ContactFr|
    +------------------+            +---------+
    |tradeName         |            |birthDate|
    |whoisOptOutEnabled|            |siren    |
    |entityType        |            |vat      |
    +------------------+            +---------+

 Avem o clasa parinte Contact care are proprietatile comune si este mostenita de clasele ContactUk si ContactFr.
 Toate clasele sunt annotate cu @Entity.
 Pe clasa parinte Contact specificam inheritance strategy prin annotarea:

 @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

 Aceasta poate avea 3 valori posibile:
    - SINGLE_TABLE,
    - TABLE_PER_CLASS,
    - JOINED;

 1) O tabela pentru fiecare clasa ( @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) )
 Ce e important de retinut in acest caz e ca id-ul pentru Contact trebuie sa aiba:
 @GeneratedValue(strategy = GenerationType.TABLE)
 (http://stackoverflow.com/questions/916169/cannot-use-identity-column-key-generation-with-union-subclass-table-per-clas)

 Pe copiii ContactUk si ContactFr punem annotarea
 @AttributeOverrides({
         @AttributeOverride(name="firstName", column=@Column(name="firstName")),
         @AttributeOverride(name="lastName", column=@Column(name="lastName"))
 })

 In acest caz in baza de date vom avea 2 tabele contactuk si contactfr pentru fiecare copil care contin si
 coloanele comune din parinte.

 2) O singura tabela ( @Inheritance(strategy = InheritanceType.SINGLE_TABLE) )

 In acest caz in baza de date vom avea o singura tabela contact care va contine toate campurile parintelui si ale
 celor 2 copii. In plus va contine o coloana aditionala "DTYPE" pe post de discriminator care va avea ca si valori
 numele claselor java "ContactFr" respectiv "ContactUk"

 Extras din log-ul de hibernate produs la rularea testului de creare de contacte:
     Hibernate: insert into Contact (firstName, lastName, birthDate, siren, vat, DTYPE) values (?, ?, ?, ?, ?, 'ContactFr')
     Hibernate: insert into Contact (firstName, lastName, entityType, tradeName, whoisOptOutEnabled, DTYPE) values (?, ?, ?, ?, ?, 'ContactUk')

 Open question: Putem modifica discriminatorul in vreun fel?

 3) Join tables ( @Inheritance(strategy = InheritanceType.JOINED) )

 In acest caz in baza de date vom avea 3 tabele: contact, contactuk si contactfr. Tabela contact va contine
 proprietatile comune iar celelate doua cele specifice fiecarui copil.
 Fiecare copil va avea un foreign key catre parinte.

 Extras din log-ul de hibernate produs la rularea testului de creare de contacte:
    Hibernate: insert into Contact (firstName, lastName) values (?, ?)
    Hibernate: insert into ContactFr (birthDate, siren, vat, id) values (?, ?, ?, ?)
    Hibernate: insert into Contact (firstName, lastName) values (?, ?)
    Hibernate: insert into ContactUk (entityType, tradeName, whoisOptOutEnabled, id) values (?, ?, ?, ?)