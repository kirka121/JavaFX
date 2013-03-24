package javafx;

import java.lang.*;

class ArmyDisplay{
	//draws the title, centers everything inside a box
	public static String draw_title(String army_title, String army_size, String army_type, String average_strength, Integer width, Integer height, String border){
		String Header_array = border; 
		String line = "";
		if (height%2 == 0){ height++;}
		if (height < 6){ height = 6;}
		if (width < army_title.length()+7){width = army_title.length()+7;}
		if (width < army_size.length()+13){width = army_size.length()+13;}
		if (width < army_type.length()+12){width = army_type.length()+12;}
		if (width < average_strength.length()+10){width = average_strength.length()+10;}
		for (int i = 1; i <= height; i++){
			if (i == 1){
				for (int x = 1; x < width; x++){
					Header_array = Header_array.concat(border);
				}
			} else if (i > 1 && i < height){
				Header_array = Header_array.concat("\n");
				Header_array = Header_array.concat(border);

				if (i == (Math.round(height/2)-1)){
					line = "Army Name: " + army_title;
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < 2; x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < width-(2+line.length()); x++){
						Header_array = Header_array.concat(" ");
					}
				} else if (i == (Math.round(height/2))){
					line = "Size: ";
					line = line.concat(army_size);
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < 2; x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < width-(2+line.length()); x++){
						Header_array = Header_array.concat(" ");
					}
				} else if (i == (Math.round(height/2))+1){
					line = "Type: ";
					line = line.concat(army_type);
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < 2; x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < width-(2+line.length()); x++){
						Header_array = Header_array.concat(" ");
					}
				} else if (i == (Math.round(height/2))+2){
					line = "Average Strength: ";
					line = line.concat(average_strength);
					if (line.length()%2 != 0){
						line = line.concat(" ");
					}
					for (int x = 1; x < 2; x++){
						Header_array = Header_array.concat(" ");
					}
					Header_array = Header_array.concat(line);
					for (int x = 1; x < width-(2+line.length()); x++){
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
	//draws the header, centers text in middle of cells
	public static String draw_header(String name, String strength, String speed, String health, Integer length, String separator){
		Integer cell_width = length/4;
		String result = "";
		result = result.concat(center_cell(name, separator, cell_width));
		result = result.concat(center_cell(strength, separator, cell_width));
		result = result.concat(center_cell(speed, separator, cell_width));
		result = result.concat(center_cell(health, separator, cell_width));
		result = result.concat("\n");
		for(int i = 0; i<=length; i++){
			result = result.concat("-");
		}
		return result;
	}

	//draws the actual entries of army creatures, left margins
	public static String draw_army(String name, String strength, String speed, String health, Integer length, String separator){
		Integer cell_width = (length/4);
		String result = "";
		result = result.concat(left_cell(name, separator, cell_width));
		result = result.concat(left_cell(strength, separator, cell_width));
		result = result.concat(left_cell(speed, separator, cell_width));
		result = result.concat(left_cell(health, separator, cell_width));
		return result;
	}

	//calculate center
	private static String center_cell(String text, String separator, Integer width){
		String result = "";
		Integer sp = (width-1)/2 - (text.length()/2);
		for(int i = 0; i<sp; i++){
			result = result.concat(" ");
		}
		result = result.concat(text);
		for(int i = 0; i<sp; i++){
			result = result.concat(" ");
		}
		result = result.concat(separator);
		return result;
	}

	//calcualte left align
	private static String left_cell(String text, String separator, Integer width){
		String result = "";
		result = result.concat(text);
		for(int i = 0; i <= ((width-1)-(text.length()+1)); i++){
			result = result.concat(" ");
		}

		result = result.concat(separator);
		return result;
	}
}
