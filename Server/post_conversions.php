<?php

include("db_info.php");

$amount = $_POST["amount"]; // list that contains all the amounts sent from the front-end
$rate = $_POST["rate"]; // list that contains all the rates sent from the front-end
$converted_from = $_POST["converted_from"]; // list that contains all the strings (USD or LBP) sent from the front-end
$converted_to = $_POST["converted_to"]; // list that contains all the strings (USD or LBP) sent from the front-end
$converted_amount = $_POST["converted_amount"]; // list that contains all the converted amounts sent from the front-end

$query = $mysqli->prepare("INSERT INTO conversions (amount, rate, converted_from, converted_to, converted_amount) VALUES (?, ?, ?, ?, ?)"); // to insert values to the table "conversions" that the user will be converting on the app 
// the values are "?" to prevent sql injections

$query->bind_param("dissd", $amount, $rate, $converted_from, $converted_to, $converted_amount); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$response = [];

$response["status"] = "Congrats!"; // check if response succeeded

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>