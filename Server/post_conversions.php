<?php

include("db_info.php");

$amount = $_POST["amount"]; // list that contains all the amounts sent from the front-end
$rate = $_POST["rate"]; // list that contains all the rates sent from the front-end
$converted_from = $_POST["converted_from"]; // list that contains all the strings (USD or LBP) sent from the front-end
$converted_to = $_POST["converted_to"]; // list that contains all the strings (USD or LBP) sent from the front-end
$converted_amount = $_POST["converted_amount"]; // list that contains all the converted amounts sent from the front-end

?>