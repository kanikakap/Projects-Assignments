<?php
session_start();




$user = $_POST['userName'];
$full = $_POST['fullName'];
$email = $_POST['emailAddress'];


$_SESSION['fName'] = $full;
$_SESSION['eName'] = $email;
setcookie("uName", $user, time()+3600);
//$date_of_expiry = time() + 60 ;


echo "Username : $user <br>";
echo "Full Name : $full <br>";
echo "Email Address : $email <br>";
//setcookie(name, value, expire, path, domain); 


?>



<!DOCTYPE html>
<html>
<body>

<form action="welcome.php" method="POST">
 
 <a href = "welcome.php">Welcome User</a>


</form> 
</body>
</html>
