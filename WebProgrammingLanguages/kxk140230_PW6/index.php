<?php

//echo("Testing API ");

$servername = "localhost";
$username = "root";
$password = "root";
$dbname="PW6_Books";


//create connection
$con=mysqli_connect($servername,$username,$password,$dbname);
// Check connection
if (!$con) 
{
    die("Connection failed: " . mysqli_connect_error());
}

$sql = "Select * from  Books";

$result = mysqli_query($con, $sql);
if (mysqli_num_rows($result) > 0) 
{
    // output data of each row
	while($row = mysqli_fetch_assoc($result)) 
    {
    	$newBooks[] = Array("id" => $row['book_id'], "category" => $row['category'], "title" => $row['title'], "author" => $row['author'], "year" => $row['year'], "price" => $row['price']);
    }
}
else 
{
   echo "0 results";
}

$resultArr = $newBooks;
header('content-type: application/json');
echo (json_encode($resultArr)); 

mysqli_close($con);

?>