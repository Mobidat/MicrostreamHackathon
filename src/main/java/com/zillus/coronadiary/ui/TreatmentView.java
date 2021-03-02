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

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.rapidclipse.framework.server.data.renderer.RenderedComponent;
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
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.Route;
import com.zillus.coronadiary.HasTitle;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.dal.TreatmentDAO;
import com.zillus.coronadiary.domain.AbstractTreatmentEntity;
import com.zillus.coronadiary.domain.MedicationEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.SymptomEntity;
import com.zillus.coronadiary.domain.TestingEntity;
import com.zillus.coronadiary.domain.VaccinationEntity;
import com.zillus.coronadiary.ui.gencolumn.GenColTreatmentDetail;
import com.zillus.coronadiary.ui.popup.MedicationPopup;
import com.zillus.coronadiary.ui.popup.SymptomPopup;
import com.zillus.coronadiary.ui.popup.TestingPopup;
import com.zillus.coronadiary.ui.popup.VaccinationPopup;


@Route(value = "treatment", layout = MainLayout.class)
public class TreatmentView extends VerticalLayout implements HasTitle
{
	
	/** The patient entity. */
	private PatientEntity patientEntity;
	
	public TreatmentView()
	{
		super();
		this.initUI();
		this.initCombos();
		this.initView();
	}

	/**
	 * Inits the view.
	 */
	private void initView()
	{
		this.patientEntity = UI.getCurrent().getSession().getAttribute(PatientEntity.class);

		if(this.patientEntity != null)
		{
			this.refresh(this.patientEntity);
		}
		else
		{
			this.resetDialog();
		}
	}

	/**
	 * Creates the symptom.
	 */
	private void createSymptom()
	{
		new SymptomPopup()
			.setSymptomEntity(new SymptomEntity())
			.setSavedCallback(() -> {
				this.refresh(this.patientEntity);
			}).open();
	}

	/**
	 * Creates the testing.
	 */
	private void createTesting()
	{
		new TestingPopup()
			.setTestingEntity(new TestingEntity())
			.setSavedCallback(() -> {
				this.refresh(this.patientEntity);
			}).open();
	}

	/**
	 * Creates the medication.
	 */
	private void createMedication()
	{
		new MedicationPopup()
			.setMedicationEntity(new MedicationEntity())
			.setSavedCallback(() -> {
				this.refresh(this.patientEntity);
			}).open();
	}

