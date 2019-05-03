<?php
$servername = "localhost";
$database = "id9418859_userdb";
$username = "id9418859_admin";
$password = "admin";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $database);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

?>