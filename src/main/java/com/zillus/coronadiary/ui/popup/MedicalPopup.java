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

package com.zillus.coronadiary.ui.popup;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.rapidclipse.framework.server.data.converter.ConverterBuilder;
import com.rapidclipse.framework.server.resources.CaptionUtils;
import com.rapidclipse.framework.server.ui.ItemLabelGeneratorFactory;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.FormItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.domain.enums.Profession;


public class MedicalPopup extends Dialog
{

	/** The medical entity. */
	private final MedicalEntity medicalEntity;

	/** The on oklistener. */
	private Runnable onOklistener;
	
	/**
	 *
	 */
	public MedicalPopup()
	{
		super();
		this.initUI();
		this.initCombos();
		this.medicalEntity = new MedicalEntity();
		this.initBinder();
	}
	
	/**
	 * Inits the binder.
	 */
	private void initBinder()
	{
		this.binder.readBean(this.medicalEntity);
		this.binder.addStatusChangeListener(event -> {
			final boolean isValid    = event.getBinder().isValid();
			final boolean hasChanges = event.getBinder().hasChanges();

			this.btnSave.setEnabled(hasChanges && isValid);
		});
	}

	/**
	 * Save binder.
	 */
	private void saveBinder()
	{
		try
		{
			this.binder.writeBean(this.medicalEntity);
			PersonDAO.addEntity(this.medicalEntity);
			this.onOklistener.run();
			this.close();
		}
		catch(final ValidationException e)
		{
			Notification.show(e.getMessage(), 3000, Position.BOTTOM_CENTER);
		}
	}
	
	/**
	 * Inits the combos.
	 */
	private void initCombos()
	{
		this.cmbProfession.setItems(Profession.values());
	}
	
	/**
	 * Sets the saved callback.
	 *
	 * @param listener
	 *            the listener
	 * @return the medical popup
	 */
	public MedicalPopup setSavedCallback(final Runnable listener)
	{
		this.onOklistener = listener;
		return this;
	}

