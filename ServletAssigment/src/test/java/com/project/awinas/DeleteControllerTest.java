package com.project.awinas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.awinas.StudentDao;
import com.project.awinas.model.StudentModel;

public class DeleteControllerTest {


	 @Before
	    public void AddBeforeDeleteTest() throws SQLException, ServletException, IOException
	    {
		 StudentDao daoA =new StudentDao();
		 StudentModel smA=new StudentModel();
		 smA.setId(30);
		 smA.setName("Aravind");
		 smA.setMark1(99);
		 smA.setMark2(99);
		 smA.setMark3(99);
		 smA.setTotal(297);
		 daoA.addStudentDetail(smA);
	    }
	 
	@After
	 public void DeleteAfterDeleteTest() throws SQLException
    {
    	try (Connection connect = DriverManager.getConnection("jdbc:sqlserver://10.4.48.18:1433;databaseName=BPIGTN_TRAINEE", "Awinas","^D$b2K5!3"); Statement selectst = connect.createStatement()) {

            String deletequery = "delete from student";
            selectst.executeUpdate(deletequery);
           
        }
    }

	
	@Test
	public void testDeleteServletPass() throws Exception {
     HttpServletRequest request = mock(HttpServletRequest.class);       
     HttpServletResponse response = mock(HttpServletResponse.class);    

     when(request.getParameter("deleteid")).thenReturn("30");
     
     

     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     when(response.getWriter()).thenReturn(writer);

     new DeleteController().doPost(request, response);

     writer.flush();
     assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\'DELETE SUCCESSFUL\');location='studentaccess.jsp';</script>");
	}

	@Test
	public void testDeleteServletFail() throws Exception {
     HttpServletRequest request = mock(HttpServletRequest.class);       
     HttpServletResponse response = mock(HttpServletResponse.class);    

     when(request.getParameter("deleteid")).thenReturn("100");
     
     

     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     when(response.getWriter()).thenReturn(writer);

     new DeleteController().doPost(request, response);

     writer.flush();
     assertEquals(stringWriter.toString(),"<script type=\"text/javascript\">alert(\'INVALID ID\');location='studentaccess.jsp';</script>");
	}
	
}
