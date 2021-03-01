/*******************************************************************************
 * Copyright 2021 Frank Zillus
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
/**
 *  (c)2010-2021 Frank Zillus mobile datensysteme
 */

package com.zillus.coronadiary.ui.popup;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class RemoveDialog extends Dialog
{

	/** The on ok listener. */
	private Runnable onOkListener;
	
	/** The on cancel listener. */
	private Runnable onCancelListener;
	
	/**
	 *
	 */
	public RemoveDialog()
	{
		super();
		this.initUI();
	}
	
	/**
	 * Sets the text.
	 *
	 * @param title
	 *            the title
	 * @return the my dialog
	 */
	public RemoveDialog setText(final String title)
	{
		this.lblTitel.setText("Entferne " + title + " ?");
		return this;
	}
	
	/**
	 * On ok.
	 *
	 * @param listener
	 *            the listener
	 * @return the my dialog
	 */
	public RemoveDialog setOkListener(final Runnable listener)
	{
		this.onOkListener = listener;
		return this;
	}
	
	/**
	 * On cancel.
	 *
	 * @param listener
	 *            the listener
	 * @return the my dialog
	 */
	public RemoveDialog setCancelListener(final Runnable listener)
	{
		this.onCancelListener = listener;
		return this;
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnOK}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnOK_onClick(final ClickEvent<Button> event)
	{
		if(this.onOkListener != null)
		{
			this.onOkListener.run();
		}
		this.close();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnCancel}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnCancel_onClick(final ClickEvent<Button> event)
	{
		if(this.onCancelListener != null)
		{
			this.onCancelListener.run();
		}
		this.close();
	}

	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.verticalLayout   = new VerticalLayout();
		this.lblTitel         = new Label();
		this.horizontalLayout = new HorizontalLayout();
		this.btnOK            = new Button();
		this.btnCancel        = new Button();
		
		this.setCloseOnEsc(false);
		this.setCloseOnOutsideClick(false);
		this.verticalLayout.setSpacing(false);
		this.verticalLayout.setPadding(false);
		this.lblTitel.setText("Label");
		this.lblTitel.getStyle().set("font-size", "x-large");
		this.btnOK.setText("Delete");
		this.btnOK.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
		this.btnOK.setIcon(VaadinIcon.TRASH.create());
		this.btnCancel.setText("Cancel");
		this.btnCancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
		this.btnCancel.setIcon(IronIcons.CANCEL.create());
		
		this.btnOK.setWidthFull();
		this.btnOK.setHeight(null);
		this.btnCancel.setWidthFull();
		this.btnCancel.setHeight(null);
		this.horizontalLayout.add(this.btnOK, this.btnCancel);
		this.horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.STRETCH, this.btnCancel);
		this.lblTitel.setSizeUndefined();
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.verticalLayout.add(this.lblTitel, this.horizontalLayout);
		this.verticalLayout.setSizeUndefined();
		this.add(this.verticalLayout);
		this.setWidth("50%");
		this.setHeight(null);
		
		this.btnOK.addClickListener(this::btnOK_onClick);
		this.btnCancel.addClickListener(this::btnCancel_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Button           btnOK, btnCancel;
	private VerticalLayout   verticalLayout;
	private HorizontalLayout horizontalLayout;
	private Label            lblTitel;
	// </generated-code>

}
