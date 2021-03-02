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

package com.zillus.coronadiary.ui.gencolumn;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.rapidclipse.framework.server.data.renderer.RenderedComponent;
import com.rapidclipse.framework.server.ui.UIUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.zillus.coronadiary.dal.TreatmentDAO;
import com.zillus.coronadiary.domain.AbstractTreatmentEntity;
import com.zillus.coronadiary.domain.MedicationEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.SymptomEntity;
import com.zillus.coronadiary.domain.TestingEntity;
import com.zillus.coronadiary.domain.VaccinationEntity;
import com.zillus.coronadiary.ui.TreatmentView;
import com.zillus.coronadiary.ui.popup.MedicationPopup;
import com.zillus.coronadiary.ui.popup.RemoveDialog;
import com.zillus.coronadiary.ui.popup.SymptomPopup;
import com.zillus.coronadiary.ui.popup.TestingPopup;
import com.zillus.coronadiary.ui.popup.VaccinationPopup;


public class GenColTreatmentDetail extends HorizontalLayout implements RenderedComponent<AbstractTreatmentEntity>
{
	
	/** The treatment. */
	private AbstractTreatmentEntity treatment;
	
	/** The patient entity. */
	private final PatientEntity patientEntity;
	
	/**
	 *
	 */
	public GenColTreatmentDetail()
	{
		super();
		this.initUI();
		this.patientEntity = UI.getCurrent().getSession().getAttribute(PatientEntity.class);
	}
	
	@Override
	public void renderComponent(final AbstractTreatmentEntity value)
	{
		this.treatment = value;
	}
	
	/**
	 * Creates the treatment.
	 */
	private void editTreatment()
	{
		final String name = this.treatment.getClass().getSimpleName();
		
		switch(name)
		{
			case "MedicationEntity":
				final MedicationEntity medicatinEntity = (MedicationEntity)this.treatment;
				new MedicationPopup()
					.setMedicationEntity(medicatinEntity)
					.setCMBMedical(medicatinEntity)
					.setSavedCallback(() -> {
						final TreatmentView treatmentView = UIUtils.getNextParent(this, TreatmentView.class);
						treatmentView.refresh(this.patientEntity);
					}).open();
			break;
		
			case "SymptomEntity":
				new SymptomPopup()
					.setSymptomEntity((SymptomEntity)this.treatment)
					.setSavedCallback(() -> {
						final TreatmentView treatmentView = UIUtils.getNextParent(this, TreatmentView.class);
						treatmentView.refresh(this.patientEntity);
					}).open();
			break;
		
			case "TestingEntity":
				final TestingEntity testEntity = (TestingEntity)this.treatment;
				new TestingPopup()
					.setTestingEntity(testEntity)
					.setCMBMedical(testEntity)
					.setSavedCallback(() -> {
						final TreatmentView treatmentView = UIUtils.getNextParent(this, TreatmentView.class);
						treatmentView.refresh(this.patientEntity);
					}).open();
			break;
		
			case "vaccinationEntity":
				
				final VaccinationEntity vaccEntity = (VaccinationEntity)this.treatment;
				new VaccinationPopup()
					.setVaccinationEntity(vaccEntity)
					.setCMBMedical(vaccEntity)
					.setSavedCallback(() -> {
						final TreatmentView treatmentView = UIUtils.getNextParent(this, TreatmentView.class);
						treatmentView.refresh(this.patientEntity);
					}).open();
			break;
		
			default:
				System.out.println("no match");
		}
	}
	
	/**
	 * Show remove dialog.
	 */
	private void showRemoveDialog()
	{
		final RemoveDialog dialog = new RemoveDialog()
			.setText(this.treatment.getName() + " on " + this.treatment.getDate())
			.setOkListener(() -> {
				
				final TreatmentView treatmentView = UIUtils.getNextParent(this, TreatmentView.class);
				TreatmentDAO.removeEntity(this.treatment);
				treatmentView.refresh(this.patientEntity);
			})
			.setCancelListener(() -> {});
		dialog.open();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnEdit}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnEdit_onClick(final ClickEvent<Button> event)
	{
		this.editTreatment();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnDelete}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnDelete_onClick(final ClickEvent<Button> event)
	{
		this.showRemoveDialog();
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.btnEdit   = new Button();
		this.btnDelete = new Button();
		
		this.setSpacing(false);
		this.btnEdit.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
		this.btnEdit.setIcon(IronIcons.SEARCH.create());
		this.btnDelete.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY,
			ButtonVariant.LUMO_ERROR);
		this.btnDelete.setIcon(VaadinIcon.TRASH.create());
		
		this.btnEdit.setSizeUndefined();
		this.btnDelete.setSizeUndefined();
		this.add(this.btnEdit, this.btnDelete);
		this.setSizeUndefined();
		
		this.btnEdit.addClickListener(this::btnEdit_onClick);
		this.btnDelete.addClickListener(this::btnDelete_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Button btnEdit, btnDelete;
	// </generated-code>
	
}
