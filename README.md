# IBM-TEST de Javier Gavilán Mérida
El proyecto está configurado para montar un jar autocontenido.

## Compilación
Para compilar solo es necesario hacer un simple `mvn clean package`.

## Ejecución
Existen 2 configuraciones distintas:
- H2
- MYSQL
### H2
La configuración H2 es la que está activa por defecto. Solo hay que situarse en el directorio donde se encuentren los ficheros `db.properties` y `script.sql` y, al ejecutar el jar, se levantará una instancia de h2 que se inicializará (si no lo está ya) con el contenido del fichero `script.sql`, para a posteriori continuar con la ejecución normal de la aplicación.

Para ejecutar la aplicación con esta configuración hay que tener en cuenta que el comando `java -jar` debe ejecutarse desde el directorio donde se encuentren tanto el fichero db.properties como el fichero script.sql:
```shell
~$ ls
script.sql   db.properties    carpeta-con-el-jar
~$ java -jar ./carpeta-con-el-jar/IBM-TEST-1.0-SNAPSHOT-jar-with-dependencies.jar 101
```

### MYSQL
Para funcionar con bases de datos mysql solo es necesario cambiar la url del fichero `db.properties` para que se apunte a una base de datos mysql. En ese caso no se precargará nada al ejecutar la aplicación, por lo que será necesario que ya exista la tabla proveedor en la base de datos objetivo, con sus propios datos.
Para ejecutarlo es similar a con H2: Solo hay que situarse en el directorio donde se encuentren el fichero `db.properties` (el fichero `script.sql` no es necesario en este caso) y ejecutar el jar:
```shell
~$ ls
db.properties    carpeta-con-el-jar
~$ java -jar ./carpeta-con-el-jar/IBM-TEST-1.0-SNAPSHOT-jar-with-dependencies.jar 101
```

## Resultado
El resultado siempre aparecerá en un fichero llamado `suppliers.csv`, que se generará en el directorio desde el que se ejecutó el jar (al menos que no se haya especificado ningún argumento, en cuyo caso no aparecerá ningún fichero e incluso se borrará el fichero si ya se había realizado una migración antes):
```shell
~$ ls
script.sql   db.properties    carpeta-con-el-jar
~$ java -jar ./carpeta-con-el-jar/IBM-TEST-1.0-SNAPSHOT-jar-with-dependencies.jar 101
~$ ls
script.sql   db.properties    carpeta-con-el-jar    suppliers.csv
```