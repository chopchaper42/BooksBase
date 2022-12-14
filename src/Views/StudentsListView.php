<html>
<head>
    <meta charset="utf-8">
    <title>Students List</title>
    <link rel="stylesheet" href="../src/Styles/students_list_style.css" type="text/css">
</head>
<body class="container">
    <div class="page_container">
        <div class="head container">
            <div class="left_head container">
                <div class="sign_in_link"><a href="Views/SignUpView.php"><p>Sign in</p></a></div>
                <div class="table_name"><h4>Students List</h4></div>
            </div>
            <div class="search container">
                <h4>Search:</h4>
                <input type="text">
                <input type="submit" value="Find">
            </div>
        </div>
        <div class="table_container">
            <table class="students_table">
                <tr>
                    <th>Name:</th>
                    <th>Last Name:</th>
                    <th>Group:</th>
                    <th>Points:</th>
                </tr>
                <?php foreach($data as $row):?>
                    <tr>
                        <td><?php echo $row['name']; ?></td>
                        <td><?php echo $row['lastName']; ?></td>
                        <td><?php echo $row['groupNumber']; ?></td>
                        <td><?php echo $row['points']; ?></td>
                    </tr>
                <?php endforeach; ?>
            </table>
        </div>
    </div>

</body>
</html>