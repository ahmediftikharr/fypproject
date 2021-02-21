<?php

$db_name="id6698694_smartlibrary";
$mysql_username="id6698694_smartlibrary";
$mysql_pass="Sami1124";
$server_name="localhost";

$con=mysqli_connect($server_name,$mysql_username,$mysql_pass,$db_name);

if($con)
{
	echo "success";
}
else
{
	echo "failure";
}

?>