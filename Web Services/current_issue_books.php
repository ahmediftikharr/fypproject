<?php 

require "init.php";

$sql_query="select * from issue_books where status LIKE 'active'";
$result=mysqli_query($con,$sql_query);

$response=array();

while($row=mysqli_fetch_array($result))
{
    
		array_push($response,array("book_name"=>$row[3],"issued_date"=>$row[5],"book_category"=>$row[4]
	,"issued_id"=>$row[0]));
}
echo json_encode($response);


 ?>

