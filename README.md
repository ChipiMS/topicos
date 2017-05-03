# topicos
La consola se abre donde está este archivo
Para empezar a trabajar o cuando alguien empuje algo hay que poner git pull origin master si el git
Hay que tratar de no trabajar en los mismos archivos
Para ver que cosas has cambiado tienes que poner git status
Para agregar las cosas a las que le has movido le pones git add *
Luego poner git commit -m "Aquí pones una descripción de lo que hiciste"
Luego git push origin master y avisar que empujaste

Para agregar base de datos y usuario que ingresa a la base de datos desde netbeans:
mysql -u root -p
source Chinook_MySql_AutoIncrementPKs.sql;
grant all privileges on chinook.* to topicos@localhost identified by 'TopicosProgra';