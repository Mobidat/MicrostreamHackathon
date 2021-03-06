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
import com.vaadin.flow.component.checkbox.Checkbox;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.dal.TreatmentDAO;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.VaccinationEntity;
import com.zillus.coronadiary.domain.enums.Vaccine;


public class VaccinationPopup extends Dialog
{

	/** The vaccination entity. */
	private VaccinationEntity vaccinationEntity;

	/** The on oklistener. */
	private Runnable onOklistener;

	/** The medical entity. */
	private MedicalEntity medicalEntity;

	/** The patient entity. */
	private final PatientEntity patientEntity;

	/**
	 *
	 */
	public VaccinationPopup()
	{
		super();
		this.initUI();
		this.initCombos();
		
		this.patientEntity = UI.getCurrent().getSession().getAttribute(PatientEntity.class);
		this.initBinder();
	}

	/**
	 * Sets the vaccination entity.
	 *
	 * @param vaccination
	 *            the vaccination
	 * @return the vaccination popup
	 */
	public VaccinationPopup setVaccinationEntity(final VaccinationEntity vaccination)
	{
		this.vaccinationEntity = vaccination;
		this.binder.readBean(this.vaccinationEntity);

		return this;
	}

	/**
	 * Sets the CMB medical.
	 *
	 * @param vaccination
	 *            the vaccination
	 * @return the vaccination popup
	 */
	public VaccinationPopup setCMBMedical(final VaccinationEntity vaccination)
	{
		this.cmbMedical.setValue((MedicalEntity)PersonDAO.findPerson(vaccination.getMedicalId()));
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
		this.cmbVaccine.setItems(Vaccine.values());
		this.cmbMedical.setItems(PersonDAO.findAllMedicals());
	}

	/**
	 * Save binder.
	 */
	private void saveBinder()
	{
		if(TreatmentDAO.isSaved(this.vaccinationEntity))
		{
			TreatmentDAO.removeEntity(this.vaccinationEntity);
		}

		try
		{
			this.binder.writeBean(this.vaccinationEntity);

			if(this.medicalEntity != null)
			{
				this.vaccinationEntity.setMedicalId(this.medicalEntity.getViewId());
			}

			if(this.patientEntity != null)
			{
				this.vaccinationEntity.setPatientId(this.patientEntity.getViewId());
			}
			TreatmentDAO.addEntity(this.vaccinationEntity);
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
	 * @return the vaccination popup
	 */
	public VaccinationPopup setSavedCallback(final Runnable listener)
	{
		this.onOklistener = listener;
		return this;
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
		this.cmbVaccine             = new ComboBox<>();
		this.formItem4              = new FormItem();
		this.lblBooster             = new Label();
		this.chkBooster             = new Checkbox();
		this.formItem5              = new FormItem();
		this.lblMedical             = new Label();
		this.cmbMedical             = new ComboBox<>();
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
		this.lblDate.setText("Vaccination Date");
		this.dateDate.setRequired(true);
		this.dateDate.setRequiredIndicatorVisible(true);
		this.lblName.setText("Vaccine");
		this.txtName.setClearButtonVisible(true);
		this.lblType.setText("Vaccine Type");
		this.cmbVaccine.setItemLabelGenerator(ItemLabelGeneratorFactory.NonNull(Vaccine::getCaption));
		this.lblBooster.setText("Is Booster");
		this.chkBooster.setLabel("Booster Vacc");
		this.lblMedical.setText("Your medical advisor");
		this.cmbMedical.setItemLabelGenerator(
			ItemLabelGeneratorFactory.NonNull(v -> CaptionUtils.resolveCaption(v, "{%name} in {%city}")));

		this.binder.forField(this.dateDate).asRequired().bind(VaccinationEntity::getDate, VaccinationEntity::setDate);
		this.binder.forField(this.txtName).withNullRepresentation("").bind(VaccinationEntity::getName,
			VaccinationEntity::setName);
		this.binder.forField(this.chkBooster).withNullRepresentation(false).bind(VaccinationEntity::getBooster,
			VaccinationEntity::setBooster);
		this.binder.forField(this.cmbVaccine).bind(VaccinationEntity::getVaccine, VaccinationEntity::setVaccine);

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
		this.cmbVaccine.setWidthFull();
		this.cmbVaccine.setHeight(null);
		this.formItem3.add(this.lblType, this.cmbVaccine);
		this.lblBooster.setSizeUndefined();
		this.lblBooster.getElement().setAttribute("slot", "label");
		this.chkBooster.setWidthFull();
		this.chkBooster.setHeight(null);
		this.formItem4.add(this.lblBooster, this.chkBooster);
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
	private FormLayout                form;
	private Checkbox                  chkBooster;
	private Button                    btnSave, btnCancel;
	private DatePicker                dateDate;
	private ComboBox<Vaccine>         cmbVaccine;
	private Binder<VaccinationEntity> binder;
	private VerticalLayout            verticalLayout;
	private HorizontalLayout          horizontalLayoutBtnBar;
	private Label                     lblDate, lblName, lblType, lblBooster, lblMedical;
	private ComboBox<MedicalEntity>   cmbMedical;
	private TextField                 txtName;
	private FormItem                  formItem, formItem2, formItem3, formItem4, formItem5;
	// </generated-code>

}
