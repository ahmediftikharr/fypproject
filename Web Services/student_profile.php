<?php 

require "init.php";

$student_roll=$_POST["roll"];


$sql_query="select * from student where std_roll LIKE('".$student_roll."')";
$result=mysqli_query($con,$sql_query);
$response=array();

if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_row($result);
    $name=$row[2];
    $fine=$row[9];
    $roll=$row[0];
    $mobile=$row[4];
    $department=$row[6];
    $batch=$row[5];
    $email=$row[3];
    
    array_push($response,array("name"=>$name,"fine"=>$fine,"roll"=>$roll,
	"mobile"=>$mobile,"department"=>$department,"batch"=>$batch,
	"email"=>$email));
    echo json_encode($response);
}



 ?>



