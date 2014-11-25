package com.gosimple.adaptativetext.client.shape_utils.tools;


public class TextShape implements Shape{

	public Size size;
	public String content;
	public TextShape(String text){
		
		this.content=text;
		String[] lines=text.split("\n");
		int maxCol=0;
		for (int i=0;i<lines.length;i++){
			if (lines[i].length()>maxCol){
				maxCol=lines[i].length();
			}
		}
		size=new Size(lines.length,maxCol);
	}
	public int getWidth(){return (int) size.width;}
	public int getHeight(){return (int) size.height;}
	@Override
	public String getText() {
		return content;
	}
}
