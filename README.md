# Maven - Dependency manager
## Introduction
Maven is a dependency manager for Java projects
it allow to generate a squeleton (boiler-plate project structure) of Maven project
The configuration file ``pom.xml`` allow to define the Project Object Model of a Maven project.
POM describe the project , its dependencies and how it's build.
contains most of the configuration.

### Initialize project
initialize project from archetype.
archetype are models of project structure.
The command below, generate a squeleton from the archetype "quickstart".
Maven will ask for more information about the project, all the information are stored in the configuration file "pom.xml":
- groupId
- artifactId
- version
- package
  More options that can add to the configuration file to describe the project
- name
- url

````shell
$ mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1
````
Maven create a boilerplate project with a configuration file "pom.xml", source folder and test folder containing sample code
````
pom.xml
src
|_ main
|   |_ java
|       |_ package
|          |_ name
|             |_ App.java
|_ test
    |_ main
        |_ java
            |_ package
                |_ name
                    |_ AppTest.java
````

### Compilation des sources
La commande ``mvn compile`` permet de compiler les sources d'un projet
````shell
mvn compile
````
### Packaging
Ci-dessous la commande pour compiler le projet

````shell
$ mvn package
````

## Fichier de configuration - pom.xml
Le fichier de configuration pom.xml contient les informations de l'application
ainsi qu'une définition des dépendances du build
### Informations du projet
Les informations du projet relative à Maven.
- groupId : identifiant de l'organisation gérant le projet. Utilise la notation des packages Java.
- artifactId : Identifiant du projet
- version : Version du projet
- packaging : Type de packaging qui sera généré (jar, war)

### Les informations générales du l'application
Le fichier de configuration d'un projet Maven contient également les informations suivantes:
- name : le nom complet
- description: une description de l'application
- url : l'URL de l'application en production
- organization: l'organisation gérant le projet
  - name: Nom de l'organisation
  - url : URL du site web de l'organisation

### Propriétés
Maven fournit des propriétés pré-définis :
- project.basedir : retourne le chemin vers le répertoire racine (root) du projet
- project.baseUri : donne le chemin vers le répertoire racine du projet sous forme d'URI
- maven.build.timestamp : date et heure du lancement du build

Maven met à disposition des propriétés particulières:
- env. : donne accès aux variables d'environnement
  Ex: ${env.PATH}: renvoie la valeur de la variable d'environnement PATH
- project. : donne accès aux valeurs définit dans le fichier de configuration pom.xml
- settings. : permet l'accès aux valeurs définis dans le fichier settings.xml
- java. : permet l'accès aux valeurs du système Java.
  Ce sont les valeurs accessible via java.lang.System.getProperties()

### Build
Les paramètres définis dans la balise <build> sont utilisées pour définir des valeurs de configurations du processus de construction (build) d'un project Maven ou de certains plugins.

 ````xml
<project>
    ...
    <!-- Build -->
    <build>
        <!-- Définit le chemin de sortie des fichiers compilés -->
        <directory>${project.basedir}/output</directory>
        <sourceEncoding>UTF-8</sourceEncoding>
    </build>
</project>
 ````
#### Exécutable Jar
On peut également rendre un Jar archive exécutable en indiquant
la classe Main dans le Manifest du Jar.
````xml
<project>
    ...
    <!-- Build -->
    <build>
        <!-- Définit le chemin de sortie des fichiers compilés -->
        <directory>${project.basedir}/output</directory>
        <sourceEncoding>UTF-8</sourceEncoding>

        <!-- Gestion des plugins (version) -->
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugin</groupId>
                    <artifactId>maven-jar-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugin</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <!-- Création du Manifest pour la définition de la classe Main -->
                        <manifest>
                            <mainClass>com.iracanyes.j2ee-intro.App</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

````
Après la reconstruction (build) du projet avec la commande ````mvn package ````,
On peut maintenant lancer l'application sans indiquer la class Main du package
````shell
# le répertoire "target" doit contenir les packages Jar
$ java -jar target/mon-appli-1.0-SNAPSHOT.jar
````

### Resources - Les ressources du projet
Les fichiers ressources sont des fichiers qui ne doivent pas à être compilés, mais simplement
copier dans le livrable (package) généré par Maven.
Par convention, les ressources se trouvent dans le répertoire ``src/main/resources``.
Maven permet de rendre accessible les propriétés définies dans le fichier de configuration
au sein des ressources en précisant dans la configuration
des ressources.
````xml
<project>
    ...
    <build>
        <resources>
            <resource>
                <directory>src/main/resources/filtered</directory>
                <!-- Maven filter resources and make all properties available to resources -->
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/resources/raw</directory>
                <!-- Disable filtering for the resource -->
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
</project>
````
Dans l'exemple suivant, on crée une resource qui contient des propriétés
utilisant des valeurs définis dans le fichier ``pom.xml`` qui sont rendus accessible par Maven via filtration

````properties
# src/main/resources/filtered/info.properties
com.iracanyes.j2ee-intro.version=${project.version}
````

### Profiles
Les profils permettent de créer des options dans le build Maven.
On peut ainsi créer 2 environnements (test et production) avec chacun son propre profil de configuration ou ses propres ressources.
On crée des profils qui définissent leur propre ressource.
````xml
<project>
    ....
    <!-- profiles -->
    <profiles>
        <!-- Profil test -->
        <profile>
            <id>test</id>
            <build>
                <resources>
                    <resource>
                        <directory>src/main/resources/test/conf</directory>
                    </resource>
                </resources>
            </build>
        </profile>
        <!-- Profil prod -->
        <profile>
            <id>prod</id>
            <build>
                <resources>
                    <resource>
                        <directory>src/main/resources/prod/conf</directory>
                    </resource>
                </resources>
            </build>
        </profile>
    </profiles>
