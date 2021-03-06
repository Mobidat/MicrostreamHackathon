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

import java.time.LocalDate;
import java.time.Month;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.rapidclipse.framework.server.data.converter.ConverterBuilder;
import com.rapidclipse.framework.server.ui.ItemLabelGeneratorFactory;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import com.zillus.coronadiary.dal.TreatmentDAO;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.SymptomEntity;
import com.zillus.coronadiary.domain.enums.Symptoms;


public class SymptomPopup extends Dialog
{
	
	/** The symptom entity. */
	private SymptomEntity symptomEntity;
	
	/** The on oklistener. */
	private Runnable onOklistener;
	
	/** The patient entity. */
	private final PatientEntity patientEntity;
	
	/**
	 *
	 */
	public SymptomPopup()
	{
		super();
		this.initUI();
		this.initCombos();

		this.patientEntity = UI.getCurrent().getSession().getAttribute(PatientEntity.class);
		this.initBinder();
	}

	/**
	 * Sets the symptom entity.
	 *
	 * @param symptom
	 *            the symptom
	 * @return the symptom popup
	 */
	public SymptomPopup setSymptomEntity(final SymptomEntity symptom)
	{
		this.symptomEntity = symptom;
		this.binder.readBean(this.symptomEntity);
		
		return this;
	}
	
	/**
	 * Inits the binder.
	 */
	private void initBinder()
	{
		this.binder.addStatusChangeListener(event -> {
			final boolean isValid    = event.getBinder().isValid();
			final boolean hasChanges = event.getBinder().hasChanges();
			
			this.btnSave.setEnabled(hasChanges && isValid);
		});
	}
	
	/**
	 * Inits the combos.
	 */
	private void initCombos()
	{
		this.cmbSymptom.setItems(Symptoms.values());
	}
	
	/**
	 * Save binder.
	 */
	private void saveBinder()
	{
		if(TreatmentDAO.isSaved(this.symptomEntity))
		{
			TreatmentDAO.removeEntity(this.symptomEntity);
		}
		
		try
		{
			this.binder.writeBean(this.symptomEntity);
			
			if(this.patientEntity != null)
			{
				this.symptomEntity.setPatientId(this.patientEntity.getViewId());
			}
			
			TreatmentDAO.addEntity(this.symptomEntity);
			this.onOklistener.run();
			this.close();
		}
		catch(final ValidationException e)
		{
			Notification.show(e.getMessage(), 3000, Position.BOTTOM_CENTER);
		}
	}
	
	/**
	 * Sets the saved callback.
	 *
	 * @param listener
	 *            the listener
	 * @return the symptom popup
	 */
	public SymptomPopup setSavedCallback(final Runnable listener)
	{
		this.onOklistener = listener;
		return this;
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
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnCancel}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnCancel_onClick(final ClickEvent<Button> event)
	{
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
		this.lblDate                = new Label();
		this.dateDate               = new DatePicker();
		this.formItem2              = new FormItem();
		this.lblName                = new Label();
		this.txtName                = new TextField();
		this.formItem3              = new FormItem();
		this.lblType                = new Label();
		this.cmbSymptom             = new ComboBox<>();
		this.formItem4              = new FormItem();
		this.lblDaysDuration        = new Label();
		this.nrDaysDuration         = new NumberField();
		this.formItem5              = new FormItem();
		this.lblIntensity           = new Label();
		this.nrIntensity            = new NumberField();
		this.binder                 = new Binder<>();

		this.verticalLayout.setPadding(false);
		this.btnSave.setEnabled(false);
		this.btnSave.setText("Save");
		this.btnSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.btnSave.setIcon(IronIcons.SAVE.create());
		this.btnCancel.setText("Cancel");
		this.btnCancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		this.btnCancel.setIcon(IronIcons.CANCEL.create());
		this.form
			.setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP));
		this.lblDate.setText("Date");
		this.dateDate.setRequired(true);
		this.dateDate.setRequiredIndicatorVisible(true);
		this.lblName.setText("Symptom");
		this.txtName.setClearButtonVisible(true);
		this.lblType.setText("Symptom Type");
		this.cmbSymptom.setItemLabelGenerator(ItemLabelGeneratorFactory.NonNull(Symptoms::getCaption));
		this.lblDaysDuration.setText("Days of Duration");
		this.nrDaysDuration.setClearButtonVisible(true);
		this.lblIntensity.setText("Symptom Intensity");
		this.nrIntensity.setClearButtonVisible(true);

		this.binder.forField(this.txtName).withNullRepresentation("").bind(SymptomEntity::getName,
			SymptomEntity::setName);
		this.binder.forField(this.nrDaysDuration).withConverter(ConverterBuilder.DoubleToInteger().build())
			.bind(SymptomEntity::getDaysDuration, SymptomEntity::setDaysDuration);
		this.binder.forField(this.nrIntensity).withConverter(ConverterBuilder.DoubleToInteger().build())
			.bind(SymptomEntity::getIntensity, SymptomEntity::setIntensity);
		this.binder.forField(this.cmbSymptom).bind(SymptomEntity::getSymptoms, SymptomEntity::setSymptoms);
		this.binder.forField(this.dateDate).withNullRepresentation(LocalDate.of(2021, Month.MARCH, 1))
			.bind(SymptomEntity::getDate, SymptomEntity::setDate);

		this.btnSave.setWidthFull();
		this.btnSave.setHeight(null);
		this.btnCancel.setWidthFull();
		this.btnCancel.setHeight(null);
		this.horizontalLayoutBtnBar.add(this.btnSave, this.btnCancel);
		this.lblDate.setSizeUndefined();
		this.lblDate.getElement().setAttribute("slot", "label");
		this.dateDate.setWidthFull();
		this.dateDate.setHeight(null);
		this.formItem.add(this.lblDate, this.dateDate);
		this.lblName.setSizeUndefined();
		this.lblName.getElement().setAttribute("slot", "label");
		this.txtName.setWidthFull();
		this.txtName.setHeight(null);
		this.formItem2.add(this.lblName, this.txtName);
		this.lblType.setSizeUndefined();
		this.lblType.getElement().setAttribute("slot", "label");
		this.cmbSymptom.setWidthFull();
		this.cmbSymptom.setHeight(null);
		this.formItem3.add(this.lblType, this.cmbSymptom);
		this.lblDaysDuration.setSizeUndefined();
		this.lblDaysDuration.getElement().setAttribute("slot", "label");
		this.nrDaysDuration.setWidthFull();
		this.nrDaysDuration.setHeight(null);
		this.formItem4.add(this.lblDaysDuration, this.nrDaysDuration);
		this.lblIntensity.setSizeUndefined();
		this.lblIntensity.getElement().setAttribute("slot", "label");
		this.nrIntensity.setWidthFull();
		this.nrIntensity.setHeight(null);
		this.formItem5.add(this.lblIntensity, this.nrIntensity);
		this.form.add(this.formItem, this.formItem2, this.formItem3, this.formItem4, this.formItem5);
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
	private DatePicker            dateDate;
	private ComboBox<Symptoms>    cmbSymptom;
	private NumberField           nrDaysDuration, nrIntensity;
	private Binder<SymptomEntity> binder;
	private VerticalLayout        verticalLayout;
	private HorizontalLayout      horizontalLayoutBtnBar;
	private Label                 lblDate, lblName, lblType, lblDaysDuration, lblIntensity;
	private TextField             txtName;
	private FormItem              formItem, formItem2, formItem3, formItem4, formItem5;
	// </generated-code>
	
}
