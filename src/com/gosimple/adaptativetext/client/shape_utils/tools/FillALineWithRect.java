package com.gosimple.adaptativetext.client.shape_utils.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FillALineWithRect implements Iterator<ShapeList > {

	ShapeList rects;
	List<NRectsInALine> listPossibilities=new ArrayList<NRectsInALine>();
	int currentPossibility=0;
	
	public FillALineWithRect(ShapeList rects){
		this.rects=rects;
		for (int i=1;i<rects.size();i++){
			listPossibilities.add(new NRectsInALine(rects,i));
		}
		
	}
	@Override
	public boolean hasNext() {
		//System.out.println("currentPossibility = "+currentPossibility);
		if (listPossibilities.size()==0){
			return false;
		}
		else if (listPossibilities.get(currentPossibility).hasNext()){
			return true;
		}else if(currentPossibility<listPossibilities.size()-1){
			currentPossibility++;
			return hasNext();
		}else{
			return false;
		}
	}

	@Override
	public ShapeList next() {
		return listPossibilities.get(currentPossibility).next();
	
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
