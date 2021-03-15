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

import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.zillus.coronadiary.HasTitle;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.dal.RandomDAO;
import com.zillus.coronadiary.dal.TreatmentDAO;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.ui.popup.RemoveDialog;


@Route(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout implements HasTitle
{
	
	private static final int NUM_MEDICALS = 200;
	
	private static final int NUM_PATIENTS = 200;
	
	private static final int NUM_SYMPTOMS = 10;
	
	private static final int NUM_TESTINGS = 10;
	
	private static final int NUM_VACCINATION = 2;
	
	private static final int NUM_MEDICATION = 8;
	
	private static final int NUM_NULL = 0;
	
	public HomeView()
	{
		super();
		this.initUI();
		PersonDAO.findAll();
		TreatmentDAO.findAll();
		
		this.initData();
	}
	
	/**
	 * Inits the data.
	 */
	private void initData()
	{
		final int countAllPatients   = PersonDAO.countAllPatients();
		final int countAllMedicals   = PersonDAO.countAllMedicals();
		final int countAllTreatments = TreatmentDAO.countAllTreatments();
		
		// Label init
		this.lblPatients.setText(countAllPatients + " Patients");
		this.lblMedicals.setText(countAllMedicals + " Medicals");
		this.lblEntries.setText(countAllTreatments + " D-Entries");
		
		// create bottons init
		this.btnMedicals.setText(HomeView.NUM_MEDICALS + " new Medicals");
		this.btnPatients.setText(HomeView.NUM_PATIENTS + " new Patients");
		this.btnEntries.setText(countAllPatients
			* (HomeView.NUM_SYMPTOMS + HomeView.NUM_TESTINGS + HomeView.NUM_VACCINATION + HomeView.NUM_MEDICATION)
			+ " Entries");
		this.btnEntries.setEnabled(countAllMedicals > HomeView.NUM_NULL && countAllPatients > HomeView.NUM_NULL);
		
		// delete bottons init
		this.btnEntriesDelete.setEnabled(countAllTreatments > HomeView.NUM_NULL);
		this.btnPatientsDelete.setEnabled(countAllPatients > HomeView.NUM_NULL);
		this.btnMedicalsDelete.setEnabled(countAllMedicals > HomeView.NUM_NULL);
	}
	
	/**
	 * Delete all patients.
	 */
	private void deleteAllPatients()
	{
		final RemoveDialog dialog = new RemoveDialog()
			.setText("all the Patients")
			.setOkListener(() -> {
				PersonDAO.removeAllPatients();
				this.initData();
			})
			.setCancelListener(() -> {});
		dialog.open();
	}
	
	/**
	 * Delete all medicals.
	 */
	private void deleteAllMedicals()
	{
		final RemoveDialog dialog = new RemoveDialog()
			.setText("all the Medicals")
			.setOkListener(() -> {
				PersonDAO.removeAllMedicals();
				this.initData();
			})
			.setCancelListener(() -> {});
		dialog.open();
	}
	
	/**
	 * Delete all treatments.
	 */
	private void deleteAllTreatments()
	{
		final RemoveDialog dialog = new RemoveDialog()
			.setText("all the Diary Entries")
			.setOkListener(() -> {
				TreatmentDAO.removeAllTreatments();
				this.initData();
			})
			.setCancelListener(() -> {});
		dialog.open();
	}
	
	/**
	 * Creates the random medicals.
	 */
	private void createRandomMedicals()
	{
		PersonDAO.storeMedicals(RandomDAO.createMedicals(HomeView.NUM_MEDICALS));
		this.initData();
	}
	
	/**
	 * Creates the random patients.
	 */
	private void createRandomPatients()
	{
		PersonDAO.storePatients(RandomDAO.createPatients(HomeView.NUM_PATIENTS));
		this.initData();
	}
	
	/**
	 * Creates the random treatments.
	 */
	private void createRandomTreatments()
	{
		if(PersonDAO.countAllPatients() > HomeView.NUM_NULL && PersonDAO.countAllMedicals() > HomeView.NUM_NULL)
		{
			final List<PatientEntity> patients = PersonDAO.findAllPatients();
			final List<MedicalEntity> medicals = PersonDAO.findAllMedicals();
			
			for(final PatientEntity patientEntity : patients)
			{
				final String viewId = patientEntity.getViewId();
				
				TreatmentDAO.storeMedications(RandomDAO.createMedications(HomeView.NUM_MEDICATION, viewId, medicals));
				TreatmentDAO
					.storeVaccinations(RandomDAO.createVaccinations(HomeView.NUM_VACCINATION, viewId, medicals));
				TreatmentDAO.storeTestings(RandomDAO.createTestings(HomeView.NUM_TESTINGS, viewId, medicals));
				TreatmentDAO.storeSymptoms(RandomDAO.createSymptoms(HomeView.NUM_SYMPTOMS, viewId));
			}
		}
		
		this.initData();
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	@Override
	public String getTitle()
	{
		return "Home";
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnPatients}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnPatients_onClick(final ClickEvent<Button> event)
	{
		this.createRandomPatients();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnMedicals}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnMedicals_onClick(final ClickEvent<Button> event)
	{
		this.createRandomMedicals();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnEntries}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnEntries_onClick(final ClickEvent<Button> event)
	{
		this.createRandomTreatments();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnPatientsDelete}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnPatientsDelete_onClick(final ClickEvent<Button> event)
	{
		this.deleteAllPatients();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnMedicalsDelete}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnMedicalsDelete_onClick(final ClickEvent<Button> event)
	{
		this.deleteAllMedicals();
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #btnEntriesDelete}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void btnEntriesDelete_onClick(final ClickEvent<Button> event)
	{
		this.deleteAllTreatments();
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.headLine                 = new H1();
		this.copyright                = new H4();
		this.horizontalLayoutPatients = new HorizontalLayout();
		this.lblPatients              = new Label();
		this.btnPatients              = new Button();
		this.btnPatientsDelete        = new Button();
		this.horizontalLayoutMedicals = new HorizontalLayout();
		this.lblMedicals              = new Label();
		this.btnMedicals              = new Button();
		this.btnMedicalsDelete        = new Button();
		this.horizontalLayoutEntries  = new HorizontalLayout();
		this.lblEntries               = new Label();
		this.btnEntries               = new Button();
		this.btnEntriesDelete         = new Button();
		
		this.setSpacing(false);
		this.headLine.setText("Corona Diary");
		this.headLine.getStyle().set("font-family", "times");
		this.headLine.getStyle().set("color", "Orange");
		this.copyright.setText("Copyright 2021 by Frank Zillus");
		this.horizontalLayoutPatients.setPadding(true);
		this.lblPatients.setText("# Patients");
		this.btnPatients.setText("Patients");
		this.btnPatients.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);
		this.btnPatients.setIcon(VaadinIcon.USER_HEART.create());
		this.btnPatientsDelete.setText("Patients");
		this.btnPatientsDelete.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
		this.btnPatientsDelete.setIcon(VaadinIcon.TRASH.create());
		this.horizontalLayoutMedicals.setPadding(true);
		this.lblMedicals.setText("# Medicals");
		this.btnMedicals.setText("Medicals");
		this.btnMedicals.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);
		this.btnMedicals.setIcon(VaadinIcon.HOSPITAL.create());
		this.btnMedicalsDelete.setText("Medicals");
		this.btnMedicalsDelete.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
		this.btnMedicalsDelete.setIcon(VaadinIcon.TRASH.create());
		this.horizontalLayoutEntries.setPadding(true);
		this.lblEntries.setText("# Entries");
		this.btnEntries.setText("Entries");
		this.btnEntries.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SUCCESS);
		this.btnEntries.setIcon(VaadinIcon.BOOK.create());
		this.btnEntriesDelete.setText("Entries");
		this.btnEntriesDelete.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
		this.btnEntriesDelete.setIcon(VaadinIcon.TRASH.create());
		
		this.lblPatients.setWidth("250px");
		this.lblPatients.setHeight(null);
		this.btnPatients.setWidthFull();
		this.btnPatients.setHeight(null);
		this.btnPatientsDelete.setWidthFull();
		this.btnPatientsDelete.setHeight(null);
		this.horizontalLayoutPatients.add(this.lblPatients, this.btnPatients, this.btnPatientsDelete);
		this.horizontalLayoutPatients.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, this.lblPatients);
		this.horizontalLayoutPatients.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, this.btnPatients);
		this.horizontalLayoutPatients.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER,
			this.btnPatientsDelete);
		this.lblMedicals.setWidth("250px");
		this.lblMedicals.setHeight(null);
		this.btnMedicals.setWidthFull();
		this.btnMedicals.setHeight(null);
		this.btnMedicalsDelete.setWidthFull();
		this.btnMedicalsDelete.setHeight(null);
		this.horizontalLayoutMedicals.add(this.lblMedicals, this.btnMedicals, this.btnMedicalsDelete);
		this.horizontalLayoutMedicals.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, this.lblMedicals);
		this.horizontalLayoutMedicals.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, this.btnMedicals);
		this.horizontalLayoutMedicals.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER,
			this.btnMedicalsDelete);
		this.lblEntries.setWidth("250px");
		this.lblEntries.setHeight(null);
		this.btnEntries.setWidthFull();
		this.btnEntries.setHeight(null);
		this.btnEntriesDelete.setWidthFull();
		this.btnEntriesDelete.setHeight(null);
		this.horizontalLayoutEntries.add(this.lblEntries, this.btnEntries, this.btnEntriesDelete);
		this.horizontalLayoutEntries.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, this.lblEntries);
		this.horizontalLayoutEntries.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, this.btnEntries);
		this.horizontalLayoutEntries.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER,
			this.btnEntriesDelete);
		this.headLine.setSizeUndefined();
		this.copyright.setSizeUndefined();
		this.horizontalLayoutPatients.setWidthFull();
		this.horizontalLayoutPatients.setHeight(null);
		this.horizontalLayoutMedicals.setWidthFull();
		this.horizontalLayoutMedicals.setHeight(null);
		this.horizontalLayoutEntries.setWidthFull();
		this.horizontalLayoutEntries.setHeight(null);
		this.add(this.headLine, this.copyright, this.horizontalLayoutPatients, this.horizontalLayoutMedicals,
			this.horizontalLayoutEntries);
		this.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, this.headLine);
		this.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, this.copyright);
		this.setSizeUndefined();
		
		this.btnPatients.addClickListener(this::btnPatients_onClick);
		this.btnPatientsDelete.addClickListener(this::btnPatientsDelete_onClick);
		this.btnMedicals.addClickListener(this::btnMedicals_onClick);
		this.btnMedicalsDelete.addClickListener(this::btnMedicalsDelete_onClick);
		this.btnEntries.addClickListener(this::btnEntries_onClick);
		this.btnEntriesDelete.addClickListener(this::btnEntriesDelete_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Button           btnPatients, btnPatientsDelete, btnMedicals, btnMedicalsDelete, btnEntries,
		btnEntriesDelete;
	private H1               headLine;
	private HorizontalLayout horizontalLayoutPatients, horizontalLayoutMedicals, horizontalLayoutEntries;
	private Label            lblPatients, lblMedicals, lblEntries;
	private H4               copyright;
	// </generated-code>
	
}
