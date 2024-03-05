from Database import DB

# Created by Gavin Edens and Ogden Wells

# Create a new database object
db = DB()

# Constant column names
columnNames = ["passenger_id", "last_name", "first_name", "age", "ticket_number", "fare", "date_of_purchase"]

print("1. Create New Database\n")
print("2. Open Database\n")
print("3. Close Database\n")
print("4. Display Record\n")
print("5. Update Record\n")
print("6. Create Report\n")
print("7. Add Record\n")
print("8. Delete Record\n")
print("9. Quit\n")


choice = int(input("Select a menu option: "))

while choice < 1 or choice > 10:
    print("Invalid option")
    choice = int(input("Select a menu option: "))

if choice == 1:
    filename = "SmallTitanic"
    db.createDB(filename, columnNames)
    db.openFile(filename)

    db.printConfig()

    data = []
    db.readRecord(0, data)
    print(data)

    data = []
    db.readRecord(19, data)
    print(data)

    data = []
    db.readRecord(6, data)
    print(data)

    data = []
    db.readRecord(-1, data)
    print(data)

    data = []
    db.readRecord(1000, data)
    print(data)

    db.closeFile()

if choice == 2:
    print("Open Database")
if choice == 3:
    print("Close Database")
if choice == 4:
    print("Display Record")
if choice == 5:
    print("Update Record")
if choice == 6:
    print("Create Report")
if choice == 7:
    print("Add Record")
if choice == 8:
    print("Delete Record")
if choice == 9:
    print("Quit")
