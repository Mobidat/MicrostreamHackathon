
package com.zillus.coronadiary.ui.popup;

import java.time.LocalDate;
import java.time.Month;

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
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.FormItem;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.RangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.zillus.coronadiary.dal.PersonDAO;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.enums.Gender;


public class PatientPopup extends Dialog
{

	/** The on oklistener. */
	private Runnable onOklistener;

	/** The patient entity. */
	private final PatientEntity patientEntity;

	/**
	 *
	 */
	public PatientPopup()
	{
		super();
		this.initUI();
		this.cmbGender.setItems(Gender.values());

		this.patientEntity = new PatientEntity();
		this.binder.readBean(this.patientEntity);
	}

	/**
	 * Save binder.
	 */
	private void saveBinder()
	{
		try
		{
			this.binder.writeBean(this.patientEntity);
			PersonDAO.addEntity(this.patientEntity);
			this.onOklistener.run();
		}
		catch(final ValidationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets the saved callback.
	 *
	 * @param listener
	 *            the listener
	 * @return the patient popup
	 */
	public PatientPopup setSavedCallback(final Runnable listener)
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
		this.lblBirthday            = new Label();
		this.dateBirthday           = new DatePicker();
		this.formItem5              = new FormItem();
		this.lblCity                = new Label();
		this.txtCity                = new TextField();
		this.formItem6              = new FormItem();
		this.lblZipCode             = new Label();
		this.nrZipCode              = new NumberField();
		this.formItem7              = new FormItem();
		this.lblCountry             = new Label();
		this.txtCountry             = new TextField();
		this.formItem8              = new FormItem();
		this.lblGender              = new Label();
		this.cmbGender              = new ComboBox<>();
		this.binder                 = new Binder<>();

		this.verticalLayout.setPadding(false);
		this.horizontalLayoutBtnBar.setSpacing(false);
		this.btnSave.setText("Save");
		this.btnSave.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
		this.btnSave.setIcon(IronIcons.SAVE.create());
		this.btnCancel.setText("Cancel");
		this.btnCancel.addThemeVariants(ButtonVariant.LUMO_ERROR);
		this.btnCancel.setIcon(IronIcons.CANCEL.create());
		this.form
			.setResponsiveSteps(new FormLayout.ResponsiveStep("0px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP));
		this.lblName.setText("Name");
		this.txtName.setTabIndex(1);
		this.lblAdress1.setText("Adress");
		this.txtAdress1.setTabIndex(2);
		this.lblAdress2.setText("Adress 2");
		this.txtAdress2.setTabIndex(3);
		this.lblBirthday.setText("Birthdate");
		this.dateBirthday.setTabIndex(4);
		this.lblCity.setText("City");
		this.txtCity.setTabIndex(5);
		this.lblZipCode.setText("Zip Code");
		this.nrZipCode.setTabIndex(6);
		this.lblCountry.setText("Country");
		this.txtCountry.setTabIndex(7);
		this.lblGender.setText("Gender");
		this.cmbGender.setTabIndex(8);
		this.cmbGender.setItemLabelGenerator(ItemLabelGeneratorFactory.NonNull(CaptionUtils::resolveCaption));

		this.binder.forField(this.txtName).asRequired().withNullRepresentation("")
			.withValidator(new StringLengthValidator("False lenghts", 3, 25))
			.bind(PatientEntity::getName, PatientEntity::setName);
		this.binder.forField(this.txtAdress1).withNullRepresentation("")
			.withValidator(new StringLengthValidator("", null, 25))
			.bind(PatientEntity::getAdress1, PatientEntity::setAdress1);
		this.binder.forField(this.txtAdress2).withNullRepresentation("")
			.withValidator(new StringLengthValidator("", null, 25))
			.bind(PatientEntity::getAdress2, PatientEntity::setAdress2);
		this.binder.forField(this.dateBirthday)
			.withValidator(RangeValidator.of("Too old", LocalDate.of(1910, Month.JANUARY, 1), null))
			.bind(PatientEntity::getBirthday, PatientEntity::setBirthday);
		this.binder.forField(this.txtCity).withNullRepresentation("")
			.withValidator(new StringLengthValidator("Too Long", null, 25))
			.bind(PatientEntity::getCity, PatientEntity::setCity);
		this.binder.forField(this.nrZipCode).withConverter(ConverterBuilder.DoubleToInteger().build())
			.withValidator(RangeValidator.of("", null, 99999))
			.bind(PatientEntity::getZipCode, PatientEntity::setZipCode);
		this.binder.forField(this.txtCountry).withNullRepresentation("")
			.withValidator(new StringLengthValidator("", null, 25))
			.bind(PatientEntity::getCountry, PatientEntity::setCountry);
		this.binder.forField(this.cmbGender).bind(PatientEntity::getGender, PatientEntity::setGender);

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
		this.lblBirthday.setSizeUndefined();
		this.lblBirthday.getElement().setAttribute("slot", "label");
		this.dateBirthday.setWidthFull();
		this.dateBirthday.setHeight(null);
		this.formItem4.add(this.lblBirthday, this.dateBirthday);
		this.lblCity.setSizeUndefined();
		this.lblCity.getElement().setAttribute("slot", "label");
		this.txtCity.setWidthFull();
		this.txtCity.setHeight(null);
		this.formItem5.add(this.lblCity, this.txtCity);
		this.lblZipCode.setSizeUndefined();
		this.lblZipCode.getElement().setAttribute("slot", "label");
		this.nrZipCode.setWidthFull();
		this.nrZipCode.setHeight(null);
		this.formItem6.add(this.lblZipCode, this.nrZipCode);
		this.lblCountry.setSizeUndefined();
		this.lblCountry.getElement().setAttribute("slot", "label");
		this.txtCountry.setWidthFull();
		this.txtCountry.setHeight(null);
		this.formItem7.add(this.lblCountry, this.txtCountry);
		this.lblGender.setSizeUndefined();
		this.lblGender.getElement().setAttribute("slot", "label");
		this.cmbGender.setWidthFull();
		this.cmbGender.setHeight(null);
		this.formItem8.add(this.lblGender, this.cmbGender);
		this.form.add(this.formItem, this.formItem2, this.formItem3, this.formItem4, this.formItem5, this.formItem6,
			this.formItem7, this.formItem8);
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
	private Binder<PatientEntity> binder;
	private Button                btnSave, btnCancel;
	private DatePicker            dateBirthday;
	private NumberField           nrZipCode;
	private VerticalLayout        verticalLayout;
	private HorizontalLayout      horizontalLayoutBtnBar;
	private Label                 lblName, lblAdress1, lblAdress2, lblBirthday, lblCity, lblZipCode, lblCountry,
		lblGender;
	private ComboBox<Gender>      cmbGender;
	private TextField             txtName, txtAdress1, txtAdress2, txtCity, txtCountry;
	private FormItem              formItem, formItem2, formItem3, formItem4, formItem5, formItem6, formItem7, formItem8;
	// </generated-code>

}
