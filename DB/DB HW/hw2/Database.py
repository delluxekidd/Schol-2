import csv
import os.path
import re

class DB:
    # Default Constructor
    def __init__(self):
        self.filestream = None
        self.num_records = 0
        self.size_record = 0
        self.columns = {}
        self.file_name = ""

    # Open Config File and Open Data File
    # Inputs: filename (string) - name of the database to open
    # Outputs: (bool) True if successful, False if not
    def openFile(self, filename):
        # Read config file
        if self.isOpen():
            print("\n There is another database open, close it to open another. \n")
            return
        with open(filename + ".config", "r") as conf_file:
            for line in conf_file:
                if line.startswith("num_records"):
                    self.num_records = int(line.split(":")[1].strip())
                elif line.startswith("size_"):
                    self.columns[line.split(":")[0]] = int(line.split(":")[1].strip())
        # Close config file
        conf_file.close()
        # Calculate record size
        self.size_record = sum(self.columns.values()) + 1

        # Open data file for reading and writing
        self.filestream = open(filename + ".data", 'r+')
        self.file_name = filename

        return self.filestream is not None

    # Debug method to print the database configuration
    # Inputs: None
    # Outputs: None
    def printConfig(self):
        print("num_records:", self.num_records)
        print("size_records:", self.size_record)
        for key, value in self.columns.items():
            print(key, ": ", value)

    # Close data file and reset variables
    # Inputs: None
    # Outputs: None
    def closeFile(self):
        # Write the config file
        if self.isOpen():
            with open(self.file_name + ".config", "w") as conf_file:
                conf_file.seek(0)
                conf_file.write("num_records: " + str(self.num_records) + "\n")
                for key, value in self.columns.items():
                    conf_file.write(key + ": " + str(value) + "\n")

            self.filestream.close()
            self.filestream = None
            self.num_records = 0
            self.size_record = 0
            self.columns = {}
            self.file_name = ""
        else:
            print("There is no database open to close.")
            return

    # Check if the database is open
    # Inputs: None
    # Outputs: (bool) True if open, False if not
    def isOpen(self):
        return self.filestream is not None

    # Read a record from the database
    # Inputs: record_num (int) - the record number to read; data (List) - the data to read into
    # Outputs: (int) -1 if recordNum is invalid, 0 if successful but empty, 1 if successful and not empty
    def readRecord(self, record_num, data):
        if record_num < 0 or record_num > self.num_records:
            return -1

        self.filestream.seek(record_num * self.size_record)
        line = self.filestream.read(self.size_record).rstrip('\n')

        if line.startswith("_empty_"):
            return 0

        start = 0
        stop = 0
        for i in range(len(self.columns)):
            stop = start + list(self.columns.values())[i]
            data.append(line[start:stop].strip())
            start = stop
        return 1

    # Write a record to the database
    # Inputs: record_num (int) - the record number to write; flag (int) - tells the function what it is doing; data (List) - the data to write
    # Outputs: (int) -1 if recordNum is invalid, 0 if successful and overwrote an empty record, 1 if successful and overwrote a non-empty record
    def writeRecord(self, recordNum, flag, data):
        if recordNum < 0 or recordNum > self.num_records:
            return -1

        FORMAT_STRING = "{:{width}.{width}}"

        # Convert data values to strings
        data = [str(value) for value in data]
        if flag == 0:
            self.filestream.seek(recordNum * self.size_record - 1)
            self.filestream.write("\n")
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format(data[i], width=list(self.columns.values())[i]))
            self.filestream.write("\n")
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format("_empty_", width=list(self.columns.values())[i]))
            self.filestream.write("\n")
            return 0
        elif flag == 1:
            self.filestream.seek(recordNum * self.size_record)
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format(data[i], width=list(self.columns.values())[i]))
            self.filestream.write("\n")
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format("_empty_", width=list(self.columns.values())[i]))
            self.filestream.write("\n")
            return 1

    # Create a new database
    # Inputs: filename (string) - name of the database to create
    # Outputs: None
    def createDB(self, filename, input_columns):
        # Check if the database is open
        if self.isOpen():
            print("\n There is another database open, close it to create another. \n")
            return
        # Check if database already exists
        if os.path.isfile(filename + ".data"):
            print("\n Database already exists \n")
            return
        # Check if csv file exists
        if not os.path.isfile(filename + ".csv"):
            print("\n CSV file does not exist \n")
            return

        # Set the columns dictionary
        for i in range(len(input_columns)):
            self.columns[input_columns[i]] = 8

        # Read the CSV file and write into data files
        with open(filename + ".csv", "r") as csv_file:
            data_list = list(csv.DictReader(csv_file, fieldnames=input_columns))

        # Find the maximum length of each column
        for dict in data_list:
            for key, value in dict.items():
                if len(value) + 1 > self.columns[key]:
                    self.columns[key] = len(value) + 1

        # Write the data file
        with open(filename + ".data", "w") as outfile:
            for dict in data_list:
                FORMAT_STRING = "{:{width}.{width}}"
                for key, value in dict.items():
                    outfile.write(FORMAT_STRING.format(value,width=self.columns[key]))
                outfile.write("\n")
                # Write an empty record
                for key, value in self.columns.items():
                    outfile.write(FORMAT_STRING.format("_empty_",width=value))
                outfile.write("\n")

        # Write the config file
        with open(filename + ".config", "w") as conf_file:
            conf_file.write("num_records: " + str(len(data_list) * 2) + "\n")
            for key, value in self.columns.items():
                conf_file.write("size_" + key + ": " + str(value) + "\n")

        # Reset the variables
        self.num_records = 0
        self.size_record = 0
        self.columns = {}

    # Read in csv file and write to database
    # Inputs: filename (string) - name of the csv file to read; overwrite (bool) - whether to overwrite the database
    # Outputs: (int) -1 if csv file does not exist, 0 if successful and database did not exist, 1 if successful and database did exist
    def readCSV(self, filename, overwrite):
        # Check if the file exists
        if not os.path.isfile(filename + ".csv"):
            return -1

        # Check if database currently exists
        if not os.path.isfile(filename + ".data"):
            self.createDB(filename)
            return 0

        # Write the csv file into the database
        if overwrite:
            self.createDB(filename)
        else:
            with open(filename + ".csv", "r") as csv_file:
                data_list = list(csv.DictReader(csv_file, fieldnames=self.columns.keys()))

            with open(filename + ".data", "a") as outfile:
                for dict in data_list:
                    FORMAT_STRING = "{:{width}.{width}}"
                    for key, value in dict.items():
                        outfile.write(FORMAT_STRING.format(value,width=self.columns[key]))
                    outfile.write("\n")
                    # Write an empty record
                    for key, value in self.columns.items():
                        outfile.write(FORMAT_STRING.format("_empty_",width=value))
                    outfile.write("\n")

    # This should display the first ten records, to the screen, nicely formatted, in sorted order by primary key.
    # Inputs: None
    # Outputs: None
    def createReport(self):
        # Check if the database is open
        if not self.isOpen():
            print("\n There is no open database \n")
            return
        for i in range(0, 20, 2):
            record = []
            ret = self.readRecord(i, record)
            if ret == 1:
                print("Passenger ID: " + record[0])
                print("First Name: " + record[1])
                print("Last Name: " + record[2])
                print("Age: " + record[3])
                print("Ticket Number: " + record[4])
                print("Fare: " + record[5])
                print("Date of Purchase: " + record[6] + "\n")
            elif ret == -1:
                print("Record number " + str(i) + " is invalid")
                break
            elif ret == 0:
                print("Record number " + str(i) + " is empty")
                break

    # If db is open, it uses binarySearch to locate the record. It then uses writeRecord to overwrite it
    # Inputs: passengerId, fname, lname, age, ticketNum, fare, date
    # Outputs: Boolean, true if record is updated, false otherwise
    def updateRecord(self, passengerId):
        numRecord = self.binarySearch(passengerId)

        if numRecord == -1:
            return False
        else:
            old_record = []
            self.readRecord(numRecord, old_record)
            new_record = [passengerId]
            for i in range(1, len(self.columns)):
                value = input("Enter the new value for " + list(self.columns.keys())[i] + " (Leave blank for no change): ")
                if value == "":
                    new_record.append(old_record[i])
                else:
                    new_record.append(value)
            ret = self.writeRecord(numRecord, 1, new_record)
            if ret == 1:
                return True
            else:
                return False

    # If db is open, it uses binarySearch to locate the record. It then uses writeRecord to overwrite it with default (empty) values. It sets the passengerId to _empty_
    # Inputs: passengerId
    # Outputs: Boolean, true if record is deleted, false otherwise
    def deleteRecord(self, passengerId):
        numRecord = self.binarySearch(passengerId)

        if numRecord == -1:
            print("Could not find record")
            return False
        else:
            # Create a temporary file to store the data without the deleted record
            temp_file = open("temp.data", "x")
            line_counter = 0
            self.filestream.seek(0)
            for line in self.filestream:
                if line_counter != numRecord and line_counter != numRecord + 1:
                    temp_file.write(line)
                line_counter += 1
            temp_file.close()
            self.filestream.close()
            os.remove(self.filestream.name)
            os.rename("temp.data", self.filestream.name)
            self.filestream = open(self.filestream.name, "r+")
            self.num_records -= 2
            return True

    # If db is open, it uses binarySearch to locate the record. If the record is found, error message is printed. If the record is not found, it uses writeRecord to insert the record
    # Inputs: passengerId, fname, lname, age, ticketNum, fare, date
    # Outputs: Boolean, true if record is inserted, false otherwise
    def insertRecord(self, passengerId, fname, lname, age, ticketNum, fare, date):
        numRecord = self.binarySearch(passengerId)

        if numRecord != -1:
            print("Record already exists")
            return False
        else:
            new_record = [passengerId, fname, lname, age, ticketNum, fare, date]
            numRecord = self.num_records
            ret = self.writeRecord(numRecord, 0, new_record)
            if ret == 0:
                self.num_records += 2
                return True
            else:
                return False

    # Binary Search by record id
    # If an empty record is found, it finds the nearest non-empty record before continuing the search
    # Inputs: input_ID
    # Outputs: record number if found, -1 if not found
    def binarySearch(self, input_ID):
        low = 0
        high = self.num_records - 1
        found = False
        recordNum = -1  # Initialize the insertion point

        while not found and high >= low:
            middle = (low + high) // 2
            record = []
            self.readRecord(middle, record)

            if record == []:
                non_empty_record = self.findNearestNonEmpty(middle, low, high)
                if non_empty_record == -1:
                    # If no non-empty record found, set recordNum for potential insertion
                    recordNum = high
                    print("Could not find record with ID..", input_ID)
                    return -1

                middle = non_empty_record
                self.readRecord(middle, record)
                if int(record[0]) > int(input_ID):
                    recordNum = middle - 1
                else:
                    recordNum = middle + 1

            if record != []:
                try:
                    if int(record[0]) == int(input_ID):
                        found = True
                        recordNum = middle
                    elif int(record[0]) > int(input_ID):
                        high = middle - 1
                    elif int(record[0]) < int(input_ID):
                        low = middle + 1
                except ValueError:
                    # Handle non-integer IDs
                    high = middle - 1

        if not found or recordNum is None:
            # Set recordNum to -1 if not found
            recordNum = -1
            print("Could not find record with ID", input_ID)

        return recordNum

    def findNearestNonEmpty(self, start, low_limit, high_limit):
        step = 1  # Initialize step size
        record = []

        while True:
            # Check backward
            if start - step >= low_limit:
                self.readRecord(start - step, record)
                if record != []:
                    #print(self.record)
                    return start - step

            # Check forward
            if start + step <= high_limit:
                self.readRecord(start + step, record)
                if record != []:
                    #print(self.record)
                    return start + step

            # Increase step size and repeat
            step += 1

            # Terminate if beyond the search range
            if start - step < low_limit and start + step > high_limit:
                break

        return -1  # No non-empty record found
