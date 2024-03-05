''' TCPClient.py
usage: python TCPClient.py HOSTNAMEorIP PORT
Reads text from user, sends to server, and prints answer
Modified by Dale R. Thompson
10/12/17 modified for Python 3
'''

import sys

# Import socket library
from socket import *

# Set hostname or IP address from command line or default to localhost
# Set port number by converting argument string to integer or use default
# Use defaults
if sys.argv.__len__() != 3:
    serverName = 'localhost'
    serverPort = 5555
# Get from command line
else:
    serverName = sys.argv[1]
    serverPort = int(sys.argv[2])

# Choose SOCK_STREAM, which is TCP
clientSocket = socket(AF_INET, SOCK_STREAM)

# Connect to server using hostname/IP and port
clientSocket.connect((serverName, serverPort))

# Get sentence from user
sentence = input('Input lowercase sentence: ')

# Send it into socket to server
sentenceBytes = sentence.encode('utf-8')
clientSocket.send(sentenceBytes)

# Receive response from server via socket
modifiedSentence = clientSocket.recv(1024)

print('From Server: {0}'.format(modifiedSentence.decode('utf-8')))

clientSocket.close()
