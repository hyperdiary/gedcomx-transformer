# GEDCOM X Transformer

A command line wrapper around https://github.com/FamilySearch/gedcomx-java

To Build:
```commandline
sbt assembly
```
To run:
```commandline
java -jar ./target/gedcomx-transformer.jar -i ./src/test/resources/WaltonFamilyTree.gedx -o WaltonFamilyTreeXml.gedx
```