	/**
	 * Creates the vaccination.
	 */
	private void createVaccination()
	{
		new VaccinationPopup()
			.setVaccinationEntity(new VaccinationEntity())
			.setSavedCallback(() -> {
				this.refresh(this.patientEntity);
			}).open();
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	@Override
	public String getTitle()
	{
		return "Diary";
	}
	
	private void initCombos()
	{
		this.cmbPatient.setItems(PersonDAO.findAllPatients());
	}
	
	/**
	 * Reset dialog.
	 */
	private void resetDialog()
	{
		this.cmbPatient.clear();
		this.gridTreatment.getDataProvider().refreshAll();
	}
	
	/**
	 * Refresh.
	 *
	 * @param patientEntity
	 *            the patient entity
	 */
	public void refresh(final PatientEntity patientEntity)
	{
		this.gridTreatment.setItems(TreatmentDAO.getPatientTreatments(patientEntity.getViewId()));
		this.cmbPatient.setValue(patientEntity);
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #addSymptomBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void addSymptomBtn_onClick(final ClickEvent<Button> event)
	{
		this.createSymptom();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #addTestingBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void addTestingBtn_onClick(final ClickEvent<Button> event)
	{
		this.createTesting();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #addMedicationBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void addMedicationBtn_onClick(final ClickEvent<Button> event)
	{
		this.createMedication();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #addVaccinationBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void addVaccinationBtn_onClick(final ClickEvent<Button> event)
	{
		this.createVaccination();
	}
	
	/**
	 * Event handler delegate method for the {@link ComboBox} {@link #cmbPatient}.
	 *
	 * @see HasValue.ValueChangeListener#valueChanged(HasValue.ValueChangeEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmbPatient_valueChanged(final ComponentValueChangeEvent<ComboBox<PatientEntity>, PatientEntity> event)
	{
		this.patientEntity = event.getValue();
		if(this.patientEntity != null)
		{
			UI.getCurrent().getSession().setAttribute(PatientEntity.class, this.patientEntity);
			this.refresh(this.patientEntity);
		}
		else
		{
			UI.getCurrent().getSession().setAttribute(PatientEntity.class, null);
		}
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnCancel}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnCancel_onClick(final ClickEvent<Button> event)
	{
		UI.getCurrent().getPage().getHistory().back();
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.cmbPatient        = new ComboBox<>();
		this.horizontalLayout  = new HorizontalLayout();
		this.addSymptomBtn     = new Button();
		this.addTestingBtn     = new Button();
		this.addMedicationBtn  = new Button();
		this.addVaccinationBtn = new Button();
		this.btnCancel         = new Button();
		this.gridTreatment     = new Grid<>(AbstractTreatmentEntity.class, false);

		this.cmbPatient.setLabel("Patient");
		this.cmbPatient.setItemLabelGenerator(
			ItemLabelGeneratorFactory.NonNull(v -> CaptionUtils.resolveCaption(v, "{%name} {%birthday} from {%city}")));
		this.addSymptomBtn.setText("Symptom");
		this.addSymptomBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.addSymptomBtn.setIcon(IronIcons.ADD.create());
		this.addTestingBtn.setText("Testing");
		this.addTestingBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.addTestingBtn.setIcon(IronIcons.ADD.create());
		this.addMedicationBtn.setText("Medication");
		this.addMedicationBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.addMedicationBtn.setIcon(IronIcons.ADD.create());
		this.addVaccinationBtn.setText("Vaccination");
		this.addVaccinationBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.addVaccinationBtn.setIcon(IronIcons.ADD.create());
		this.btnCancel.setText("Cancel");
		this.btnCancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		this.btnCancel.setIcon(IronIcons.CANCEL.create());
		this.gridTreatment.setPageSize(60);
		this.gridTreatment.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COMPACT);
		this.gridTreatment
			.addColumn(new LocalDateRenderer<>(AbstractTreatmentEntity::getDate,
				DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG), "No date"))
			.setKey("date").setHeader("Date").setFrozen(true).setSortable(true).setAutoWidth(true).setFlexGrow(0)
			.setTextAlign(ColumnTextAlign.END);
		this.gridTreatment.addColumn(AbstractTreatmentEntity::getName).setKey("name").setHeader("Name")
			.setSortable(true);
		this.gridTreatment.addColumn(RenderedComponent.Renderer(GenColTreatmentDetail::new)).setKey("renderer")
			.setHeader("...").setSortable(false).setAutoWidth(true).setFlexGrow(0);
		this.gridTreatment.setSelectionMode(Grid.SelectionMode.SINGLE);

		this.addSymptomBtn.setWidthFull();
		this.addSymptomBtn.setHeight(null);
		this.addTestingBtn.setWidthFull();
		this.addTestingBtn.setHeight(null);
		this.addMedicationBtn.setWidthFull();
		this.addMedicationBtn.setHeight(null);
		this.addVaccinationBtn.setWidthFull();
		this.addVaccinationBtn.setHeight(null);
		this.btnCancel.setWidthFull();
		this.btnCancel.setHeight(null);
		this.horizontalLayout.add(this.addSymptomBtn, this.addTestingBtn, this.addMedicationBtn, this.addVaccinationBtn,
			this.btnCancel);
		this.cmbPatient.setWidthFull();
		this.cmbPatient.setHeight(null);
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.gridTreatment.setWidthFull();
		this.gridTreatment.setHeight(null);
		this.add(this.cmbPatient, this.horizontalLayout, this.gridTreatment);
		this.setFlexGrow(1.0, this.gridTreatment);
		this.setSizeUndefined();

		this.cmbPatient.addValueChangeListener(this::cmbPatient_valueChanged);
		this.addSymptomBtn.addClickListener(this::addSymptomBtn_onClick);
		this.addTestingBtn.addClickListener(this::addTestingBtn_onClick);
		this.addMedicationBtn.addClickListener(this::addMedicationBtn_onClick);
		this.addVaccinationBtn.addClickListener(this::addVaccinationBtn_onClick);
		this.btnCancel.addClickListener(this::btnCancel_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private ComboBox<PatientEntity>       cmbPatient;
	private Button                        addSymptomBtn, addTestingBtn, addMedicationBtn, addVaccinationBtn, btnCancel;
	private HorizontalLayout              horizontalLayout;
	private Grid<AbstractTreatmentEntity> gridTreatment;
	// </generated-code>
	
}
