<?php
    use \Controllers\Paths;
    $bookFileName = $data['file_name'];
    $booksFolder = Paths::PATHS['BookFiles'];
    $downloadPath = $booksFolder . $bookFileName;

    if (!file_exists($downloadPath)) {
        echo "<a href='#' class=\"link\" title=\"This book isn't available yet\">Download isn't available</a>";
    } else {
        echo "<a href='$downloadPath' class=\"link\" title=\"Download this book\" download>Download</a>";
    }