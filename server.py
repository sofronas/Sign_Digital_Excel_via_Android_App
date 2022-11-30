import socket
import datetime

def main():
    while True:
        #Sets up listener
        listensocket = socket.socket()
        listenPort = 8000
        numberOfConnections = 999
        thisIp = socket.gethostname()
        listensocket.bind(('', listenPort))

        #Starts Server
        listensocket.listen(numberOfConnections)
        printNow("Started Listening")

        #print("Started Listening")


        #Accepts Connection
        (clientsocket, address) = listensocket.accept()
        printNow("Device Connected")
        #print("Device Connected")

        #Define File Name
        fname = "C:\GYM\imgc.png"

        #Opens File
        f = open(fname, 'wb')
        datain = 1

        #Receives Image
        while datain:
            datain = clientsocket.recv(999999999) #Gets incomming data
            f.write(datain) #Writes data to file

        #Closes socket
        f.close()
        listensocket.close()
        printNow("Socket closed")
        #print("While ends here")

def printNow(s):
    x = datetime.datetime.now()
    print("{}-> {}-{}-{} {}:{}".format(s, x.strftime("%d"),x.strftime("%m"),x.year,x.strftime("%H"),x.strftime("%M")))


if __name__ == "__main__":
    main()