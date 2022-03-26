<?php

$db_host = "localhost"; // the database is hosted locally
$db_user = "root"; // the user used to login to the database (by default)
$db_pass = null; // the password (by default)
$db_name = "currency_converter_db"; // the name of the database

$mysqli = new mysqli($db_host, $db_user, $db_pass, $db_name); // object of type mysqli

if(mysqli_connect_errno()){ // in case this function is true (error occurred)
    die("Connection Failed!"); // die here and print connection failed
}

?>