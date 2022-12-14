<?php
    use \Controllers\Paths;
    $directory = Paths::PATHS['BookCovers'];
    $fileName = "id" . $data['id'] . ".jpg";
    $fullPath = $directory . $fileName;

    if (!file_exists($fullPath)) {
        $fullPath = $directory . "no_cover.jpg";
    }

    echo "<img src=\"$fullPath\" class=\"book_cover_img book_info_item\">" . "\n";