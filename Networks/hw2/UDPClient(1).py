''' UDPClient.py
usage: python UDPClient.py HOSTNAMEorIP PORT
Reads text from user, sends to server, and prints answer
Modified by Dale R. Thompson
10/16/17 converted to Python 3
'''

import sys

# Import socket library
from socket import *

# Set hostname or IP address from command line or default to localhost
# Set port number by converting argument string to integer or use default
# Use defaults
if sys.argv.__len__() != 3:
    serverName = 'localhost'
    serverPort = 5556
# Get from command line
else:
    serverName = sys.argv[1]
    serverPort = int(sys.argv[2])

# Choose SOCK_DGRAM, which is UDP
clientSocket = socket(AF_INET, SOCK_DGRAM)

# Get message from user
message = input('Input lowercase sentence: ')

messageBytes = message.encode('utf-8')

# Create UDP segment with message, hostname/IP, and port. Send it
clientSocket.sendto(messageBytes,(serverName, serverPort))

# Wait for segment from server
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)

modifiedMessageString = modifiedMessage.decode('utf-8')

print(modifiedMessageString)

clientSocket.close()

