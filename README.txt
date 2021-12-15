Jake's Magical Scheduling Assistant

Purpose:    You are working for a software company that has been contracted to develop a GUI-based scheduling desktop application.
            The contract is with a global consulting organization that conducts business in multiple languages and has main offices in
            Phoenix, Arizona; White Plains, New York; Montreal, Canada; and London, England. The consulting organization has provided a
            MySQL database that the application must pull data from. The database is used for other systems, so its structure cannot be modified.

            The organization outlined specific business requirements that must be met as part of the application.
            From these requirements, a system analyst at your company created solution statements for you to implement in developing the application.
            These statements are listed in the requirements section.

            Your company acquires Country and First-Level-Division data from a third party that is updated once per year. These tables are
            prepopulated with read-only data. Please use the attachment “Locale Codes for Region and Language” to review division data.
            Your company also supplies a list of contacts, which are prepopulated in the Contacts table; however, administrative functions
            such as adding users are beyond the scope of the application and done by your company’s IT support staff. Your application
            should be organized logically using one or more design patterns and generously commented using Javadoc so your code can be read
            and maintained by other programmers.

Author:     Jake Williams
Contact:    jwil939@wgu.edu
Version:    1.0
Date:       12/14/2021
IDE:        IntelliJ IDEA 2021.1.1 (Community Edition)
            Build #IC-211.71442.45 Built on April 30, 2021
            Runtime Version: 11.0.10+9-b1341.41 amd64
            full JDK of version 11 used (e.g., Java SE 11.0.11),
            and JavaFX version compatible with JDK 11 (e.g. JavaFX-SDK-11.0.11)

MySQL Driver: mysql-connector-java-8.0.25

Description and How To:

Program opens with Log In Screen. This displays in English or French based on the system language settings. It displays the time zone based on
the system default time zone.

User begins with entering username and password and clicking the log in button. An error will display if the user name and/or password is incorect
If successful, there will be a pop up either indicating no upcoming appointments, or an appointment scheduled in the next 15 minutes.
A prompt will tell you to check appointments

The main menu screen will be launched upon log in. Here, there is an option to go to the customers scree, the appointments screen, or to log out and
the reports screen, or to return to the log in screen.

If customer is selected, the user can access a table to view customers in the database.
From here, the user can select to add, update, or delete a customer.
There is a separate screen to add a customer and to update a customer. There are text fields for noncontrolled items and combo boxes for preset options

The appointment screen works in the same fashion as the customer screen with the same add/update/delete options

The reports screen has three tabs. The first tab allows the user to determine the number of appointments listed by
type and month by selecting from the drop down options. The second tab shows all appointments and allows the user to
filter the appointments by contact. The third screen shows the appointments that are occuring on the day of log in (today)

All screens have navigation back to the main menu and the customers and appointments screens allow the user to log out directly as well.