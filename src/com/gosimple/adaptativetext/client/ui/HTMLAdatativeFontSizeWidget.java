package com.gosimple.adaptativetext.client.ui;


import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ResizeLayoutPanel;
import com.gosimple.adaptativetext.client.fonts_util.FontsUtils;
import com.gosimple.adaptativetext.client.shape_utils.tools.Shape;

public  class HTMLAdatativeFontSizeWidget extends Composite implements HasText, HasClickHandlers,Shape{


	String text="";
	public String content="";
	public HTML htmlWidget;

	int width,height;
	ResizeLayoutPanel resizeLayoutPanel;

	public HTMLAdatativeFontSizeWidget() {
		htmlWidget=new HTML();
		commonSettings();
		setText("");
		
	}
	public HTMLAdatativeFontSizeWidget(String data) {
		super();
		htmlWidget=new HTML();
	
		commonSettings();
		setText(data);
	}
	

	public void commonSettings(){

		resizeLayoutPanel=new ResizeLayoutPanel();
		resizeLayoutPanel.add(htmlWidget);
		initWidget(resizeLayoutPanel);
	}


	@Override
	public String getText() {
		return text;
	}




	public  void setContent(){
		content=text.replaceAll("\n", "<br>");
		htmlWidget.setHTML("<div class=\"textContent\"><p>"+content+"</div>");
	}

	@Override
	public void setText(String text) {
		this.text=text;	
		setContent();
		width=FontsUtils.getWidth(content);
		height=FontsUtils.getHeight(content);
		if (text!=null && !text.equals("")){
			setFontSize() ;
		}
		
	
	}
	void setFontSize() {

		Element div=htmlWidget.getElement().getFirstChildElement();
		double fontSize=FontsUtils.gettFontSize(this);
		
		div.getStyle().setFontSize(fontSize*0.8,  Style.Unit.PX);

	

	}
	
	public void setMargin() {
		Element div=htmlWidget.getElement().getFirstChildElement();
		div.getStyle().setMarginLeft(	( getOffsetWidth()-div.getOffsetWidth())/2, Style.Unit.PX);
		div.getStyle().setMarginTop(	( getOffsetHeight()-div.getOffsetHeight())/2, Style.Unit.PX);
	}

	
	

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return htmlWidget.addClickHandler(handler);
	}
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public int getHeight() {
		return height;
	}


	

	


}
