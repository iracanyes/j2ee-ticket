# Générer un site pour votre projet

## Configuration de base d'un site

### Arborescence répertoire 
Par convention, les sources servant à la génération du site du projet sont dans 
le répertoire ``src/site``:
````
🗁 src
└── 🗁 site
    ├── 🗁 apt
    │   ├── 🗎 xxx.apt
    │   └── ...
    ├── 🗁 fml
    │   ├── 🗎 faq.fml
    │   └── ...
    ├── 🗁 markdown
    │   ├── 🗎 yyy.md
    │   └── ...
    ├── 🗁 ...
    ├── 🗁 resources
    │   ├── 🗁 css
    │   │   └── 🗎 style.css
    │   ├── 🗁 img
    │   │   ├── 🗎 zzz.png
    │   │   └── ...
    │   └── ...
    └── 🗎 site.xml
````
L'arborescence contient:
* Le fichier ``src/site/site.xml`` est le *site descriptor* qui permet de structure du site
* Les répertoires ``apt``, ``markdown``, ``fml``, ... contiennent les fichiers sources 
  des pages du site qui seront converties en HTML par Maven.
  * ``apt`` : répertoire pour les fichiers au format *Almost Plain Text*
  * ``fml`` : Format *FAQ Markup Language*
  * ``markdown`` : Format *Markdown*
* Le répertoire ``resources`` contient les resources autres que du code java. Ex: CSS, Javascript, SASS, etc.
  * ``css/style.css``
  * ...

