package com.gosimple.adaptativetext.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.gosimple.adaptativetext.client.ui.ArrangedLayoutPanel;
import com.gosimple.adaptativetext.client.ui.HTMLAdatativeFontSizeWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AdatativeTextDemo implements EntryPoint {

	String textes[]=new String[]{
			"Proposition 1",
			"Deux choix :\nA ou \nB",
			"Je ne sais pas",
			"Peut être"
	};
	Map<TextArea,HTMLAdatativeFontSizeWidget> widgetsAndTextAreas;
	ArrangedLayoutPanel arrangedLayoutPanel;
	public void onModuleLoad() {
		widgetsAndTextAreas=new HashMap<TextArea,HTMLAdatativeFontSizeWidget>();
		for (int i=0;i<4;i++){
				
			HTMLAdatativeFontSizeWidget widget=new HTMLAdatativeFontSizeWidget(textes[i]);
			widget.htmlWidget.setStyleName("adatativeFontSizeWidget");
			
			
			TextArea textArea=new TextArea();
			textArea.setText(textes[i]);
			widgetsAndTextAreas.put( textArea,widget);
			
			textArea.addValueChangeHandler(new ValueChangeHandler<String>(){
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					widgetsAndTextAreas.get((TextArea) event.getSource()).setText(event.getValue());
					arrangedLayoutPanel.changeValues(widgetsAndTextAreas.values());	
					
				}});

		}
		
		arrangedLayoutPanel=new ArrangedLayoutPanel(widgetsAndTextAreas.values());
	
		LayoutPanel layoutPanel=new LayoutPanel();
		
		
		
		int height=0;
		for (TextArea textArea:widgetsAndTextAreas.keySet()){
			layoutPanel.add(textArea);
			
			layoutPanel.setWidgetLeftWidth(textArea, height, Unit.PCT, 25, Unit.PCT);
			arrangedLayoutPanel.setWidgetTopHeight(widgetsAndTextAreas.get(textArea), height, Unit.PCT, 25, Unit.PCT);
			height+=25;
			
		}
		LayoutPanel v=new LayoutPanel();
	
		
		v.add(arrangedLayoutPanel);
		v.add(layoutPanel);
		v.setWidgetTopHeight(arrangedLayoutPanel, 0, Unit.PCT, 75, Unit.PCT);
		v.setWidgetTopHeight(layoutPanel, 76, Unit.PCT, 24, Unit.PCT);
		
		
		RootLayoutPanel.get().add(v);

		
	
	}
}
