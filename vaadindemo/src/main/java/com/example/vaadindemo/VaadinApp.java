package com.example.vaadindemo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

import com.example.vaadindemo.domain.Person;
import com.example.vaadindemo.service.PersonManager;
import com.vaadin.annotations.Title;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.sass.internal.parser.function.ListAppendFunctionGenerator;
import com.vaadin.server.Page;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.google.gwt.user.datepicker.client.CalendarUtil;

@Title("Vaadin Demo App")
public class VaadinApp extends UI {

	private static final long serialVersionUID = 1L;
	
	OptionGroup single = new OptionGroup("Wypożycza na");
	Date data = new Date();
	
	
	private PersonManager personManager = new PersonManager();

	private Person person = new Person("Jasiu", 1967, "Kowalski",data,"takimail@mail.pl");
	private BeanItem<Person> personItem = new BeanItem<Person>(person);

	private BeanItemContainer<Person> persons = new BeanItemContainer<Person>(
			Person.class);

	enum Action {
		EDIT, ADD;
	}

	private class MyFormWindow extends Window {
		private static final long serialVersionUID = 1L;

		private Action action;
		
		public MyFormWindow(Action act) {
			this.action = act;

			setModal(true);
			center();
			
			switch (action) {
			case ADD:
				setCaption("Dodaj nową osobę");
				break;
			case EDIT:
				setCaption("Edytuj osobę");
				break;
			default:
				break;
			}

			final FormLayout form = new FormLayout();
			final FieldGroup binder = new FieldGroup(personItem);

			final Button saveBtn = new Button(" Dodaj osobę ");
			final Button cancelBtn = new Button(" Anuluj ");

			ArrayList<String> list = new ArrayList<String>();
			list.add("day");list.add("week");list.add("2 weeks");list.add("month");
			single.addItems(list);
			
			form.addComponent(binder.buildAndBind("Tytuł książki", "title"));
			form.addComponent(binder.buildAndBind("Rok urodzenia", "yob"));
			form.addComponent(binder.buildAndBind("Imię", "firstName"));
			form.addComponent(binder.buildAndBind("Email", "email"));
			form.addComponent(binder.buildAndBind("Data wypozyczenia","data_wyp"));
			form.addComponent(single);

			single.setNullSelectionItemId("day");
			binder.setBuffered(true);

			binder.getField("title").setRequired(true);
			binder.getField("firstName").setRequired(true);
			binder.getField("data_wyp").setRequired(true);
			
			VerticalLayout fvl = new VerticalLayout();
			fvl.setMargin(true);
			fvl.addComponent(form);

			HorizontalLayout hl = new HorizontalLayout();
			hl.addComponent(saveBtn);
			hl.addComponent(cancelBtn);
			fvl.addComponent(hl);

			setContent(fvl);

			saveBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					saveBtn.setComponentError(new UserError("Bad click"));
					try {
						binder.commit();
						if(person.W_email() && person.W_yob() && person.W_name())
						{
							if (action == Action.ADD) {
								personManager.addPerson(person,(String) single.getValue());
							} 	
							else if (action == Action.EDIT) {
								personManager.updatePerson(person,(String) single.getValue());
							}
							else{
							saveBtn.getErrorMessage();
							}
						}

						persons.removeAllItems();
						persons.addAll(personManager.findAll());
						close();
					} catch (CommitException e) {
						e.printStackTrace();
					}
				}
			});

			cancelBtn.addClickListener(new ClickListener() {

				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					binder.discard();
					close();
				}
			});
		}
	}

	@Override
	protected void init(VaadinRequest request) {

		Button addPersonFormBtn = new Button("Add");
		Button deletePersonFormBtn = new Button("Delete");
		Button editPersonFormBtn = new Button("Edit");

		VerticalLayout vl = new VerticalLayout();
		setContent(vl);

		addPersonFormBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new MyFormWindow(Action.ADD));
			}
		});

		editPersonFormBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				addWindow(new MyFormWindow(Action.EDIT));
			}
		});

		deletePersonFormBtn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (!person.getFirstName().isEmpty()) {
					personManager.delete(person);
					persons.removeAllItems();
					persons.addAll(personManager.findAll());
				}
			}
		});

		HorizontalLayout hl = new HorizontalLayout();
		hl.addComponent(addPersonFormBtn);
		hl.addComponent(editPersonFormBtn);
		hl.addComponent(deletePersonFormBtn);

		final Table personsTable = new Table("Biblioteka", persons);
		personsTable.setColumnHeader("firstName", "Imię");
		personsTable.setColumnHeader("title", "Tytuł książki");
		personsTable.setColumnHeader("yob", "Rok urodzenia");
		personsTable.setColumnHeader("data_wyp","Data wypozyczenia");
		personsTable.setColumnHeader("data_zwr","Data zwrotu");
		personsTable.setColumnReorderingAllowed(true);
		personsTable.setSelectable(true);
		personsTable.setImmediate(true);
		personsTable.setEnabled(true);

		personsTable.addValueChangeListener(new Property.ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {

				Person selectedPerson = (Person) personsTable.getValue();
				if (selectedPerson == null) {
					person.setFirstName("");
					person.settitle("");
					person.setYob(0);
					person.setId(null);
					person.setData_zwr(null);
					person.setData_wyp(null);
				} else {
					person.setFirstName(selectedPerson.getFirstName());
					person.settitle(selectedPerson.gettitle());
					person.setYob(selectedPerson.getYob());
					person.setId(selectedPerson.getId());
					person.setData_wyp(selectedPerson.getData_wyp());
					person.setData_zwr(selectedPerson.getData_zwr());
				}
			}
		});

		vl.addComponent(hl);
		vl.addComponent(personsTable);
		
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Label label = new Label();
		horizontalLayout.addComponent(label);
		label.setValue(UI.getCurrent().toString());
		
		vl.addComponent(horizontalLayout);
	}

}
