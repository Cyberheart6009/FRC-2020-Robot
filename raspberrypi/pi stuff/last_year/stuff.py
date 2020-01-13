#!/usr/bin/python
import cv2
from BaseHTTPServer import BaseHTTPRequestHandler,HTTPServer
import time
import threading
from grip import *
from post_to_netowrktable import *

myIP = "raspberrypi.local"

capture=None

print('Creating pipeline')
pipeline = GripPipeline()


print('Creating video capture')
cap = cv2.VideoCapture(0)

print('Establishing Newtork Tables')
networkTables = NetworkTablePush()

w = int(cap.get(3))
h = int(cap.get(4))

trimW = 0 # int(w * 0.2)
print('W' + str(w))
print("trimW "+str(trimW))
print("w-trimW" + str(w-trimW))

def xSend():
	while True:
		have_frame, yframe = cap.read()
		if have_frame:
			pipeline.process(yframe)
			cntr = pipeline.find_contours_output
			if len(cntr):
				temp = []
				prev_time = time.clock()
				for i in cntr:
					for j in i:
						for k in j:
							temp.append(k[0])
				avg = numpy.average(temp)
				print ("Processing Time: %.2f ms" % ((time.clock() - prev_time)*1000))
				#print (cntr, "\n")
				#print (cntr[0], "\n")
				#print (cntr[0][0][0][0], cntr[0][0][0][1])
				#print (temp)
				print ("%.2f" %(avg))
				networkTables.extra_processing(avg)


xSend()
