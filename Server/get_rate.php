<?php

// https://lirarate.org/ (the url of the website)

// https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP (the url of the api that the website is calling to get the rate)

$ch = curl_init(); // create curl resource using curl function
curl_setopt($ch, CURLOPT_URL, 'https://lirarate.org/wp-json/lirarate/v2/rates?currency=LBP'); // set the live url api of the website
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true); // return the transfer as a string
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true); // tell cURL that it should follow any redirects

$data = curl_exec($ch); // $data contains the output string

curl_close($ch); // close curl resource to free up system resources

$string = substr($data, -8, -3); // get the last number by removing the last three elements of the string and getting the 8 digit number

?>