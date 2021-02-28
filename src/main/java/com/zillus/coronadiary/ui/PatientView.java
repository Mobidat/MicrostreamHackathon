
package com.zillus.coronadiary.ui;

import com.flowingcode.vaadin.addons.ironicons.IronIcons;
import com.rapidclipse.framework.server.data.renderer.CaptionRenderer;
import com.rapidclipse.framework.server.data.renderer.RenderedComponent;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.zillus.coronadiary.HasTitle;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.ui.gencolumn.GenColPatientDetail;
import com.zillus.coronadiary.ui.popup.PatientPopup;


@Route(value = "patient", layout = MainLayout.class)
public class PatientView extends VerticalLayout implements HasTitle
{
	public PatientView()
	{
		super();
		this.initUI();
		this.refresh();
		
	}
	
	@Override
	public String getTitle()
	{
		return "Patient";
	}
	
	/**
	 * Event handler delegate method for the {@link Button} {@link #addBtn}.
	 *
	 * @see ComponentEventListener#onComponentEvent(ComponentEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void addBtn_onClick(final ClickEvent<Button> event)
	{
		new PatientPopup()
			.setSavedCallback(() -> {
				this.refresh();
			}).open();
	}
	
	/**
	 * Refresh.
	 */
	public void refresh()
	{
		this.gridPatient.setItems(PersonDAO.findAllPatients());
	}
	
	/* WARNING: Do NOT edit!<br>The content of this method is always regenerated by the UI designer. */
	// <generated-code name="initUI">
	private void initUI()
	{
		this.horizontalLayout = new HorizontalLayout();
		this.addBtn           = new Button();
		this.cancelBtn        = new Button();
		this.gridPatient      = new Grid<>(PatientEntity.class, false);

		this.horizontalLayout.setSpacing(false);
		this.addBtn.setText("Patient");
		this.addBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.addBtn.setIcon(IronIcons.ADD.create());
		this.cancelBtn.setText("Cancel");
		this.cancelBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
		this.cancelBtn.setIcon(IronIcons.CANCEL.create());
		this.gridPatient.addColumn(PatientEntity::getName).setKey("name").setHeader("Patient").setSortable(true);
		this.gridPatient.addColumn(PatientEntity::getBirthday).setKey("birthday").setHeader("Birthdate")
			.setSortable(true);
		this.gridPatient.addColumn(PatientEntity::getAdress1).setKey("adress1").setHeader("Adress").setSortable(true);
		this.gridPatient.addColumn(PatientEntity::getAdress2).setKey("adress2").setHeader("Adress 2").setSortable(true);
		this.gridPatient.addColumn(PatientEntity::getCity).setKey("city").setHeader("City").setSortable(true);
		this.gridPatient.addColumn(PatientEntity::getZipCode).setKey("zipCode").setHeader("Zip Code").setSortable(true);
		this.gridPatient.addColumn(new CaptionRenderer<>(PatientEntity::getGender)).setKey("gender").setHeader("Gender")
			.setSortable(true);
		this.gridPatient.addColumn(RenderedComponent.Renderer(GenColPatientDetail::new)).setKey("renderer")
			.setHeader("...")
			.setSortable(false).setAutoWidth(true).setFlexGrow(0).setTextAlign(ColumnTextAlign.CENTER);
		this.gridPatient.setSelectionMode(Grid.SelectionMode.SINGLE);

		this.addBtn.setWidthFull();
		this.addBtn.setHeight(null);
		this.cancelBtn.setWidthFull();
		this.cancelBtn.setHeight(null);
		this.horizontalLayout.add(this.addBtn, this.cancelBtn);
		this.horizontalLayout.setWidthFull();
		this.horizontalLayout.setHeight(null);
		this.gridPatient.setWidthFull();
		this.gridPatient.setHeight(null);
		this.add(this.horizontalLayout, this.gridPatient);
		this.setFlexGrow(1.0, this.gridPatient);
		this.setSizeUndefined();

		this.addBtn.addClickListener(this::addBtn_onClick);
	} // </generated-code>
	
	// <generated-code name="variables">
	private Button              addBtn, cancelBtn;
	private HorizontalLayout    horizontalLayout;
	private Grid<PatientEntity> gridPatient;
	// </generated-code>
	
}
