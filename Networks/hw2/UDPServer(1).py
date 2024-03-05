''' UDPServer.py
usage: python UDPServer.py PORT
Reads in text, changes all letters to uppercase, and returns
the text to the client
Modified by Dale R. Thompson
10/16/17 converted to Python 3
'''

import sys

# Import socket library
from socket import *

# Set port number by converting argument string to integer
# If no arguments set a default port number
# Defaults
if sys.argv.__len__() != 2:
    serverPort = 5556
# Get port number from command line
else:
    serverPort = int(sys.argv[1])

# Choose SOCK_DGRAM, which is UDP
serverSocket = socket(AF_INET, SOCK_DGRAM)

# The SO_REUSEADDR flag tells the kernel to reuse a local socket
# in TIME_WAIT state, without waiting for its natural timeout to expire.
serverSocket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)

# Start listening on specified port
serverSocket.bind(('', serverPort))

print("The server is ready to receive")

# Forever, read in sentence, convert to uppercase, and send
while 1:
    # Return data and address
    message, clientAddress = serverSocket.recvfrom(25970)

    # Converts to uppercase
    messageString = message.decode('utf-8')
    modifiedMessage = messageString.upper()
    modifiedMessageBytes = modifiedMessage.encode('utf-8')

    # Send modified message back to client
    serverSocket.sendto(modifiedMessageBytes, clientAddress)
