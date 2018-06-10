<?php
  $con = mysqli_connect("localhost", "xnatalx", "SFMario1@", "xnatalx"); //ip, dbID, dbPassword, dbID

  $userID = $_POST["userID"]; //Post 방식
  $userPassword = $_POST["userPassword"];
  //SQL query 문 : 특정 ID 와 특정 Password 에 해당하는 USER 가 있는지 검색
  $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ? AND userPassword = ?");
  mysqli_stmt_bind_param($statement, "ss", $userID, $userPassword); //SQL query 문에서 두개 변수 String
  mysqli_stmt_execute($statement); //SQL query 문 실행

  //SQL query 문 결과 가져옴
  mysqli_stmt_store_result($statement);
  mysqli_stmt_bind_result($statement, $companyName, $userID, $userPassword, $userName, $userAge, $helmetID, $beltID, $shoesID);

  $response = array();
  $response["success"] = false;
  //reponse 에 결과를 담는다
  while(mysqli_stmt_fetch($statement)){
    $response["success"] = true;
    $response["companyName"] = $companyName;
    $response["userID"] = $userID;
    $response["userPassword"] = $userPassword;
    $response["userName"] = $userName;
    $response["userAge"] = $userAge;
    $response["helmetID"] = $helmetID;
    $response["beltID"] = $beltID;
    $response["shoesID"] = $shoesID;

  }
  //안드로이드에 보내줌
  echo json_encode($response);
?>
