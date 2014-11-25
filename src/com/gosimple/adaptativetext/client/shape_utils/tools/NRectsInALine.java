package com.gosimple.adaptativetext.client.shape_utils.tools;

import java.util.Iterator;
import java.util.List;


public class NRectsInALine implements Iterator<ShapeList >{
	
	List<Shape> rects;
	int n;
	CombinationIterator<Shape> iterator;
	public NRectsInALine(List<Shape> rects,int n){
		this.rects=rects;
		this.n=n;
		iterator=new CombinationIterator<Shape>(rects,n);		
	}
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}
	@Override
	public ShapeList next() {
		return new ShapeList(iterator.next());
	}
	@Override
	public void remove() {
		iterator.remove();
	}
	


}
