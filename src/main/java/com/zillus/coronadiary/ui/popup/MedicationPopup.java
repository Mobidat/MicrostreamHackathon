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
import com.rapidclipse.framework.server.resources.CaptionUtils;
import com.rapidclipse.framework.server.ui.ItemLabelGeneratorFactory;
import com.vaadin.flow.component.AbstractField.ComponentValueChangeEvent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasValue;
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
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.dal.TreatmentDAO;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.domain.MedicationEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.enums.Medication;


public class MedicationPopup extends Dialog
{
	
	/** The medication entity. */
	private MedicationEntity medicationEntity;
	
	/** The on oklistener. */
	private Runnable onOklistener;
	
	/** The medical entity. */
	private MedicalEntity medicalEntity;
	
	/** The patient entity. */
	private final PatientEntity patientEntity;
	
	/**
	 *
	 */
	public MedicationPopup()
	{
		super();
		this.initUI();
		this.initCombos();
		this.patientEntity = UI.getCurrent().getSession().getAttribute(PatientEntity.class);
		this.initBinder();
	}
	
	/**
	 * Sets the medication entity.
	 *
	 * @param medication
	 *            the medication
	 * @return the medication popup
	 */
	public MedicationPopup setMedicationEntity(final MedicationEntity medication)
	{
		this.medicationEntity = medication;
		this.binder.readBean(this.medicationEntity);
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
		this.cmbMedication.setItems(Medication.values());
		this.cmbMedical.setItems(PersonDAO.findAllMedicals());
		this.dateDate.setValue(LocalDate.now());
	}
	
	/**
	 * Save binder.
	 */
	private void saveBinder()
	{
		try
		{
			this.binder.writeBean(this.medicationEntity);
			
			if(this.medicalEntity != null)
			{
				this.medicationEntity.setMedicalId(this.medicalEntity.getViewId());
			}
			
			if(this.patientEntity != null)
			{
				this.medicationEntity.setPatientId(this.patientEntity.getViewId());
			}
			
			TreatmentDAO.addEntity(this.medicationEntity);
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
	 * @return the medication popup
	 */
	public MedicationPopup setSavedCallback(final Runnable listener)
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
	
	/**
	 * Event handler delegate method for the {@link ComboBox} {@link #cmbMedical}.
	 *
	 * @see HasValue.ValueChangeListener#valueChanged(HasValue.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbMedical_valueChanged(final ComponentValueChangeEvent<ComboBox<MedicalEntity>, MedicalEntity> event)
	{
		this.medicalEntity = event.getValue();
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
		this.cmbMedication          = new ComboBox<>();
		this.formItem4              = new FormItem();
		this.lblDose                = new Label();
		this.nrDose                 = new NumberField();
		this.formItem5              = new FormItem();
		this.lblMedical             = new Label();
		this.cmbMedical             = new ComboBox<>();
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
		this.lblDate.setText("Medication Date");
		this.dateDate.setRequired(true);
		this.dateDate.setRequiredIndicatorVisible(true);
		this.dateDate.setPlaceholder("Date");
		this.dateDate.setInitialPosition(LocalDate.of(2021, Month.MARCH, 1));
		this.lblName.setText("Medication");
		this.txtName.setClearButtonVisible(true);
		this.lblType.setText("Medication Type");
		this.cmbMedication.setItemLabelGenerator(ItemLabelGeneratorFactory.NonNull(CaptionUtils::resolveCaption));
		this.lblDose.setText("Medication Dose 1-10");
		this.nrDose.setClearButtonVisible(true);
		this.lblMedical.setText("Your medical advisor");
		this.cmbMedical.setItemLabelGenerator(
			ItemLabelGeneratorFactory.NonNull(v -> CaptionUtils.resolveCaption(v, "{%name} in {%city}")));

		this.binder.forField(this.dateDate).bind(MedicationEntity::getDate, MedicationEntity::setDate);
		this.binder.forField(this.txtName).withNullRepresentation("").bind(MedicationEntity::getName,
			MedicationEntity::setName);
		this.binder.forField(this.nrDose).withConverter(ConverterBuilder.DoubleToInteger().build())
			.bind(MedicationEntity::getDose, MedicationEntity::setDose);
		this.binder.forField(this.cmbMedication).bind(MedicationEntity::getMedication, MedicationEntity::setMedication);

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
		this.cmbMedication.setWidthFull();
		this.cmbMedication.setHeight(null);
		this.formItem3.add(this.lblType, this.cmbMedication);
		this.lblDose.setSizeUndefined();
		this.lblDose.getElement().setAttribute("slot", "label");
		this.nrDose.setWidthFull();
		this.nrDose.setHeight(null);
		this.formItem4.add(this.lblDose, this.nrDose);
		this.lblMedical.setSizeUndefined();
		this.lblMedical.getElement().setAttribute("slot", "label");
		this.cmbMedical.setWidthFull();
		this.cmbMedical.setHeight(null);
		this.formItem5.add(this.lblMedical, this.cmbMedical);
		this.form.add(this.formItem, this.formItem2, this.formItem3, this.formItem4, this.formItem5);
		this.horizontalLayoutBtnBar.setWidthFull();
		this.horizontalLayoutBtnBar.setHeight(null);
		this.form.setWidthFull();
		this.form.setHeight(null);
		this.verticalLayout.add(this.horizontalLayoutBtnBar, this.form);
		this.verticalLayout.setSizeUndefined();
		this.add(this.verticalLayout);
		this.setWidth("90%");
		this.setHeight("80%");

		this.btnSave.addClickListener(this::btnSave_onClick);
		this.btnCancel.addClickListener(this::btnCancel_onClick);
		this.cmbMedical.addValueChangeListener(this::cmbMedical_valueChanged);
	} // </generated-code>
	
	// <generated-code name="variables">
	private FormLayout               form;
	private ComboBox<Medication>     cmbMedication;
	private Button                   btnSave, btnCancel;
	private DatePicker               dateDate;
	private Binder<MedicationEntity> binder;
	private NumberField              nrDose;
	private VerticalLayout           verticalLayout;
	private HorizontalLayout         horizontalLayoutBtnBar;
	private Label                    lblDate, lblName, lblType, lblDose, lblMedical;
	private ComboBox<MedicalEntity>  cmbMedical;
	private TextField                txtName;
	private FormItem                 formItem, formItem2, formItem3, formItem4, formItem5;
	// </generated-code>
	
}