### Site descriptor
Le fichier ``site.xml`` contient le *site descriptor*.
Il permet de définir la structure du site.
Ce fichier est filtré par Maven, on peut utiliser les propriétés du POM dedans
Voir la documentation pour plus d'exemples de configuration
- [maven-site-plugin - site descriptor](https://maven.apache.org/plugins/maven-site-plugin/examples/sitedescriptor.html)
- [doxia-decoration-model - decoration](https://maven.apache.org/doxia/doxia-sitetools/doxia-decoration-model/decoration.html)
````xml
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/DECORATION/1.7.0"
         xsi:schemaLocation="http://maven.apache.org/DECORATION/1.7.0 http://maven.apache.org/xsd/decoration-1.7.0.xsd">

  <!-- Utilisation du template de site fluido -->
  <skin>
    <groupId>org.apache.maven.skins</groupId>
    <artifactId>maven-fluido-skin</artifactId>
    <version>1.6</version>
  </skin>

  <!-- Affichage de la date et de la version à droite dans le bandeau du haut -->
  <publishDate position="right"/>
  <version position="right"/>

  <body>
    <!-- Ajout d'un fil d'ariane -->
    <breadcrumbs>
      <item name="Accueil" href="index.html"/>
    </breadcrumbs>

    <!-- ===== Menus ===== -->
    <!-- Ajout d'un menu vers le projet parent -->
    <menu ref="parent" inherit="top"/>
    <!-- Ajout d'un menu vers les différents modules du projet -->
    <menu ref="modules" inherit="top"/>
  </body>
</project> 
````

### Configuration du site dans le POM
Il faut ensuite ajouter dans le POM parent les informations de déploiement du site.
Pour un déploiement en local, on peut utiliser l'URL ```scp://localhost/tmp/```
````xml
<project>
  <!-- Distribution Management -->
  <distributionManagement>
    <site>
      <id>site-projet</id>
      <!-- balise URL requise-->
      <url>scp://example.org/www/</url>
    </site>
  </distributionManagement>
</project>
````

### Générer le site

#### Ajout du plugin *maven-site-plugin*
Le plugin *maven-site-plugin* permet de générer le site.
Le plugin doit être ajouté dans la section ``<dependencyManagement>``
`````xml
<project>
  ....
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.6</version>
          <configuration>
            <!-- Je veux le site en français -->
            <locales>fr</locales>
          </configuration>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
`````

#### Génération du site
Pour générer le site, nous allons utiliser le *build lifecycle* **site**.

Pour un projet multi-module, la génération du site est réalisé en 2 étapes:
   1. génére un site pour le projet parent et pour chaque module
   (correspond au *goal* **site** du plugin *maven-site-plugin*)
   2. Aggregation des différents sites pour la génération du site.
   Maven réalise automatiquement cette tâche.

La dernière étape est réalisée automatiquement dans la phase *site-deploy*, via le goal *deploy*.

Lorsqu'on souhaite obtenir le site agrégé sans le déployer en production,
on utilisera le *goal* **stage**.
Par défaut, ce *goal* génére le site agrégé dans le répertoire ``target/staging``;

> Rappel: 
> Pour exécuter un *goal* d'un plugin precis, il suffit d'utiliser la syntaxe suivante
> ``mvn <plugin>:<goal>``. Ex: ``mv site:stage``
> Pour la partie <plugin>, le prefix du nom du plugin. 
> Ex: maven-<prefix>-plugin => Pour ``maven-site-plugin``, le prefix sera *site*.

````shell
mvn package site site:stage
````
La commande précédente enchaîne 3 executions:
1. La phase ``package``
2. La phase ``site``
3. La phase ``site:stage``

> Remarque:
> La génération du site doit être précédé de la phase *package*, sinon,
> Maven ne trouve pas les modules du projet car il n'ont pas été téléchargé dans 
> le *repository local*

### Ajout des pages personnalisés 
On va ajouter des pages personnalisés:
- Une page de documentation au format *markdown*
- Une foire aux questions au format *fml*

#### Ajout d'une page au format markdown
Ici, j'utilise une copie du document sur Maven.

> Rappel:
> Les fichiers ressources pour les documents seront placés 
> dans le répertoire ``src/site/resources/``.
> Ex: les images seront dans ```src/site/resources/img```

Ensuite, on ajoute une entrée au menu dans le *site descriptor* pour accéder à cette page

````xml
<project>
  ...
  <body>
    ...
    <!-- Menus -->
    <menu name="Documentation">
      <item name="Maven" href="maven.html" />
    </menu>
  </body>
</project>
````

## Ajout d'un fichier de type *fml*
Fichier au format *FAQ markup language*

````fml
<?xml version="1.0" encoding="UTF-8"?>
<faqs xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://maven.apache.org/FML/1.0.1"
      xsi:schemaLocation="http://maven.apache.org/FML/1.0.1 http://maven.apache.org/xsd/fml-1.0.1.xsd"

      title="Foire Aux Questions"
      toplink="false">

    <part id="general">
        <title>Général</title>
        <faq id="why-x">
            <question>
                Pourquoi ... ?
            </question>
            <answer>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam aperiam architecto assumenda
                    consequuntur delectus deleniti dolores dolorum ex excepturi explicabo ipsa, labore magnam maxime,
                    modi
                    nemo sint, sit veniam voluptate.
                </p>
                <p>...</p>
            </answer>
        </faq>

        <faq id="how-x">
            <question>
                Comment ... ?
            </question>
            <answer>
                <p>...</p>
            </answer>
        </faq>
    </part>

    <part id="install">
        <title>Installation</title>
        <faq id="how-install">
            <question>
                Comment installer ... ?
            </question>
            <answer>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquam aperiam architecto assumenda
                    consequuntur delectus deleniti dolores dolorum ex excepturi explicabo ipsa, labore magnam maxime,
                    modi
                    nemo sint, sit veniam voluptate.
                </p>
                <source>apt-get install xxx</source>
            </answer>
        </faq>
    </part>
</faqs>
````
Ensuite, on ajoute une entrée au menu dans le *site descriptor* pour pouvoir accéder
à cette FAQ.
````xml
<project>
    ...
    <body>
        ...
        <!-- ===== Menus ===== -->
        <!-- Ajout d'un menu vers la documentation -->
        <menu name="Documentation">
            <!-- Entrée de menu vers la page Architecture -->
            <item name="Architecture" href="architecture.html"/>
            <!-- Entrée de menu vers la page FAQ -->
            <item name="FAQ" href="faq.html"/>
        </menu>
    </body>
</project>
````

#### Générer des rapports
Maven permet d'accéder à des rapports sur l'application :
- Projet
  - résumé du projet (nom, version, description, organisation...)
  - liste des modules du projet
  - membres du projet (contributeur, dévs...)
  - licence du projet (section ``<license>``)
  - gestion de la distribution du projet (section ``<distributionManagement>``)
  - listes de diffusion (section ``<mailingLists>`` )
  - dépôt des sources du projet (section ``<scm>`` )
  - intégration continue (section ``<ciManagement>`` )
  - gestion des anomalies (section ``<issueManagement>`` )
- Plugins
  - gestion des plugins (section ``<pluginManagement>`` )
  - liste des plugins utilisés dans le projet/module
- Dépendances
  - gestion des dépendances (section ``<dependencyManagement>`` )
  - liste des dépendances utilisées dans le projet/module
  - convergence des dépendances entre les différents modules du projet

Pour ajouter les rapports au site, on ajouter une menu dans le fichier ```site.xml```
````xml
<project>
    ...
    <body>
        ...
        <!-- ===== Menus ===== -->
        <!-- Ajout d'un menu vers les différents rapport -->
        <menu ref="reports" inherit="top"/>
    </body>
</project>
````

On doit également ajouter le plugin de génération des rapports dans le POM du parent
````xml
<project>
    ...
    <!-- =============================================================== -->
    <!-- Gestion des rapports -->
    <!-- =============================================================== -->
    <reporting>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-project-info-reports-plugin</artifactId>
                <version>3.4.1</version>
            </plugin>
        </plugins>
    </reporting>
</project>
````
Il existe d'autres plugins permettant de créer des rapports. 
Chaque rapport est fourni par un goal d'un de ces plugins.
Par défaut, lorsqu'on ajoute un plugin, tous les goals du plugin sont exécutés.
Ainsi, tous les rapports sont générés.
On peut spécifier les ensembles de rapports voulus

````xml
<project>
    ...
    <!-- =============================================================== -->
    <!-- Gestion des rapports -->
    <!-- =============================================================== -->
    <reporting>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-project-info-reports-plugin</artifactId>
                <version>3.4.1</version>
                <reportSets>
                  <reportSet>
                    <reports>
                      <report>index</report>
                      <report>summary</report>
                      <report>plugins</report>
                    </reports>
                  </reportSet>
                </reportSets>
            </plugin>
        </plugins>
    </reporting>
</project>
````

#### Ajouter d'autres rapports
Pour générer des rapports supplémentaires, il suffit de rajouter les plugins correspondants
````xml
<project>
    ...
    <!-- =============================================================== -->
    <!-- Gestion des rapports -->
    <!-- =============================================================== -->
    <reporting>
        <plugins>
            ...
            <!-- ===== Rapport sur les tests ===== -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-report-plugin</artifactId>
                <version>2.20</version>
                <configuration>
                    <linkXRef>false</linkXRef>
                </configuration>
                <reportSets>
                    <reportSet>
                        <reports>
                            <report>report-only</report>
                        </reports>
                    </reportSet>
                </reportSets>
            </plugin>
        </plugins>
    </reporting>
</project>
````

#### Agréger des rapports
Certains plugins de rapport permettent d'agréger les rapports des modules au niveau
du projet parent
````xml
<project>
    ...
    <!-- =============================================================== -->
    <!-- Gestion des rapports -->
    <!-- =============================================================== -->
    <reporting>
        <plugins>
            ...
            <!-- ===== Rapport sur les tests ===== -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-report-plugin</artifactId>
                <version>2.20</version>
                <configuration>
                    <linkXRef>false</linkXRef>
                </configuration>
                    <!-- reportSet d'agrégation des rapports des sous-projets (modules) -->
                    <reportSet>
                        <id>aggregate</id>
                        <reports>
                            <report>report</report>
                        </reports>
                        <!-- ne pas exécuter ce sous-rapport dans les sous-projets -->
                        <inherited>false</inherited>
                        <configuration>
                            <aggregate>true</aggregate>
                        </configuration>
                    </reportSet>

                    <!-- reportSet non agrégé, exécuté dans tous les sous-projets (modules) -->
                    <reportSet>
                        <id>modules</id>
                        <!-- exécuter ce sous-rapport dans les sous-projets -->
                        <inherited>true</inherited>
                        <reports>
                            <report>report</report>
                        </reports>
                        <configuration>
                            <aggregate>false</aggregate>
                        </configuration>
                    </reportSet>
            </plugin>
        </plugins>
    </reporting>
</project>
````
> Remarque :
> Notez l'utilisation de la base <inherited> :
>   - false pour le reportSet d'agrégation
>   - true pour le reportSet de base
>
> Notez également que l'ordre de déclaration des reportSet est important. 
> Pour le plugin surefire-report, le reportSet d'agrégation doit être déclaré avant l'autre, 
> sinon l'agrégation ne fonctionnera pas.


#### Javadoc 
Pour la génération de la Javadoc, la configuration serait celle-ci:
````xml
<project>
  ...
  <!-- Gestion des rapports -->
  <reporting>
    <plugins>
      ...
      <!-- ======= Génération de la Javadoc ======= -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-javadoc-plugin</artifactId>
        <version>3.4.1</version>
        <configuration>
          <quiet>true</quiet>
          <locale>fr</locale>
        </configuration>
        <reportSets>
          <!-- Ensemble de rapport exécutés dans tous les modules -->
          <reportSet>
            <reports>
              <report>javadoc</report>
            </reports>
          </reportSet>
          <!-- Ensemble de rapport d'agrégation des rapports des sous-projets -->
          <reportSet>
            <id>aggregate</id>
            <inherited>false</inherited>
            <reports>
              <report>aggregate</report>
            </reports>
          </reportSet>
        </reportSets>
      </plugin>
    </plugins>
  </reporting>
</project>
````

#### CheckStyle - Rapport sur la qualité du code

````xml
<project>
  ....
  <reporting>
    <plugins>
      ....
      <!-- ==== Rapport d'analyse du code par Checkstyle === -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-checkstyle-plugin</artifactId>
        <version>3.2.0</version>
        <configuration>
          <configLocation>src/build/checkstyle.xml</configLocation>
          <linkXRef>false</linkXRef>
        </configuration>
        <reportSets>
          <!-- reportSet exécuté dans toutes les modules -->
          <reportSet>
            <reports>
              <report>checkstyle</report>
            </reports>
          </reportSet>
          <!-- reportSet d'agrégation des rapports des sous-modules -->
          <reportSet>
            <id>checkstyle-aggregate</id>
            <inherited>false</inherited>
            <configuration>
              <skipExec>true</skipExec>
            </configuration>
            <reports>
              <report>checkstyle-aggregate</report>
            </reports>
          </reportSet>
        </reportSets>
      </plugin>
    </plugins>
  </reporting>
</project>
````