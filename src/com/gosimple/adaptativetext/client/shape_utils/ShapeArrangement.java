package com.gosimple.adaptativetext.client.shape_utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gosimple.adaptativetext.client.shape_utils.tools.Arrangement;
import com.gosimple.adaptativetext.client.shape_utils.tools.FillALineWithRect;
import com.gosimple.adaptativetext.client.shape_utils.tools.Line;
import com.gosimple.adaptativetext.client.shape_utils.tools.ShapeList;
import com.gosimple.adaptativetext.client.shape_utils.tools.Size;

public class ShapeArrangement {
	
	Size containerSize;
	public double containerRatio;
	
	double diffWithRatio;
	public double bestRatio ;
	public Map<Double,Arrangement > possiblesArrangements;
	
	
	
	
	public ShapeArrangement(Size containerSize){
		possiblesArrangements=new HashMap<Double,Arrangement >();
		this.containerSize=containerSize;
		containerRatio=containerSize.width/containerSize.height;
	
	}

	
	public Arrangement getBestArrangementSet(){
		//containerSize.dump();
	
		return possiblesArrangements.get(bestRatio);
	}
	
	public Arrangement getBestArrangement(){
		//System.out.println("Ratio is "+containerRatio);

		diffWithRatio=Double.MAX_VALUE;
		for (Double ratio:possiblesArrangements.keySet()){
			double currentDiff=Math.abs(containerRatio-ratio);
			if (currentDiff<diffWithRatio){
				diffWithRatio=currentDiff;
				bestRatio=ratio;
			}
		}
		
		return getBestArrangementSet();
	}
	
	
	public void changeShapes(ShapeList rects){
		possiblesArrangements.clear();
		addNRects( rects);
	}
	public void changeContainer(Size containerSize){
		this.containerSize=containerSize;
		containerRatio=containerSize.width/containerSize.height;
	}
	
	public void addNRects(ShapeList rects){
		diffWithRatio=Double.MAX_VALUE;
		List<Line > lines=new ArrayList<Line >();
		for (int i=0;i<rects.size();i++){
			lines.add(new Line());
		}
		
		addNRects(rects,lines,  lines.get(0));

	}
	
	
	
	
	public void addNRects(ShapeList remainingRects,List<Line> lines, Line currentLine){
		currentLine.clear();
		

		if (lines.indexOf(currentLine)==lines.size()-1){
			currentLine.addAll(remainingRects);
			//System.out.println("Line  "+lines.indexOf(currentLine)+" "+dump(currentLine));
			registerArrangement(lines);
			return;
			
		}
		FillALineWithRect  fillALineWithRect=new FillALineWithRect(remainingRects);
		while (fillALineWithRect.hasNext()){
			currentLine.clear();
			ShapeList putRects=(ShapeList) fillALineWithRect.next();
			currentLine.addAll(putRects);
			//System.out.println("Line  "+lines.indexOf(currentLine)+" "+dump(currentLine));
			
			addNRects(getRemainingRect(remainingRects,putRects),lines,lines.get(lines.indexOf(currentLine)+1));
			
		}
		
		
		for (int i=lines.indexOf(currentLine);i<lines.size();i++){
			lines.get(i).clear();
		}
		currentLine.addAll(remainingRects);
		//System.out.println("Line  "+lines.indexOf(currentLine)+" filled "+dump(currentLine));
		registerArrangement(lines);
		return;
	
	}
	

	
	
	public Size stringToSizeInChar(String text){
		
		String[] lines=text.split("\n");
		int maxCol=0;
		for (int i=0;i<lines.length;i++){
			if (lines[i].length()>maxCol){
				maxCol=lines[i].length();
			}
		}
		return new Size(maxCol,lines.length);
	}
	public ShapeList getRemainingRect(ShapeList remainingRects, ShapeList rectsPut){
		ShapeList result=new ShapeList();

		// System.out.println("Remaining was  "+dump(remainingRects));
		// System.out.println("Put is  "+dump(rectsPut));
		 
		 for (int i=0;i<remainingRects.size();i++){
			 if (!rectsPut.contains(remainingRects.get(i))){
				 result.add(remainingRects.get(i));
			 }
		
		 }
	//	System.out.println("Remaining "+dump(result));
		 return result;
		 
	}
	
	void registerArrangement(List<Line > lines){
		
		
/*
		System.out.println("--------------");
		System.out.println("Ratio is "+ratio);
		System.out.println("Best diff "+diffWithRatio);
		System.out.println("Diff "+(diffWithRatio+Math.abs(ratio-containerRatio)));
	*/	

		Arrangement arrangement=new Arrangement(lines);
		if (possiblesArrangements.containsKey(arrangement.ratio))
			return;
		else{
			possiblesArrangements.put(arrangement.ratio,new Arrangement(lines));
		}
	

		/*
		System.out.println("--- Arrangement is ");
		for (int i=0;i<lines.size();i++){
			System.out.println(dump(lines.get(i)));
		}
		System.out.println("--------------");
		*/
		
	}
	double getRatio(	List<Line > lines){
		
		int nbLines=0;
		int maxCols=0;
		
		for (int i_lines=0;i_lines<lines.size();i_lines++){
			int maxLines=0;
			int nbCols=0;
			for (int i_col=0;i_col<lines.get(i_lines).size();i_col++){
				
				nbCols+=lines.get(i_lines).get(i_col).getWidth();

				if (lines.get(i_lines).get(i_col).getHeight()>maxLines){
					maxLines=lines.get(i_lines).get(i_col).getHeight();
				}
			}
			nbLines+=maxLines;
			if (nbCols>maxCols){
				maxCols=nbCols;
			}

		}
	
		double ratio=(double) maxCols/(double) nbLines;
		return ratio;
	}
	public String dump(ShapeList rects){
		String dump="";
		for (int i=0;i<rects.size();i++){
			dump+=rects.get(i).getText();
		}
		return dump;
	}
	public String dump_1(List<Line > filled){
		String dump="";
		for (int i=0;i<filled.size();i++){
			dump+= dump(filled.get(i))+"\n";
		}
		return dump;
	}
	
}
