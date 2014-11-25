package com.gosimple.adaptativetext.client.shape_utils.tools;

public class Size {
	public double width;
	public double height;
	
	public Size(int width, int height){
		this.width=width;
		this.height=height;
	}
	
	public double rattioTo(Size size){
		if (width==0 || height==0){
			return 1;
		}
		double wR=size.width/width;
		double wH=size.height/height;
		return Math.min(wR,wH);
	}
	public void dump(){
		System.out.println("Size "+width+" , "+height);
	}
}
