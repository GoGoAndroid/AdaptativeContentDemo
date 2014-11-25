<?php
$dir = "fonts";

// Ouvre un dossier bien connu, et liste tous les fichiers



if (is_dir ( $dir )) {
	if ($dh = opendir ( $dir )) {
		while ( ($file = readdir ( $dh )) !== false ) {
			$pos = strpos($file, "Regular");
			if ($pos === false){
				
			}else{
				$font= str_replace(".ttf", "", $file);
				echo "@font-face {\n";
				echo "font-family: '"."$font"."';\n"."src: url('fonts/"."$file"."') format('truetype');\n\n";
				echo "}\n";
			}
		
			
		}
		closedir ( $dh );
	}
}

?>