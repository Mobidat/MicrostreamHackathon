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

package com.zillus.coronadiary.ui;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.rapidclipse.framework.server.data.renderer.CaptionRenderer;
import com.rapidclipse.framework.server.data.renderer.RenderedComponent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.zillus.coronadiary.HasTitle;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.ui.gencolumn.GenColMedicalDetail;
import com.zillus.coronadiary.ui.popup.MedicalPopup;


@Route(value = "medical", layout = MainLayout.class)
public class MedicalView extends VerticalLayout implements HasTitle
{
	public MedicalView()
	{
		super();
		this.initUI();
		this.refresh();
	}
	
	@Override
	public String getTitle()
	{
		return "Medical";
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #addBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void addBtn_onClick(final ClickEvent<Button> event)
	{
		new MedicalPopup()
			.setSavedCallback(() -> {
				this.refresh();
			}).open();
	}
	
	/**
	 * Refresh.
	 */
	public void refresh()
	{
		this.gridMedical.setItems(PersonDAO.findAllMedicals());
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #cancelBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cancelBtn_onClick(final ClickEvent<Button> event)
	{
		UI.getCurrent().getPage().getHistory().back();
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.horizontalLayout = new HorizontalLayout();
		this.addBtn           = new Button();
		this.cancelBtn        = new Button();
		this.gridMedical      = new Grid<>(MedicalEntity.class, false);

		this.addBtn.setText("Medical");
		this.addBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.addBtn.setIcon(IronIcons.ADD.create());
		this.cancelBtn.setText("Cancel");
		this.cancelBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
		this.cancelBtn.setIcon(IronIcons.CANCEL.create());
		this.gridMedical.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
		this.gridMedical.addColumn(MedicalEntity::getName).setKey("name").setHeader("Name").setFrozen(true)
			.setSortable(true).setAutoWidth(true).setFlexGrow(0);
		this.gridMedical.addColumn(new CaptionRenderer<>(MedicalEntity::getProfession)).setKey("profession")
			.setHeader("Profession").setSortable(true);
		this.gridMedical.addColumn(MedicalEntity::getAdress1).setKey("adress1").setHeader("Adress").setSortable(true);
		this.gridMedical.addColumn(MedicalEntity::getAdress2).setKey("adress2").setHeader("Adress 2").setSortable(true);
		this.gridMedical.addColumn(MedicalEntity::getCity).setKey("city").setHeader("City").setSortable(true);
		this.gridMedical.addColumn(MedicalEntity::getZipCode).setKey("zipCode").setHeader("Zip Code").setSortable(true);
		this.gridMedical.addColumn(RenderedComponent.Renderer(GenColMedicalDetail::new)).setKey("renderer")
			.setHeader("...")
			.setSortable(false).setAutoWidth(true).setFlexGrow(0);
		this.gridMedical.setSelectionMode(Grid.SelectionMode.SINGLE);

		this.addBtn.setWidthFull();
		this.addBtn.setHeight(null);
		this.cancelBtn.setWidthFull();
		this.cancelBtn.setHeight(null);
		this.horizontalLayout.add(this.addBtn, this.cancelBtn);
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.gridMedical.setWidthFull();
		this.gridMedical.setHeight(null);
		this.add(this.horizontalLayout, this.gridMedical);
		this.setFlexGrow(1.0, this.gridMedical);
		this.setWidthFull();
		this.setHeight(null);

		this.addBtn.addClickListener(this::addBtn_onClick);
		this.cancelBtn.addClickListener(this::cancelBtn_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Button              addBtn, cancelBtn;
	private HorizontalLayout    horizontalLayout;
	private Grid<MedicalEntity> gridMedical;
	// </generated-code>
	
}
