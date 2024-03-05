''' TCPServer.py
usage: python TCPServer.py PORT
Reads in text, changes all letters to uppercase, and returns
the text to the client
Modified by Dale R. Thompson
10/12/17 converted to Python 3
'''

import sys

# Import socket library
from socket import *

# Set port number by converting argument string to integer
# If no arguments set a default port number
# Defaults
if sys.argv.__len__() != 2:
    serverPort = 5555
# Get port number from command line
else:
    serverPort = int(sys.argv[1])

# Choose SOCK_STREAM, which is TCP
# This is a welcome socket
serverSocket = socket(AF_INET, SOCK_STREAM)

# The SO_REUSEADDR flag tells the kernel to reuse a local socket
# in TIME_WAIT state, without waiting for its natural timeout to expire.
serverSocket.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)

# Start listening on specified port
serverSocket.bind(('', serverPort))

# Listener begins listening
serverSocket.listen(1)

print("The server is ready to receive")

# Forever, read in sentence, convert to uppercase, and send
while 1:
    # Wait for connection and create a new socket
    # It blocks here waiting for connection
    connectionSocket, addr = serverSocket.accept()

    # Read bytes from socket
    sentence = connectionSocket.recv(1024)
  
    # Convert sentence to uppercase
    # Note you can often send strings directly and it will work but
    # you should be aware that it does not always act correct.
    sentenceString = sentence.decode('utf-8')
    capitalizedSentence = sentenceString.upper()
    capitalizedSentenceBytes = capitalizedSentence.encode('utf-8')
  
    # Send it into established connection
    connectionSocket.send(capitalizedSentenceBytes)
  
    # Close connection to client but do not close welcome socket
    connectionSocket.close()
