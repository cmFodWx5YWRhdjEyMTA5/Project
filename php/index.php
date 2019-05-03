<?php
    //$choice = $_POST['choice'];
    
    switch ($choice)
    {
        case "1":
        {
            signup();
            break;
        }
    }

    function signup()
    {
        $name = $_POST['name'];
        $email = $_POST['email'];
        $password = $_POST['password'];
        
        include "config.php";
        $sql = "insert into temployee (name, email, password) values('$name', '$email', '$password')";
    
        if ($conn->query($sql) == "TRUE")
        {
            echo "1";
        }
        else
        {
            echo "0";
        }
        $conn->close();
    }
?>