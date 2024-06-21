# Syntax backend


## Instalación

Desde la terminal clonamos el repositorio con el siguiente comando

```bash
git clone https://github.com/raulgm00/clinica-odontologica.git
```
Accedemos a la carpeta 
```bash
cd clinica-odontologica

```
Una vez dentro de la raíz del proyecto, si queremos desarrollar la voista lo haremos en Clinica-View
```bash
cd Clinica-View
```
Una vez dentro de la raíz del proyecto, si queremos desarrollar la voista lo haremos en Clinica-MVC
```bash
cd Clinica-MVC
```

El servidor para la View debería estar corriendo en 
```bash
http://localhost:5500/ la url base es http://localhost:5500/index.html
```


El servidor para la Bakcend debería estar corriendo en 
```bash
http://localhost:8080/ + los paths definidos para la API en SpringBoot
```

Para validar la capa de persistencia en la base de datos de H2, se debera colcoar la siguiente configuracion en :

```bash
#Configuracion h2 Persistente
spring.h2.console.enabled=true
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:~/clinicatest11
spring.datasource.username=sa
spring.datasource.password=sa
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Para mirar los datos en la base de datos de H2, deberas entrar a la consola

```bash
http://localhost:8080/h2-console/login.jsp
```

![imagen](https://github.com/raulgm00/clinica-odontologica/assets/58957933/16a4458b-d80c-4afd-a2b9-88fd09972755)



## Licencia

Este repositorio contiene código privado desarrollado por Backend I. El acceso y el uso de este código están restringidos a los empleados de Backend I para fines específicos dentro del ámbito de sus responsabilidades laborales.

Cualquier distribución, reproducción o modificación de este código sin autorización expresa de Backend I está estrictamente prohibida.

<p align="center">
Derechos de autor © 2024 Backend I
</p>
