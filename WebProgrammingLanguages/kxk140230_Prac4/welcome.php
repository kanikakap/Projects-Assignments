<?php
session_start();





//get cookie
$k1 = $_COOKIE['uName'];

//get session var
$k2 = $_SESSION['fName'];
$k3 = $_SESSION['eName'];

//print them 
echo "Welcome,  $k1 <br> ";
echo "<br>";
echo "Full Name - $k2 <br>";
echo "Email Address - $k3 <br>";
?>


