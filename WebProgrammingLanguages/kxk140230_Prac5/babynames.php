<?php

$year1=$_GET["year"];
$gender1=$_GET["gender"];

$servername = "localhost";
$username = "root";
$password = "root";
$dbname="PW5_Baby";

//create connection
$con=mysqli_connect($servername,$username,$password,$dbname);
// Check connection
if (!$con) 
{
    die("Connection failed: " . mysqli_connect_error());
}

//Table name - Baby 
//Fields - (year,gender,ranking,name)

$sql = "Select * from  Baby where gender='".$gender1."' and year=".$year1;

$result = mysqli_query($con, $sql);
if (mysqli_num_rows($result) > 0) 
{
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) 
    {
        //echo "id: " . $row["id"]. " - Name: " . $row["firstname"]. " " . $row["lastname"]. "<br>";
        echo "For Year $year1 and Gender $gender1, Top Name is: ".$row["name"];
    }
}
 else 
{
    echo "0 results";
}

mysqli_close($con);
?> 