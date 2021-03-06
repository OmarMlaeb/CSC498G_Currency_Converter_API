<?php

include("db_info.php");

// $_GET to collect form data after submitting
$amount = $_GET["amount"]; // list that contains all the amounts sent from the front-end
$rate = $_GET["rate"]; // list that contains all the rates sent from the front-end
$converted_from = $_GET["converted_from"]; // list that contains all the strings (USD or LBP) sent from the front-end
$converted_to = $_GET["converted_to"]; // list that contains all the strings (USD or LBP) sent from the front-end
// (removed it since we're not getting it from the front end as a value) $converted_amount = $_GET["converted_amount"]; // list that contains all the converted amounts sent from the front-end

$query = $mysqli->prepare("INSERT INTO conversions (amount, rate, converted_from, converted_to, converted_amount) VALUES (?, ?, ?, ?, ?)"); // to insert values to the table "conversions" that the user will be converting on the app 
// the values are "?" to prevent sql injections

if($converted_from === "USD" && $converted_to === "LBP") { // check the strings
    $converted_amount = $amount * $rate;
} elseif($converted_from === "LBP" && $converted_to === "USD") {
    $converted_amount = number_format($amount / $rate, 3, '.', ''); // show only three digits after decimal
}

$conversion = array("conversion_value"=>$converted_amount); // array having the value of converted amount and a pointer pointing to it

echo json_encode($conversion); // return the conversion to the font end as json

$query->bind_param("ddssd", $amount, $rate, $converted_from, $converted_to, $converted_amount); // binds the parameters to the SQL query and tells the database what the parameters are

$query->execute(); // the database executes the statement after binding the values to the parameters

$response = [];

$response["status"] = "Congrats!"; // check if response succeeded

$json_response = json_encode($response); // encode the string to JSON format

echo $json_response; // return the json response

?>