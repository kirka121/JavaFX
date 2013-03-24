package javafx;

import java.lang.*;

class Header{
	public static String draw_header(String program_title, String student_name, String student_course, String teacher_name, Integer width, Integer height, String border){
		String Header_array = border; 
		String line = "";
		if (height%2 == 0){ height++;}
		if (height < 7){ height = 7;}
		if (width < program_title.length()+7){width = program_title.length()+7;}
		if (width < student_name.length()+13){width = student_name.length()+13;}
		if (width < student_course.length()+12){width = student_course.length()+12;}
		if (width < teacher_name.length()+10){width = teacher_name.length()+10;}
		for (int i = 1; i <= height; i++){
			if (i == 1){
				for (int x = 1; x < width; x++){
					Header_array = Header_array.concat(border);
				}
			} else if (i > 1 && i < height){
				Header_array = Header_array.concat("\n");
				Header_array = Header_array.concat(border);

				if (i == (Math.round(height/2)-1)){
					line = program_title;
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
				} else if (i == (Math.round(height/2))+1){
					line = "Student: ";
					line = line.concat(student_name);
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
				} else if (i == (Math.round(height/2))+2){
					line = "Course: ";
					line = line.concat(student_course);
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
				} else if (i == (Math.round(height/2))+3){
					line = "Prof: ";
					line = line.concat(teacher_name);
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < ((width/2)-(line.length()/2)); x++){
						Header_array = Header_array.concat(" ");
					}
				} else {
					for (int x = 2; x < width; x++){
						Header_array = Header_array.concat(" ");
					}
				}
				Header_array = Header_array.concat(border);
			} else if (i == height) {
				Header_array = Header_array.concat("\n");
				Header_array = Header_array.concat(border);
				for (int x = 1; x < width;x++){
					Header_array = Header_array.concat(border);
				}
			}
		}
		Header_array = Header_array.concat("\n");
		return Header_array;
	}
}
