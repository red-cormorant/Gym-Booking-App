# Gym-Booking-App
GUI for a gym to manage bookings more easily. It can be used as a client or as a personal trainer. A personal trainer may create an event and invite his desired clients for
individual training or cadio/aerobic sessions.

REQUIREMENTS: 
- XAMPP, IntelliJ Idea IDE, Java 13

HOW TO USE THIS APP:
- first, you need to connect to MySQL and Apache servers from XAMPP Control Panel
- After the connection is established, go to http://localhost/phpmyadmin/
- create a new database named "planificator".
- select this database and import into it using file "planificator.sql"
- after the import, open IntelliJ and run Main
- use "user" table to find information about existing accounts in order to login or register a new client 

FEATURES:
- client can login or register in order to use the app
- client can see the his events with invitations received from personal trainers and delete his invitation from event with no possibility to modify it.
- client can book multiple time intervals for a individual training
- client can modify personal info like name, telephone, e-mail.

- personal trainer can create an event, invite clients, modify fields.
- personal trainer can modify personal info.

Main Menu for Client ![Main Menu for Client](https://github.com/red-cormorant/Gym-Booking-App/blob/master/screenshots/main_menu_for_client.PNG)
