<?php

  $con = mysqli_connect("localhost", "xnatalx", "SFMario1@", "xnatalx");

  $companyName = $_POST["companyName"];
  $userID = $_POST["userID"];
  $userPassword = $_POST["userPassword"];
  $userName = $_POST["userName"];
  $userAge = $_POST["userAge"];

  $helmetOn = $_POST["helmetOn"];
  $beltOn = $_POST["beltOn"];
  $shoesOn = $_POST["shoesOn"];

  $helmetID = $_POST["helmetID"];
  $beltID = $_POST["beltID"];
  $shoesID = $_POST["shoesID"];

  $statement = mysqli_prepare($con, "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
  mysqli_stmt_bind_param($statement, "ssssiiii", $userID, $userPassword, $userName, $companyName, $userAge, $helmetID, $beltID, $shoesID);
  mysqli_stmt_execute($statement);

  $statement2 = mysqli_prepare($con, "INSERT INTO EQUIPMENT_STATE VALUES (?, ?, ?, ?)");
  mysqli_stmt_bind_param($statement2, "ssss", $userID, $helmetOn, $beltOn, $shoesOn);
  mysqli_stmt_execute($statement2);

  $response = array();
  $response["success"] = true;

  echo json_encode($response);

?>
