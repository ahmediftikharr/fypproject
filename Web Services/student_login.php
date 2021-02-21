<?php 

require "init.php";

 $student_roll=$_POST["roll"];
 $student_pass=$_POST["pass"];


$sql_query="select * from student where std_roll LIKE('".$student_roll."')
AND std_pass LIKE('".$student_pass."')";
$result=mysqli_query($con,$sql_query);
$response=array();

if(mysqli_num_rows($result)>0)
{
    $row=mysqli_fetch_row($result);
    $roll=$row[0];
    $name=$row[2];
    $status="success";
    array_push($response,array("roll"=>$roll,"name"=>$name,"result"=>$status));
    echo json_encode($response);
}
else
{
    $status="fail";
    array_push($response,array("result"=>$status));
    echo json_encode($response);
}


 ?>



