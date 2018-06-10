<?php

  $con = mysqli_connect("localhost", "xnatalx", "SFMario1@", "xnatalx");

  $userID = $_POST["userID"];
  $helmetOn = $_POST["helmetOn"];
  $beltOn = $_POST["beltOn"];
  $shoesOn = $_POST["shoesOn"];

  $statement = mysqli_prepare($con, "UPDATE EQUIPMENT_STATE SET helmetOn = ?, beltOn = ?, shoesOn = ? WHERE userID = ?");
  mysqli_stmt_bind_param($statement, "ssss", $helmetOn, $beltOn, $shoesOn, $userID);
  mysqli_stmt_execute($statement);

  $response = array();
  $response["success"] = true;

  echo json_encode($response);

?>
