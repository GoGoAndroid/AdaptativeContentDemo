package com.gosimple.adaptativetext.client.shape_utils.tools;

public class RectTopHeightLeftWidth {

	public double top,height,left,width;
	
	public RectTopHeightLeftWidth(double top,double height,double left,double width){
		this.top=top;
		this.height=height;
		this.width=width;
		this.left=left;
	}
	public void dump(){
		System.out.println("Top "+(int) top+" height "+(int) height+" left "+(int) left+" width "+(int) width);
	}
	
}
