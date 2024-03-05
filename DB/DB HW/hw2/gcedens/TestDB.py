from Database import DB

# Created by Gavin Edens and Ogden Wells

# Create a new database object
db = DB()

# Constant column names
columnNames = ["passenger_id", "last_name", "first_name", "age", "ticket_number", "fare", "date_of_purchase"]

while True:
    print("1. Create New Database\n")
    print("2. Open Database\n")
    print("3. Close Database\n")
    print("4. Select Record\n")
    print("5. Update Record\n")
    print("6. Create Report\n")
    print("7. Insert Record\n")
    print("8. Delete Record\n")
    print("9. Quit\n")


    choice = int(input("Select a menu option: "))

    while choice < 1 or choice > 10:
        print("Invalid option")
        choice = int(input("Select a menu option: "))


    # The user should be prompted for the name of the csv file to be used to create the database.
    # The user should also be prompted for the names of the columns in the csv file.
    # The createDB method of the DB class should be called.
    if choice == 1:
        csvFile = input("Enter the name of the csv file (exclude .csv): ")
        db.createDB(csvFile, columnNames)

    # The user should be prompted for the prefix for an already created pair of files
    # storing the database contents. (e.g., "Titanic"). If another database is already
    # open, the user should be instructed to close the current database first before opening a new one.
    # The open method of the DB class should be called.
    elif choice == 2:
        databaseName = input("What database would you like to open? ")
        db.openFile(databaseName)

    #The current database files should be closed by calling the appropriate method.
    elif choice == 3:
        db.closeFile()

        if (db.isOpen() == True):
            print("Database could not be closed.")

    # You must find the record via the record number using readRecord, then display it.
    # Each field should display the name (optionally from the config file) and the value (from the data file record).
    elif choice == 4:
        data = []
        passengerID = input("Enter the desired passenger ID: ")
        numRecord = db.binarySearch(passengerID)
        if numRecord == -1:
            print("Record not found.")
        else:
            ret = db.readRecord(numRecord, data)
            if ret == 1:
                print("Passenger ID: " + data[0])
                print("First Name: " + data[1])
                print("Last Name: " + data[2])
                print("Age: " + data[3])
                print("Ticket Number: " + data[4])
                print("Fare: " + data[5])
                print("Date of Purchase: " + data[6] + "\n")
            elif ret == 0:
                print("Record found but empty.")
            else:
                print("Record not found.")

    # if db is open, it uses binarySearch to locate the record. It then uses writeRecord to overwrite it.
    # NOTE: it assumes that the key (passengerId) will not be changed or binarySearch will break.
    elif choice == 5:
        update = input("What passenger would you like to update?: ")
        ret = db.updateRecord(update)
        if ret:
            print("Record updated.")
        else:
            print("Record could not be updated.")

    # This should display the first ten records, to the screen, nicely formatted, in sorted order by primary key.
    elif choice == 6:
        db.createReport()

    # As a first step, you have to find the record using the same binarySearch routine as 4.
    # Then you should display the contents and allows updates in a specified field.
    # Don't allow the updating of the primary key.
    elif choice == 7:
        passengerID = input("Enter the desired passenger ID: ")
        fName = input("Enter the new first name: ")
        lName = input("Enter the new last name: ")
        age = input("Enter the new age: ")
        ticketNum = input("Enter the new ticket number: ")
        fare = input("Enter the new fare: ")
        date = input("Enter the new date of purchase: ")
        ret = db.insertRecord(passengerID, fName, lName, age, ticketNum, fare, date)
        if ret:
            print("Record successfully inserted.")
        else:
            print("Record could not be inserted.")

    # Delete a record from the database.
    # As records are deleted, they are logically removed from the file (most likely just overwrite them with a blank record).
    elif choice == 8:
        delete = input("What passenger would you like to delete? (Enter passengerId) ")
        ret = db.deleteRecord(delete)
        if ret:
            print("Record deleted.")
        else:
            print("Record could not be deleted.")

    # 1) Write the new record by overwriting an existing empty record.
    # 2) If there was no empty record in the correct sorted location, rewrite the file as double size and update the config file.
    elif choice == 9:
        break
