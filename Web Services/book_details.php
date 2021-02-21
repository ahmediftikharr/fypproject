<?php 

require "init.php";

 $issued_id=$_POST["issued_id"];

$sql_query="select * from issue_books where issue_id LIKE('".$issued_id."')";
$result=mysqli_query($con,$sql_query);
$response=array();

if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_row($result);
    $book_name=$row[3];
    $book_category=$row[4];
    $issued_date=$row[5];
    $returned_date=$row[7];
    $fine=$row[8];
    
    array_push($response,array("book_name"=>$book_name,"book_category"=>$book_category,"issued_date"=>$issued_date,
	"returned_date"=>$returned_date,"fine"=>$fine));
    echo json_encode($response);
}
else
{
	echo "Error";
}


 ?>



