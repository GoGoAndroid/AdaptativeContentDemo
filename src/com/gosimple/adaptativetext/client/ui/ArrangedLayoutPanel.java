package com.gosimple.adaptativetext.client.ui;
import java.util.Collection;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gosimple.adaptativetext.client.shape_utils.ShapeArrangement;
import com.gosimple.adaptativetext.client.shape_utils.tools.Arrangement;
import com.gosimple.adaptativetext.client.shape_utils.tools.RectTopHeightLeftWidth;
import com.gosimple.adaptativetext.client.shape_utils.tools.Shape;
import com.gosimple.adaptativetext.client.shape_utils.tools.ShapeList;
import com.gosimple.adaptativetext.client.shape_utils.tools.Size;

public class ArrangedLayoutPanel extends LayoutPanel implements ResizeHandler{
	ShapeArrangement shapeArrangement=new ShapeArrangement(new Size(1,1));
	ShapeList shapeList=new ShapeList();
	
	public ArrangedLayoutPanel(){
		super();
		Window.addResizeHandler(this);
	}
	public ArrangedLayoutPanel(Collection<HTMLAdatativeFontSizeWidget> widgets){
		super();
		changeValues( widgets);
		Window.addResizeHandler(this);
		
	}
	

	public void changeValues(Collection<HTMLAdatativeFontSizeWidget> widgets){
		shapeList.clear();;
		clear();
		for (HTMLAdatativeFontSizeWidget widget:widgets){
			add(widget);
			shapeList.add(widget);
		}
		shapeArrangement.changeShapes(shapeList);
		reFormat();
	}
	
	protected void onAttach(){
		
	
		Scheduler.get().scheduleDeferred(new ScheduledCommand(){
			@Override
			public void execute() {
		           changeSize();
			}});
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		Scheduler.get().scheduleDeferred(new ScheduledCommand(){
			@Override
			public void execute() {
				changeSize();
			}});
	
	}
	
	public void changeSize(){
		if (getOffsetWidth()!=0){
			shapeArrangement.changeContainer(new Size(getOffsetWidth(),getOffsetHeight()));
			reFormat();
		}
	}
	

	void reFormat(){

		Arrangement bestArrangement=shapeArrangement.getBestArrangement();
		if (shapeArrangement.possiblesArrangements.isEmpty()){
			shapeArrangement.changeShapes(shapeList);
			bestArrangement=shapeArrangement.getBestArrangement();
		}
		//bestArrangement.dump();
		for (Shape shape:shapeList){
			RectTopHeightLeftWidth rect=bestArrangement.getRect(shape);
			
		//	rect.dump();
			setWidgetTopHeight( (Widget) shape, rect.top, Unit.PCT, rect.height, Unit.PCT);
			setWidgetLeftWidth( (Widget) shape, rect.left, Unit.PCT, rect.width, Unit.PCT);
		
			((HTMLAdatativeFontSizeWidget) shape).setFontSize();
			
		}
		forceLayout();
		
		for (Shape shape:shapeList){
			((HTMLAdatativeFontSizeWidget) shape).setFontSize();
		}
		for (Shape shape:shapeList){
			((HTMLAdatativeFontSizeWidget) shape).setMargin();
		}
		

	
	
	}

	

	
}
