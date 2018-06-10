<?php
  $con = mysqli_connect("localhost", "xnatalx", "SFMario1@", "xnatalx");

  $userID = $_POST["userID"];

  $statement = mysqli_prepare($con, "DELETE FROM USER WHERE userID = ?");
  mysqli_stmt_bind_param($statement, "s", $userID);
  mysqli_stmt_execute($statement);

  $statement2 = mysqli_prepare($con, "DELETE FROM EQUIPMENT_STATE WHERE userID = ?");
  mysqli_stmt_bind_param($statement2, "s", $userID);
  mysqli_stmt_execute($statement2);

  $response = array();
  $response["success"] = true;

  echo json_encode($response);
?>