	/**
	 * Event handler delegate method for
	 * the {@link Button} {@link #btnCancel}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnCancel_onClick(final ClickEvent<Button> event)
	{
		this.close();
	}

	/**
	 * Event handler delegate method for the {@link Button} {@link #btnSave}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnSave_onClick(final ClickEvent<Button> event)
	{
		this.saveBinder();
		this.close();
	}

	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.verticalLayout         = new VerticalLayout();
		this.horizontalLayoutBtnBar = new HorizontalLayout();
		this.btnSave                = new Button();
		this.btnCancel              = new Button();
		this.form                   = new FormLayout();
		this.formItem               = new FormItem();
		this.lblName                = new Label();
		this.txtName                = new TextField();
		this.formItem2              = new FormItem();
		this.lblAdress1             = new Label();
		this.txtAdress1             = new TextField();
		this.formItem3              = new FormItem();
		this.lblAdress2             = new Label();
		this.txtAdress2             = new TextField();
		this.formItem4              = new FormItem();
		this.lblCity                = new Label();
		this.txtCity                = new TextField();
		this.formItem5              = new FormItem();
		this.lblZipCode             = new Label();
		this.nrZipCode              = new NumberField();
		this.formItem6              = new FormItem();
		this.lblCountry             = new Label();
		this.txtCountry             = new TextField();
		this.formItem7              = new FormItem();
		this.lblProfession          = new Label();
		this.cmbProfession          = new ComboBox<>();
		this.binder                 = new Binder<>();
		
		this.verticalLayout.setPadding(false);
		this.btnSave.setText("Save");
		this.btnSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.btnSave.setIcon(IronIcons.SAVE.create());
		this.btnCancel.setText("Cancel");
		this.btnCancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		this.btnCancel.setIcon(IronIcons.CANCEL.create());
		this.form
			.setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP));
		this.lblName.setText("name");
		this.lblAdress1.setText("Adress");
		this.lblAdress2.setText("Adress 2");
		this.lblCity.setText("City");
		this.lblZipCode.setText("Zip Code");
		this.lblCountry.setText("Country");
		this.lblProfession.setText("profession");
		this.cmbProfession.setItemLabelGenerator(ItemLabelGeneratorFactory.NonNull(CaptionUtils::resolveCaption));
		
		this.binder.forField(this.txtName).asRequired().withNullRepresentation("").bind(MedicalEntity::getName,
			MedicalEntity::setName);
		this.binder.forField(this.txtAdress1).withNullRepresentation("").bind(MedicalEntity::getAdress1,
			MedicalEntity::setAdress1);
		this.binder.forField(this.txtAdress2).withNullRepresentation("").bind(MedicalEntity::getAdress2,
			MedicalEntity::setAdress2);
		this.binder.forField(this.txtCity).withNullRepresentation("").bind(MedicalEntity::getCity,
			MedicalEntity::setCity);
		this.binder.forField(this.nrZipCode).withConverter(ConverterBuilder.DoubleToInteger().build())
			.bind(MedicalEntity::getZipCode, MedicalEntity::setZipCode);
		this.binder.forField(this.txtCountry).withNullRepresentation("").bind(MedicalEntity::getCountry,
			MedicalEntity::setCountry);
		this.binder.forField(this.cmbProfession).bind(MedicalEntity::getProfession, MedicalEntity::setProfession);
		
		this.btnSave.setWidthFull();
		this.btnSave.setHeight(null);
		this.btnCancel.setWidthFull();
		this.btnCancel.setHeight(null);
		this.horizontalLayoutBtnBar.add(this.btnSave, this.btnCancel);
		this.lblName.setSizeUndefined();
		this.lblName.getElement().setAttribute("slot", "label");
		this.txtName.setWidthFull();
		this.txtName.setHeight(null);
		this.formItem.add(this.lblName, this.txtName);
		this.lblAdress1.setSizeUndefined();
		this.lblAdress1.getElement().setAttribute("slot", "label");
		this.txtAdress1.setWidthFull();
		this.txtAdress1.setHeight(null);
		this.formItem2.add(this.lblAdress1, this.txtAdress1);
		this.lblAdress2.setSizeUndefined();
		this.lblAdress2.getElement().setAttribute("slot", "label");
		this.txtAdress2.setWidthFull();
		this.txtAdress2.setHeight(null);
		this.formItem3.add(this.lblAdress2, this.txtAdress2);
		this.lblCity.setSizeUndefined();
		this.lblCity.getElement().setAttribute("slot", "label");
		this.txtCity.setWidthFull();
		this.txtCity.setHeight(null);
		this.formItem4.add(this.lblCity, this.txtCity);
		this.lblZipCode.setSizeUndefined();
		this.lblZipCode.getElement().setAttribute("slot", "label");
		this.nrZipCode.setWidthFull();
		this.nrZipCode.setHeight(null);
		this.formItem5.add(this.lblZipCode, this.nrZipCode);
		this.lblCountry.setSizeUndefined();
		this.lblCountry.getElement().setAttribute("slot", "label");
		this.txtCountry.setWidthFull();
		this.txtCountry.setHeight(null);
		this.formItem6.add(this.lblCountry, this.txtCountry);
		this.lblProfession.setSizeUndefined();
		this.lblProfession.getElement().setAttribute("slot", "label");
		this.cmbProfession.setWidthFull();
		this.cmbProfession.setHeight(null);
		this.formItem7.add(this.lblProfession, this.cmbProfession);
		this.form.add(this.formItem, this.formItem2, this.formItem3, this.formItem4, this.formItem5, this.formItem6,
			this.formItem7);
		this.horizontalLayoutBtnBar.setWidthFull();
		this.horizontalLayoutBtnBar.setHeight(null);
		this.form.setSizeUndefined();
		this.verticalLayout.add(this.horizontalLayoutBtnBar, this.form);
		this.verticalLayout.setSizeUndefined();
		this.add(this.verticalLayout);
		this.setWidth("90%");
		this.setHeight("80%");
		
		this.btnSave.addClickListener(this::btnSave_onClick);
		this.btnCancel.addClickListener(this::btnCancel_onClick);
	} // </generated-code>

	// <generated-code name="variables">
	private FormLayout            form;
	private Button                btnSave, btnCancel;
	private NumberField           nrZipCode;
	private VerticalLayout        verticalLayout;
	private HorizontalLayout      horizontalLayoutBtnBar;
	private Label                 lblName, lblAdress1, lblAdress2, lblCity, lblZipCode, lblCountry, lblProfession;
	private ComboBox<Profession>  cmbProfession;
	private TextField             txtName, txtAdress1, txtAdress2, txtCity, txtCountry;
	private FormItem              formItem, formItem2, formItem3, formItem4, formItem5, formItem6, formItem7;
	private Binder<MedicalEntity> binder;
	// </generated-code>

}
