<?php
  $con = mysqli_connect("localhost", "xnatalx", "SFMario1@", "xnatalx");
  $result = mysqli_query($con, "SELECT * FROM USER JOIN EQUIPMENT_STATE ON USER.userID = EQUIPMENT_STATE.userID;");
  $response = array();
  //연결 후 차례대로 DB정보 뽑아서 response 에 담아서 보여줌.
  while($row = mysqli_fetch_array($result)){
    array_push($response, array("userID"=>$row[0], "userPassword"=>$row[1], "userName"=>$row[2], "companyName"=>$row[3], "userAge"=>$row[4], "helmetOn"=>$row[9], "beltOn"=>$row[10], "shoesOn"=>$row[11],));
  }

  echo json_encode(array("response"=>$response));

?>
