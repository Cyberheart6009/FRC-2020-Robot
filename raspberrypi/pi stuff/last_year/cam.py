#!/usr/bin/python
import cv2
# Was BaseHTTPServer in python 2.7
from http.server import BaseHTTPRequestHandler,HTTPServer
import time
from grip import *

print('Creating video capture')
cap = cv2.VideoCapture(0)

class CamHandler(BaseHTTPRequestHandler):
	def do_GET(self):
		print (self.path)
		if self.path.endswith('.mjpg'):
			self.send_response(200)
			self.send_header('Content-type','multipart/x-mixed-replace; boundary=--jpgboundary')
			self.end_headers()
			while True:
				try:
					if True:
						capture = cap
						rc,img = cap.read()  
						if not rc:
							continue
					else:
						img = pipeline.getThreshold(cap)
					#imgRGB = cv2.cvtColor(img,cv2.COLOR_BGR2RGB)
					r, buf = cv2.imencode(".jpg",img)
					self.wfile.write("--jpgboundary\r\n")
					self.send_header('Content-type','image/jpeg')
					self.send_header('Content-length',str(len(buf)))
					self.end_headers()
					self.wfile.write(bytearray(buf))
					self.wfile.write('\r\n')
					time.sleep(0.08)
				except KeyboardInterrupt:
					break
			return
		if self.path.endswith('.html') or self.path=="/":
			self.send_response(200)
			self.send_header('Content-type','text/html')
			self.end_headers()
			self.wfile.write('<html><head></head><body>')
			self.wfile.write('<h1>Camera Stream</h1>')
			self.wfile.write('<img src="http://10.16.148.225:9090/cam.mjpg"/>')
			self.wfile.write('</body></html>')
			return

def main():
	#global capture
	#capture = cv2.VideoCapture(0)
	"""capture.set(cv2.CAP_PROP_FRAME_WIDTH, 320); 
	capture.set(cv2.CAP_PROP_FRAME_HEIGHT, 240);"""
	try:
		server = HTTPServer(('127.0.0.1',9090),CamHandler)
		print ("server started")
		server.serve_forever()
	except KeyboardInterrupt:
		#capture.release()
		server.socket.close()

if __name__ == '__main__':
	main()