</project>
````
On peut maintenant isoler les isoler les profils de configuration (test et prod) et leurs ressources

````shell
# Construction du livrable (package) pour l'environnement test
$ mvn package -P test

# Construction du livrable (package) pour l'environnement test
$ mvn package -P prod
````

#### Activation automatique des profils
On peut activer automatiquement un profil via les variables d'environnement

````xml
<project>
    ....
    <profiles>
        <profile>
            ...
            <!-- Profil activé automatiquement en fonction de la version du JDK -->
            <activation>
                <jdk>1.8</jdk>
            </activation>
        </profile>
        <profile>
            ...
            <!-- Profil activé automatiquement en fonction d'une propriétés définis -->
            <activation>
              <property>
                    <name>environnement</name>
                    <value>test</value>
                </property>
            </activation>
        </profile>
    </profiles>
</project>

````

On peut activer un profil au moment de la construction du livrable (package)
avec la commande
````shell
$ mvn package -Denvironnement=test
````

On peut également activer un profil selon le OS(Operating System), l'architecture, ou
encore en fonction de la présence d'un fichier.
[Voir la documentation sur les profiles de Maven](https://maven.apache.org/guides/introduction/introduction-to-profiles.html)


## Architecture d'un projet Maven

### MVC model
Le modèle MVC permet d'avoir une structure basique commune à tous pour l'organisation des fichiers d'une application.
- Model : Permet de définir une structure des données transitant dans l'application
- Vue : Permet de gérer les vues retournées pour une requête HTTP classique
- Controller : Gère les interactions avec les utilisateurs.
  ![Architecture MVC](../images/archi_MVC.png)
  Il existe différents modèles avancées pour l'organisation des fichiers.
  Par exemple, La Data Access Object permet de gérer la persistance des données de manière isoler

### Architecture multi-tiers
Permet de séparer les résponsabilités en différentes couches.
- Présentation : Cette couche gère les interactions client (controller) et les réponses retournées
- Metier : responsable de la logique métier de l'application.
- Persistance : responsable de la persistance et de la gestion des accès aux données
  ![Architecture multi-tiers](../images/archi_multi_tiers_modules_maven.png)
  Toutes ces couches respecte une même structure de donnée  (Java beans object) pour leur communication, le modèle.
  Chaque couche ne peut communiquer qu'avec la couche qui la juxte.

#### Modules Maven
On peut matérialiser le découpage de l'architecture multi-tiers
grâce à des modules Maven.
Chaque couche de l'architecture est représenté par un module.
Dans l'exemple suivant, on decoupe une application web qui effectue aussi
des traitements de données massif avec des scripts batch (jobs répétitifs).
La couche présentation est subdivisé en 2 parties (batch et webapp).
La couche persistance est modifié en consumer.
Les consumers regroupent le code qui intéragit avec les services interne ou externe.

![Architecture multi-tiers avec modules Maven](../images/archi_modules_maven.png)

### Les projets multi-modules
Un projet multi-module est organisé de la manière suivante:
````
🗁 projet-x
├── 🗁 module-a
│   ├── 🗎 pom.xml
│   └── 🗁 src
│       ├── 🗁 main
│       │   └── 🗁 java
│       │       └── ...
│       └── ...
├── 🗁 module-b
│   ├── 🗎 pom.xml
│   └── ...
├── 🗎 pom.xml
````

#### Créer un projet multi-projets
On commence par générer un projet Maven avec l'archetype "maven-archetype-quickstart"

````shell
$ mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.1 \
    -DgroupId=com.iracanyes..demo.j2ee \
    -DartifactId=ticket \
    -Dversion=1.0-SNAPSHOT
````
Il faut ensuite modifier le fichier de configuration ``pom.xml``
car le template est pour un projet à module unique dont le packaging est jar archive.
Il faut modifier le packaging en pom .
````xml
<project>
    ...
    <packaging>pom</packaging>    
</project>
````
Il faut ensuite supprimer le module ``junit``
````xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>  
</project>
````

Ensuite, on crée les sous-modules du projet
- ticket-batch (maven-archetype-quickstart)
- ticket-webapp (maven-archetype-webapp)
- ticket-business (maven-archetype-quickstart)
- ticket-consumer (maven-archetype-quickstart)
- ticket-model (maven-archetype-quickstart)
````shell
cd ticket
# Supprimer le répertoire pour module unique
rm -r ./src
# module ticket-batch
# -B disable interactive mode
mvn -B archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.1 \
    -DgroupId=com.iracanyes.demo.j2ee.ticket \
    -DartifactId=ticket-batch
    -Dpackage=com.iracanyes.demo.j2ee.ticket.batch

# module ticket-webapp
mvn -B archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-webapp \
    -DgroupId=com.iracanyes.demo.j2ee.ticket \
    -DartifactId=ticket-webapp
    -Dpackage=com.iracanyes.demo.j2ee.ticket.webapp

# module ticket-business
mvn -B archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.1 \
    -DgroupId=com.iracanyes.demo.j2ee.ticket \
    -DartifactId=ticket-business
    -Dpackage=com.iracanyes.demo.j2ee.ticket.business

# module ticket-consumer
mvn -B archetype:generate \
    -DarchetypeGroupId=org.apache.maven.archetypes \
    -DarchetypeArtifactId=maven-archetype-quickstart \
    -DarchetypeVersion=1.1 \
    -DgroupId=com.iracanyes.demo.j2ee.ticket \
    -DartifactId=ticket-consumer
    -Dpackage=com.iracanyes.demo.j2ee.ticket.consumer

# module ticket-model
  mvn -B archetype:generate \
      -DarchetypeGroupId=org.apache.maven.archetypes \
      -DarchetypeArtifactId=maven-archetype-quickstart \
      -DarchetypeVersion=1.1 \
      -DgroupId=com.iracanyes.demo.j2ee.ticket \
      -DartifactId=ticket-model
      -Dpackage=com.iracanyes.demo.j2ee.ticket.model

````

Les modules seront listés dans le fichier de configuration ```pom.xml```
````xml
<project>
    <modules>
        <module>ticket-batch</module>
        <module>ticket-webapp</module>
        <module>ticket-business</module>
        <module>ticket-consumer</module>
        <module>ticket-model</module>
    </modules>
</project>
````

#### Définir les dépendances entre les modules

Dans le POM du projet parent, on liste les dépendances et leurs versions de manière globale
Dans le POM du module, on définit les dépendances utilisées par celui-ci.

##### Dans le POM du projet parent
Dans le POM parent, on ajoute la section ``<dependencyManagement>``

````xml

<project>
    ...
    <!-- Gestion des dépendances -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.iracanyes.demo.j2ee</groupId>
                <artifactId>ticket-batch</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.iracanyes.demo.j2ee</groupId>
                <artifactId>ticket-webapp</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.iracanyes.demo.j2ee</groupId>
                <artifactId>ticket-business</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.iracanyes.demo.j2ee</groupId>
                <artifactId>ticket-consumer</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
            <dependency>
                <groupId>com.iracanyes.demo.j2ee</groupId>
                <artifactId>ticket-model</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
````

##### Dans le POM des modules
Il reste à définir les dépendances à utiliser dans les POM des modules dans la
section ``dependencies``
![Dépendances entre les modules](../images/dependances_modules_archi_multi_tiers.png)

Dans le POM du module ``ticket-batch``, on définit la dépendances suivantes:
- ticket-model
- ticket-business
````xml
<project>
    ...
    <dependencies>
        <!-- ====== Modules ====== -->
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-model</artifactId>
        </dependency>
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-batch</artifactId>
        </dependency>

        <!-- ====== External libraries ====== -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
````

Dans le POM du module ``ticket-webapp``, on définit la dépendances suivantes:
- ticket-model
- ticket-business
````xml
<project>
    ...
    <dependencies>
        <!-- ====== Modules ====== -->
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-model</artifactId>
        </dependency>
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-batch</artifactId>
        </dependency>

        <!-- ====== External libraries ====== -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
````

Dans le POM du module ``ticket-business``, on définit la dépendances suivantes:
- ticket-model
- ticket-consumer
````xml
<project>
    ...
    <dependencies>
        <!-- ====== Modules ====== -->
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-model</artifactId>
        </dependency>
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-consumer</artifactId>
        </dependency>

        <!-- ====== External libraries ====== -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
````

Dans le POM du module ``ticket-consumer``, on définit la dépendances suivantes:
- ticket-model
````xml
<project>
    ...
    <dependencies>
        <!-- ====== Modules ====== -->
        <dependency>
            <groupId>com.iracanyes.demo.j2ee</groupId>
            <artifactId>ticket-model</artifactId>
        </dependency>

        <!-- ====== External libraries ====== -->
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
      </dependency>
    </dependencies>
  <dependencyManagement>
    <dependencies>

    </dependencies>
  </dependencyManagement>
</project>
````

#### construction d'un projet multi-tiers
En lançant la commande ``mvn package`` dans le répertoire du projet parent.
````shell
# Se place dans le répertoire du projet parent
#cd /path/to/projet-multi-modules
cd /path/to/ticket
# lancer la construction
mvn package
````
Maven télécharge les dépendances externes à l'application dans des ``repositories`` d'application Java et les place dans l'archive
``war`` sous le répertoire ``WEB-INF/lib/[nom_package].jar``.

Voici l'arborence de dépendances dans le module ``ticket-webapp.war``
````
ticket-webapp.war
|-- META-INF
    |-- ...
|-- WEB-INF
    |-- lib
        |-- commons-collections4-4.4.jar
        |-- ticket-model-1.0-SNAPSHOT.jar
        |-- ticket-business-1.0-SNAPSHOT..jar
        |-- ticket-consumer-1.0-SNAPSHOT..jar

````
Une erreur de compilation peut être lancé car la version par défaut du compiler Maven
est très veille, c'est la version 1.5.
````shell
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project ticket-batch: Compilation failure: Compilation failure:
[ERROR] Source option 5 is no longer supported. Use 7 or later.
````
Dans ce cas, il faut modifier la version du plugin utilisé pour la compilation du projet,
en définissant une propriété ``maven.compiler.source`` et ``maven.compiler.target``

````xml
<project>
  ...
  <properties>
    ...
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
</project>

````

### Les dépendances
> Rappel:
> Un projet Maven (projet, module, bibliothèque..) est identifié par ses coordonnées
> sous le format ```groupId:artifactId:version```

#### Ajouter une dépendance
On va ajouter une bibliothèque fournit par le projet Apache Commons (`qui fournit des composants réutilisables en Java)
``org.apache.commons:commons-collection4:4.1`` au module ``ticket-webapp``
````xml
<project>
    ...
  <dependencies>
    <!-- ====== Modules ====== -->
    <dependency>
      <groupId>com.iracanyes.demo.j2ee</groupId>
      <artifactId>ticket-model</artifactId>
    </dependency>
    <dependency>
      <groupId>com.iracanyes.demo.j2ee</groupId>
      <artifactId>ticket-business</artifactId>
    </dependency>
    <!-- ====== dependencies ====== -->
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-collections4</artifactId>
      <version>4.1</version>
    </dependency>
  </dependencies>
</project>
````
On construit le livrable (package) du module ``ticket-webapp``
````shell
mvn package
````
L'arborescence du livrable (fichier compressé que l'on ouvrir avec zip)
est la suivante après construction du livrable
````
ticket-webapp.war
|-- META-INF
    |-- ...
|-- WEB-INF
    |-- lib
        |-- commons-collections4-4.4.jar
        |-- ticket-model-1.0-SNAPSHOT.jar
        |-- ticket-business-1.0-SNAPSHOT.jar
        |-- ticket-consumer-1.0-SNAPSHOT.jar

````
Maven télécharge les dépendances dans les ``repositories``
et les place dans le ``repository`` local par défaut ``~/.m2/repository``.
Par défaut, il utilise le [```repository``` central](https://repo.maven.apache.org/maven2/).

On peut définir un autre chemin pour son repository local dans le fichier de configuration globale
de Maven ``~/.m2/settings.xml``.
````xml
<settings>
  ...
  <localRepository>C:\path\to\local\repository</localRepository>
</settings>
````
#### Transitivité des dépendances
Maven gère la transitivité des dépendances.
Lorsqu'on ajoute un module, Maven se charge d'ajouter automatiquement les dépendances requises pour le module
Dans le module ``ticket-webapp``, le POM definit la dépendance ``ticket-business`` qui elle-même dépend du module ```ticket-consumer```
Maven se charge automatiquement du téléchargement de la dépendance ``ticket-consumer`` pour le module ``ticket-business``
et le place dans le dossier de destination des dépendances  ``WEB-INF/lib/ticket-consumer-1.0-SNAPSHOT.jar`` dans le livrable ```ticket-webapp-1.0-SNAPSHOT.jar```

##### Exclusions de dépendances
On peut exclure des dépendances en utilisant les balises ``<exclusions>`` et ``exclusion``.
Le module ``org.apache.commons:commons-text:1.1`` dépend du module ``org.apache.commons:common-lang3``.
Dans le cas où on voudrait utiliser le module sans pour autant avoir besoin de sa dépendance,
on peut exclure celle-ci.
````xml
<project>
  ...
  <dependencies>
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>common-text</artifactId>
      <version>1.1</version>
      <exclusions>
        <exclusion>
          <groupId>org.apache.commons</groupId>
          <artifactId>commons-lang3</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>
</project>
````

#### Définir la portée d'une dépendance
Lorsqu'on souhaite qu'une dépendance soit liée à un contexte particulier,
par exemple dans l'environnement de test,
on peut définir le ``scope`` (la portée) du module.
Il existe 4 portée prédéfinis (compile, test, provided, runtime) :

Le *scope* par défaut, est **compile**.
Cette portée indique que la dépendance est utilisée lors de la compilation et sera
accessible dans tous les contextes.

Le *scope* **test** indique que la dépendance est utilisée lors de la compilation des test
et leur exécution.

Le *scope* **provided** indique que la dépendance est disponible à la compilation
et durant la compilation des tests et leur exécution.
Mais elle devra être fournie par le contexte d'exécution (serveur d'application par exemple).
Les dépendances transitives ne sont pas ajoutés au projet.

Le *scope* **runtime** indique la dépendance n'est pas disponible à la compilation mais
qu'elle le sera lors de l'exécution.
Les dépendances transitives sont disponibles dans 1'environnement d'exécution.
Au moment de la compilation, Maven donne aux dépendances le *scope* **runtime**.

Le tableau ci-dessus reprend la transitivité et la disponibilité des dépendances selon le *scope*

| Scope    | Transitif | Compilation | Test | Exécution |
|----------|-----------|-------------|------|-----------|
| compile  |     ✓     |    ✓        |  ✓   |    ✓      |
| test     |           |             |  ✓   |           |    
| provided |           |    ✓        |  ✓   |           |
| runtime  |     ✓     |             |  ✓   |    ✓      |

Si la colonne n'est pas cochée (✓), cela signifie que la transitivité en tant que sous-dépendance s'arrête là. Je reformule : lorsque vous ajoutez à votre projet une dépendance vers X et que X a une dépendance vers Y, si le scope de la dépendance vers Y dans X est :

- transitif (compile ou runtime), Maven ajoute la dépendance vers Y à votre projet
- non transitif (test ou provided), Maven n'ajoute pas la dépendance vers Y à votre projet

De manière plus précise, si votre projet a une dépendance vers X et X une dépendance vers Y, voici le scope qui sera donné par Maven à Y dans votre projet en fonction :

    - du scope de X dans votre projet (colonne de gauche)
    - du scope de Y dans X (ligne d'entête)

| Scope : X↓ ❘ Y→ | compile  | test |	provided | runtime  |
|-----------------|----------|------|----------|-----------|
| compile 	       | compile  | ∅ 	| ∅ 	     | runtime   |
| test 	         | test 	  | ∅ 	| ∅ 	     | test      |
| provided 	     | provided | ∅ 	| ∅ 	     | provided  |
| runtime 	       | runtime  | ∅ 	| ∅ 	     | runtime   |

##### Utilisation des différents scope
On va ajouter des dépendances au module *ticket-webapp* utilisant différents portées:
- ``junit:junit``: pour les tests unitaires. (scope : test)
- ``javax.servlet:servlet-api``
  - Implémenter une servlet
    Cette librarie est nécessaire lors de la compilation pour créer une Servlet
    Elle ne doit pas être inclus dans l'archive *war* car elle entrerait en conflit
    avec celle fournie par le serveur d'application Java EE lors de l'exécution.
- ``javax.validation:validation-api``:
  - pour utiliser l'API de validation de Bean (JSR 303)
    Cette bibliothèque est nécessaire lors de la compilation, afin de pouvoir
    utiliser les annotations de cette API dans mes Beans.
- ``org.apache.bval:bval-jsr``
  - Implémentation de l'API de validation de Bean
    Le code n'utilise pas directement l'API, il n'est donc pas nécessaire lors de la compilation
    Lors de l'exécution de l'application, une implémentation de l'API est nécessaire
    afin de procéder à la validation des Beans object.
    Une alternative a cette bibliothèque est ``org.hibernate:hibernate-validator``

> La mise en oeuvre de l'API de validation de Bean (JSR 303) s'appuie sur le mécanisme
> de **SPI - Service Provider Interface**
>
> Ainsi, l'interface de l'API est découplée de l'implémentation.
> L'implémentation est chargée et connecté *dynamiquement* par la **JVM** à l'exécution.

```xml
<project>
  ...
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>servlet-api</artifactId>
      <version>2.5</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>javax.validation</groupId>
      <artifactId>validation-api</artifactId>
      <version>1.1.0.Final</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.apache.bval</groupId>
      <artifactId>bval-jsr</artifactId>
      <version>1.1.12</version>
      <scope>runtime</scope>
    </dependency>
  </dependencies>
</project>
```

Après, on peut lancer une re-construction du livrable
```shell
mvn clean package
```

#### Gestion des dépendances de manière globale dans le POM du projet parent
Le projet parent peut gérer de manière globale l'ensemble des dépendances utilisées
au sein des différents composants de l'application.
En utilisant la balise ``<dependencyManagement>``, le POM du projet parent peut
définir les dépendances utilisées au sein de l'application, de gérer leur version et leurs exclusions.

Dans le POM du projet parent ``ticket``, on définit les dépendances, leur version et scope
```xml
<project>
  ...
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
      </dependency>
      <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
        <scope>provided</scope>
      </dependency>
      <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>1.1.0.Final</version>
        <scope>compile</scope>
      </dependency>
      <dependency>
        <groupId>org.apache.bval</groupId>
        <artifactId>bval-jsr</artifactId>
        <version>1.1.12</version>
        <scope>runtime</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

</project>
```

Les sous-modules du projet n'auront plus qu'à déclarer leur utilisation.
Dans le POM du module ``ticket-webapp``
````xml
<project>
  ...
  <dependencies>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
      </dependency>
      <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
      </dependency>
      <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
      </dependency>
      <dependency>
        <groupId>org.apache.bval</groupId>
        <artifactId>bval-jsr</artifactId>
      </dependency>
    </dependencies>
</project>
````

## Bills of Materials
Un framework (comme Spring, Struts, etc.) est composé de plusieurs dépendances
Pour ajouter le framework à notre application, au lieu d'importer toutes dépendances,
on va utiliser un **bill of materials**.
C'est un fichier POM qui contient la définitionn des dépendances du frameworks,
il est mis à disposition par les mainteneurs du framework.

Pour l'utiliser, il suffit de l'importer dans la section ``dependencyManagement``
````xml
<project>
  ...
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-framework-bom</artifactId>
        <version>4.3.11.RELEASE</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
</project>
````
## Versions Snapshot et Release
Un concept important de Maven est la **reproductibilité de la construction**.
Un package publié doit produire les mêmes resultats lors de plusieurs téléchargement.
Le livrable doit également contenir les mêmes dépendances afin de garantir la reproductibilité.
selon le principe immuabilité de la version d'une dépendance de l'application.
Une fois un package publiée, les versions de ces dépendances ne peuvent être modifiées.
On parle d'une version **release**.
On utilisera le suffixe *RELEASE* dans la version du package ``[version_number].RELEASE``

Un package en cours de développement sera appelé **snapshot**.
On utilisera le suffixe *SNAPSHOT* dans la version du package ``[version_number].SNAPSHOT``


## build lifecycle
Pour automatiser la construction d'un projet, Maven s'appuie sur des cycles de vie de
construction appelés **build lifecycle** dans le jargon de Maven.
Il existe 3 types de *build lifecycle* dans Maven:
- ``default`` : qui permet de construire et déployer le projet
- ``clean`` : qui permet de nettoyer le projet en supprimant les éléments issus de la construction de celui-ci.
- ``site`` : qui permet de créer un site web pour le projet.

Ces *build lifecycles* sont découpés en **phases** qui sont exécutées séquentiellement
les unes à la suite des autres pour :
- ``validate`` : vérifie la configuration du projet (POM, éléments manquants, ...).
- ``compile`` : compile les sources du projet
- ``test`` : teste le code compilé avec les classes de tests unitaires contenues dans le projet
- ``package`` : package les éléments issus de la compilation dans un format distribuable (jar, war, etc.)
- ``install`` : installe le package dans votre repository local
- ``deploy`` : envoie le package dans le repository distant défini dans le POM

### Lancer un build lifecycle
Lorsqu'on lance une construction avec Maven en ligne de commande,
on précise simplement la phase d'un des *build lifecycles* et Maven gère l'exécution
dans l'ordre de toutes les phases qui composent le *build lifecycles* jusqu'à la phase indiquée.
Ex: La commande ``mvn package`` va réaliser les phases *validate*, *compile*, *test*, et enfin *package*.

Pour les projets multi-modules, Maven lance le build lifecycle dans chaque module, les uns à la suite
des autres, en respectant l'ordre des dépendances inter-modules.

On peut chainer l'exécution de plusieurs *build lifecycles* dans une seule commande Maven.
Ex: ``mvn clean package``
1. nettoyer le projet
2. exécuter le build lifecycle jusqu'à la phase *package*

## Les goals
La granularité de l'exécution de Maven peut être plus fine.
Les *phases* sont découpées en tâches.
Chaque tâche est assurée par un **plugin Maven**.
Une tâche est appelée **goal** dans le jargon de Maven.

Ex: La phase *test* est réalisée par défaut par le *goal* ``surefire:test``,
c'est-à-dire le *goal test* du plugin *surefire*.

Maven fournit de base un certain nombre de plugins.
Ex: maven-jar-plugin permet de définir la classe main de l'application afin de rendre
l'archive jar exécutable.
````xml
<project>
    ...
    <!-- =============================================================== -->
    <!-- Build -->
    <!-- =============================================================== -->
    <build>
        <!-- Gestion des plugins (version) -->
        <pluginManagement>
            <plugins>
                <!-- Plugin responsable de la génération du fichier JAR -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-jar-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <!-- Création du Manifest pour la définition de la classe Main -->
                        <manifest>
                            <mainClass>org.exemple.demo.App</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ...
</project>
````

## Build avec les plugins
> Rappel:
> Les phases d'un *build lifecycle* sont découpées en tâches réalisées par les **goal**
> de différents *plugins*

Le *build lifecycle* et le *packaging* utilisées détermine les différents goals qui seront câblées par défaut.
Ex: Pour le packaging *Jar*, voici le câblage ** *binding* **
````xml
<phases>
  <process-resources>
    org.apache.maven.plugins:maven-resources-plugin:2.6:resources
  </process-resources>
  <compile>
    org.apache.maven.plugins:maven-compiler-plugin:3.1:compile
  </compile>
  <process-test-resources>
    org.apache.maven.plugins:maven-resources-plugin:2.6:testResources
  </process-test-resources>
  <test-compile>
    org.apache.maven.plugins:maven-compiler-plugin:3.1:testCompile
  </test-compile>
  <test>
    org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test
  </test>
  <package>
    org.apache.maven.plugins:maven-jar-plugin:2.4:jar
  </package>
  <install>
    org.apache.maven.plugins:maven-install-plugin:2.4:install
  </install>
  <deploy>
    org.apache.maven.plugins:maven-deploy-plugin:2.7:deploy
  </deploy>
</phases>
````
Il existe bcp de plugin, dans la section suivante, un exemple de configuration d'un plugin est présenté

### Configuration d'un plugin

Ici, nous voulons configurer la phase de compilation pour afficher les utilisations de méthodes
dépréciées (``@Deprecated``) ainsi que les avertissements de compilation *warnings*.

On remarque dans la documentation de Maven que la phase ``compile`` est assurée par le *goal*
``compile`` du plugin ``org.apache.maven.plugins:maven-compiler-plugin:3.1:compile``.
On peut retrouver ces informations aux URL suivant:
- [Maven plugins index](https://maven.apache.org/plugins/index.html)
- [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/compile-mojo.html)

On peut ainsi voir la description du goal et la liste des options de configuration qui lui sont
applicables.
Les 2 options recherchées:
- ``showDeprecation`` : \n
  Permet de configurer l'affichage de la localisation des sources utilisant une API dépréciées.
  La valeur attendue pour cette option est un type ``boolean``. Par défaut, la valeur est ``false``
  L'option peut être défini sous forme de *propriété* en utilisant nom de balise ``maven.cmopiler.showDeprecation``
- ``showWarnings`` :
  Permet de configurer l'affichage des avertissements *warnings* de compilation
  Default value: ``false``
  User property: ``maven.compiler.showWarnings``

Pour définir une option de configuration, il existe 2 options:
1. En ajoutant une section ``<configuration>`` dans la définition du plugin
````xml
 <project>
  ...
  <!-- === Build === -->
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <showDeprecation>true</showDeprecation>
          <showWarnings>true</showWarnings>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
````
2. En ajoutant une propriété au sein de la balise ``properties``
````xml
<project>
    ...
    <!-- Propriétés -->
    <properties>
        <maven.compiler.showDeprecation>true</maven.compiler.showDeprecation>
        <maven.compiler.showWarnings>true</maven.compiler.showWarnings>
    </properties>
    ...
</project>
````  

### Définir la classe Main - archive exécutable
On va définir la classe Main dans le *manifest* du JAR généré par Maven.

Dans la documentation Maven, la phase ``package`` est assurée par le *goal* ``jar``
du plugin ``org.apache.maven.plugins:maven-jar-plugin``.
Visitez la documentation du *goal* [**jar**](https://maven.apache.org/plugins/maven-jar-plugin/jar-mojo.html)
````xml
<phases>
  ...
  <package>
    org.apache.maven.plugins:maven-jar-plugin:2.4:jar
  </package>
</phases>
````
L'option ``archive`` permet de définir les éléments de configuration du *manifest*
L'option ``archive.manifest.mainClass`` permet de définir la classe Main de l'archive
````xml
<projec>
  ...
  <!-- Build -->
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.0.2</version>
        <configuration>
          <archive>
            <manifest>
              <mainClass>org.example.demo.App</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
    </plugins>
  </build>
</projec>
````

## Gestion des plugins de manière globale
A partir de la section ``dependencyManagement`` du POM du projet parent,
on peut gérer de manière globale les plugins utilisés par le projet.
On peut ainsi fixer les versions des plugins mais aussi leur configuration.
````xml
<project>
  ...
  <!-- Build -->
  <build>
    <!-- Gestion des plugins -->
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.1</version>
          <configuration>
            <showDeprecation>true</showDeprecation>
            <showWarnings>true</showWarnings>
          </configuration>
        </plugin>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.1</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
````
Dans le module, il ne reste que les options de configurations spécifique au module.
Par exemple: la configuration de la classe ``main`` est différente selon le module.

````xml
<project>
  <!-- Build -->
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <configuration>
          <archive>
            <manifest>
              <mainClass>org.example.demo.App</mainClass>
            </manifest>
          </archive>          
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
````

### Ajouter de nouveaux plugins dans les phases
Dans l'exemple suivant, on définit 3 profils permettant de spécifier l'environnement cible de
ma construction (développement, test, production).
````xml
<project>
  ...
  <!-- Profile -->
  <profiles>
    <!-- Profil env dev -->
    <profile>
      <id>target-dev</id>
      ...
    </profile>
    <!-- Profil env test -->
    <profile>
      <id>target-test</id>
      ...
    </profile>
    <!-- Profil env prod -->
    <profile>
      <id>target-prod</id>
      ...
    </profile>
  </profiles>
</project>
````
Lors du *build* avec Maven, on veut s'assurer qu'au moins un des profils
est activé.
On va utiliser pour cela le *goal* ``enforce`` du plugin
``maven-enforcer-plugin``.
Voir la documentation du plugin [**maven-enforcer-plugin**](https://maven.apache.org/enforcer/maven-enforcer-plugin/)


On ajoute le plugin et on le connecte le *goal* ``enforce`` à la phase ``validate``
du *build lifecycle default*. On utilisera la section ``<execution>``
````xml
<project>
    ...
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-enforcer-plugin</artifactId>
                <version>1.4.1</version>
                <executions>
                    <execution>
                        <!-- je choisis un nom unique pour définir cette exécution -->
                        <id>enforce-profile-target</id>
                        <!-- je branche l'exécution à la phase "validate" -->
                        <phase>validate</phase>
                        <!-- cette exécution lancera le goal "enforce" -->
                        <goals>
                            <goal>enforce</goal>
                        </goals>
                        <!-- La configuration du plugin propre à cette exécution -->
                        <configuration>
                            <rules>
                                <requireActiveProfile>
                                    <profiles>target-dev,target-test,target-prod</profiles>
                                    <all>false</all>
                                </requireActiveProfile>
                            </rules>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    ...
</project>
````
En lançant la commande ``mvn package -Ptarget-dev``, on constate que comme un profil
est actif le build va jusqu'au bout.
Cependant la commande ``mvn package`` génére une erreur car aucun profil n'est indiqué.

## Générer le livrable

### Générer un WAR de l'application web
On va utiliser le plugin Maven ``maven-war-plugin``.
Lors du processus de construction du projet, on va ajouter:
- Ajouter automatiquement certains informations du projet dans les JSP
- Garantir qu'aucune version SNAPSHOT ne soit envoyée en production.

#### Filtrer les ressources de la webapp
Le plugin ``maven-war-plugin`` permet de compiler le module ```ticket-webapp```
au format *war* (web archive).
Il permet aussi d'ajouter automatiquement certains informations du projet dans les JSP
par **filtrage**.
Avant d'intégrer les ressources JSP dans le répertoire ``WEB-INF`` du fichier compressé.

````xml
<project>
  ...
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugin</groupId>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
````

> Rappel: Dans Maven
> Le répertoire standard contenant les *Java web resources* est ```src/main/webapp```
> Le répertoire standard contenant le *Java code* est ``src/main/java``
> Le répertoire standard contenant les *non-java resources* est ``src/main/resources``

Dans notre cas, on configure le plugin ``maven-war-plugin`` dans le module ``ticket-webapp``
pour activer le *filtrage* des *web resources* suivantes:
- ```jsp/_include/header.jsp``` :
  - fragment de JSP contenant le header de toutes les pages HTML de l'application web.
  - On injecte le nom *public* de l'application
- ``jsp/_include/footer.jsp`` :
  - fragment de JSP contenant le footer de toutes les pages HTML de l'appli web.
  - On y injecte aussi le nom de l'organisation et la version de l'application.
- ``jsp/about.jsp`` :
  - Page *about*
  - On injecte quelques informations sur le projet (version, date du build,...).

````xml
<project>
  ...
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-war-plugin</artifactId>
          <configuration>
            <webResources>
              <resource>
                <filtering>true</filtering>
                <directory>src/main/webapp</directory>
                <includes>
                  <include>jsp/_include/header.jsp</include>
                  <include>jsp/_include/footer.jsp</include>
                  <include>jsp/about.jsp</include>
                </includes>
              </resource>
            </webResources>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>      
  </build>
</project>
````
On ajoute ensuite le POM du module ``ticket-webapp`` les propriétés qui seront
injecter dans les fragments et pages JSP
````xml
<project>
  ...
  <properties>
    <!-- Nom *public* de l'application -->
    <application.name>Ticket Tac</application.name>
    <!-- Le format de date utilisée pour l'affichage de la date du build -->
    <maven.build.timestamp.format>dd/MM/yyyy</maven.build.timestamp.format>
    <!--
      Propriété servant à contourner le bug du non remplacement de la propriété
      maven.build.timestamp lors du filtrage des ressources
     -->
    <buildTimestam>${maven.build.timestamp}</buildTimestam>
  </properties>
</project>
````

Ensuite on crée les fragments et les pages JSP.
1. Le fragment ``webapp/jsp/_include/header.jsp``
````
<!-- webapp/jsp/_include/header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${application.name}</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="..">Accueil</a></li>
                <li><a href="../jsp/about.jsp">A propos</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
````

2. Le fragment ``jsp/_include/footer.jsp``
````
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<footer class="footer">
<div class="container">
  <p>
    ${application.name} - version ${project.version}
    &copy; <a href="${organization.url}">${organization.name}</a>
  </p>
</div>
</footer>

    <!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

````
3. La page ``jsp/about.jsp``
````
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${application.name} - A propos</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
      crossorigin="anonymous"
    />
    <link href="../../style/custom.css" rel="stylesheet" />
  </head>
  <body>
    <!-- includes file: header.jsp -->
    <%@ include file="_include/header.jsp" %>

    <div class="container">
      <ul>
        <li>Application: ${application.name}</li>
        <li>Version: ${project.version}</li>
        <li>Date du build: ${maven.build.timestamp}</li>

      </ul>
    </div>

    <!-- includes file: footer.jsp -->
    <%@ include file="_include/footer.jsp" %>
  </body>
</html>
````

#### Garantir qu'aucune version SNAPSHOT soit poussé en production
``maven-enforcer-plugin`` permet de garantir qu'aucune version SNAPSHOT ne soit envoyée
en production.
Dans notre cas, comme cette vérification ne converne que les constructions ayant
pour cible l'environnement de production,
on ajoute l'exécution de ce plugin au profil dédié à la production.
On va ajouter le plugin au build du profil ``target-prod``
````xml
<project>
  <!-- Profile -->
  <profiles>
    ....
    <profile>
      <id>target-prod</id>
      <build>
        <plugins>
          <plugin>
            <groupId>org.apache.maven.plugin</groupId>
            <artifactId>maven-enforcer-plugin</artifactId>
            <executions>
              <execution>
                <id>enforce-target-prod-no-snapshot</id>
                <phase>validate</phase>
                <goals>
                  <goal>enforce</goal>
                </goals>
                <configuration>
                  <rules>
                    <!-- Le projet et son parent ne doivent pas être des version SNAPSHOT -->
                    <requireReleaseVersion />
                    <!-- Aucune dépendance ne doit être en SNAPSHOT -->
                    <requireReleaseDeps />
                  </rules>
                </configuration>
              </execution>
            </executions>
          </plugin>
        </plugins>
      </build>
    </profile>
  </profiles>
</project>
````

## Générer une archive pour nos fichiers batch
L'objectif est de générer une archive (tar.gz et .zip) pour nos fichiers
batch de l'application de gestion de tickets

L'archive contiendra:
- Jar du module ticket-batch
- Jar de tous ses dépendances
- Fichiers de configuration (modifiable par l'administrateur)
- script shell de lancement de chaque batch

L'arborescence des répertoires de l'archive:
- ``bin``  : répertoire contenant les scripts shell de lancement des batchs
- ``conf`` : répertoire contenant les fichiers de configuration
- ``lib`` : répertoire contenant le Jar du module ``ticket-batch`` et le Jar des dépendances

````
🗄 archive
├── 🗁 bin
│   ├── 🗎 batch-X.sh
│   ├── 🗎 batch-Y.sh
│   └── ...
├── 🗁 conf
│   ├── 🗎 config.properties
│   ├── 🗎 db-X.properties
│   └── ...
└── 🗁 lib
    ├── 🗎 ticket-batch-*.jar
    ├── 🗎 ticket-business-*.jar
    ├── 🗎 ticket-model-*.jar
    ├── 🗎 commons-lang3-*.jar
    └── ...
````

L'arborescence de répertoire du répertoire ``src``:
````
🗁 src/data
├── 🗁 scripts
│   ├── 🗎 batch-X.sh
│   ├── 🗎 batch-Y.sh
│   └── ...
└── 🗁 conf
    ├── 🗎 config.properties  (conf. de l'application)
    ├── 🗎 db-X.properties    (conf. de la base de données)
    └── ...
````

#### Configuration du JAR des batchs
Comme, les JAR des dépendances seront placées dans le répertoire ``lib``
à côté du JAR du module ``ticket-batch``,
on doit ajouter le **classpath** au *manifest* de ce JAR.
Ainsi toutes les classes sont retrouvées par le JVM lors de l'exécution de l'application
`````xml
<project>
  ....
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <configuration>
        <archive>
          <manifest>
            <addClasspath>true</addClasspath>
            <mainClass>com.iracanyes.demo.j2ee.ticket.ticket-batch.App</mainClass>
            <classpathPrefix>lib/</classpathPrefix>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</project>
`````

#### Assembler le tout dans un archive
On utilisera le package ``maven-assembly-plugin``

##### Définition de l'assemblage
La configuration des assemblages se fait grâce à des fichiers XML
qui seront stocké dans le répertoire ``src/assempbly``.
On créer un fichier ``src/assembly/archive-deploy.xml``
````xml
<assembly
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://maven.apache.org/ASSEMBLY/2.0.0"
  xsi:schemaLocation="http://maven.apache.org/ASSEMBLY/2.0.0 http://maven.apache.org/xsd/ASSEMBLY/2.0.0.xsd"
>
  <!-- Identification de l'assemblage -->
  <id>archive-deploy</id>

  <!-- Les formats d'archive à générer -->
  <formats>
    <format>tar.gz</format>
    <format>zip</format>
  </formats>

  <!-- lib : dépendances + JAR ticket-batch  -->
  <dependencySets>
    <!--
      Définir l'archivage d'un ensembles de dépendances
      Ajout des dépendances "runtime" (scope "runtime" et "compile")
    -->
    <dependencySet>
      <outputDirectory>lib</outputDirectory>
      <scope>runtime</scope>
    </dependencySet>
  </dependencySets>

  <fileSets>
    <!-- Définition d'un ensemble de fichiers
      scripts shell de lancement
    -->
    <fileSet>
      <directory>src/data/scripts</directory>
      <outputDirectory>bin</outputDirectory>
      <!-- Droit UNIX d'exécution sur les fichiers (-rwx-rx-rx) -->
      <fileMode>0755</fileMode>
    </fileSet>

    <!-- Fichiers de configuration -->
    <fileSet>
      <directory>src/data/conf</directory>
      <outputDirectory>conf</outputDirectory>
    </fileSet>
  </fileSets>

</assembly>
````

##### Connexion du plugin et de sa configuration
````xml
<project>
  ....
  <build>
    <plugins>
      ...
      <!-- Creation des archives de déploiement -->
      <plugin>
        <groupId>org.apache.maven.plugin</groupId>
        <artifactId>maven-assembly-plugin</artifactId>
        <configuration>
          <descriptors>
            <descriptor>src/assembly/archive-deploy.xml</descriptor>
          </descriptors>
        </configuration>
        <executions>
          <execution>
            <id>assembly-archive-deploy</id>
            <phase>package</phase>
            <goals>
              <goal>single</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
````

Maintenant on peut construire le livrable de l'application
````shell
mvn package site site:stage
````
