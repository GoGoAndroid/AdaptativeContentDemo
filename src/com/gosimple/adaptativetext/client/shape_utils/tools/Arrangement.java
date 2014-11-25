package com.gosimple.adaptativetext.client.shape_utils.tools;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Arrangement {
	
	
	
	public Map<Shape,Position> positionOfShapeInStructure=new HashMap<Shape,Position>();
	public Map<Position,Size> positionSize=new HashMap<Position,Size>();
	
	double totWidth,totHeight;
	public double ratio;

	
	
	public Arrangement(List<Line> lines){
		
		 totHeight=0;
		 totWidth=0;
		
		for (int i_line=0;i_line<lines.size();i_line++){
			Line line=lines.get(i_line);
			int height=line.height;
			totHeight+=height;
		
			int width=0;
			for (int i_col=0;i_col<line.size();i_col++){
				Shape shape=line.get(i_col);
				Position position=new Position(i_col,i_line);
				positionOfShapeInStructure.put(shape,position);
				positionSize.put(position, new Size(shape.getWidth(),height));
				width+=shape.getWidth();
			}
			if (width>totWidth){
				totWidth=width;
			}
			
		}
		ratio=totWidth/totHeight;
		//dump();
		
	}

	public RectTopHeightLeftWidth getRect(Shape shape){
		
		
		double top,height,left,width;
		Position position=positionOfShapeInStructure.get(shape);

		
		//System.out.println("Max "+totWidth+" , "+totHeight);
		height=  positionSize.get(position).height;

		top=0;
		left=0;
		for (int l=0;l<position.y;l++){
			top+=positionSize.get(getPosition(0,l)).height;
		}
	
		width=  positionSize.get(position).width;
		double totLineWidth=0;
		for (int c=0; ;c++){
			Position position_=getPosition(c,position.y);
			if(position_==null){
				break;
			}
			double width_=positionSize.get(position_).width;
			if (c<position.x){
				left+=width_;
			}
			totLineWidth+=width_;
		
		}
	

		// width and left are relative to line width, and not totWidth
		//ratio width = totWidth/
		double ratioWidth=totWidth/totLineWidth;
		
		return new RectTopHeightLeftWidth(100.0*top/totHeight,100.0*height/totHeight,100.0*ratioWidth*left/totWidth,100.0*ratioWidth*width/totWidth);
		

	}
	
	public Position getPosition(int col, int l){
		for (Position position:positionSize.keySet()){
			if (position.x==col && position.y==l){
				return position;
			}
		}
		return null;
	}
	
	public void dump(){
	
		System.out.println("Arrangement "+ratio);
		for (Shape shape:positionOfShapeInStructure.keySet()){
			Position position=positionOfShapeInStructure.get(shape);
			System.out.println("\t'"+shape.getText()+"' :" +position.x+","+ position.y);
		}
	}

}
