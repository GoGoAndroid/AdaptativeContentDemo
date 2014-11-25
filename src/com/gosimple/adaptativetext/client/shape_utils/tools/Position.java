package com.gosimple.adaptativetext.client.shape_utils.tools;

public class Position {
	public int x,y;
	public Position(int x,int y){
		this.x=x;
		this.y=y;
	}
	boolean equals(Position position){
		return position.x==x&& position.y==y;
	}
	
	public int hashCode(){
		return (100*x)+y;
	}
}
