# Practical_Activity4
• Business needs a GUI customer management app. Based on what you developed in the previous activity, you are required to develop the database program in GUI.
o Your application will use the database smtbiz you created in the previous text-based application. This database has one table, customer.
o The customer table has 4 fields: Id, Name, Email and Mobile
o Your GUI application runs must provide option to create the database/table and 5 records.
 This option will be used when your program runs for the first time or when it needs to re-create the database.
 You are also required to provide your SQL statements for database creation in a text file. Name this file as smtbiz.sql.
o Your application must implement features below:
 Insert a new customer.
• No validity check is required. E.g. wrong format of email or mobile can be entered.
 Delete an existing customer.
 Search for a customer.
• A customer is searched by Id.
• If the customer is not found, the application shows an appropriate message.
 Update customer’s info.
• Name, Email and/or Mobile can be updated. ID cannot be changed.
No validity check is required. E.g. wrong format of email or mobile can be entered.
 Use the commit() method for database transaction integrity.
o You must use JavaFX for the GUI as 3rd party framework in your application.
o Develop your application in MVC design pattern.
 For example, Model is the Customer objects from the database, View is JavaFX GUI and Control is another class (e.g. CustomerDAO) that acts as an interface between Model and View.
• Your client wants to run your CustomerManagementGUI application on his MS-Windows machine that does not have JRE. So, you must provide a standalone application package for your client.
o To meet the client’s requirements, you need to:
 Create an optimal JRE that contains the only Java modules required for your application.
 Organise the directories of this application to make the application a self-contained standalone application.
 Provide a launcher, e.g., a batch file, that starts your application.
o Once you completed your Custom JRE, test it if the only modules are included in your JRE.
