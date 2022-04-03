# CSC498G_Currency_Converter
Mobile Application - Project - Currency Converter - Android Studio
## Mobile Application - Currency Converter

### Currency Converter
### #Project

You are asked to implement a currency converter (From LBP to USD and vice versa) using Android Studio.
<br />

### Project Requirements: 

- First API: Scrape the Lira's website ([link](https://lirarate.org/)) and get the updated currency rate using PHP. Once extracted, return it to the front-end as JSON object. 
- Second API: Will receive the amount to be converted and the currency, the script should save it in a MySQL database. Once saved and given the updated currency rate, the script will convert the amount and return it back to the front-end as JSON object.
- The android application consists of two pages (Landing Page and Calculator Page) linked together. The Calculator page calls the first API onCreate() and calls the second API when the user clicks on the conversion button. 
- The application should display the returned JSON objects on the screen (the update rate as well as the converted amount). 

### Demo Images: 

<p align="center">
  <img width="370" height="750" src="https://user-images.githubusercontent.com/98115261/161427161-0b7ceabc-fc5f-42f9-8b94-bb7b4bc15c5e.png">
</p>

<p align="center">
  <img width="370" height="750" src="https://user-images.githubusercontent.com/98115261/161427164-79dc893e-19f5-456c-bbfd-47ec6c7b66d8.png">
</p>

<p align="center">
  <img width="370" height="750" src="https://user-images.githubusercontent.com/98115261/161427166-6294de9a-048f-4da3-b816-d3e60e961d2d.png">
</p>
