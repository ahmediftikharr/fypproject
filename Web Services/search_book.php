<?php 

require "init.php";

$name=$_POST["name"];

$sql_query="select * from books where book_name like '".$name."'";
$result=mysqli_query($con,$sql_query);

$response=array();

while($row=mysqli_fetch_array($result))
{
    
	array_push($response,array("book_name"=>$row[1],"book_author"=>$row[3],"book_edition"=>$row[4]
	,"book_shelf"=>$row[5],"book_rack"=>$row[6],"book_quantity"=>$row[7],"book_status"=>$row[8],
	"book_category"=>$row[2]));
	
}
echo json_encode($response);


 ?>

