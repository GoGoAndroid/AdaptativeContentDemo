package com.gosimple.adaptativetext.client.shape_utils.tools;

import java.util.ArrayList;
import java.util.List;

public class ShapeList extends ArrayList<Shape>{
	private static final long serialVersionUID = 1L;
	public ShapeList(){
		super();
	}
	public ShapeList(List<Shape> shapes){
		super();
		addAll(shapes);
	}

}
