package com.project.awinas;

import java.io.IOException;
import java.sql.SQLException;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;

import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

public class Student extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;
	public static final String INVALID="INVALID ID";
	public static final String MARK1="MARK1";
	public static final String MARK2="MARK2";
	public static final String MARK3="MARK3";
	public Student()
	{
         
		// TAB
        TabSheet tab = new TabSheet();
        tab.addStyleNames(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
        
       //AddTab
        VerticalLayout addTab = addTab();
       
        //displayTab
        VerticalLayout displayTab = displayTab();
         
        //deleteTab
        VerticalLayout deleteTab= deleteTab();

      
        //rankTab
         VerticalLayout rankTab = rankTab();

         //editTab
        
        //editComponents
        
        VerticalLayout editTab =editTab();
      
          tab.addTab(addTab,"ADD");
          tab.addTab(displayTab,"DISPLAY");
           tab.addTab(deleteTab,"DELETE");
           tab.addTab(rankTab,"RANK");
           tab.addTab(editTab,"EDIT");
           addComponents(tab);
          
           
        }
	
	
	
	
	//addTabMethod
	
	public final VerticalLayout addTab()
	{
		final	VerticalLayout addTab=new VerticalLayout();
		TextField addId = new TextField();
        addId.setCaption("ID");
        addId.setIcon(VaadinIcons.TAB);
        addId.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        TextField addName = new TextField("NAME");
        addName.setIcon(VaadinIcons.USER);
        addName.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        TextField mark1 = new TextField(MARK1);
        mark1.setIcon(VaadinIcons.SCALE);
        mark1.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
      
        TextField mark2 = new TextField(MARK2);
        mark2.setIcon(VaadinIcons.SCALE);
        mark2.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        TextField mark3 = new TextField(MARK3);
        mark3.setIcon(VaadinIcons.SCALE);
        mark3.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        
        Button addButton = new Button("ADD STUDENT");
        
        StudentDao dao=new StudentDao();
		StudentModel addModel=new StudentModel();
		
        addButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				addModel.setId(Integer.parseInt(addId.getValue()));
	    		addModel.setName(addName.getValue());
	    		addModel.setMark1(Integer.parseInt(mark1.getValue()));
	    		addModel.setMark2(Integer.parseInt(mark2.getValue()));
	    		addModel.setMark3(Integer.parseInt(mark3.getValue()));
	    		addModel.setTotal(addModel.getMark1()+addModel.getMark2()+addModel.getMark3());
	    		try {
					 dao.addStudentDetail(addModel);
					 Notification.show("STUDENT ADDED SUCCESSFUL");
		    		} 
				catch ( IOException | SQLException e1) {
					Notification.show("FAILED TO ADD STUDENT DETAIL : ID ALREADY PRESENT");
	    			Logger.getLogger(Student.class.getName()).log(Level.INFO, null, e1);
				}}});
			addButton.setIcon(VaadinIcons.ADD_DOCK);
			addButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
			addTab.addComponents(addId,addName,mark1,mark2,mark3,addButton);
         return addTab;
}

	
	//displaytabMethod
	public final VerticalLayout displayTab()
{
		final	VerticalLayout displayTab=new VerticalLayout();
	TextField displayId = new TextField("ENTER THE ID");
    
    displayId.setIcon(VaadinIcons.USER);
    displayId.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
    
    Button displayButton = new Button("DISPLAY");
    
    Label nameL=new Label();
	Label idL=new Label();
	Label mark1L=new Label();
	Label mark2L=new Label();
	Label mark3L=new Label();
	Label totalL=new Label();
	Label rankL=new Label();
	displayButton.addClickListener(new Button.ClickListener() {
		@Override
		public void buttonClick(ClickEvent event) {
			int id= Integer.parseInt(displayId.getValue());
        	StudentModel displayresult = null;
			try {
		        StudentDao dao=new StudentDao();
		        displayresult = dao.getStudentDetail(id);
	    			displayTab.removeAllComponents();
	    			nameL.setValue("NAME     :" +displayresult.getName());
	    			idL.setValue("ID      :" +displayresult.getId());
	    			mark1L.setValue("MARK1    :"+displayresult.getMark1());
	    			mark2L.setValue("MARK2    :"+displayresult.getMark2());
	    			mark3L.setValue("MARK3    :"+displayresult.getMark3());
	    			totalL.setValue("TOTAL    :"+displayresult.getTotal());
	    			rankL.setValue("RANK     :"+displayresult.getRank());
	    			displayTab.addComponents(displayId,displayButton);
	    			displayTab.addComponents(nameL,idL,mark1L,mark2L,mark3L,totalL,rankL);
	    			displayId.setValue("");
	    	}
			catch (IOException | SQLException e1) {
				Notification.show(INVALID);
				Logger.getLogger(Student.class.getName()).log(Level.INFO, null, e1);
			}}});
	displayButton.setIcon(VaadinIcons.EYE);
    displayButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
    displayTab.addComponents(displayId,displayButton);
   
   return displayTab;
}
	
	//deleteTabMethod
	
	public final VerticalLayout deleteTab()
	{
		
		final	VerticalLayout deleteTab =new VerticalLayout();
        TextField deleteId = new TextField("ENTER THE ID");
        deleteId.setIcon(VaadinIcons.USER);
        deleteId.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
       
     
     
      
      Button deleteButton = new Button("DELETE");
      
      deleteButton.addClickListener(new Button.ClickListener() {
    	  @Override
			public void buttonClick(ClickEvent event) {
				int id= Integer.parseInt(deleteId.getValue());
	    		String deleteresult = null;
				try {
					StudentDao dao=new StudentDao();
					deleteresult = dao.deleteStudentDetail(id);

		    		if("REMOVED".equals(deleteresult)){
		    			Notification.show("STUDENT DELETED");
		    			deleteId.setValue("");
		    		}
		    		else{
		    			Notification.show(INVALID);
		    		}}
				catch ( IOException | SQLException e1) {
					Logger.getLogger(Student.class.getName()).log(Level.INFO, null, e1);
				}}});
      deleteButton.setIcon(VaadinIcons.DEL);
      deleteButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
      deleteTab.addComponents(deleteId,deleteButton);
    	return deleteTab;
	}
	
	//rankTabMethod
	
	public final VerticalLayout rankTab()
	{
		final VerticalLayout rankTab=new VerticalLayout();
		
        TextField rank = new TextField("ENTER THE RANK");
       
        rank.setIcon(VaadinIcons.BULLSEYE);
        rank.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
       Button rankButton = new Button("SHOW");
      
       
      
       rankButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				rankTab.removeAllComponents();
	        	int rank1=Integer.parseInt(rank.getValue());
	        	List<StudentModel> rankList;
	        	Grid<StudentModel> grid = new Grid<>();
	        	try {
	        		StudentDao dao=new StudentDao();
					rankList=	dao.getStudentRank(rank1);
					
					grid.setItems(rankList);
					grid.addColumn(StudentModel::getName).setCaption("NAME");
		        	grid.addColumn(StudentModel::getId).setCaption("ID");
		        	grid.addColumn(StudentModel::getMark1).setCaption(MARK1);
		        	grid.addColumn(StudentModel::getMark2).setCaption(MARK2);
		        	grid.addColumn(StudentModel::getMark3).setCaption(MARK3);
		        	grid.addColumn(StudentModel::getTotal).setCaption("TOTAL");
		        	grid.addColumn(StudentModel::getRank).setCaption("RANK");
		        	rankTab.addComponents(rank,rankButton,grid);
		        }
	        	catch ( IOException | SQLException e1) {
	        		Notification.show("INVALID RANK");
	        		Logger.getLogger(Student.class.getName()).log(Level.INFO, null, e1);
	        		rankTab.addComponents(rank,rankButton);
				}}});
       rankButton.setIcon(VaadinIcons.SEARCH);
       rankButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
       rankTab.addComponents(rank,rankButton);
       return rankTab;
	}

	public final VerticalLayout editTab()
	{
		final VerticalLayout editTab=new VerticalLayout();
		  TextField leditId =new TextField("ID");
	        leditId.setIcon(VaadinIcons.USER);
	        leditId.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
	        
	        Button editButton =new Button("EDIT");
	        editButton.setIcon(VaadinIcons.EDIT);
	        editButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
	        
	        editTab.addComponents(leditId,editButton);
	        
	        TextField editId =new TextField("ID");
	        TextField editName =new TextField("NAME");
	        TextField editMark1 =new TextField(MARK1);
	        TextField editMark2 =new TextField(MARK2);
	        TextField editMark3 =new TextField(MARK3);
	        Button updateButton =new Button("UPDATE");
	        StudentDao dao=new StudentDao();
	        StudentModel usm=new StudentModel();
	        
	        editButton.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
				 	int id= Integer.parseInt(leditId.getValue());
		        	StudentModel displayresult = null;
					try {
						displayresult = dao.getStudentDetail(id);
						editTab.removeAllComponents();
			    			editName.setValue(displayresult.getName());
			    			editId.setValue(String.valueOf(displayresult.getId()));
			    			editId.setReadOnly(true);
			    			editMark1.setValue(String.valueOf(displayresult.getMark1()));
			    			editMark2.setValue(String.valueOf(displayresult.getMark2()));
			    			editMark3.setValue(String.valueOf(displayresult.getMark3()));
			    			editTab.addComponents(editId,editName,editMark1,editMark2,editMark3,updateButton);
			    	} 
					catch ( IOException | SQLException e1) {
						Notification.show(INVALID);
						Logger.getLogger(Student.class.getName()).log(Level.INFO, null, e1);
					}}});
	          
	        updateButton.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					String updateresult;        	
		        	usm.setId(Integer.parseInt(editId.getValue()));
		        	usm.setName(editName.getValue());
		        	usm.setMark1(Integer.parseInt(editMark1.getValue()));
		        	usm.setMark2(Integer.parseInt(editMark2.getValue()));
		        	usm.setMark3(Integer.parseInt(editMark3.getValue()));
		        	usm.setTotal(usm.getMark1()+usm.getMark2()+usm.getMark3());
		        	try {
						updateresult = dao.updateStudent(usm);
						if("UPDATED".equals(updateresult)){
			        		Notification.show("STUDENT UPDATE SUCCESSFUL");
			        		editTab.removeAllComponents();
			        		leditId.setValue("");
			        		editTab.addComponents(leditId,editButton);
						}
			        	else{
			        		Notification.show("UPDATE UN-SUCCESSFUL");
			        	}}
					catch ( IOException | SQLException e1) {
						Logger.getLogger(Student.class.getName()).log(Level.INFO, null, e1);
					}}});
	       
	        return editTab;
	}
	
	
}

