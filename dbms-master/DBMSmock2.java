import java.sql.*;
import java.util.*;

public class DBMSmock2 {
	static final String JDBC_DRIVER= "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.10.15.202:3306/te3415db";
	
	static final String USER = "root";
	static final String PASS = "root";
	
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
					
			System.out.println("Connecting to Database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			System.out.println("Creating Connection...");
			stmt = conn.createStatement();
			int contn = 1;
			
			while(contn==1)
			{
				System.out.println("Enter your choice:");
				System.out.println("1. View\n2. Index\n3. Sequence\n");
				
				Scanner input = new Scanner(System.in);
				int o = input.nextInt();
				if(o == 1)
				{
System.out.println("1. Create Simple View \n2. Create Complex view \n3. Display View \n4. Drop View \n5. Update View");
					
					int opt = input.nextInt();
					String sql;
					switch(opt)
					{
					case 1:
					{
						String view_name = new String();
						System.out.println("Enter view name");
						view_name = input.next();
						sql = "CREATE VIEW " + view_name + " AS SELECT prof_id, prof_fname, prof_lname, dept_id, phone FROM Professors";
						int status = stmt.executeUpdate(sql);
						System.out.println("Successful Creation of View");
						break;
					}
					
					case 2:
					{
						String view_name = new String();
						System.out.println("Enter view name");
						view_name = input.next();
						sql = "CREATE VIEW "+ view_name + " AS SELECT dept_name, prof_fname, prof_lname FROM Professors, Departments WHERE Professors.dept_id = Departments.dept_id" ;
						int status = stmt.executeUpdate(sql);
						System.out.println("Successful creation of View");
						break;
					}
					
					case 3:
					{
						System.out.println("1. Simple View\n2. Complex View");
						int x = input.nextInt();
						if(x == 1)
						{
							String view_name = new String();
							System.out.println("Enter view name");
							view_name = input.next();
							sql = "SELECT * FROM " + view_name;
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next())
							{
								int no = rs.getInt("prof_id");
								String fname = rs.getString("prof_fname");
								String lname = rs.getString("prof_lname");
								long p = rs.getLong("phone");
								
								System.out.print("Professor ID : "  + no + "    " );
								System.out.print("Professor Name : "  + fname + " " + lname  + "   ");
								System.out.println("Professor Phone : "  + p);
								
							}
						}
						else
						{
							String view_name = new String();
							System.out.println("Enter view name");
							view_name = input.next();
							sql = "SELECT * FROM " + view_name;
							ResultSet rs = stmt.executeQuery(sql);
							while(rs.next())
							{
								String dept_name = rs.getString("dept_name");
								String fname = rs.getString("prof_fname");
								String lname = rs.getString("prof_lname");
								System.out.print("Department Name : "  + dept_name + "    " );
								System.out.print("Professor Name : "  + fname + " " + lname  + "   ");
								System.out.println();
							}
						}
						break;
					}
					case 4:
					{
						String view_name = new String();
						System.out.println("Enter view name");
						view_name = input.next();
						sql = "DROP VIEW " + view_name;
						int status = stmt.executeUpdate(sql);
						System.out.println("Successful Drop of View");
						break;
					}
					case 5:
					{
		
						String view_name = new String();
						System.out.println("Enter view name");
						view_name = input.next();
						sql = "UPDATE " + view_name + " SET prof_fname = 'Tanvi'" ;
						int status = stmt.executeUpdate(sql);
						System.out.println("Successful updation of View");
						break;	
					}
					
					}
				}
				
				else if(o == 2)
				{
					System.out.println("1. Unique Index\n2. Compound Index");
					int opt = input.nextInt();
					if(opt == 1)
					{
						
						String sql = "CREATE UNIQUE INDEX DeptName on Departments(dept_name)";
						System.out.println("Unique Index Successfuly created ::");
						int status = stmt.executeUpdate(sql);
						
						sql = "SHOW INDEX FROM Departments";
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							System.out.println(rs.getString("Key_name") + " :: " + rs.getString("Column_name"));
						}
						sql = "SELECT * FROM Departments use index(DeptName)";
						rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							System.out.println(rs.getString("dept_id") + " :: " + rs.getString("dept_name"));
						}
						sql = "DROP INDEX DeptName on Departments";
						status = stmt.executeUpdate(sql);
					
					}
					else
					{
						
						String sql = "CREATE INDEX profname on Professors(prof_fname, prof_lname)";
						System.out.println("Compound Index Successfuly created ::");
						int status = stmt.executeUpdate(sql);
						
						sql = "SHOW INDEX FROM Professors";
						ResultSet rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							System.out.println(rs.getString("Key_name") + " :: " + rs.getString("Column_name"));
						}
						sql = "SELECT * FROM Professors use index(profname)";
						rs = stmt.executeQuery(sql);
						while(rs.next())
						{
							System.out.println(rs.getString("prof_fname") + " :: " + rs.getString("prof_lname"));
						}
						sql = "DROP INDEX profname on Professors";
						status = stmt.executeUpdate(sql);
					}
					
				}
				else
				{
					String sql = "CREATE TABLE seq_example2(prof_id int not null auto_increment key, prof_fname varchar(20))";
					int status =  stmt.executeUpdate(sql);
					System.out.println("Sequence Successfuly created ::");
					sql = "INSERT INTO seq_example2(prof_fname) values('Aditya')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO seq_example2(prof_fname) values('Amey')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO seq_example2(prof_fname) values('Pratik')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO seq_example2(prof_fname) values('Mandar')";
					stmt.executeUpdate(sql);
					sql = "INSERT INTO seq_example2(prof_fname) values('Pokar')";
					stmt.executeUpdate(sql);
					ResultSet rs = stmt.executeQuery("SELECT * FROM seq_example2");
					while(rs.next())
					{
						System.out.println(rs.getInt("prof_id") + " :: "  + rs.getString("prof_fname"));
					}
					
				}
				System.out.println("Do you want to continue?(1/0)");
				contn = input.nextInt();
			}
					
					 stmt.close();
					 conn.close();
			
		}
			
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
				{
					stmt.close();
				}
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			try
			{
				if(conn!=null)
				{
					conn.close();
				}
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}	
}
