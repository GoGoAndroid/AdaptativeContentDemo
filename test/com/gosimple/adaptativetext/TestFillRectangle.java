package com.gosimple.adaptativetext;



import com.gosimple.adaptativetext.client.shape_utils.ShapeArrangement;
import com.gosimple.adaptativetext.client.shape_utils.tools.Arrangement;
import com.gosimple.adaptativetext.client.shape_utils.tools.Position;
import com.gosimple.adaptativetext.client.shape_utils.tools.Shape;
import com.gosimple.adaptativetext.client.shape_utils.tools.TextShape;
import com.gosimple.adaptativetext.client.shape_utils.tools.ShapeList;
import com.gosimple.adaptativetext.client.shape_utils.tools.Size;

import junit.framework.TestCase;

public class TestFillRectangle extends TestCase{

	/*
	ShapeArrangement shapesInclusion;
	protected void setUp() throws Exception {
		super.setUp();
		shapesInclusion=new ShapeArrangement(new Size(30,40));
		}
	*/

	
	public void testArrangement() {
		ShapeArrangement shapesInclusion=new ShapeArrangement(new Size(4,1));
		
		String textes[]=new String[]{
				"1",
				"2",
				"3",
				"4",				
		};
		
		ShapeList shapes=new ShapeList ();
		
		for (int i=0;i<textes.length;i++){
			shapes.add((Shape) new TextShape(textes[i]));
		}
		shapesInclusion.addNRects(shapes);
		
		Arrangement arrangement=shapesInclusion.getBestArrangement();
		for (Position position:arrangement.positionSize.keySet()){
			
			int x=position.x;
			int y=position.y;
			for (int i_line=0;i_line<y;i_line++){
				assertTrue(arrangement.getPosition(0,i_line)!=null);
			}
			for (int i_col=0;i_col<x;i_col++){
				assertTrue(arrangement.getPosition(i_col,y)!=null);
			}
			
			
		}
	}
	
	public void testLine() {
		
		ShapeArrangement shapesInclusion=new ShapeArrangement(new Size(4,1));
	
		String textes[]=new String[]{
				"1",
				"2",
				"3",
				"4",				
		};
		
		ShapeList shapes=new ShapeList ();
		
		for (int i=0;i<textes.length;i++){
			shapes.add((Shape) new TextShape(textes[i]));
		}
		shapesInclusion.addNRects(shapes);
		
		Arrangement arrangement=shapesInclusion.getBestArrangement();
		
		System.out.println("Best is ");
		arrangement.dump();
		
		arrangement.positionOfShapeInStructure.get(shapes.get(0)).equals(new Position(0,0));
		arrangement.positionOfShapeInStructure.get(shapes.get(1)).equals(new Position(1,0));
		arrangement.positionOfShapeInStructure.get(shapes.get(2)).equals(new Position(2,0));
		arrangement.positionOfShapeInStructure.get(shapes.get(3)).equals(new Position(3,0));
	}
	

	public void testCol() {
		
		ShapeArrangement shapesInclusion=new ShapeArrangement(new Size(1,4));
		String textes[]=new String[]{
				"1",
				"2",
				"3",
				"4",				
		};
		
		ShapeList shapes=new ShapeList ();
		
		for (int i=0;i<textes.length;i++){
			shapes.add((Shape) new TextShape(textes[i]));
		}
		shapesInclusion.addNRects(shapes);
		
		Arrangement arrangement=shapesInclusion.getBestArrangement();
		System.out.println("Ratio is "+shapesInclusion.containerRatio);
		System.out.println("Best is ");
		arrangement.dump();
		
		arrangement.positionOfShapeInStructure.get(shapes.get(0)).equals(new Position(0,0));
		arrangement.positionOfShapeInStructure.get(shapes.get(1)).equals(new Position(0,1));
		arrangement.positionOfShapeInStructure.get(shapes.get(2)).equals(new Position(0,2));
		arrangement.positionOfShapeInStructure.get(shapes.get(3)).equals(new Position(0,3));
	
	}
	
	public void testQuare() {
		
		ShapeArrangement shapesInclusion=new ShapeArrangement(new Size(2,2));
		String textes[]=new String[]{
				"1",
				"2",
				"3",
				"4",				
		};
		
		ShapeList shapes=new ShapeList ();
		
		for (int i=0;i<textes.length;i++){
			shapes.add((Shape) new TextShape(textes[i]));
		}
		shapesInclusion.addNRects(shapes);
		
		Arrangement arrangement=shapesInclusion.getBestArrangement();
		System.out.println("Ratio is "+shapesInclusion.containerRatio);
		System.out.println("Best is ");
		arrangement.dump();
		
		arrangement.positionOfShapeInStructure.get(shapes.get(0)).equals(new Position(0,0));
		arrangement.positionOfShapeInStructure.get(shapes.get(1)).equals(new Position(1,0));
		arrangement.positionOfShapeInStructure.get(shapes.get(2)).equals(new Position(0,1));
		arrangement.positionOfShapeInStructure.get(shapes.get(3)).equals(new Position(1,1));
	}
}
