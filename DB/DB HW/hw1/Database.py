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

    # Open Config File and Open Data File
    # Inputs: filename (string) - name of the database to open
    # Outputs: (bool) True if successful, False if not
    def openFile(self, filename):
        # Read config file
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
        self.filestream.close()
        self.filestream = None
        self.num_records = 0
        self.size_record = 0
        self.columns = {}

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
    # Inputs: record_num (int) - the record number to write; data (List) - the data to write
    # Outputs: (int) -1 if recordNum is invalid, 0 if successful and overwrote an empty record, 1 if successful and overwrote a non-empty record
    def writeRecord(self, recordNum, data):
        if recordNum < 0 or recordNum > self.num_records:
            return -1

        self.filestream.seek(recordNum * self.size_record)
        line = self.filestream.read(self.size_record).rstrip('\n')
        FORMAT_STRING = "{:{width}.{width}}"

        # Convert data values to strings
        data = [str(value) for value in data]
        if line.find("_empty_") or line == "":
            self.filestream.write("\n")
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format(data[i], width=list(self.columns.values())[i]))
            self.filestream.write("\n")
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format("_empty_", width=list(self.columns.values())[i]))
                self.filestream.write("\n")
            return 0
        else:
            for i in range(len(data)):
                self.filestream.write(FORMAT_STRING.format(data[i], width=list(self.columns.values())[i]))
            return 1

    # Create a new database
    # Inputs: filename (string) - name of the database to create
    # Outputs: None
    def createDB(self, filename, input_columns):
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
            conf_file.write("num_records: " + str(len(data_list)) + "\n")
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
