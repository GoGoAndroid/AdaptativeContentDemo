package com.gosimple.adaptativetext.client.shape_utils.tools;

import java.util.List;



public class Line extends ShapeList{
	private static final long serialVersionUID = 1L;
	public  int height=0;
	public  int width=0;
	public Line() {
		super();
	}
	public Line(List<Shape> shapes) {
		super(shapes);
		resetSize();
		//System.out.println("Line size "+height+","+width);

	}
	
	public void clear(){
		super.clear();
		height=0;
		width=0;
	
	}
	
	public boolean addAll(ShapeList shapes){
		if (super.addAll(shapes)){
			resetSize();
			//System.out.println("Line size "+height+","+width);
			return true;
		}

		else{
			return false;
		}

	}
	public boolean add(Shape shape){
		if (super.add (shape)){
			 changeSize( shape);
			//	System.out.println("Line size "+height+","+width);
			return true;
		}
		else{
			return false;
		}

	}
	
	public List<Shape> toListShape(){
		return (List<Shape>) this;
	}
	
	public void resetSize(){
		height=0;
		width=0;
		for (int i=0;i<size();i++){
			changeSize(get(i));
		}
	}
	
	void changeSize(Shape shape){
		if (shape.getHeight()>height){
			height=shape.getHeight();
		}
		width+=shape.getWidth();
	
	}

	
}
