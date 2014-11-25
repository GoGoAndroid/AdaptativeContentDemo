package com.gosimple.adaptativetext.client.fonts_util;


import com.google.gwt.user.client.ui.RootPanel;
import com.gosimple.adaptativetext.client.shape_utils.tools.Size;
import com.gosimple.adaptativetext.client.ui.HTMLAdatativeFontSizeWidget;

public class FontsUtils {

	static RootPanel testZone=RootPanel.get("testZone") ;
	static double sizeInTest=12;
	
	public static Size get12pxSize(String htmlText){
	
		testZone.getElement().setInnerHTML(htmlText);
		return new Size(testZone.getElement().getOffsetWidth(),testZone.getElement().getOffsetHeight());		
		
	}
	public static double gettFontSize(HTMLAdatativeFontSizeWidget  widget){
		
		Size size12px =new Size(widget.getWidth(),widget.getHeight());
		

		Size expectedSize=new Size(widget.getOffsetWidth(),widget.getOffsetHeight());
		return sizeInTest*size12px.rattioTo(expectedSize);	
		
	}
	
	public static int  getWidth(String htmlContent){
		testZone.getElement().setInnerHTML(htmlContent);
		return testZone.getElement().getOffsetWidth();
	}
	public static int  getHeight(String htmlContent){
		testZone.getElement().setInnerHTML(htmlContent);
		return testZone.getElement().getOffsetHeight();
	}
}
